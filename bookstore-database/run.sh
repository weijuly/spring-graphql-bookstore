#!/bin/bash
echo "Stopping container: bookstore-database ..."
docker stop bookstore-database
echo "Removing container: bookstore-database ..."
docker rm bookstore-database
echo "Building image: bookstore-database ..."
docker build -t bookstore-database .
echo "Running image: bookstore-database ..."
docker run --name bookstore-database --publish 3306:3306 --detach bookstore-database
echo "Waiting for 10 secs ..."
sleep 10
echo "Please check if the container is running using this command:"
echo "    docker ps --filter name=bookstore-database"
echo "In case if you want to access the database, use below commands:"
echo "    docker exec -it bookstore-database /bin/bash"
echo "    mysql -u bookstore -pbookstore bookstore"
