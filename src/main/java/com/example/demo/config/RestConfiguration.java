package com.example.demo.config;

import com.example.demo.repository.RestFakeRepository;
import com.example.demo.repository.RestRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RestConfiguration {

    @Value("${app.useFakeRepo:false}")
    private Boolean useFakeRepo;

    @Bean
    RestRepo restRepo() {
        log.info("useFakeRepo = " + useFakeRepo);
        return new RestFakeRepository();

    }
}
