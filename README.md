# 🥧 **PitosPies** - Traditional Greek Pie Web Application

**PitosPies** is a **Spring Boot Web Application** for ordering traditional Greek pies online. The application provides a complete customer journey from browsing the pie catalog to creating an order, together with user authentication, email verification, password recovery, validation, and a basic administration area.

## Features

### Customer Features

- Browse the traditional pie catalog with prices and images.
- View detailed information about each pie, including ingredients and awards.
- View information about the physical store, including contact and location details.
- Register a new account with email verification.
- Log in and log out securely.
- Use the "Remember Me" option when logging in.
- Request a password reset by email.
- Add pies to the shopping cart and manage quantities.
- Place an order as a guest or authenticated user.
- Select delivery information, delivery area, payment method, and order comments.
- View the five most recent orders when logged in.
- Reorder a previous order.
- Receive order and account-related email notifications.
- Contact the store through the contact form.

### Administration

- Access the administration panel with the `ROLE_ADMIN` authority.
- View the number of users who have not completed email verification.
- Manage unverified user accounts.

## Technology Stack

| Category | Technology |
| :--- | :--- |
| Language | Java 17+ |
| Framework | Spring Boot 3.x |
| Web Layer | Spring Web MVC |
| Views | Thymeleaf |
| Persistence | Spring Data JDBC |
| Database | MySQL |
| Security | Spring Security |
| Password Hashing | BCrypt |
| Validation | Jakarta Bean Validation + custom constraints |
| Email | Spring Mail / `JavaMailSender` |
| SMS Integration | Twilio SDK |
| Boilerplate Reduction | Lombok |
| Frontend | Thymeleaf(HTML) + SCSS + JavaScript |
| Database Driver | MySQL Connector |

## Application Screenshots

### User Registration

Registration flow:

`GET /register` → enter user details → validation → verification email → `GET /register/{code}` to activate the account.

| Registration Form | Validation Messages | Verification Email |
| :---: | :---: | :---: |
| ![Registration Form](https://github.com/user-attachments/assets/f8f90919-07b0-4129-9473-326dde78c258) | ![Registration Errors](https://github.com/user-attachments/assets/f5a3f618-0124-40a7-8abd-e66b95639189) | ![Verification Email](https://github.com/user-attachments/assets/26162e05-e631-4d81-ab0e-7cdd12a3014b) |

### User Login

Login is handled through Spring Security form login, with success and error states and an optional Remember Me feature.

| Login Form | Successful Login | Invalid Credentials |
| :---: | :---: | :---: |
| ![Login Form](https://github.com/user-attachments/assets/d7f2ca7b-ab77-48a9-a147-00299da04a70) | ![Successful Login](https://github.com/user-attachments/assets/17d17c9b-46e2-4549-8166-6725149d0156) | ![Invalid Credentials](https://github.com/user-attachments/assets/eb6cee55-2706-42dc-ad39-77f771652731) |

### Forgot / Reset Password

The password recovery flow allows a user to request a verification code by email and then set a new password.

| Reset Request | Verification Email | New Password |
| :---: | :---: | :---: |
| ![Reset Request](https://github.com/user-attachments/assets/ce6a0277-6db3-4f25-b67a-3eda51e3ffaa) | ![Reset Email](https://github.com/user-attachments/assets/0f94328e-fdb6-4192-8cef-6a38b5b8c067) | ![New Password](https://github.com/user-attachments/assets/2d29b037-f3a9-415d-8268-031ab2d0fcf3) |

### Contact Form

Users can submit their name, email, telephone number, and message through the contact form. The application sends an email notification to the administrator and a confirmation email to the user.

| Contact Form | Successful Submission | Confirmation Email |
| :---: | :---: | :---: |
| ![Contact Form](https://github.com/user-attachments/assets/b2faed25-3c2c-47bd-9b22-a091a5e678e5) | ![Successful Submission](https://github.com/user-attachments/assets/57de81cd-24b2-4416-932a-103ae1f57d84) | ![Confirmation Email](https://github.com/user-attachments/assets/b8d1b238-4290-4082-b429-f5741565e821) |

### Pie Catalog & Details

The catalog is available at `/pies`. Users can open a pie's details page to view its information, ingredients, and awards, and can add a selected quantity to the cart.

| Pie Catalog | Pie Details |
| :---: | :---: |
| ![Pie Catalog](https://github.com/user-attachments/assets/f3534a76-602c-4b97-adfc-305fcbb55734) | ![Pie Details](https://github.com/user-attachments/assets/560e8f19-f5e6-4f86-a6e5-c61af377558b) |

### Store

The store page provides information about the physical shop, including its address, opening hours, and contact details.

| Store Page |
| :---: |
| ![Store Page](https://github.com/user-attachments/assets/25112c5b-6295-40c6-b21b-5c5acfafe04e) |

### Order Creation & Checkout

The `/buy` page handles the shopping cart and checkout process.

Users can:

- Review the current cart.
- Select or enter delivery information.
- Select a delivery area.
- Select a payment method.
- Add comments to the order.
- Review their five most recent orders when logged in.
- Reorder a previous order.

When an order is successfully submitted, it is stored in the database and confirmation emails are sent to the customer and administrator.

| Order Form | Previous Orders | Validation Errors | Successful Order | Order Confirmation Email |
| :---: | :---: | :---: | :---: | :---: |
| ![Order Form](https://github.com/user-attachments/assets/45971e56-3b82-42e8-9a94-d96a0cec0722) | ![Previous Orders](https://github.com/user-attachments/assets/d8a4258d-1688-474a-8931-c222234b4151) | ![Order Validation](https://github.com/user-attachments/assets/6be4ca21-f976-4f43-987c-e82326dbe907) | ![Successful Order](https://github.com/user-attachments/assets/989f6ce0-c54c-433a-bac1-4453ada086c8) | ![Order Email](https://github.com/user-attachments/assets/6a2ceed2-e089-489b-94b6-671368a8e305) |

### MySQL DB

[MySQL Database Schema](src/main/resources/db/DB_schema.sql)
<p align="center">
  <img width="544" height="566" alt="pitos_schema" src="https://github.com/user-attachments/assets/297f1a66-3755-4c5d-bff6-b315dc1548cd" />
</p>

## Project Structure

The application follows a traditional Spring Boot MVC architecture:

```text
src/main
├── java
│   └── com.nikolas.app
│       ├── config
│       ├── controllers
│       ├── repositories
│       ├── services
│       ├── models
│       └── ...
└── resources
    ├── static
    │   ├── js
    │   ├── sass
    │   └── styles
    ├── templates
    └── application.properties
```

The main application layers are:

- **Controllers** - handle HTTP requests and application flows.
- **Services** - contain business logic such as authentication, email delivery, SMS integration, and visit metrics.
- **Repositories** - provide database access through Spring Data JDBC and SQL queries.
- **Models** - represent users, pies, orders, ingredients, awards, roles, and delivery areas.
- **Templates** - Thymeleaf views for the web interface.
- **Static assets** - SCSS/CSS and JavaScript used by the frontend.

## Data Model

The main domain objects are:

- **User** - account information, credentials, verification status, and user details.
- **Role** - application roles such as `USER` and `ADMIN`.
- **Pie** - name, price, image, ingredients, and awards.
- **Ingredient**  ingredients associated with pies.
- **Award** - awards associated with pies.
- **Order** - customer, delivery, payment, and order information.
- **OrderItem** - individual pies and quantities belonging to an order.
- **Area** - available delivery areas.

Spring Data JDBC is used for persistence. Collections such as order items and pie awards are mapped using `@MappedCollection`, while some many-to-many relationships are handled through SQL queries.

## Security & Authentication

Authentication and authorization are implemented with Spring Security.

### Authentication

- Custom `UserDetailsService` loads users and their roles from the database.
- Passwords are hashed using BCrypt.
- Form-based login is supported.
- Logout is handled by Spring Security.
- Remember Me is supported.
- CSRF protection is enabled.

### Authorization

The application uses role-based authorities, including `ROLE_ADMIN`.

The administration entry point is protected with:

```java
.authorizeHttpRequests(authorize -> authorize
    .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
    .anyRequest().permitAll()
)
```

## Validation

The application uses Jakarta Bean Validation together with custom validation constraints.

Implemented validation rules include:

1. **`@AtLeastOneItemInOrderConstraint`** - ensures that an order contains at least one pie.
2. **`@EmailNotExistsConstraint`** - validates email uniqueness where required.
3. **`@UsernameNotExistsConstraint`** - validates username uniqueness during registration.
4. **`@TelephoneConstraint`** - validates Greek telephone number formats.
5. **`@OrderTimestampConstraint`** - validates order timing according to the application's order rules.
6. **`@OrderItemValuesConstraint`** - validates pie quantities from 0 to 100.
7. **`@MessageConstraint`** - validates contact messages between 5 and 100 characters.

## Service Layer

### `AuthService`

Handles authentication-related operations and user registration. Passwords are encoded with BCrypt before being stored.

### `MailService`

Provides reusable email functionality through `JavaMailSender`, including plain-text and HTML emails.

### `EmailTemplates`

Builds the email content used by the application for:

- Account verification.
- Contact form confirmations.
- New order notifications.
- Order confirmations.
- Password reset messages.

### `SMSService`

Provides the application's Twilio-based SMS integration.

### `VisitsMetricsService`

Maintains basic in-memory visit counters for application pages and pies.

## Email & Notifications

Email delivery is implemented through Gmail SMTP using Spring Mail.

The application sends emails for important user and order events, including:

- Account verification.
- Password recovery.
- Contact form confirmation.
- New order notification.
- Order confirmation.

The application also includes a Twilio-based SMS service for notification-related functionality.

## Internationalization

The project includes message property files for English and Greek locales, providing a foundation for localized application messages.

## Traffic Metrics

`VisitsMetricsService` provides basic in-memory visit statistics for pages and pies. The counters are maintained while the application is running and reset when the application restarts.

## Routes / Endpoints

| Method | Path | Controller | Description |
| :--- | :--- | :--- | :--- |
| GET | `/` | `IndexController` | Home page |
| GET | `/pies` | `PiesController` | Pie catalog |
| GET | `/pies/{id}` | `PiesController` | Pie details and ingredients |
| POST | `/pies/{id}` | `PiesController` | Add pie quantity to cart |
| GET / POST | `/buy` | `BuyController` | Cart and checkout |
| GET / POST | `/contact` | `ContactController` | Contact form |
| GET | `/store` | `StoreController` | Store information |
| GET | `/login` | `AuthController` | Login page |
| POST | `/login` | Spring Security | Process login |
| GET | `/do-logout` | `AuthController` | Logout confirmation page |
| GET | `/logout` | Spring Security | Logout |
| GET / POST | `/register` | `AuthController` | User registration |
| GET | `/register/{code}` | `AuthController` | Email verification |
| GET / POST | `/password-reset` | `AuthController` | Password reset request |
| GET | `/password-reset/{code}` | `AuthController` | Password reset code verification |
| POST | `/password-reset2` | `AuthController` | Set new password |
| GET | `/admin` | `AdminController` | Administration panel |
| GET | `/admin/{action}` | `AdminController` | Administration action |
| GET | `/send-sms` | `SendSMSController` | SMS notification endpoint |
| GET | `/attr` | `AttrController` | Application/demo endpoint |
| GET | `/error` | `CustomErrorController` | Custom error page |

## Setup & Execution

[Instructions](https://github.com/nikwilldoit/PitosPies/blob/main/setup-instructions.md)

## Credits

Developed by [Nikolaos Poulopoulos](https://github.com/nikwilldoit).
