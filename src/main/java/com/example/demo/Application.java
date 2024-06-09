package com.example.demo;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Location;
import com.example.demo.model.Run;
import com.example.demo.repository.JdbcClientRunRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application has started successfully!");
    }

    @Bean
    CommandLineRunner runner(JdbcClientRunRepository runRepository) {
        return args -> {
            // Run run = new Run(1, "First Run", LocalDateTime.now(),
            // LocalDateTime.now().plus(1, ChronoUnit.HOURS), 3,
            // Location.OUTDOOR);
            // runRepository.create(run);
            // log.info("Run = " + run);
        };
    }

}
