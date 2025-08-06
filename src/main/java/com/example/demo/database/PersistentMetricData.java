package com.example.demo.database;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.opentelemetry.sdk.metrics.data.MetricData;

@Document(collection = "persistent_metrics")
public class PersistentMetricData {
    @Id
    private String id;
    private Collection<MetricData> metricData;
    
    public PersistentMetricData() {}

    public PersistentMetricData(Collection<MetricData> metricData) {
        this.metricData = metricData;
    }

    public String getId() { return id; }
    public Collection<MetricData> getMetricData() { return metricData; }
    public void setMetricData(Collection<MetricData> metricData) { this.metricData = metricData; }
}
