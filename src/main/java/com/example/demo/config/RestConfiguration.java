package com.example.demo.config;

import com.example.demo.dao.RestFakeRepository;
import com.example.demo.dao.RestRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

    @Value("${app.useFakeRepo:false}")
    private Boolean useFakeRepo;

    @Bean
    RestRepo restRepo() {
        System.out.println("useFakeRepo = " + useFakeRepo);
        return new RestFakeRepository();

    }
}
