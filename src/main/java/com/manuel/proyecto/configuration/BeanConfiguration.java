package com.manuel.proyecto.configuration;

import com.manuel.proyecto.domain.api.IDiaServicePort;
import com.manuel.proyecto.domain.spi.DiaPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {


    @Bean
    public IDiaServicePort diaServicePort(){
        return null;
    }
    @Bean
    public DiaPersistencePort diaPersistencePort(){
        return null;
    }
}
