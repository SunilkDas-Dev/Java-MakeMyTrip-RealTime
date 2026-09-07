# ✈️ MakeMyTrip Real-Time Travel Booking API

A **Spring Boot REST API project** inspired by the core functionality of modern travel-booking platforms such as MakeMyTrip.

This project provides backend APIs for **user registration, user login, flight management, airport management, and ticket booking**. It follows a layered architecture using Controllers, Services, Service Implementations, Repositories, DTOs, and Entities.

> **Note:** This is an independent learning project inspired by travel-booking applications. It is not affiliated with or an official product of MakeMyTrip.

---

## 🚀 Features

### 👤 User Management

* User registration
* User login
* User input validation
* User lookup using email
* Multipart file upload during registration
* Standardized API response handling

### ✈️ Flight Management

* Flight-related REST APIs
* Flight information management
* Flight search/retrieval functionality

### 🛫 Airport Management

* Airport-related APIs
* Airport information management

### 🎫 Ticket Booking

* Book flight tickets
* Passenger information handling
* Flight ID validation
* Passenger count validation
* Booking response handling

### 📁 File Management

* Multipart file upload support
* File-related REST APIs

### ⚠️ Exception & Response Handling

* Custom exception package
* HTTP status-based responses
* Centralized response DTO
* Validation of request data

### 📖 API Documentation

The project uses **Swagger/OpenAPI annotations** for API documentation.

---

## 🛠️ Technology Stack

* **Java**
* **Spring Boot**
* **Spring Web / REST API**
* **Spring Data JPA**
* **Hibernate / JPA**
* **MySQL**
* **Maven**
* **Swagger / OpenAPI**
* **JUnit**
* **Git & GitHub**

---

## 🏗️ Project Architecture

The project follows a layered architecture:

```text
src
└── main
    └── java
        └── com.pro
            ├── constants
            ├── controller
            ├── dto
            ├── entity
            ├── exceptions
            ├── repo
            ├── service
            ├── serviceImpl
            └── MakeMyTrip_Application.java
```

### Controller Layer

Handles incoming HTTP requests and exposes REST endpoints.

```text
AirportController
BookingController
FilesController
FlightController
UserRegister
```

### Service Layer

Contains the business logic of the application.

```text
service
serviceImpl
```

### Repository Layer

Responsible for database interaction.

```text
AirportRepo
BookingRepo
FilesRepo
FlightRepo
UserRegistrationRepo
```

### Entity Layer

Contains the JPA entities representing application data.

```text
Airport
Booking
Files
Flight
UserRegistration
```

---

## 🔐 User Authentication

The project provides APIs for user registration and login.

### User Registration

The registration API accepts user details and validates required fields before saving the user.

### User Login

The login API accepts:

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

The application validates the request and searches for the user using the provided email and login information.

Successful login returns a welcome response, while invalid credentials return an appropriate failure response.

---

## 🎫 Booking API

The application provides a ticket-booking endpoint:

```http
POST /bookTicket
```

Example request:

```json
{
  "passengerName": "John Doe",
  "email": "john@example.com",
  "flightId": 101,
  "passengers": 2,
  "phone": "9876543210"
}
```

The application validates passenger information, flight ID, passenger count, email, and phone number before processing the booking.

---

## 📡 API Endpoints

| Module  | Method | Endpoint      | Description                         |
| ------- | ------ | ------------- | ----------------------------------- |
| User    | POST   | `/...`        | Register a user                     |
| User    | POST   | `/...`        | User login                          |
| Booking | POST   | `/bookTicket` | Book a flight                       |
| Flight  | REST   | Flight APIs   | Manage/retrieve flight information  |
| Airport | REST   | Airport APIs  | Manage/retrieve airport information |
| Files   | REST   | File APIs     | Upload/manage files                 |

> Check the Swagger/OpenAPI documentation in the running application for the exact endpoint paths defined in the current configuration.

---

## ⚙️ How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/SunilkDas-Dev/Java-MakeMyTrip-RealTime.git
```

### 2. Navigate to the project

```bash
cd Java-MakeMyTrip-RealTime
```

### 3. Configure the database

Create a MySQL database and configure your database credentials in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/makemytrip
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Do not commit your real database password to GitHub.**

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

Or run:

```text
MakeMyTrip_Application.java
```

from your IDE.

---

## 📖 Swagger API Documentation

After starting the application, Swagger UI can be used to explore and test the REST APIs.

Typical URL:

```text
http://localhost:8080/swagger-ui/index.html
```

The actual port depends on your Spring Boot configuration.

---

## 🧪 Testing APIs

You can test the REST APIs using tools such as:

* Postman
* Swagger UI
* Insomnia
* Hoppscotch

Example login request:

```http
POST /user-login
Content-Type: application/json
```

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

---

## 🎯 Learning Objectives

This project was developed to strengthen practical knowledge of:

* Java backend development
* Spring Boot
* REST API development
* Layered architecture
* Dependency Injection
* Spring Data JPA
* Database integration
* DTO pattern
* Exception handling
* Request validation
* Multipart file handling
* API documentation with Swagger
* REST API testing

---

## 🔮 Future Improvements

Possible future enhancements include:

* JWT-based authentication
* Spring Security integration
* Password encryption using BCrypt
* Global exception handling using `@ControllerAdvice`
* Advanced flight search
* Booking cancellation
* Payment gateway integration
* Email booking confirmation
* Role-based authorization
* Docker deployment
* CI/CD integration

---

## 👨‍💻 Author

**Sunil K Das**

Java Developer | Backend Developer

---

## ⭐ Support

If you find this project useful for learning Java and Spring Boot, consider giving the repository a ⭐.

### 🔗 Repository

https://github.com/SunilkDas-Dev/Java-MakeMyTrip-RealTime
