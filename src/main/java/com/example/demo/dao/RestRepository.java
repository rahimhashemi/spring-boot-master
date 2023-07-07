package com.example.demo.dao;

import com.example.demo.model.Customer;

import java.util.List;

public class RestRepository implements RestRepo {

    @Override
    public List<Customer> getCustomers() {
        return List.of(
                new Customer(0, "RealDB", "passWord", "email@gmail.com"));
    }
}

