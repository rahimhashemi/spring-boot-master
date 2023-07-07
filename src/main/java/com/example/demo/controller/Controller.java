package com.example.demo.controller;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Customer;
import com.example.demo.service.RestService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1/customer")
@RestController
public class Controller {

    private final RestService restService;

    public Controller(RestService restService) {
        this.restService = restService;
    }

    @GetMapping()
    public String hello() {
        return "service is up and running";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Customer> getCustomers() {
        return restService.getCustomers();
    }

    @GetMapping(path = "{customerId}")
    Customer getCustomer(@PathVariable("customerId") Integer id) {
        return restService.getCustomer(id);
    }

    @GetMapping(path = "{customerId}/exception")
    Customer getCustomerException(@PathVariable("customerId") Integer id) {
        throw new ApiRequestException("ApiRequestException for customer by id: " + id);
    }

    @PostMapping
    public void createNewCustomer(@Valid @RequestBody Customer customer) {
        System.out.println("customer = " + customer);
    }
}
