package com.projectoFinalMotorsport.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI springShopOpenAPI() {
                return new OpenAPI()
                                .info(new Info().title("Documentacion de proyecto final: MotorSpring")
                                                .description("Proyecto de Spring consiste en un ABM de pilotos de multiples categorias que participaran de distintas carreras en un campeonato ficticio, compuesto por distintas carreras que fueron dadas de alta previamente\r\n" + //
                                                                                                                "en el demo del aplicativo del sistema (asi como los distintos autos y equipos a los que puede pertencer un piloto).")
                                                .version("v0.0.1")
                                                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                                .externalDocs(new ExternalDocumentation()
                                                .description("SpringShop Wiki Documentation")
                                                .url("https://springshop.wiki.github.org/docs"));
        }

}