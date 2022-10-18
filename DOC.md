# Technical Documentation

## Design Decisions
The following points state the decisions I made in the design of the application and the reason for each.

* **BigDecimal:** I represented prices with the non-primitive data type, `BigDecimal`, for the reason that the ability to handle an infinitely long precision of values accurately is of more importance than performance, since we are dealing with a financial application. `Float` and `Double` both come short in this.

## Running the Application
Follow the instructions in order to run the application.

* From the project directory, start up the application by running `docker compose up`. Compose pulls Amazon Corretto JDK 1.8 from Docker Hub as base image, and MySQL 8.0.31 image, from Docker Hub as well. The MySQL and the Spring Boot app services are started. Alternatively, the application can be run with `docker run`.
* Enter http://localhost:8000/ in a browser to see the application running.

The Postman collection can be found at [BayzTracker API Collection.postman_collection.json](./BayzTracker API Collection.postman_collection.json)