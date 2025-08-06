package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.demo.database")
public class DemoApplication {

    public static void main(String[] args) {
       // Bootstrap OpenTelemetry SDK
       OpenTelemetrySdkConfig.create();
       SpringApplication.run(DemoApplication.class, args);
    }

    // static void createCounter(OpenTelemetry openTelemetry) {
    //     // Example: get a meter (optional)
    //     Meter meter = openTelemetry.getMeter("com.example.demo");
    //     LongCounter counter = meter.counterBuilder("test").build();
    //     counter.add(1);
    // }

    // static void bootstrapOpenTelemetrySDK() {
    //     OtlpHttpMetricExporter metricExporter = OtlpHttpMetricExporter.builder().build();
    //
    //     SdkMeterProvider meterProvider = SdkMeterProvider.builder()
    //             .setResource(Resource.getDefault())
    //             .registerMetricReader(PeriodicMetricReader.builder(metricExporter).build())
    //             .build();
    //
    //     OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
    //             .setMeterProvider(meterProvider)
    //             .buildAndRegisterGlobal();
    // }

    // public static class CustomMeter {
    //     private final LongCounter counter;
    //     private final DataSource dataSource;
    //
    //     public CustomMeter(OpenTelemetry openTelemetry, DataSource dataSource) {
    //         Meter meter = openTelemetry.getMeter("com.example.demo.custom");
    //         this.counter = meter.counterBuilder("custom_db_counter")
    //                 .setDescription("Counts custom events and stores in DB")
    //                 .setUnit("1")
    //                 .build();
    //         this.dataSource = dataSource;
    //     }
    //
    //     // Example method to increment the counter and store the value in the database
    //     public void incrementAndStore(long value) {
    //         counter.add(value);
    //         storeMeasurementInDb(value);
    //     }

    //     private void storeMeasurementInDb(long value) {
    //         try (Connection conn = dataSource.getConnection()) {
    //             PreparedStatement stmt = conn.prepareStatement(
    //                     "INSERT INTO otel_measurements (counter_name, value) VALUES (?, ?)");
    //             stmt.setString(1, "custom_db_counter");
    //             stmt.setLong(2, value);
    //             stmt.executeUpdate();
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
}