package com.grupo108.contenedores.service;

import com.grupo108.contenedores.repository.ClienteRepository;
import com.grupo108.contenedores.repository.ContenedorRepository;
import com.grupo108.models.Cliente;
import com.grupo108.models.Contenedor;
import com.grupo108.models.dtos.ContenedorDto;
import com.grupo108.models.enums.EstadoContenedor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContenedorService {

    private static final Logger log = LoggerFactory.getLogger(ContenedorService.class);

    private final ContenedorRepository contenedorRepository;
    private final ClienteRepository clienteRepository;

    public ContenedorService(ContenedorRepository contenedorRepository, ClienteRepository clienteRepository) {
        this.contenedorRepository = contenedorRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Contenedor crearDesdeDto(ContenedorDto dto) {
        log.info("Registrando nuevo contenedor con peso {}kg", dto.getPeso());

        Contenedor contenedor = new Contenedor();
        contenedor.setPeso(dto.getPeso());
        contenedor.setVolumen(dto.getVolumen());
        contenedor.setEstadoContenedor(EstadoContenedor.PENDIENTE_RETIRO);

        if (dto.getIdCliente() != null) {

            Cliente clienteRef = clienteRepository.findById(dto.getIdCliente())
                    .orElseGet(() -> {

                        log.warn("🚨 Creando referencia 'proxy' de Cliente ID: {} en DB local", dto.getIdCliente());
                        Cliente proxy = new Cliente();
                        proxy.setIdCliente(dto.getIdCliente());
                        proxy.setNombre("REF-INTERNAL");
                        proxy.setApellido("REF-INTERNAL");
                        proxy.setEmail("ref-cont-" + dto.getIdCliente() + "@interno.com");
                        proxy.setTelefono("000000000");

                        return clienteRepository.save(proxy);
                    });

            contenedor.setCliente(clienteRef);
        } else {
            log.error("Fallo de integridad: No se recibió ID de Cliente.");
            throw new IllegalArgumentException("El contenedor debe estar asociado a un cliente válido.");
        }

        return contenedorRepository.save(contenedor);
    }
}