package com.example.demo.service;

import com.example.demo.dao.RestRepo;
import com.example.demo.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestService {

    private final RestRepo restRepository;

    public RestService(RestRepo restRepository) {
        this.restRepository = restRepository;
    }

    public List<Customer> getCustomers() {
        return restRepository.getCustomers();
    }
}
