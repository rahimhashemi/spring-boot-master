package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@ToString
public class CustomerDto {
    private Integer id;
    private String name;
    private String email;
}
