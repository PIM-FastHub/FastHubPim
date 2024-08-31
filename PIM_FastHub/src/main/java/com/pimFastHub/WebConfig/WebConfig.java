package com.pimFastHub.WebConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permite todos os domínios
                .allowedMethods("*") // Permite todos os métodos HTTP (GET, POST, etc.)
                .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}
