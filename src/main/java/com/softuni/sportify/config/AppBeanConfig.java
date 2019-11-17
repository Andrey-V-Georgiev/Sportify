package com.softuni.sportify.config;

import com.softuni.sportify.mappings.MappingInitializer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class AppBeanConfig {

    static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        MappingInitializer.initMappings(mapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        return mapper;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
