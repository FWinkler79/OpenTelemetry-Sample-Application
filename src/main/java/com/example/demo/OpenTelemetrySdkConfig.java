package com.example.demo;
import com.example.demo.logs.SdkLoggerProviderConfig;
import com.example.demo.metrics.SdkMeterProviderConfig;
import com.example.demo.traces.ContextPropagatorsConfig;
import com.example.demo.traces.SdkTracerProviderConfig;

import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;

public class OpenTelemetrySdkConfig {
    public static OpenTelemetrySdk create() {
        Resource resource = ResourceConfig.create();
        return OpenTelemetrySdk.builder()
                .setTracerProvider(SdkTracerProviderConfig.create(resource))
                .setMeterProvider(SdkMeterProviderConfig.create(resource))
                .setLoggerProvider(SdkLoggerProviderConfig.create(resource))
                .setPropagators(ContextPropagatorsConfig.create())
                .buildAndRegisterGlobal();
    }
}