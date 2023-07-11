package com.example.demo.service;

import com.example.demo.dao.RestRepository;
import com.example.demo.model.Customer;
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

    private RestService underTest;

    private Customer customer;

    @BeforeEach
    void setUp() {
        underTest = new RestService(restRepository);
        customer = new Customer(1,
                "rahim",
                "hello",
                "behsazan@gmail.com");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCustomers() {
        // when
        underTest.getCustomers();
        // then
        verify(restRepository).findAll();
    }

    @Test
    public void testGetCustomer() {
        // Mock the repository's findById method to return the customer object
        when(restRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer retrievedCustomer = underTest.getCustomer(1);

        // Verify that the repository's findById method was called with the correct ID
        verify(restRepository).findById(1);

        // Verify that the returned customer object is the same as the one we created
        assertEquals(customer, retrievedCustomer);
    }

    @Test
    public void testSaveCustomer() {

        // Mock the repository's save method to return the customer object
        when(restRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = underTest.saveCustomer(customer);

        // Verify that the repository's save method was called with the customer object
        verify(restRepository).save(customer);

        // Verify that the returned customer object is the same as the one passed in
        assertEquals(customer, savedCustomer);
    }

    @Test
    void saveCustomer() {
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