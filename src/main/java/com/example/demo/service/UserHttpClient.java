package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.example.demo.model.User;

public interface UserHttpClient {

    @GetExchange("/users")
    public List<User> findAll();

    @GetExchange("/users/{id}")
    public User findById(@PathVariable Integer id);
}
