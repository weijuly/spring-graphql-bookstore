#!/bin/bash
TIMESTAMP=`date '+%Y%m%d%H%M%S'`
echo "Building image: bookstore-gateway:0.0.1-${TIMESTAMP}"
docker build -t bookstore-gateway:0.0.1-${TIMESTAMP} .
