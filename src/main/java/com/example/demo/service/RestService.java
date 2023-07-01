package com.example.demo.service;

import com.example.demo.dao.RestRepository;
import com.example.demo.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestService {

    private final RestRepository restRepository;

    public RestService(RestRepository restRepository) {
        this.restRepository = restRepository;
    }

    public List<Customer> getCustomers() {
        return restRepository.getCustomers();
    }
}
