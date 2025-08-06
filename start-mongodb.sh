#!/bin/bash
# start-mongodb.sh
# This script starts a MongoDB database using docker-compose.

set -e

echo "Starting MongoDB database using docker-compose..."
docker-compose up mongodb mongo-express

echo "MongoDB and Mongo Express containers started."
