package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.RestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final RestService restService;

    public Controller(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/customers")
    @ResponseBody
    public List<Customer> getCustomers() {
        return restService.getCustomers();
    }

    @GetMapping("/")
    public String hello() {
        return "service is up and running";
    }
}
