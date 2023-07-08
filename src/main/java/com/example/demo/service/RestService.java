package com.example.demo.service;

import com.example.demo.dao.RestRepository;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestService {

    private final RestRepository restRepository;

    public RestService(RestRepository restRepository) {
        this.restRepository = restRepository;
    }

    public List<Customer> getCustomers() {
        return restRepository.findAll();
    }

    public Customer getCustomer(Integer id) {
        return restRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("not found customer by id: "+ id ));
    }

    public void saveCustomer(Customer customer){
        restRepository.save(customer);
    }
}
