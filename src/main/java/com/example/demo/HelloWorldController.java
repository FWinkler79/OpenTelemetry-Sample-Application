package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/test/helloWorld")
    public String helloWorld(@RequestParam(value = "message", required = false) String message) {
        if (message != null && !message.isEmpty()) {
            return message;
        } else {
            return "No message provided.";
        }
    }
}
