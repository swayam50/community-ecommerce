package com.ecommerce.rest.config.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper fetchModelMapper() {
        return new ModelMapper();
    }

}
