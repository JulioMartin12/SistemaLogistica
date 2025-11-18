package com.grupo108.integracion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsrmResponseDto {
    private List<OsrmRoute> routes;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OsrmRoute {
        private Double distance;
        private Double duration;
    }
}