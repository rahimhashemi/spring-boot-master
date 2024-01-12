package com.example.demo.model;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void itShouldEntityToDto() {
        // Given
        Customer customer = new Customer(1,
                "John Doe",
                "123 Main St",
                "johndoe@example.com");

        // When
        CustomerDto customerDto = customerMapper.entityToDto(customer);

        // Then
        assertEquals(customer.getId(), customerDto.getId());
        assertEquals(customer.getName(), customerDto.getName());
        assertEquals(customer.getEmail(), customerDto.getEmail());
    }

}