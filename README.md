# spring-graphql-bookstore

A draft implementation of [GraphQL](https://graphql.org/) service on [spring-boot](https://spring.io/projects/spring-boot), using [graphql-spring-boot-starter](https://github.com/graphql-java-kickstart/graphql-spring-boot). This project consists of multiple applications:

# Setting up development environment
## Requirements
* [Docker desktop](https://www.docker.com/products/docker-desktop)
* [Java 8 JDK](https://www.azul.com/downloads/?package=jdk)
* [Maven 3.6](https://maven.apache.org/) or above
* Any Java IDE, preferably [IntelliJ](https://www.jetbrains.com/idea/)
* [Postman](https://www.postman.com/downloads/)

## Quick Run
Use `docker-compose up -d` to bring up the applications. The following endpoints are exposed:

- `https://localhost/graphql` - GraphQL server 
- `https://localhost/graphiql` - GraphiQL playground

Refer to the Postman collection `graphql-bookstore.postman_collection.json` for samples


## Build
1. Use the build script at the root directory to build all the applications as docker images: `bash < build.sh`
2. Note down the latest versions of the application images: `docker images`
3. Change the version of each application as appropriate in `docker-compose.yml`
4. Bring up the containers using `docker-compose up -d`
5. Once the containers are up, you can access the servers as mentioned above

> The server uses a self-signed certificate - you may want to disable SSL verification to interact with the server

> The server is configured to listen on port 443. If you have any other applications listening on 443, the startup will fail. In such cases, you can either stop the application listening on 443 or change the application port in `bookstore-gateway/nginx.conf` file

## Applications
### bookstore-database
* A [MySQL database](https://www.mysql.com/) running as a docker container
* The database setup is in `bookstore-database/init.sql` file, modify it as appropriate
* Use the `build.sh` and `run.sh` to build and run the database respectively

### bookstore-gateway
* An [nginx server](https://www.nginx.com/) as a docker container, used for load balancing, SSL termination and rate limiting
* The configuration can be found in `bookstore-gateway/nginx.conf` file.
* Use the `build.sh` and `run.sh` to build and run the database respectively. 

> When running the `bookstore-gateway` as an independent application, configure the upstream as appropriate

### bookstore-service
* A spring-boot based Java 8 web application exposing a GraphQL implementation, as per the specification [bookstore.graphqls]
* Import the application as maven the project into IDE for development and debugging

### bookstore-client
* A spring-boot based Java 8 command line REPL application, that talks to the GraphQL server 
* Import the application as maven the project into IDE for development and debugging

