package com.example.demo.dto;

import com.example.demo.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

//    @Mapping(source = "price", target = "formattedPrice")
    CustomerDto entityToDto(Customer customer);
}