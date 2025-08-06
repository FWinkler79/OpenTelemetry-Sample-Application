package com.example.demo.database;

import org.springframework.stereotype.Component;

@Component
public class PersistentMetricRepositoryProvider {
    private static PersistentMetricRepository persistentMetricRepository = null;

    public PersistentMetricRepositoryProvider(PersistentMetricRepository persistentMetricRepository) {
        PersistentMetricRepositoryProvider.persistentMetricRepository = persistentMetricRepository;
    }

    public static PersistentMetricRepository getPersistentMetricRepository() {
        return persistentMetricRepository;
    }
}
