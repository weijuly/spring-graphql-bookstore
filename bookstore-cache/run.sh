#!/bin/bash
echo "Stopping container: bookstore-cache ..."
docker stop bookstore-cache
echo "Removing container: bookstore-cache ..."
docker rm bookstore-cache
echo "Building image: bookstore-cache ..."
docker build -t bookstore-cache .
echo "Running image: bookstore-cache ..."
docker run --name bookstore-cache --publish 6379:6379 --detach bookstore-cache redis-server --requirepass "bookstore"
echo "Waiting for 10 secs ..."
sleep 10
echo "Please check if the container is running using this command:"
echo "    docker ps --filter name=bookstore-cache"
echo "In case if you want to access the database, use below commands:"
echo "    docker exec -it bookstore-cache /bin/bash"
echo "    redis-cli"
