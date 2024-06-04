package com.riwi.surveylance.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Configuracion de swagger
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Surveylance API", version = "0", description = "All the Endpoints available of the API"))
public class OpenApiConfig {

}
