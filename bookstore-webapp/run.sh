#!/bin/bash
echo "Stopping container: bookstore-webapp ..."
docker stop bookstore-webapp
echo "Removing container: bookstore-webapp ..."
docker rm bookstore-webapp
echo "Building image: bookstore-webapp ..."
docker build -t bookstore-webapp .
echo "Running image: bookstore-webapp ..."
docker run --name bookstore-webapp --publish 8000:8000 --detach bookstore-webapp