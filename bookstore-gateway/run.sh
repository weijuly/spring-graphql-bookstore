#!/bin/bash
echo "Stopping container: bookstore-gateway ..."
docker stop bookstore-gateway
echo "Removing container: bookstore-gateway ..."
docker rm bookstore-gateway
echo "Building image: bookstore-gateway ..."
docker build -t bookstore-gateway .
echo "Running image: bookstore-gateway ..."
docker run --name bookstore-gateway --publish 443:443 --detach bookstore-gateway
# echo "Waiting for 10 secs ..."
# sleep 10
# echo "Please check if the container is running using this command:"
# echo "    docker ps --filter name=bookstore-gateway"
# echo "In case if you want to access the gateway, use below commands:"
# echo "    docker exec -it bookstore-gateway /bin/bash"
# echo "    mysql -u bookstore -pbookstore bookstore"
