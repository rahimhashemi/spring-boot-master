package com.example.demo.dao;

import com.example.demo.model.Customer;

import java.util.List;

public interface RestRepo {
    List<Customer> getCustomers();
}
