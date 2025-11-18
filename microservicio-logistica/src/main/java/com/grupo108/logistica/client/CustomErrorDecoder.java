package com.grupo108.logistica.client;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() >= 500) {
            if (response.request().url().contains("/api/v1/geocoding")) {

                return new FeignException.NotFound(
                        response.request().httpMethod().name(),
                        response.request(),
                        null,
                        response.headers()
                );
            }
        }

        return defaultDecoder.decode(methodKey, response);
    }
}