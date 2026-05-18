# GlobalSync-Bonus-System

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:002F6C,25:004B87,50:0072CE,75:4192D9,100:74B3CE&height=200&section=header&text=GlobalSync%20Bonus%20System&fontSize=38&fontColor=ffffff&animation=fadeIn"/>
</p>

<p align="center"> 
  <a href="https://github.com/iammrranik"> 
    <img src="https://img.shields.io/badge/_Click_Here_to_Explore_My_GitHub_Profile-181717?style=for-the-badge&logo=github&logoColor=white"/> 
  </a> 
</p>

---

## ✨ Status
🚧 **Completed** 🧠 Built with Spring Boot 3.x & Java 17+  
💾 Powered by Spring JDBC (`NamedParameterJdbcTemplate`) & MySQL  
🛡️ Secured with Spring Security (RBAC)  
🎯 Designed for Final Evaluation

---

## 📋 Project Overview

**GlobalSync-Bonus-System** is a secure enterprise RESTful API designed for **GlobalSync**, a multinational logistics company, to automate its annual employee performance bonus distribution.

The system processes multiple employee Key Performance Indicator (KPI) attributes via assessments, enforces rigorous evaluation validation boundary constraints, assigns organizational tiers, maps bonuses, tracks total compensation and commits state updates atomically under Spring Transaction Management.

---

## 🔥 Features

- 🧩 **Clean Layered Architecture** – Strict separation of concerns using Api, Service and Repository layers.
- 💾 **Spring JDBC Data Persistence** – Highly optimized relational queries via `NamedParameterJdbcTemplate`.
- 🛡️ **Role-Based Access Control (RBAC)** – Security path isolation restricting access to ADMIN, MANAGER and EMPLOYEE roles.
- 🧮 **Automated Multi-KPI Engine** – Math utility processing cumulative point metrics across 7 mandatory evaluation parameters.
- 📈 **Transactional Integrity** – `@Transactional` interceptors ensuring database updates roll back instantly upon any runtime failure.
- 📊 **Clean Query Parameters** – Decoupled pagination layout logic handling large data lists gracefully.

---

## 📌 Tech Stack

- **Backend Framework:** Spring Boot
- **Database Access:** Spring JDBC (`NamedParameterJdbcTemplate`)
- **Security Framework:** Spring Security
- **Database Engine:** MySQL Server
- **Build Tool:** Apache Maven
- **API Architecture:** REST API

---

## 🗂️ Project Architecture & Structure

```text
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── globalsync/
│   │   │           └── bonus/
│   │   │               └── system/
│   │   │                   ├── api/
│   │   │                   │   ├── BonusApi.java
│   │   │                   │   ├── EmployeeApi.java
│   │   │                   │   └── PerformanceApi.java
│   │   │                   ├── config/
│   │   │                   │   └── SecurityConfig.java
│   │   │                   ├── domain/
│   │   │                   │   ├── enums/
│   │   │                   │   ├── BonusRecord.java
│   │   │                   │   ├── Employee.java
│   │   │                   │   └── PerformanceReview.java
│   │   │                   ├── repository/
│   │   │                   │   ├── implementation/
│   │   │                   │   │   ├── BonusRecordRepository.java
│   │   │                   │   │   ├── EmployeeRepository.java
│   │   │                   │   │   └── PerformanceReviewRepository.java
│   │   │                   │   ├── mapper/
│   │   │                   │   ├── IBonusRepository.java
│   │   │                   │   ├── IEmployeeRepository.java
│   │   │                   │   └── IPerformanceRepository.java
│   │   │                   ├── service/
│   │   │                   │   ├── implementation/
│   │   │                   │   │   ├── BonusService.java
│   │   │                   │   │   ├── EmployeeService.java
│   │   │                   │   │   └── PerformanceService.java
│   │   │                   │   ├── IBonusService.java
│   │   │                   │   ├── IEmployeeService.java
│   │   │                   │   └── IPerformanceService.java
│   │   │                   └── Application.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       ├── api.txt
│   │       ├── api2.txt
│   │       ├── application.properties
│   │       └── db.sql
```

---

## 🚦 REST API Endpoints Map

### 1. Employee Management
| Action | Method | Endpoint | Sample JSON Body / Parameters |
| :--- | :--- | :--- | :--- |
| **Add Employee (Ex 1)** | POST | `/api/employees` | `{"name": "Anik Rahman", "designation": "Software Engineer", "baseSalary": 55000.0, "role": "EMPLOYEE", "lastPromotionDate": "2025-01-15"}` |
| **Add Employee (Ex 2)** | POST | `/api/employees` | `{"name": "Nusrat Jahan", "designation": "Senior Developer", "baseSalary": 85000.0, "role": "MANAGER", "lastPromotionDate": "2024-06-10"}` |
| **Update Employee** | PUT | `/api/employees/{id}` | `{"name": "Anik Rahman", "designation": "Senior Engineer", "baseSalary": 65000.0, "role": "EMPLOYEE", "lastPromotionDate": "2026-05-18"}` |
| **Get Employee** | GET | `/api/employees/{id}` | No body required. Example: `/api/employees/1` |
| **List Employees** | GET | `/api/employees/{p}/{s}` | No body. Example (Page 1, Size 10): `/api/employees/1/10` |
| **Delete Employee** | DELETE | `/api/employees/{id}` | No body required. Example: `/api/employees/1` |

### 2. Performance Review & Calculation Management
| Action | Method | Endpoint | Sample JSON Body / Parameters |
| :--- | :--- | :--- | :--- |
| **Process Performance & Calculate (Step 6)** | POST | `/api/performances/calculate` | `{"employeeId": 1, "reviewYear": 2026, "taskCompletionRate": 23, "attendanceAndPunctuality": 14, "teamCollaboration": 13, "problemSolvingSkill": 14, "communicationSkill": 9, "leadershipAndInitiative": 8, "clientSatisfaction": 9}` |
| **Get Review** | GET | `/api/performance-reviews/{id}` | No body required. Example: `/api/performance-reviews/1` |
| **List Reviews** | GET | `/api/performance-reviews/{p}/{s}` | No body. Example (Page 1, Size 5): `/api/performance-reviews/1/5` |
| **Total Count** | GET | `/api/performance-reviews/count` | No body. Returns an integer (e.g., 12) |

### 3. Payout & Calculation Logic
| Action | Method | Endpoint | Sample JSON Body / Parameters |
| :--- | :--- | :--- | :--- |
| **View Bonus Record** | GET | `/api/bonus-records/{id}` | No body required. Example: `/api/bonus-records/1` |
| **View All Payouts** | GET | `/api/bonus-records/{p}/{s}` | No body. Example (Page 1, Size 10): `/api/bonus-records/1/10` |

---

## 🛡️ Spring Security & RBAC Matrix

The application implements a security strategy mapped to corporate business roles:

- 🟢 **EMPLOYEE** $\rightarrow$ Can only view own bonus records.
- 🟡 **MANAGER** $\rightarrow$ Can submit KPI reviews.
- 🔴 **ADMIN** $\rightarrow$ Can view all employee bonuses.

---

## 🧮 KPI Distribution & Calculation Logic

The system strictly executes business rules calculations according to the following policies:

### 1. KPI Point Caps Validation Checklist
* **Task Completion Rate:** Max 25 points
* **Attendance & Punctuality:** Max 15 points
* **Team Collaboration:** Max 15 points
* **Problem Solving Skill:** Max 15 points
* **Communication Skill:** Max 10 points
* **Leadership & Initiative:** Max 10 points
* **Client Satisfaction:** Max 10 points

### 2. Tier Threshold Rules
* **Gold Tier (90 - 100 points):** **20%** bonus multiplier
* **Silver Tier (75 - 89 points):** **12%** bonus multiplier
* **Bronze Tier (60 - 74 points):** **5%** bonus multiplier
* **No Tier (Below 60 points):** **0%** bonus multiplier

```text
Bonus Amount = (Base Salary * Bonus Percentage) / 100
Total Compensation = Base Salary + Bonus Amount
```

---

## 🚦 Installation and Local Execution

1. Clone this repository locally.
2. Complete data provisioning parameters within `src/main/resources/application.properties`.
3. Build the clean project using Apache Maven:
   ```bash
   mvn clean install
   ```
4. Start up your Spring Boot server:
   ```bash
   mvn spring-boot:run
   ```

---

## 🖐️ Credits

Developed by [iammrranik](https://github.com/iammrranik) for the Advanced Programming with Java.

<p align="center"> <img src="https://capsule-render.vercel.app/api?type=rect&color=0:002F6C,20:004B87,40:0072CE,60:4192D9,80:74B3CE,100:8B00FF&height=4" width="80%"/> </p>