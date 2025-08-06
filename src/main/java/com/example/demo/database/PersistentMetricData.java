package com.example.demo.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.opentelemetry.sdk.metrics.data.MetricData;

@Document
public class PersistentMetricData {
    @Id
    private String id;
    private MetricData metricData;
    
    public PersistentMetricData() {}

    public String getId() { return id; }
    public MetricData getMetricData() { return metricData; }
    public void setMetricData(MetricData metricData) { this.metricData = metricData; }
}
