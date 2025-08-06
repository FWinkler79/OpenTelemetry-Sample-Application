package com.example.demo.metrics;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.database.PersistentMetricData;
import com.example.demo.database.PersistentMetricRepository;
import com.example.demo.database.PersistentMetricRepositoryProvider;

import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.common.export.MemoryMode;
import io.opentelemetry.sdk.metrics.Aggregation;
import io.opentelemetry.sdk.metrics.InstrumentType;
import io.opentelemetry.sdk.metrics.data.AggregationTemporality;
import io.opentelemetry.sdk.metrics.data.MetricData;
import io.opentelemetry.sdk.metrics.export.AggregationTemporalitySelector;
import io.opentelemetry.sdk.metrics.export.CollectionRegistration;
import io.opentelemetry.sdk.metrics.export.MetricExporter;
import io.opentelemetry.sdk.metrics.export.MetricReader;

public class CustomMetricReader implements MetricReader {

  private static final Logger logger = Logger.getLogger(CustomMetricReader.class.getName());
  private MetricExporter exporter;

  private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
  private final AtomicReference<CollectionRegistration> collectionRef = new AtomicReference<>(
      CollectionRegistration.noop());

  public CustomMetricReader(MetricExporter exporter) {
    this.exporter = exporter;
  }

  @Override
  public void register(CollectionRegistration collectionRegistration) {
    // Callback invoked when SdkMeterProvider is initialized, providing a handle to
    // collect metrics.
    collectionRef.set(collectionRegistration);
    executorService.scheduleWithFixedDelay(this::collectMetrics, 0, 5, TimeUnit.SECONDS);
  }

  private void collectMetrics() {
    // Collect metrics. Typically, records are sent out of process via some network
    // protocol, but we
    // simply log for illustrative purposes.
    logger.log(Level.INFO, "Collecting metrics");
    Collection<MetricData> metricData = collectionRef
        .get()
        .collectAllMetrics();

    metricData.forEach(metric -> logger.log(Level.INFO, "Metric: " + metric));

    // PersistentMetricRepository persistentMetricRepository = PersistentMetricRepositoryProvider
    //     .getPersistentMetricRepository();
    // if (persistentMetricRepository == null) {
    //   logger.log(Level.WARNING, "PersistentMetricRepository is not initialized");
    // } else {
    //   logger.log(Level.INFO, "Saving metrics to persistent storage");
    //   PersistentMetricData persistentMetricData = new PersistentMetricData(metricData);
    //   persistentMetricRepository.save(persistentMetricData);
    // }

    CompletableResultCode resultCode = exporter.export(metricData);
    // export could be an aysnchronous operation, so we handle the result code when
    // it is ready.
    resultCode.whenComplete(() -> {
      if (!resultCode.isSuccess()) {
        logger.log(Level.WARNING, "Failed to export metrics: " + resultCode);
      } else {
        logger.log(Level.INFO, "Metrics exported successfully");
      }
    });
  }

  @Override
  public CompletableResultCode forceFlush() {
    // Export any records which have been queued up but not yet exported.
    logger.log(Level.INFO, "flushing");
    return CompletableResultCode.ofSuccess();
  }

  @Override
  public CompletableResultCode shutdown() {
    // Shutdown the exporter and cleanup any resources.
    logger.log(Level.INFO, "shutting down");
    return CompletableResultCode.ofSuccess();
  }

  @Override
  public AggregationTemporality getAggregationTemporality(InstrumentType instrumentType) {
    // Specify the required aggregation temporality as a function of instrument type
    return AggregationTemporalitySelector.deltaPreferred()
        .getAggregationTemporality(instrumentType);
  }

  @Override
  public MemoryMode getMemoryMode() {
    // Optionally specify the memory mode, indicating whether metric records can be
    // reused or must
    // be immutable
    return MemoryMode.REUSABLE_DATA;
  }

  @Override
  public Aggregation getDefaultAggregation(InstrumentType instrumentType) {
    // Optionally specify the default aggregation as a function of instrument kind
    return Aggregation.defaultAggregation();
  }
}
