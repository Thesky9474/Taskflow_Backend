# TaskFlow Backend

A modern task management REST API built with Spring Boot 4.0.1, providing secure authentication and comprehensive task management capabilities.

## 🚀 Tech Stack

- **Java 25**
- **Spring Boot 4.0.1**
- **Spring Security** - JWT-based authentication
- **Spring Data JPA** - Database ORM
- **MySQL** - Relational database
- **Lombok** - Boilerplate reduction
- **Maven** - Build tool

## 📋 Prerequisites

Before running this application, ensure you have:

- Java 25 or higher
- MySQL 8.0+
- Maven 3.6+

## ⚙️ Configuration

### Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE taskflow_db;
```

2. Update `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/taskflow_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# JWT Configuration
spring.jwt.secret=your-super-secret-key-minimum-256-bits-long
```

> ⚠️ **Security Note**: Change the JWT secret to a secure random string (at least 256 bits) in production!

## 🏃 Running the Application

### Using Maven Wrapper (Recommended)

```bash
# Navigate to project directory
cd Task_Flow_Backend

# Run the application
./mvnw spring-boot:run
```

### Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/auth/register` | Register new user |
| POST | `/api/v1/auth/login` | Login user |

### Tasks

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/tasks` | Create new task |
| GET | `/api/v1/tasks/user/{userId}` | Get user's tasks (paginated) |
| PATCH | `/api/v1/tasks/{id}` | Update task status |
| DELETE | `/api/v1/tasks/{id}` | Delete task |

## 📦 Request/Response Examples

### Register User
```json
POST /api/v1/auth/register
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "SecurePassword123"
}
```

### Create Task
```json
POST /api/v1/tasks
Authorization: Bearer {jwt-token}
{
  "title": "Complete project documentation",
  "description": "Write comprehensive README",
  "status": "OPEN",
  "priority": "HIGH",
  "userId": 1
}
```

### Update Task Status
```json
PATCH /api/v1/tasks/1
Authorization: Bearer {jwt-token}
{
  "status": "DONE"
}
```

## 🔒 Security

- **JWT Authentication**: All task endpoints require a valid JWT token
- **BCrypt Password Encoding**: Passwords are securely hashed
- **CORS Configuration**: Configured for `http://localhost:5173` (frontend)

## 📂 Project Structure

```
src/main/java/com/taskflow/TaskFlowApplication/
├── config/          # Configuration classes (Security, CORS, etc.)
├── controller/      # REST API controllers
├── dto/            # Data Transfer Objects
│   ├── request/    # Request DTOs
│   └── response/   # Response DTOs
├── entity/         # JPA Entities
├── repository/     # Spring Data JPA repositories
├── security/       # Security components (JWT, UserDetails)
└── service/        # Business logic layer
```

## 🌟 Features

- ✅ User registration and authentication
- ✅ JWT-based security
- ✅ Task CRUD operations
- ✅ Task priority levels (HIGH, MEDIUM, LOW)
- ✅ Task status management (OPEN, IN_PROGRESS, DONE)
- ✅ Pagination support
- ✅ Due date tracking
- ✅ User-specific task filtering

## 🛠️ Development

### Build the Project
```bash
./mvnw clean package
```

### Run Tests
```bash
./mvnw test
```

## 📄 License

This project is created for educational purposes.

## 👤 Author

**Shivam Kumar Yadav**

---

For frontend setup, see [task-flow-frontend README](../task-flow-frontend/README.md)