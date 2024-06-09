package com.example.demo.repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.example.demo.model.Location;
import com.example.demo.model.Run;

import jakarta.annotation.PostConstruct;

@Repository
public class JdbcClientRunRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from Run").query(Run.class).list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("select id,title,started_on,completed_on,miles,location from Run where id = :id")
                .param("id", id).query(Run.class).optional();
    }

    public void create(Run run) {
        var updated = jdbcClient
                .sql("insert into Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(),
                        run.location().toString()))
                .update();
        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient
                .sql("update Run set title = ?, started_on = ?, completed_on = ?, location = ? where id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.location().toString(), id))
                .update();
        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from Run where id = :id").param("id", id).update();
        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from Run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from Run where location = :location").param("location", location)
                .query(Run.class).list();
    }

    // public List<Run> findAll() {
    // return runs;
    // }

    // public Optional<Run> findById(Integer id) {
    // return runs.stream().filter(run -> run.id() == id).findFirst();
    // }

    // public void create(Run run) {
    // runs.add(run);
    // }

    // public void update(Run run, Integer id) {
    // Optional<Run> existingRun = findById(id);
    // if (existingRun.isPresent()) {
    // runs.set(runs.indexOf(existingRun.get()), run);
    // }
    // }

    // public void delete(Integer id) {
    // runs.removeIf(run -> run.id().equals(id));
    // }

    // @PostConstruct
    // private void init() {
    // runs.add(new Run(1, "First Run", LocalDateTime.now(),
    // LocalDateTime.now().plus(1, ChronoUnit.HOURS), 3,
    // Location.OUTDOOR));

    // runs.add(new Run(2, "First Run", LocalDateTime.now(),
    // LocalDateTime.now().plus(3, ChronoUnit.HOURS), 9,
    // Location.INDOOR));
    // }
}