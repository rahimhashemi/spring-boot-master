package com.example.demo.service;

import com.example.demo.dao.RestRepository;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

import java.util.List;

@Service
public class RestService {
    private final Logger logger = LoggerFactory.getLogger(RestService.class);
    private final Logger infoLogger = LoggerFactory.getLogger("infoLogger");

    private final RestRepository restRepository;

    public RestService(RestRepository restRepository) {
        this.restRepository = restRepository;
    }

    public List<Customer> getCustomers() {
        logger.info("getCustomers was called ...");
        infoLogger.info("getCustomers was called by infoLogger ...");
        return restRepository.findAll();
    }

    public Customer getCustomer(Integer id) {
        return restRepository
                .findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException("not found customer by id: " + id);
                    logger.error("error in getting customer " + id, notFoundException);
                    return notFoundException;
                });
    }

    public void saveCustomer(Customer customer) {
        restRepository.save(customer);
    }
}
