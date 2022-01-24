# spring-graphql-bookstore

A draft implementation of [GraphQL](https://graphql.org/) service on [spring-boot](https://spring.io/projects/spring-boot), using [graphql-spring-boot-starter](https://github.com/graphql-java-kickstart/graphql-spring-boot). This project consists of multiple applications:
* [bookstore-database]()
* [bookstore-service]()

## bookstore-database
A [MySQL](https://www.mysql.com/) database running as a [docker](https://www.docker.com/) container

## bookstore-service
A [spring-boot](https://spring.io/projects/spring-boot) based [GraphQL](https://graphql.org/) web service, implementing [this specification]()

# Setting up development environment
## Requirements
* [Docker desktop](https://www.docker.com/products/docker-desktop)
* [Java 8 JDK](https://www.azul.com/downloads/?package=jdk)
* [Maven 3.6](https://maven.apache.org/) or above
* Any Java IDE, preferably [IntelliJ](https://www.jetbrains.com/idea/)

  
## Steps
1. Start the MySQL database container: `cd bookstore-database; bash < run.sh`
2. Import the `bookstore-service` as a maven project into your IDE
3. Use `mvn spring-boot:run` or `java -jar target/bookstore-service-0.0.1.jar org.people.weijuly.bookstore.BookStoreApplication` to launch the application
4. Import [`graphql-bookstore.postman_collection.json`]() into your postman and start interacting with the service

# Deployment
