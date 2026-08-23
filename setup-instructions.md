# PitosPies - Project Setup Instructions (Spring Boot)

Follow these steps to get the PitosPies backend running locally and the database properly set up.

---

## 1. Clone the Project

Clone the repository and open it in your IDE of choice (IntelliJ IDEA, VS Code, etc.).

```bash
git clone https://github.com/nikwilldoit/PitosPies.git
```

---

## 2. Create the Database Locally

1. Open **MySQL Workbench** (or any MySQL client) - MySQL 8.0+.
2. Create a new empty database named **`pitos`**:

```sql
CREATE DATABASE pitos
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

---

## 3. Create the Database User

1. Log into MySQL as root:

```bash
mysql -u root -p
```

2. Create a dedicated application user:

```sql
CREATE USER 'pitos'@'localhost' IDENTIFIED BY 'your-secure-password';
```

3. Grant privileges on the `pitos` database:

```sql
GRANT ALL PRIVILEGES ON pitos.* TO 'pitos'@'localhost';
```

4. Apply changes:

```sql
FLUSH PRIVILEGES;
```

5. Exit:

```sql
EXIT;
```

> ⚠️ Do not reuse a throwaway password like `pitos`/`pitos` outside of local development. Pick a strong password and keep it out of source control (see [Configuration & Security](#configuration--security) below).

---

## 4. Run the Database Schema

1. Locate the SQL file in the project:

```text
src/main/resources/db/DB_schema.sql
```

2. Run it against the `pitos` database in MySQL Workbench or your MySQL client:

```bash
mysql -u pitos -p pitos < src/main/resources/db/Dump20260815.sql
```

---

## 5. (Optional) Add the Database to IntelliJ

1. Open **View → Tool Windows → Database** in IntelliJ (enable the panel if it's not visible).
2. Click **"+" → Data Source → MySQL**.
3. Enter your database details:

```text
Host: localhost
Port: 3306
User: pitos
Password: <your-secure-password>
Database: pitos
```

4. Download the MySQL driver if prompted.
5. Click **Test Connection** - it should show **Successful**.
6. Click **Apply** or **OK** to save the connection. IntelliJ will now show the database and its tables.

---

## 6. Configure the Application

### Database connection

Configure the database connection in `src/main/resources/application.properties` using environment variables rather than hardcoded values:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pitos
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Email (SMTP)

Configure the SMTP account, also via environment variables instead of storing credentials directly in the repository:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_APP_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

Set the corresponding environment variables before running the app, e.g.:

```bash
export DB_USERNAME=pitos
export DB_PASSWORD=your-secure-password
export MAIL_USERNAME=your-email@gmail.com
export MAIL_APP_PASSWORD=your-gmail-app-password
```

---

## 7. Build the Application

```bash
mvn clean package
```

---

## 8. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

The application is available at:

```text
http://localhost:8080
```

> Note: PitosPies is a server-rendered **Spring Boot + Thymeleaf application*** - there is no separate frontend project/dev server to start (no React/Node build step is required).

---

## Configuration & Security

For local development and production environments, sensitive configuration should always be supplied through environment variables or another secure secrets mechanism - never hardcoded in `application.properties`.

In particular, avoid committing:

- Database passwords.
- Email passwords or app passwords.
- Twilio credentials (`SMSService.ACCOUNT_SID` / `AUTH_TOKEN`).
- Security keys (e.g. the Spring Security remember-me key).

If any of the above have already been committed to the repository at any point (as was the case with the original `application.properties` in this project), rotate/revoke them immediately, even after removing them from source control.
