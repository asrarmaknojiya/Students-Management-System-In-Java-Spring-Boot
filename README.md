<div align="center">

<img src="https://img.icons8.com/color/96/000000/graduation-cap.png" alt="Logo" width="96"/>

# 🎓 Students Management System

### A robust, production-ready RESTful API built with Java & Spring Boot

<br/>

[![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.11-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-Build_Tool-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Lombok](https://img.shields.io/badge/Lombok-Enabled-red?style=for-the-badge&logo=lombok&logoColor=white)](https://projectlombok.org/)

<br/>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](https://opensource.org/licenses/MIT)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=flat-square&logo=github-actions)](https://github.com/asrarmaknojiya/Students-Management-System-In-Java-Spring-Boot)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](https://github.com/asrarmaknojiya/Students-Management-System-In-Java-Spring-Boot/pulls)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
- [Configuration](#-configuration)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🌟 Overview

The **Students Management System** is a fully-featured backend REST API built using **Java Spring Boot**. It provides a clean and scalable architecture for managing student records — including adding, updating, retrieving, and deleting student data — backed by a **MySQL** relational database.

Designed with best practices in mind: layered architecture, validation, AOP-based cross-cutting concerns, and ORM via Spring Data JPA.

---

## ✨ Features

| Feature | Description |
|---|---|
| 📝 **CRUD Operations** | Create, Read, Update, and Delete student records |
| ✅ **Input Validation** | Request validation using Spring Validation (`@Valid`, constraints) |
| 🛡️ **AOP Support** | Aspect-Oriented Programming for logging and cross-cutting concerns |
| 🗄️ **JPA / Hibernate** | Seamless ORM with Spring Data JPA and Hibernate |
| 🧹 **Clean Code** | Lombok annotations to eliminate boilerplate |
| 🔌 **RESTful API** | Standard HTTP methods with meaningful status codes |
| 🧪 **Testing Ready** | Spring Boot Test dependency configured |

---

## 🛠️ Tech Stack

<div align="center">

| Layer | Technology |
|---|---|
| ☕ **Language** | Java 25 (Preview Features Enabled) |
| 🌱 **Framework** | Spring Boot 3.5.11 |
| 🗃️ **ORM** | Spring Data JPA + Hibernate |
| 🗄️ **Database** | MySQL |
| 🧩 **Build Tool** | Apache Maven |
| 🔧 **Utilities** | Lombok, Spring AOP |
| ✔️ **Validation** | Spring Boot Starter Validation |
| 🧪 **Testing** | Spring Boot Starter Test |

</div>

---

## 📁 Project Structure

```
Students-Management-System/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/SMS/
│   │   │   ├── controller/       # REST Controllers (API layer)
│   │   │   ├── service/          # Business logic layer
│   │   │   ├── repository/       # JPA Repositories (Data access layer)
│   │   │   ├── model/            # Entity classes
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   └── SmsApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/SMS/
│
├── .mvn/wrapper/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## 🚀 Getting Started

### ✅ Prerequisites

Make sure you have the following installed:

- ![Java](https://img.shields.io/badge/JDK-25+-ED8B00?style=flat-square&logo=openjdk) &nbsp; Java Development Kit 25+
- ![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=flat-square&logo=apachemaven) &nbsp; Apache Maven 3.8+
- ![MySQL](https://img.shields.io/badge/MySQL-8.0+-4479A1?style=flat-square&logo=mysql) &nbsp; MySQL Server 8.0+
- ![Git](https://img.shields.io/badge/Git-Latest-F05032?style=flat-square&logo=git) &nbsp; Git

---

### 📦 Installation

**1. Clone the repository**

```bash
git clone https://github.com/asrarmaknojiya/Students-Management-System-In-Java-Spring-Boot.git
cd Students-Management-System-In-Java-Spring-Boot
```

**2. Create the MySQL database**

```sql
CREATE DATABASE students_db;
```

**3. Configure `application.properties`**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/students_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Build the project**

```bash
./mvnw clean install
```

> On Windows, use `mvnw.cmd clean install`

**5. Run the application**

```bash
./mvnw spring-boot:run
```

The server will start on **`http://localhost:8080`** 🎉

---

## 📡 API Endpoints

Base URL: `http://localhost:8080/api/students`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Retrieve all students |
| `GET` | `/{id}` | Get a student by ID |
| `POST` | `/` | Add a new student |
| `PUT` | `/{id}` | Update an existing student |
| `DELETE` | `/{id}` | Delete a student |

### 📝 Sample Request — Create Student

```json
POST /api/students
Content-Type: application/json

{
  "firstName": "Asrar",
  "lastName": "Maknojiya",
  "email": "asrar@example.com",
  "phone": "9876543210",
  "course": "Computer Science"
}
```

### 📤 Sample Response

```json
{
  "id": 1,
  "firstName": "Asrar",
  "lastName": "Maknojiya",
  "email": "asrar@example.com",
  "phone": "9876543210",
  "course": "Computer Science"
}
```

---

## ⚙️ Configuration

Key properties in `src/main/resources/application.properties`:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/students_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

---

## 🤝 Contributing

Contributions are warmly welcome! Here's how to get involved:

1. **Fork** the repository
2. **Create** your feature branch: `git checkout -b feature/amazing-feature`
3. **Commit** your changes: `git commit -m 'Add some amazing feature'`
4. **Push** to the branch: `git push origin feature/amazing-feature`
5. **Open** a Pull Request

Please make sure your code follows clean code principles and includes relevant tests.

---

## 👨‍💻 Author

<div align="center">

**Asrar Maknojiya**

[![GitHub](https://img.shields.io/badge/GitHub-asrarmaknojiya-181717?style=for-the-badge&logo=github)](https://github.com/asrarmaknojiya)

</div>

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

<div align="center">

⭐ **If you found this project helpful, please give it a star!** ⭐

Made with ❤️ using Java & Spring Boot

</div>
