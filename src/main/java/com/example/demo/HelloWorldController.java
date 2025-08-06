package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;

@RestController
public class HelloWorldController {

    private static final Meter meter = GlobalOpenTelemetry.getMeter("com.example.demo");
    private final LongCounter apiCallCounter;


    public HelloWorldController() {
        // Example: create a counter
        apiCallCounter = meter.counterBuilder("hello_world_counter").build();
    }

    @GetMapping("/test/helloWorld")
    public String helloWorld(@RequestParam(value = "message", required = false) String message) {
        apiCallCounter.add(1);

        if (message != null && !message.isEmpty()) {
            return message;
        } else {
            return "No message provided.";
        }
    }
}
