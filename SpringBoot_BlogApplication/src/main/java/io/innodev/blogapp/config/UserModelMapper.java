package io.innodev.blogapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
