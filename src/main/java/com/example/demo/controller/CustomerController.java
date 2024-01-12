package com.example.demo.controller;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Customer;
import com.example.demo.dto.CustomerDto;
import com.example.demo.service.RestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1/customers")
@RestController
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private final RestService restService;

    @GetMapping("/")
    @ResponseBody
    public List<CustomerDto> getCustomers() {
        return restService.getCustomers();
    }

    @GetMapping(path = "{customerId}")
    CustomerDto getCustomer(@PathVariable("customerId") Integer id) {
        return restService.getCustomer(id);
    }

    @GetMapping(path = "{customerId}/exception")
    Customer getCustomerException(@PathVariable("customerId") Integer id) {
        throw new ApiRequestException("ApiRequestException for customer by id: " + id);
    }

    @PostMapping
    public void saveCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = restService.saveCustomer(customer);
        log.info("savedCustomer = " + savedCustomer);
    }
}
