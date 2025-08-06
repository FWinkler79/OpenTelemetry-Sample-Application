#!/bin/bash
# start-postgres.sh
# This script starts a PostgreSQL database using docker-compose.

set -e

echo "Starting PostgreSQL database using docker-compose..."
docker-compose up postgres

echo "PostgreSQL container started."
