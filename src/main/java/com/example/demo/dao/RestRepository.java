package com.example.demo.dao;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestRepository extends JpaRepository<Customer, Integer> {
}

