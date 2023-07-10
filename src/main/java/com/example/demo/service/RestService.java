package com.example.demo.service;

import com.example.demo.dao.RestRepository;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestService {
    private final Logger logger = LoggerFactory.getLogger(RestService.class);
    private final Logger infoLogger = LoggerFactory.getLogger("infoLogger");

    private final RestRepository restRepository;

    public RestService(RestRepository restRepository) {
        this.restRepository = restRepository;
    }

    public List<Customer> getCustomers() {
        infoLogger.info("getCustomers was called by infoLogger ...");
        return restRepository.findAll();
    }

    public Customer getCustomer(Integer id) {
        infoLogger.info("getCustomer was called by infoLogger ..." + id);
        return restRepository
                .findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException("not found customer by id: " + id);
                    logger.error("error in getting customer " + id, notFoundException);
                    return notFoundException;
                });
    }

    public Customer saveCustomer(Customer customer) {
        Integer customerId = customer.getId();
        Optional<Customer> customerOptional = restRepository.findById(customerId);
        System.out.println("customerOptional = " + customerOptional);
        if (customerOptional.isPresent()) {
            logger.error("error in saving customer " + customerId);
            throw new ApiRequestException("found customer by id: " + customerId);
        }

        return restRepository.save(customer);
    }
}
