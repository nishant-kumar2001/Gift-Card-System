# Gift-Card-System

## Overview
The Gift Card System is a Spring Boot-based application that enables users to manage gift cards, perform transactions, and authenticate securely using JWT. It follows industry best practices, ensuring scalability and security.

## Features
- **User Authentication**: JWT-based authentication and authorization.
- **Gift Card Management**: CRUD operations for gift cards.
- **Transaction Processing**: Purchase and validation of gift card transactions.
- **MCC Validation**: Merchant Category Code validation for transactions.
- **Docker Support**: Deployment via Docker Compose.
- **Database Integration**: Configurable database support via Spring JPA.

## Tech Stack
- **Backend**: Spring Boot, Java, Spring Security, JWT
- **Database**: PostgreSQL
- **Containerization**: Docker, Docker Compose

## Project Structure
```
giftcard-system/
  ├── src/main/java/com/giftcard/
  │   ├── controller/        # API Controllers
  │   ├── service/           # Business Logic
  │   ├── repository/        # Data Access
  │   ├── model/             # Entity Models
  │   ├── security/          # JWT & Authentication
  │   ├── dto/               # Data Transfer Objects
  │   ├── exception/         # Custom Exceptions
  │   ├── config/            # Configuration Files
  ├── src/main/resources/
  │   ├── application.properties  # Configurations
  ├── docker-compose.yml          # Docker Configuration
  ├── pom.xml                     # Maven Dependencies
```

## Setup & Installation
### Prerequisites
- Java 17+
- Maven
- Docker (optional for containerized deployment)
- PostgreSQL (or configure another database in `application.properties`)

### Steps to Run Locally
1. Clone the repository:
   ```sh
   git clone https://github.com/nishant-kumar2001/Gift-Card-System.git
   cd giftcard-system
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```

### Run with Docker
1. Ensure Docker is installed.
2. Start the services:
   ```sh
   docker-compose up --build
   ```

## API Endpoints
### Authentication
- **Login:** `POST /api/auth/login`
- **Register:** `POST /api/auth/register`

### Gift Cards
- **Create:** `POST /api/giftcards/create`
- **Get All:** `GET /api/giftcards/allgift`
- **Purchase:** `POST /api/giftcards/purchase`
- **Delete:** `POST /api/giftcards/{id}`

### Transactions
- **Get User Transactions:** `GET /api/transactions`

## Future Enhancements
- Implementing Kafka for real-time notifications.
- Adding UI for better user interaction.
- Enhancing security with OAuth2.



## License
This project is licensed under the MIT License.

