package com.example.demo.database;

import org.springframework.data.repository.CrudRepository;

public interface PersistentMetricRepository extends CrudRepository<PersistentMetricData, String> {
    // Custom query methods can be added here
}
