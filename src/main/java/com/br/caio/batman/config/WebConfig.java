package com.br.caio.batman.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // todas as rotas
                .allowedOrigins("*") // todos os domínios
                .allowedMethods("*") // todos os métodos (GET, POST etc)
                .allowedHeaders("*"); // todos os headers
    }
}
