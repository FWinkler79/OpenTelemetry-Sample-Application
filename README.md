# OpenTelemetry-Sample-Application

This project shows how to configure the OpenTelemetry SDK programmatically.

When using the OpenTelemetry Auto-Instrumentation Agent, this is typically done by OpenTelemetry's auto-configuration under the hood.
However, it is good to understand the basics, especially, if you want to extend OpenTelemetry SDK functionalities with custom implementations.

See also the other branches of this Repo:

| Branch | Description |
|:-|:-|
| working-using-local-SDK-snapshot | Showing how to use a `MetricReader` to scrape metrics data from memory, store it to a database using Spring Data, reading it from the database and exporting it via a `MetricExporter` using OTLP.  This shows how metrics can be temporarily buffered. To support Spring Data, the OpenTelemetry Java SDK needed to be forked and extended. See [changes here](https://github.com/open-telemetry/opentelemetry-java/compare/main...FWinkler79:opentelemetry-java:working-persistency-additions) for details. |

## Setup

This setup shows how to extend OpenTelemetry's metrics handling with custom `MetricReader` and `MetricExporter` implementations.
A `MetricReader` is used to read metrics data from in-memory state. A `MetricExporter` is used to export metrics data via OTLP or other ways.
See the [`metrics`](src/main/java/com/example/demo/metrics/) package for details.

The OpenTelemetry SDK is programmatically configured in [`OpenTelemetrySdkConfig.java`](src/main/java/com/example/demo/OpenTelemetrySdkConfig.java) and dependent classes.
The way how this was done is described by [Manage Telemetry with the SDK](https://opentelemetry.io/docs/languages/java/sdk/).

In this proof-of-concept we investigate the use of a custom `MetricReader` implementation to retrieve metrics from in-memory state, serialize it to temporary storage and using a `MetricExporter` de-serialize metrics from temporary storage to then send them to a nearby OTel collector.
The intention behind that is to increase resiliency against crashing applications or unavailability of downstream metrics backends to make sure that metrics delivery to downstream systems can be guaranteed to a reasonable degree.

## Inspirations from OpenTelemetry's Default Implementations

* [PeriodicMetricReader](https://github.com/open-telemetry/opentelemetry-java/blob/main/sdk/metrics/src/main/java/io/opentelemetry/sdk/metrics/export/PeriodicMetricReader.java) - Shows how a `MetricReader` can be used to periodically read the in-memory state of metric values and pass them on to a `MetricExporter` for export.

## References

* [OpenTelemetry - Manage Telemetry with the SDK](https://opentelemetry.io/docs/languages/java/sdk/)
* [OpenTelemetry Metrics API](https://opentelemetry.io/docs/specs/otel/metrics/api/)
* [Metrics SDK](https://opentelemetry.io/docs/specs/otel/metrics/sdk/)
* [MetricReader](https://opentelemetry.io/docs/specs/otel/metrics/sdk/#metricreader)
* [MetricExporter](https://opentelemetry.io/docs/specs/otel/metrics/sdk/#metricexporter)
* [Meter](https://opentelemetry.io/docs/specs/otel/metrics/sdk/#meter)
* [MeterProvider](https://opentelemetry.io/docs/specs/otel/metrics/sdk/#meterprovider)
* [MetricProducer](https://opentelemetry.io/docs/specs/otel/metrics/sdk/#metricproducer)
* [Dependencies and BOMs](https://opentelemetry.io/docs/languages/java/intro/#dependencies-and-boms)