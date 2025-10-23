# Movie Collection App

A Spring Boot web application for managing and organizing your movie collection. Track movies, actors, and streaming platforms in one convenient place.

## Features

- Browse and manage movie collections
- Track actors and their filmographies
- Organize movies by streaming platforms
- Web-based interface for easy access
- In-memory database with sample data

## Technologies

### Backend
- **Java 21** - Programming language
- **Spring Boot 3.5.4** - Application framework
- **Spring Data JPA** - Data persistence layer
- **Spring Web** - REST API and web layer
- **Spring Validation** - Input validation
- **H2 Database** - In-memory database
- **Lombok** - Reduces boilerplate code

### Frontend
- **Thymeleaf** - Server-side templating engine
- **HTML/CSS/JavaScript** - Frontend technologies

### Build & Testing
- **Maven** - Build automation and dependency management
- **Spring Boot Test** - Testing framework

## Architecture

The application follows a layered architecture:

```
Controller Layer (MoviesController)
        ↓
Service Layer (MovieService, ActorService, StreamingPlatformService)
        ↓
Repository Layer (JPA Repositories)
        ↓
Database Layer (H2 In-Memory Database)
```

**Key Components:**
- **Entities**: Movie, Actor, StreamingPlatform - JPA entities representing data models
- **DTOs**: MovieDTO - Data transfer objects for API communication
- **Repositories**: JPA repositories for database operations
- **Services**: Business logic layer
- **Controllers**: REST endpoints and web request handling

## Prerequisites

- Java 21 or higher
- Maven 3.6+ (or use included Maven wrapper)

## How to Run

### Option 1: Using Maven Wrapper (Recommended)
```bash
# Clone the repository
git clone https://github.com/VitalinaKliuieva/FilmReviewerApplication
cd MovieCollectionApplication

# Run the application
./mvnw spring-boot:run
```

### Option 2: Using Maven
```bash
# Compile and run
mvn clean spring-boot:run
```

### Option 3: Build and Run JAR
```bash
# Build the application
./mvnw clean package

# Run the JAR file
java -jar target/MovieLibraryApplication-0.0.1-SNAPSHOT.jar
```

## Access the Application

- **Web Interface**: http://localhost:8080/movies
- **H2 Database Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:moviesdb`
  - Username: `sa`
  - Password: (leave empty)

## Project Structure

```
src/
├── main/
│   ├── java/com/kliuieva/
│   │   ├── controller/          # REST controllers
│   │   ├── dto/                 # Data transfer objects
│   │   ├── entity/              # JPA entities
│   │   ├── repositories/        # Data repositories
│   │   ├── service/             # Business logic
│   │   └── FilmReviewerApplication.java
│   └── resources/
│       ├── static/              # CSS, JS files
│       ├── templates/           # Thymeleaf templates
│       ├── application.properties
│       └── data.sql             # Sample data
└── test/                        # Unit tests
```

## Development

### Running Tests
```bash
./mvnw test
```

### Building for Production
```bash
./mvnw clean package -Dmaven.test.skip=true
```

## Configuration

Key configuration in `application.properties`:
- Database: H2 in-memory database
- JPA: Hibernate with auto DDL creation
- SQL logging enabled for development
- H2 console enabled for database inspection