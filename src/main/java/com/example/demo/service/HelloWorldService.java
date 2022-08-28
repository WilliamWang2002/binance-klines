package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    @Value("${firstName}")
    public String firstName;

    @Value("${lastName}")
    public String lastName;
}
