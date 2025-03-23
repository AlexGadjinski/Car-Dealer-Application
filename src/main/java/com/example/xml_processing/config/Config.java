package com.example.xml_processing.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper mapper = new ModelMapper();

        Converter<String, LocalDate> localDateMap = context -> LocalDate.parse(context.getSource().split("T")[0]);
        // NOTE: Using the more descriptive addConverter method because Java's type inference struggles with generics.
        // Even though the generic types <S, D> are clearly defined in the Converter<String, LocalDate>, the compiler
        // fails to resolve the newly added mappings without explicitly specifying the source and destination types.
        mapper.addConverter(localDateMap, String.class, LocalDate.class);
        return mapper;
    }

    @Bean
    public Validator createValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
