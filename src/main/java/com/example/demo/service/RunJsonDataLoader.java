package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Run;
import com.example.demo.model.Runs;
import com.example.demo.repository.RunRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count() == 0) {
            // objectMapper.registerModule(new JavaTimeModule());
            // JSONParser parser = new JSONParser();
            // JSONArray arr = (JSONArray) parser.parse(
            // new FileReader("D:/Document
            // Folder/GitHub/Spring-Boot-Demo/src/main/resources/data/runs.json"));
            // String str = arr.toString();
            // List<Run> runs = objectMapper.readValue(str, new TypeReference<List<Run>>() {
            // });

            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                runRepository.saveAll(allRuns.runs());
            } catch (Exception e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }

    }
}
