#!/bin/bash
TIMESTAMP=`date '+%Y%m%d%H%M%S'`
echo "Building image: ghcr.io/weijuly/bookstore-webapp:0.0.1-${TIMESTAMP}"
docker build -t ghcr.io/weijuly/bookstore-webapp:0.0.1-${TIMESTAMP} .