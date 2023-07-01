package com.example.demo.dao;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RestRepository implements RestRepo {
    @Override
    public List<Customer> getCustomers() {
        return Arrays.asList(
                new Customer(1, "Rahim"),
                new Customer(2, "Vahid"));
    }
}

