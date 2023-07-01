package com.example.demo.dao;


import com.example.demo.model.Customer;

import java.util.Arrays;
import java.util.List;

public class RestFakeRepository implements RestRepo {
    @Override
    public List<Customer> getCustomers() {
        return Arrays.asList(
                new Customer(1, "Rahim"),
                new Customer(2, "Vahid"));
    }
}
