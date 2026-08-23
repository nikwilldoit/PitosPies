# E-Commerce Web Application for Traditional Bakery / Pie Store ("PitosPies")

A comprehensive, production-ready Spring Boot web application designed for an online traditional pie shop ("PitosPies"). The system provides a complete ordering pipeline, menu management, user authentication & role-based access control (RBAC), custom form validations, internationalization (i18n), metrics tracking, database persistence with JDBC, and notification services (Email & SMS).

---

## 📋 Table of Contents
1. [Project Overview](#-project-overview)
2. [Key Features](#-key-features)
3. [Technology Stack](#-technology-stack)
4. [Architecture & Project Structure](#-architecture--project-structure)
5. [Domain Model & Database Schema](#-domain-model--database-schema)
6. [Security & Authentication](#-security--authentication)
7. [Validation Engine](#-validation-engine)
8. [Services & Components](#-services--components)
9. [Controllers & API Routes](#-controllers--api-routes)
10. [Internationalization & Localization (i18n)](#-internationalization--localization-i18n)
11. [Configuration & Environment Setup](#-configuration--environment-setup)
12. [Database Initialization & Seed Data](#-database-initialization--seed-data)
13. [Installation & Execution Guide](#-installation--execution-guide)
14. [Testing & Quality Assurance](#-testing--quality-assurance)

---

## 🔍 Project Overview

The **PitosPies Web Application** is built with Spring Boot to facilitate online ordering of artisanal baked goods and traditional pies. The application caters to both retail customers (browsing products, configuring custom orders, checking delivery availability by area, tracking order history) and administrative personnel (managing store inventory, reviewing order streams, sending SMS updates, and viewing web traffic/visit metrics).

### Key Architectural Highlights:
* **Framework:** Spring Boot (Java 17/21 compatible).
* **Architecture Layering:** Standard Controller-Service-Repository (DAO) pattern.
* **Security Layer:** Spring Security with BCrypt password hashing, session data handling, and custom login/logout success handlers.
* **Data Persistence:** Spring Data JDBC connected to MySQL database (`Dump20260815.sql`).
* **Custom Validation Framework:** Custom JSR-380 (Bean Validation) annotations and validator implementations (e.g., verifying phone formats, preventing duplicate emails/usernames, validating order timestamp ranges, and ensuring non-empty carts).
* **Notification System:** Asynchronous email dispatch via Spring Mail (`MailService`) and SMS messaging engine (`SMSService`).
* **Multi-Language Support:** Full i18n support with fallback properties (`messages.properties`), Greek (`messages_el_GR.properties`), and English (`messages_en_US.properties`).

---

## ✨ Key Features

### 🛒 Customer-Facing Features
* **Interactive Menu & Pie Catalog:** Browse pies categorized with rich details, ingredients list (`Ingredient`), pricing, awards (`Award`), and high-resolution visuals.
* **Delivery Area Verification:** Check if delivery service is supported in specific geographic areas (`Area`).
* **Seamless Checkout & Ordering:** Form-driven checkout workflow with real-time validation (ensuring at least one item per order, valid delivery timestamps, correct item quantities, and phone numbers).
* **Order History & DTOs:** Registered users can track past purchases via `PreviousOrder` DTOs.
* **Password Reset Workflow:** Two-step secure password reset process (`FormPasswordReset` and `FormPasswordReset2`).
* **Contact & Feedback Form:** User contact form with message length and content checks (`FormDataContact`).

### 🛡 Admin & Store Management Features
* **Admin Dashboard:** Access-controlled interface (`AdminController`) for store administration.
* **Store Metrics & Visit Analytics:** `VisitsMetricsService` tracks website traffic, user interactions, and visitor analytics.
* **SMS Notifications & Dispatch:** Integrated SMS notification hub (`SendSMSController`, `SMSService`) for immediate order updates or marketing alerts.
* **Global Exception Handling:** Custom error pages (`CustomErrorController`) and advice (`GlobalExceptionHandler`) for graceful failure management.

---

## 🛠 Technology Stack

| Layer / Aspect | Technology / Library |
| :--- | :--- |
| **Language** | Java 17+ |
| **Framework** | Spring Boot |
| **Security** | Spring Security, Spring Session |
| **Persistence / ORM** | Spring Data JPA, Hibernate, MySQL Driver |
| **Validation** | Jakarta Bean Validation (Hibernate Validator) |
| **Messaging & Mailing** | JavaMailSender (Spring Mail), SMS Gateway Client |
| **Template Engine / Views** | Thymeleaf / HTML5 static assets |
| **Database** | MySQL 8.0 / MariaDB (`Dump20260815.sql`) |
| **Build System** | Maven / Gradle |

---

## 📁 Architecture & Project Structure

```text
com.nikolas.app/
├── AppApplication.java                      # Main Spring Boot Entry Point
├── beans/
│   └── Counter.java                         # Application-scoped / Session counter bean
├── components/
│   ├── EmailTemplates.java                  # HTML/Text templates for automated emails
│   └── SessionData.java                     # User session context wrapper
├── config/
│   ├── PasswordEncoderConfig.java           # BCrypt Password Encoder configuration
│   ├── WebSecurityConfig.java               # Spring Security configuration (HTTP security, routes, roles)
│   └── interceptors/
│       ├── CustomLoginSuccessHandler.java   # Post-login redirect & audit logic
│       └── CustomLogoutSuccessHandler.java  # Post-logout cleanup logic
├── controllers/
│   ├── AdminController.java                 # Admin management endpoints (/admin/**)
│   ├── AttrController.java                  # Model attributes & global controller advice
│   ├── AuthController.java                  # Authentication routes (login, register, reset password)
│   ├── BuyController.java                   # Order submission and cart validation endpoints
│   ├── ContactController.java               # Contact form endpoints (/contact)
│   ├── IndexController.java                 # Landing page & home routes (/)
│   ├── PiesController.java                  # Pie catalog & product details routes (/pies)
│   ├── SendSMSController.java               # SMS messaging administration
│   ├── StoreController.java                 # Store information & delivery area lookup
│   ├── dtos/
│   │   └── PreviousOrder.java               # DTO for customer order history display
│   └── forms/
│       ├── FormDataContact.java             # Form binding object for contact requests
│       ├── FormDataOrder.java               # Form binding object for checkout orders
│       ├── FormLogin.java                   # Form binding object for user login
│       ├── FormPasswordReset.java           # Step 1 Password reset request form
│       ├── FormPasswordReset2.java          # Step 2 Password confirmation & token form
│       ├── FormRegister.java                # User registration form object
│       └── custom_validators/
│           ├── AtLeastOneItemInOrderConstraint.java / Validator.java
│           ├── EmailNotExistsConstraint.java / Validator.java
│           ├── MessageConstraint.java / Validator.java
│           ├── OrderItemValuesConstraint.java / Validator.java
│           ├── OrderTimestampConstraint.java / Validator.java
│           ├── TelephoneConstraint.java / Validator.java
│           └── UsernameNotExistsConstraint.java / Validator.java
├── excluded/
│   └── ExcludedController.java              # Experimental or sandbox endpoints
├── models/
│   ├── Area.java                            # Delivery area entity
│   ├── Award.java                           # Product awards / recognitions entity
│   ├── Ingredient.java                      # Recipe ingredient entity
│   ├── Order.java                           # Customer order entity
│   ├── OrderItem.java                       # Order item junction entity with quantity & price
│   ├── Pie.java                             # Bakery product entity
│   ├── Role.java                            # Security role entity (ROLE_USER, ROLE_ADMIN)
│   └── User.java                            # Application user entity
├── repositories/
│   ├── AreaRepository.java                  # JPA Repository for Area
│   ├── OrderItemRepository.java             # JPA Repository for OrderItem
│   ├── OrderRepository.java                 # JPA Repository for Order
│   ├── PieRepository.java                   # JPA Repository for Pie
│   ├── RoleRepository.java                  # JPA Repository for Role
│   └── UserRepository.java                  # JPA Repository for User
├── services/
│   ├── AuthService.java                     # User authentication, registration & security logic
│   ├── MailService.java                     # SMTP email delivery service
│   ├── SMSService.java                      # Telephony / SMS delivery service
│   └── VisitsMetricsService.java            # Traffic tracking & analytics aggregator
└── settings/
    ├── ConfigBeans.java                     # Additional Spring bean definitions
    ├── CustomErrorController.java           # Custom HTTP error handling (404, 403, 500)
    └── GlobalExceptionHandler.java          # @ControllerAdvice for centralized exception mapping

resources/
├── application.properties                   # Main application configuration file
├── project.properties                       # Project metadata & environment flags
├── db/
│   └── Dump20260815.sql                     # Full MySQL database SQL dump & schema seed
├── messages.properties                      # Default translation bundle
├── messages_el_GR.properties                # Greek localized message bundle
├── messages_en_US.properties                # English localized message bundle
└── static/
    └── images/
        ├── boureki.jpg                      # Product imagery
        ├── cook.png                         # Branding graphics
        └── delivery.jpg                     # Banner imagery
```

---

## 🗄 Domain Model & Database Schema

The domain layer utilizes JPA annotations (`@Entity`, `@Table`, `@Id`, `@OneToMany`, `@ManyToMany`, `@ManyToOne`) to represent the business entities.

### Entity Relationships Summary:
* **User & Role:** Many-to-Many (`User` <-> `Role`). Users can possess multiple authorities (e.g., `ROLE_USER`, `ROLE_ADMIN`).
* **User & Order:** One-to-Many (`User` -> `Order`). A user can place multiple historical orders.
* **Order & OrderItem:** One-to-Many (`Order` -> `OrderItem`). Each order consists of line items representing selected items and their quantities.
* **Pie & OrderItem:** One-to-Many (`Pie` -> `OrderItem`). Each order line references a pie item.
* **Pie & Ingredient:** Many-to-Many (`Pie` <-> `Ingredient`). A pie is made of multiple ingredients.
* **Pie & Award:** One-to-Many / Many-to-Many (`Pie` -> `Award`). Recognitions earned by specific pies.
* **Area:** Represents serviceable delivery postal codes / neighborhoods.

---

## 🔒 Security & Authentication

The security infrastructure is configured in `WebSecurityConfig.java`:

* **Password Encoding:** BCrypt hashing strategy configured via `PasswordEncoderConfig`.
* **Authentication Provider:** Custom DAO authentication wired with `AuthService` and `UserRepository`.
* **Authorization Rules:**
  * Public access: `/`, `/pies/**`, `/store/**`, `/contact`, `/register`, `/login`, `/css/**`, `/js/**`, `/images/**`.
  * Authenticated user access: `/buy/**`, `/profile/**`, `/orders/**`.
  * Admin access: `/admin/**`, `/sms/**`.
* **Login Handlers:**
  * `CustomLoginSuccessHandler`: Processes post-login analytics, records metrics via `VisitsMetricsService`, and redirects users according to assigned roles.
  * `CustomLogoutSuccessHandler`: Cleans up active `SessionData` and invalidates user session state.

---

## 🧪 Validation Engine

The application enforces declarative data validation using custom annotations in `com.nikolas.app.controllers.forms.custom_validators`:

1. **`@AtLeastOneItemInOrderConstraint` (`AtLeastOneItemInOrderValidator`):** Verifies that an incoming `FormDataOrder` contains at least one non-zero item selection.
2. **`@EmailNotExistsConstraint` (`EmailNotExistsValidator`):** Ensures email uniqueness during registration by querying `UserRepository`.
3. **`@UsernameNotExistsConstraint` (`UsernameNotExistsValidator`):** Prevents duplicate username registrations.
4. **`@TelephoneConstraint` (`TelephoneValidator`):** Validates phone numbers against regular expressions (supporting Greek and international phone formats).
5. **`@OrderTimestampConstraint` (`OrderTimestampValidator`):** Guarantees that delivery target dates/times fall within store operational hours and valid lead-time windows.
6. **`@OrderItemValuesConstraint` (`OrderItemValuesValidator`):** Sanitizes order item inputs against negative quantities or stock threshold violations.
7. **`@MessageConstraint` (`MessageValidator`):** Validates user contact messages against spam, forbidden HTML tags, or character length constraints.

---

## ⚙ Service Layer & Business Logic

* **`AuthService`:** Manages user registration, credential validation, password updates, and SecurityContext management.
* **`MailService`:** Handles transaction notifications (order confirmation, password reset links, email contact confirmations) using `EmailTemplates`.
* **`SMSService`:** Interacts with SMS gateways to dispatch automated SMS updates to customers.
* **`VisitsMetricsService`:** In-memory & DB-backed metric recorder tracking active sessions, total route visits, and pie product popularity.

---

## 🌐 Internationalization & Localization (i18n)

The application supports seamless language switching via Spring's `LocaleResolver` and `LocaleChangeInterceptor`:
* **`messages.properties`**: Default fallback strings.
* **`messages_el_GR.properties`**: Complete Greek translations for localized store messaging.
* **`messages_en_US.properties`**: English translations for international clients.

---

## 🚀 Installation & Setup Guide

### Prerequisites
* **Java Development Kit (JDK):** Version 17 or higher.
* **Database:** MySQL 8.0+ or MariaDB.
* **Build Tool:** Maven 3.8+ (or Gradle wrapper).

### 1. Database Setup
Create the target MySQL database and restore the database seed script:
```sql
CREATE DATABASE nikolas_pies_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
Import the dump provided in the resources folder:
```bash
mysql -u root -p nikolas_pies_db < src/main/resources/db/Dump20260815.sql
```

### 2. Application Configuration
Update `src/main/resources/application.properties` with your database and mail credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nikolas_pies_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Mail Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_EMAIL@gmail.com
spring.mail.password=YOUR_EMAIL_APP_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### 3. Build & Run
Compile the application and run the executable JAR:
```bash
# Using Maven
mvn clean package -DskipTests
java -jar target/app-0.0.1-SNAPSHOT.jar
```
Or execute via Spring Boot plugin:
```bash
mvn spring-boot:run
```

Access the application in your browser at `http://localhost:8080`.

---

## 📝 License & Credits
Developed by **Nikolas App Development Team**. Built with Spring Boot and standard open-source tools.
