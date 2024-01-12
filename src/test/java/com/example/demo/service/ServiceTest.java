package com.example.demo.service;

import com.example.demo.repository.RestRepository;
import com.example.demo.model.Customer;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    private RestRepository restRepository;
    @Mock
    private CustomerMapper customerMapper;

    private RestService underTest;

    private Customer customer;

    @BeforeEach
    void setUp() {
        underTest = new RestService(restRepository, customerMapper);
        customer = new Customer(1,
                "rahim",
                "hello",
                "behsazan@gmail.com");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldGetCustomers() {
        // when
        underTest.getCustomers();
        // then
        verify(restRepository).findAll();
    }

    @Test
    void itShouldGetCustomer_ByMocking() {
        // Mock the repository's findById method to return the customer object
        when(restRepository.findById(1)).thenReturn(Optional.of(customer));

        CustomerDto retrievedCustomer = underTest.getCustomer(1);

        // Verify that the repository's findById method was called with the correct ID
        verify(restRepository).findById(1);

        // Verify that the returned customer object is the same as the one we created
        assertEquals(customerMapper.entityToDto(customer), retrievedCustomer);
    }

    @Test
    void itShouldSaveCustomer_ByMocking() {

        // Mock the repository's save method to return the customer object
        when(restRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = underTest.saveCustomer(customer);

        // Verify that the repository's save method was called with the customer object
        verify(restRepository).save(customer);

        // Verify that the returned customer object is the same as the one passed in
        assertEquals(customer, savedCustomer);
    }

    @Test
    void itShouldSaveCustomer() {
        // given
        Customer rahim = new Customer(1,
                "rahim",
                "hello",
                "behsazan@gmail.com");

        // when
        underTest.saveCustomer(rahim);

        // then
        ArgumentCaptor<Customer> customerArgumentCaptor =
                ArgumentCaptor.forClass(Customer.class);

        verify(restRepository)
                .save(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(rahim);
    }
}