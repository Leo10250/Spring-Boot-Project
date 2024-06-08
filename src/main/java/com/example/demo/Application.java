package com.example.demo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Location;
import com.example.demo.model.Run;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application has started successfully!");
    }

    // @Bean
    // CommandLineRunner runner() {
    // return args -> {
    // Run run = new Run(1, "First Run", LocalDateTime.now(),
    // LocalDateTime.now().plus(1, ChronoUnit.HOURS), 3,
    // Location.OUTDOOR);
    // log.info("Run = " + run);
    // };
    // }

}
