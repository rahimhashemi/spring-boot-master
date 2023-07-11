package com.example.demo.integration;

import com.example.demo.dao.RestRepository;
import com.example.demo.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class RestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestRepository restRepository;

    private final Faker faker = new Faker();

    @Test
    void name() {
        System.out.println("mockMvc = " + mockMvc);
    }

    @Test
    void canRegisterNewStudent() throws Exception {
        // given
        String name = faker.name().firstName();

        Customer customer = new Customer(faker.random().nextInt(1, 100),
                name,
                faker.internet().password(),
                String.format("%s@gmail.com",
                        StringUtils.trimAllWhitespace(name.trim().toLowerCase()))
        );

        // when
        ResultActions resultActions = mockMvc
                .perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)));
        // then
        resultActions.andExpect(status().isOk());
        List<Customer> students = restRepository.findAll();
        assertThat(students)
                .usingElementComparatorIgnoringFields("id")
                .contains(customer);
    }
}
