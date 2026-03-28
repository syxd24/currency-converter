Currency Converter

##Overview

Currency Converter is a full-stack web application built with Java Spring Boot that converts amounts between currencies using live exchange rates from the Frankfurter API.
The project includes a backend REST API, a simple frontend built with HTML, CSS, and JavaScript, input validation, global exception handling, unit and controller tests, and deployment support for cloud platforms such as Railway.

##Features

Convert amounts between currencies using live exchange rates
REST API built with Spring Boot
Frontend integrated into the same Spring Boot application
Input validation for amount and currency fields
Proper error handling with meaningful error responses
Unit tests for the service layer
Controller tests for API endpoints
Deployment-ready configuration
Tech Stack
Backend
Java
Spring Boot
Maven
Frontend
HTML
CSS
JavaScript
Testing
JUnit
Spring Boot Test
MockMvc
External API
Frankfurter API
Deployment
Railway

##Project Structure

src
├── main
│   ├── java/com/syed/currencyconverter
│   │   ├── client
│   │   ├── config
│   │   ├── controller
│   │   ├── dto
│   │   ├── exception
│   │   ├── service
│   │   └── CurrencyconverterApplication.java
│   └── resources
│       ├── static
│       │   ├── index.html
│       │   ├── style.css
│       │   └── script.js
│       └── application.properties
└── test
    └── java/com/syed/currencyconverter
        ├── service
        │   ├── CurrencyServiceTest.java
        │   └── ExchangeRateApiServiceTest.java
        ├── CurrencyControllerTest.java
        └── CurrencyconverterApplicationTests.java

##How It Works

The application accepts an amount, a source currency, and a target currency from the user.
The frontend sends a request to the backend API.
The backend fetches the latest exchange rate from the Frankfurter API, performs the conversion, and returns the result to the user.

API Endpoints
Health Check
GET /api/health

Example response:

Currency Converter API is running
Convert Currency
GET /api/convert?amount=100&from=USD&to=EUR

Example response:

{
  "amount": 100.0,
  "from": "USD",
  "to": "EUR",
  "rate": 0.92,
  "convertedAmount": 92.0
}
Validation and Error Handling

The application validates user input before processing the request.

Examples of invalid cases:

Amount is missing
Amount is zero or negative
Currency code is missing
Source and target currency are the same
External exchange rate API is unavailable

Example error response:

{
  "message": "Amount must be greater than zero",
  "status": 400
}
Running the Application Locally
Prerequisites
Java 21
Git
Clone the Repository
git clone <your-repository-url>
cd currencyconverter
Run the Application

On Windows:

.\mvnw spring-boot:run

The application will start on:

http://localhost:9090
Running Tests

On Windows:

.\mvnw test
Building the Project

On Windows:

.\mvnw clean package

The generated JAR file will be created in the target directory.

Frontend Access

Because the frontend is served from Spring Boot static resources, the UI is available directly from the root path:

http://localhost:9090/
Deployment

This project is configured to run on Railway.

The application uses the following port configuration in application.properties:

server.port=${PORT:9090}

This allows local development on port 9090 and cloud deployment using the platform-provided port.

Live Demo
https://currency-converter-production-7b51.up.railway.app/
Future Improvements
Add more supported currencies dynamically
Improve result formatting and rounding
Enhance UI styling and responsiveness
Add integration tests
Add loading indicators and better frontend feedback
Add historical exchange rate support
Learning Goals of This Project

This project was built to practice real backend and full-stack development concepts, including:

Building REST APIs with Spring Boot
Structuring code using controllers, services, DTOs, and exceptions
Consuming third-party APIs
Writing unit and controller tests
Connecting frontend and backend in one application
Preparing and deploying a production-ready application
Author

Syed
