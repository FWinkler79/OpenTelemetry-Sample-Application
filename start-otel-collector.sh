#!/bin/bash
set -e

echo "Starting OpenTelemetry Collector using docker-compose..."
docker-compose up otel-collector
