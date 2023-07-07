package com.example.demo.service;

import com.example.demo.dao.RestRepo;
import com.example.demo.exception.NotFoundException;
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

    public Customer getCustomer(Integer id) {
        return getCustomers()
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("not found customer by id: "+ id ));
    }
}
