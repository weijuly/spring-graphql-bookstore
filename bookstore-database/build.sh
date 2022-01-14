#!/bin/bash
TIMESTAMP=`date '+%Y%m%d%H%M%S'`
echo "Building image: bookstore-database:0.0.1-${TIMESTAMP}"
docker build -t bookstore-database:0.0.1-${TIMESTAMP} .
