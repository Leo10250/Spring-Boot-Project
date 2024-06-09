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
import com.example.demo.repository.JdbcClientRunRepository;
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
        if (runRepository.count() > 0) {
            log.info("======= Not loading Runs from JSON data because the collection is contains data.");
            return;
        }

        try {
            // objectMapper doesn't support Java 8 DateTime for
            objectMapper.registerModule(new JavaTimeModule());
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(
                    new FileReader("D:/Document Folder/GitHub/Spring-Boot-Demo/src/main/resources/data/runs.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("runs");
            String json_str = jsonArray.toString();
            List<Run> runs = objectMapper.readValue(json_str, new TypeReference<List<Run>>() {
            });
            log.info("======= Reading {} runs from JSON data and saving it to a database.", runs.size());
            runRepository.saveAll(runs);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }

        // try (InputStream inputStream =
        // TypeReference.class.getResourceAsStream("/data/runs.json")) {
        // Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
        // log.info("Reading {} runs from JSON data and saving it to a database.",
        // allRuns.runs().size());
        // runRepository.saveAll(allRuns.runs());
        // } catch (Exception e) {
        // throw new RuntimeException("Failed to read JSON data", e);
        // }
    }
}
