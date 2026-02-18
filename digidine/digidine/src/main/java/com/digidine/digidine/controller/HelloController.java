package com.digidine.digidine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Simple REST controller
@RestController
public class HelloController {

    // http://localhost:8080/api/hello
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from DigiDine backend";
    }
}
