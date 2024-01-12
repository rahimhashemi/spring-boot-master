package com.example.demo.service;

import com.example.demo.repository.RestRepository;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RestService {
    private final Logger infoLogger = LoggerFactory.getLogger("infoLogger");
    private final RestRepository restRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getCustomers() {
        infoLogger.info("getCustomers was called ...");
        List<Customer> customers = restRepository.findAll();
        return customers.stream().map(customerMapper::entityToDto
        ).toList();
    }

    public CustomerDto getCustomer(Integer id) {
        infoLogger.info("getCustomer was called by infoLogger ... {}", id);
        Optional<Customer> customer = restRepository
                .findById(id);
        if (customer.isPresent()) {
            Customer customerEntity = customer.get();
            return customerMapper.entityToDto(customerEntity);
        } else {
            NotFoundException notFoundException = new NotFoundException("not found customer by id: " + id);
            log.error("error in getting customer " + id, notFoundException);
            throw notFoundException;
        }
    }

    public Customer saveCustomer(Customer customer) {
        Integer customerId = customer.getId();
        Optional<Customer> customerOptional = restRepository.findById(customerId);
        infoLogger.info("customerOptional : {}" , customerOptional);
        if (customerOptional.isPresent()) {
            log.error("error in saving customer " + customerId);
            throw new ApiRequestException("found customer by id: " + customerId);
        }
        return restRepository.save(customer);
    }
}
