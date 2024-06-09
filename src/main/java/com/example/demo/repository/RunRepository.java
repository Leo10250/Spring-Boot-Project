package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.model.Run;

public interface RunRepository extends ListCrudRepository<Run, Integer> {
    @Query("select * from run where location = :location")
    public List<Run> findAllByLocation(String location);

    @Query("select * from run where title = :title")
    public List<Run> findAllByTitle(String title);
}
