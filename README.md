# 🔐 Secret Vault API

Store secrets that self-destruct after being viewed or expired.

A secure REST API built with Spring Boot that allows users to create one-time secret messages, share them using a unique secret key, and automatically destroy them after viewing or expiration.

---

## 🚀 Features

* ✅ User Registration & Login
* ✅ JWT Authentication & Authorization
* ✅ Create Secret Notes
* ✅ One-Time Secret Viewing
* ✅ Secret Expiration Support
* ✅ Automatic Cleanup of Expired Secrets
* ✅ Spring Security Integration
* ✅ DTO-Based Request & Response Handling
* ✅ Global Exception Handling

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok

---

## 📂 Project Structure

```text
src/main/java/com/secret
│
├── auth
│   ├── config
│   ├── service
│
├── controller
│
├── dto
│   ├── request
│   ├── response
│
├── entity
├── repository
├── service
├── exception
│
├── user
│   ├── controller
│   ├── repository
│   ├── service
│   ├── userDto
│   ├── userEntity
```

---

## 🗄️ Database Design

### Users Table

| Column   | Type    |
| -------- | ------- |
| id       | BIGINT  |
| username | VARCHAR |
| email    | VARCHAR |
| password | VARCHAR |

### Secrets Table

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| secret_key | VARCHAR   |
| content    | TEXT      |
| expires_at | TIMESTAMP |
| is_viewed  | BOOLEAN   |
| viewed_at  | TIMESTAMP |
| user_id    | BIGINT    |

---

## 🔑 Authentication APIs

### Register User

**POST**

```http
/api/auth/register
```

#### Request

```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "Password@123"
}
```

#### Response

```json
{
  "message": "User registered successfully"
}
```

---

### Login User

**POST**

```http
/api/auth/login
```

#### Request

```json
{
  "email": "john@example.com",
  "password": "Password@123"
}
```

#### Response

```json
{
  "token": "jwt-token"
}
```

---

## 🔐 Secret APIs

### Create Secret

**POST**

```http
/api/secret
```

#### Header

```http
Authorization: Bearer <JWT_TOKEN>
```

#### Request

```json
{
  "content": "My ATM pin is 1234",
  "expirationMinutes": 30
}
```

#### Response

```json
{
  "secretKey": "a7f92c4d1b",
  "expiresAt": "2026-06-17T10:30:00",
  "viewed": true
}
```

---

### View Secret

**GET**

```http
/api/secrets/{secretKey}
```

#### Example

```http
/api/secrets/68d56879-a40a-4220-a520-49ca69e073b2
```

#### Response

```json
{
  "content": "My ATM pin is 1234"
}
```

> ⚠️ After successful retrieval, the secret is marked as viewed and cannot be accessed again.

---

### Secret Already Viewed

```json
{
  "message": "Secret already destroyed"
}
```

---

### Secret Expired

```json
{
  "message": "Secret expired or does not exist"
}
```

---

## ⏰ Automatic Cleanup

A scheduled task runs periodically and removes expired secrets from the database.

```java
@Scheduled(fixedRate = 60000)
```

This ensures that expired secrets are automatically deleted without manual intervention.

---

## 🔒 Security

* JWT-based Authentication
* BCrypt Password Encryption
* Protected Endpoints
* Stateless Session Management
* Spring Security Filter Chain

---

## ▶️ Running the Project

### 1️⃣ Clone Repository

```bash
git clone https://github.com/<your-username>/secret-vault-api.git
```

### 2️⃣ Configure Database

Update the following properties in:

```properties
application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/secret_vault
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

---

## 🎯 Learning Objectives

This project demonstrates:

* REST API Development
* Spring Boot Fundamentals
* Spring Security & JWT
* JPA Entity Relationships
* DTO Design Pattern
* Scheduled Tasks
* Exception Handling
* Database Operations
* Authentication & Authorization

---

## 👨‍💻 Author

Built as a Spring Boot learning project to practice secure API development and backend design.

**Jayakrishna**
