package com.example.demo.service;

import com.example.demo.dao.RestRepository;
import com.example.demo.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RestServiceTest {

    @Autowired
    private RestRepository restRepository;

    private RestService underTest;

    private Customer rahim;

    @BeforeEach
    void setUp() {
        restRepository.deleteAll();
        underTest = new RestService(restRepository);
        rahim = new Customer(1,
                "rahim",
                "hello",
                "behsazan@gmail.com");
    }

    @AfterEach
    void tearDown() {
        restRepository.deleteAll();
    }

    @DisplayName("JUnit test for getCustomers operation")
    @Test
    void getCustomers() {
        //given
        Customer ali = new Customer(2,
                "ali",
                "hi",
                "behsazan@gmail.com");

//        restRepository.saveAll(Arrays.asList(rahim, ali));
        restRepository.save(rahim);
        restRepository.save(ali);

        //when
        List<Customer> customers = underTest.getCustomers();
        System.out.println("customers.toString() = " + customers.toString());

        // then - verify the output
        assertThat(customers).isNotNull();
        assertThat(customers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("JUnit test for getCustomer by id operation")
    void getCustomer() {
        //given
        restRepository.save(rahim);

        //when
        Customer customer = underTest.getCustomer(1);
        System.out.println("customers.toString() = " + customer.toString());

        //then
        assertThat(customer).isNotNull();
        assertEquals(1, customer.getId());
        assertEquals("rahim", customer.getName());
    }

    @Test
    @DisplayName("JUnit test for saveCustomer operation")
    void saveCustomer() {
        //given - precondition or setup
        Customer ali = new Customer(4,
                "ali4",
                "hi4",
                "behsazan4@gmail.com");
        restRepository.save(rahim);
//        restRepository.save(ali);//todo complete later

        // when - action or the behaviour that we are going test
        Customer savedCustomer = underTest.saveCustomer(ali);

        // then - verify the output
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isEqualTo(4);
        assertThat(savedCustomer.getName()).isEqualTo("ali4");
    }
}