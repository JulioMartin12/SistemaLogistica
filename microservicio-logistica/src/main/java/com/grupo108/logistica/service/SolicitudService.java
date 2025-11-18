package com.grupo108.logistica.service;

import com.grupo108.logistica.client.CamionClient;
import com.grupo108.logistica.client.ClienteClient;
import com.grupo108.logistica.client.ContenedorClient;
import com.grupo108.logistica.client.GeocodingClient; // 👈 NECESARIO
import com.grupo108.logistica.repository.RutaRepository;
import com.grupo108.logistica.repository.SolicitudRepository;
import com.grupo108.models.*;
import com.grupo108.models.dtos.GeolocalizacionDto; // 👈 NECESARIO
import com.grupo108.models.dtos.SolicitudCreacionDto;
import com.grupo108.models.enums.EstadoSolicitud;
import com.grupo108.models.enums.EstadoTramo;
import com.grupo108.models.enums.TipoTramo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitudService {

    private static final Logger log = LoggerFactory.getLogger(SolicitudService.class);
    private final CamionClient camionClient;
    private final SolicitudRepository solicitudRepository;
    private final ClienteClient clienteClient;
    private final ContenedorClient contenedorClient;
    private final RutaRepository rutaRepository;
    private final GeocodingClient geocodingClient;

    @PersistenceContext
    private EntityManager entityManager;

    public SolicitudService(SolicitudRepository solicitudRepository,
                            ClienteClient clienteClient,
                            ContenedorClient contenedorClient,
                            CamionClient camionClient, RutaRepository rutaRepository,
                            GeocodingClient geocodingClient) {
        this.solicitudRepository = solicitudRepository;
        this.clienteClient = clienteClient;
        this.contenedorClient = contenedorClient;
        this.camionClient = camionClient;
        this.rutaRepository = rutaRepository;
        this.geocodingClient = geocodingClient;
    }

    @Transactional
    public Solicitud crearSolicitud(SolicitudCreacionDto dto) {
        log.info("Iniciando proceso de creación de solicitud...");

        Cliente clienteConfirmado = clienteClient.buscarOCrear(dto.getCliente());
        dto.getContenedor().setIdCliente(clienteConfirmado.getIdCliente());
        Contenedor contenedorConfirmado = contenedorClient.crear(dto.getContenedor());

        Double costoEstimado = null;
        GeolocalizacionDto origenCoords = dto.getOrigen();
        GeolocalizacionDto destinoCoords = dto.getDestino();

        try {
            // A. GEOCODIFICACIÓN (Llama a Integración)
            log.info("Intentando Geocodificación de Origen/Destino...");
            origenCoords = geocodingClient.obtenerCoordenadas(dto.getOrigen().getDireccion());
            destinoCoords = geocodingClient.obtenerCoordenadas(dto.getDestino().getDireccion());

            costoEstimado = camionClient.calcularCostoEstimado(
                    dto.getContenedor().getPeso(),
                    dto.getContenedor().getVolumen(),
                    origenCoords.getLatitud(),
                    origenCoords.getLongitud(),
                    destinoCoords.getLatitud(),
                    destinoCoords.getLongitud()
            );

        } catch (Exception e) {
            log.error("❌ ERROR FATAL en la cadena Geocodificación/Costeo. La Solicitud se guardará sin estimación. Causa: {}", e.getMessage());
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setEstado(EstadoSolicitud.BORRADOR);
        solicitud.setCostoEstimado(costoEstimado);

        Cliente clienteRef = entityManager.getReference(Cliente.class, clienteConfirmado.getIdCliente());
        Contenedor contenedorRef = entityManager.getReference(Contenedor.class, contenedorConfirmado.getIdContenedor());
        solicitud.setCliente(clienteRef);
        solicitud.setContenedor(contenedorRef);

        Solicitud solicitudPersistida = solicitudRepository.save(solicitud);

        if (origenCoords.getLatitud() != null) {
            Ruta rutaInicial = crearRutaInicial(solicitudPersistida, origenCoords, destinoCoords);
            solicitudPersistida.setRuta(rutaInicial);
        } else {
            log.warn("Ruta inicial NO creada. Faltan coordenadas de Origen/Destino.");
        }

        log.info("Solicitud #{} creada exitosamente. Costo Estimado: {}",
                solicitudPersistida.getNumeroSolicitud(), solicitudPersistida.getCostoEstimado());

        return solicitudPersistida;
    }

    private Ruta crearRutaInicial(Solicitud solicitudPersistida, GeolocalizacionDto origenDto, GeolocalizacionDto destinoDto) {

        Geolocalizacion origen = new Geolocalizacion(null, origenDto.getLatitud(), origenDto.getLongitud(), origenDto.getDireccion());
        Geolocalizacion destino = new Geolocalizacion(null, destinoDto.getLatitud(), destinoDto.getLongitud(), destinoDto.getDireccion());

        Tramo tramo = new Tramo();
        tramo.setOrigen(origen);
        tramo.setDestino(destino);
        tramo.setTipoTramo(TipoTramo.ORIGEN_DESTINO);
        tramo.setEstadoTramo(EstadoTramo.ESTIMADO);

        Ruta ruta = new Ruta();
        ruta.setSolicitud(solicitudPersistida);
        ruta.setFechaAsignacion(LocalDateTime.now());
        ruta.setCantidadTramos(1);
        ruta.setCantidadDepositos(0);

        if (ruta.getTramos() == null) {
            ruta.setTramos(new ArrayList<>());
        }
        ruta.getTramos().add(tramo);
        tramo.setRuta(ruta);

        return rutaRepository.save(ruta);
    }


    @Transactional
    public Solicitud asignarRuta(Integer idSolicitud) {
        log.info("Iniciando asignación de ruta y recursos para Solicitud #{}", idSolicitud);

        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada: " + idSolicitud));

        if (solicitud.getEstado() != EstadoSolicitud.BORRADOR) {
            throw new RuntimeException("Solo se pueden asignar rutas a solicitudes en estado BORRADOR.");
        }

        if (solicitud.getRuta() == null || solicitud.getRuta().getTramos().isEmpty()) {
            throw new RuntimeException("Error: La solicitud no tiene una ruta inicial válida (ejecute el POST /solicitudes primero).");
        }

        Contenedor contenedor = solicitud.getContenedor();
        List<Camion> camionesAptos = camionClient.buscarElegibles(contenedor.getPeso(), contenedor.getVolumen());

        if (camionesAptos.isEmpty()) {
            throw new RuntimeException("No se encontraron camiones disponibles que soporten la carga.");
        }

        Camion camionAsignado = camionesAptos.get(0);
        Tramo tramoInicial = solicitud.getRuta().getTramos().get(0);
        Camion camionRef = entityManager.getReference(Camion.class, camionAsignado.getIdCamion());
        tramoInicial.setCamion(camionRef);
        tramoInicial.setEstadoTramo(EstadoTramo.ASIGNADO);
        solicitud.setEstado(EstadoSolicitud.PROGRAMADA);
        log.info("Solicitud #{} PROGRAMADA. Camión asignado: {}", idSolicitud, camionAsignado.getPatente());

        return solicitudRepository.save(solicitud);
    }
}