package com.example.demo.jsonplaceholder;

public record Post(Integer id,
                   Integer userId,
                   String title,
                   String body) {
}
