#!/bin/bash
TIMESTAMP=`date '+%Y%m%d%H%M%S'`
echo "Building image: bookstore-cache:0.0.1-${TIMESTAMP}"
docker build -t bookstore-cache:0.0.1-${TIMESTAMP} .
