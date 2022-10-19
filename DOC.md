# Project Documentation

## Design Decisions
The following points state the decisions I made in the design of the application and the reason for each.

* **BigDecimal:** I represented prices with the non-primitive data type, `BigDecimal`, for the reason that the ability to handle an infinitely long precision of values accurately is of more importance than performance, since we are dealing with a financial application. `Float` and `Double` both come short in this.
* I followed the Clean Architecture, by ensuring that entities (models or domains) make up the single independent part of the project. Services act on entities, and not vice versa. 

## Running the Application
Follow the instructions in order to run the application.

* From the project directory, start up the application by running `docker compose up`. Compose pulls Amazon Corretto JDK 1.8 from Docker Hub as base image, and MySQL 8.0.31 image, from Docker Hub as well. The MySQL and the Spring Boot app services are started. Alternatively, the application can be run with `docker run`.
* Enter http://localhost:8000/ in a browser to see the application running.
* Log in as user or admin via a ``POST`` request to http://localhost:8080/login. To log in as a user, pass in the following as body of request ``{
  "username": "user",
  "password": "pass"
  }``. To log in as an admin, pass in the following as body of request ``{
  "username": "admin",
  "password": "pass"
  }``
* Access protected endpoints by passing in the generated bearer token upon Log In a authorization header of the request. 

The Postman collection at [BayzTracker API Collection.postman_collection.json](./BayzTracker API Collection.postman_collection.json) contains the API's endpoints.