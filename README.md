
# ApplyAI – AI-Powered Job Application Tracker

ApplyAI is a full-stack web application in development, designed to help job seekers organize and track their job applications and prepare for technical interviews using AI.

The project is being developed with **Java, Spring Boot, PostgreSQL, and Groq AI**.

> **Project Status:** In Progress – Backend development

## Features

### Authentication and Security
- User registration and login
- JWT-based authentication
- Password encryption using BCrypt
- Protected REST API endpoints
- User-specific data access

### Job Application Management
- Create new job applications
- View all applications
- View application details
- Update existing applications
- Delete applications
- Filter applications by status
- Track companies, positions, application dates, and notes

### AI Interview Preparation
- Generate technical interview questions using AI
- Customize questions based on company and job position
- Integration with Groq API using the GPT-OSS model

### Dashboard
- REST endpoint for job application statistics
- User-specific application overview

## Tech Stack

**Backend**
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- REST API
- JWT Authentication
- Maven

**Database**
- PostgreSQL

**AI Integration**
- Groq API
- OpenAI GPT-OSS 20B model

**Development Tools**
- IntelliJ IDEA
- Git & GitHub
- Postman

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user |

### Job Applications

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/applications` | Get user applications |
| GET | `/api/applications/{id}` | Get application by ID |
| POST | `/api/applications` | Create application |
| PUT | `/api/applications/{id}` | Update application |
| DELETE | `/api/applications/{id}` | Delete application |

### AI Interview Preparation

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/applications/{id}/interview-questions` | Generate interview questions |

### Dashboard

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/dashboard` | Get application statistics |

## Project Structure

```text
applyai-job-tracker/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/applyai/backend/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── entity/
│   │   │   │   ├── repository/
│   │   │   │   ├── security/
│   │   │   │   └── service/
│   │   │   └── resources/
│   └── pom.xml
└── README.md
```

## Getting Started

### Prerequisites
- Java 21
- PostgreSQL
- Maven or Maven Wrapper
- Groq API key

### Installation

1. Clone the repository:

```bash
git clone https://github.com/MarinaJanjicic/applyai-job-tracker.git
```

2. Navigate to the backend directory:

```bash
cd applyai-job-tracker/backend
```

3. Create a PostgreSQL database named `applyai_db`.

4. Configure the database connection, JWT secret, and Groq API key in your local application configuration.

5. Run the application using the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

## Planned Improvements

- React frontend
- User-friendly dashboard
- Application management interface
- Enhanced AI interview preparation
- Additional filtering and search features
- Automated testing

## Author

**Marina Janjičić**

Information Systems and Technologies Graduate  
Faculty of Organizational Sciences, University of Belgrade

GitHub: [MarinaJanjicic](https://github.com/MarinaJanjicic)

---

*This project is being developed as a personal portfolio project to demonstrate backend development, REST API design, authentication, database management, and AI integration.*
