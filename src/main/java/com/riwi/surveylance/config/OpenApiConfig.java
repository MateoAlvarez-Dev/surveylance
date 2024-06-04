package com.riwi.surveylance.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Configuracion de swagger
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mateo Alvarez Yepes",
                        url = "https://github.com/MateoAlvarez-Dev",
                        email = "matheoz2003@gmail.com"
                ),
                title = "Documentation: Surveylance API",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local",
                        url = "http://localhost:8080/api/v1/surveylance")
        }
)
public class OpenApiConfig {

}
