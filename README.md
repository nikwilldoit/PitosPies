# 🥧 PitosPies — Εφαρμογή Ηλεκτρονικού Καταστήματος Παραδοσιακής Πιτόπιτας

Μια web εφαρμογή **Spring Boot** για online παραγγελίες πιτών από ένα παραδοσιακό αρτοποιείο/πιτσαρία ("PitosPies"). Καλύπτει τον πλήρη κύκλο παραγγελίας (κατάλογος πιτών, καλάθι, checkout), εγγραφή/είσοδο χρηστών με ρόλους (RBAC), custom Bean-Validation κανόνες, αποστολή email, βασικό διαχειριστικό panel και ένα (μη ολοκληρωμένο) module SMS.

> ⚠️ Αυτό το README είναι γραμμένο **βάσει του πραγματικού πηγαίου κώδικα** που ανέβηκε (φάκελος `src/main`), όχι βάσει υποθέσεων. Στο τέλος υπάρχει ειδική ενότητα με όλες τις διορθώσεις σε σχέση με το προηγούμενο κείμενο που μου έστειλες, γιατί περιείχε αρκετές ανακρίβειες (κυρίως γύρω από persistence layer, security rules και i18n).

---

## 📋 Πίνακας Περιεχομένων
1. [Επισκόπηση](#-επισκόπηση)
2. [📸 Στιγμιότυπα Εφαρμογής (Screenshots)](#-στιγμιότυπα-εφαρμογής-screenshots)
3. [Στοίβα Τεχνολογιών (ακριβής)](#-στοίβα-τεχνολογιών-ακριβής)
4. [Δομή Project](#-δομή-project)
5. [Μοντέλο Δεδομένων & Βάση](#-μοντέλο-δεδομένων--βάση)
6. [Ασφάλεια & Authentication](#-ασφάλεια--authentication)
7. [Routes / Endpoints (πλήρης λίστα)](#-routes--endpoints-πλήρης-λίστα)
8. [Custom Validation Engine](#-custom-validation-engine)
9. [Service Layer](#-service-layer)
10. [Email & SMS](#-email--sms)
11. [Internationalization (i18n) — πραγματική κατάσταση](#-internationalization-i18n--πραγματική-κατάσταση)
12. [Μετρήσεις Επισκεψιμότητας](#-μετρήσεις-επισκεψιμότητας)
13. [Ρύθμιση & Εκτέλεση](#-ρύθμιση--εκτέλεση)
14. [Γνωστά Θέματα / Τεχνικό Χρέος](#-γνωστά-θέματα--τεχνικό-χρέος)
15. [Τι διορθώθηκε σε σχέση με το αρχικό README](#-τι-διορθώθηκε-σε-σχέση-με-το-αρχικό-readme)

---

## 🔍 Επισκόπηση

Η **PitosPies** επιτρέπει σε επισκέπτες να δουν τον κατάλογο πιτών, να δουν λεπτομέρειες/υλικά/βραβεία κάθε πίτας, να ελέγξουν αν η περιοχή τους καλύπτεται από delivery, να κάνουν παραγγελία (με ή χωρίς λογαριασμό) και να στείλουν μήνυμα επικοινωνίας. Οι εγγεγραμμένοι χρήστες βλέπουν τις 5 τελευταίες παραγγελίες τους και μπορούν να τις "επαναλάβουν". Υπάρχει επίσης ένα ελάχιστο admin panel για διαγραφή μη επαληθευμένων εγγραφών χρηστών.

Βασικά αρχιτεκτονικά χαρακτηριστικά:
* **Framework:** Spring Boot, Java (χρήση Jakarta EE namespaces `jakarta.*`, άρα Spring Boot 3.x / Java 17+).
* **Layering:** Controller → Service/Repository, με φόρμες (`FormXxx`) ως DTOs εισόδου.
* **Persistence:** **Spring Data JDBC** (όχι JPA/Hibernate — δες παρακάτω).
* **Security:** Spring Security με BCrypt, custom login/logout handlers, remember-me cookie.
* **Views:** Thymeleaf server-side rendering, με SCSS/CSS πηγές στο `static/sass`.
* **Async:** Χρήση `@Async` στα emails (βλ. σημείωση σε "Γνωστά Θέματα" — δεν είναι ενεργοποιημένο σωστά).

---

## 📸 Στιγμιότυπα Εφαρμογής (Screenshots)

> Πρόσθεσε τα screenshots σου μέσα στον φάκελο `docs/screenshots/` (ή όποιο path προτιμάς) και άλλαξε τα paths στις εικόνες παρακάτω ώστε να δείχνουν στα δικά σου αρχεία. Η ονομασία των αρχείων παρακάτω είναι ενδεικτική πρόταση, ώστε να μείνει το README οργανωμένο.

### 🔐 Εγγραφή Χρήστη (Register)
Ροή: `GET /register` → συμπλήρωση στοιχείων (ονοματεπώνυμο, e-mail, τηλέφωνο, username, password) → validation (`FormRegister` + custom validators `@EmailNotExistsConstraint`, `@UsernameNotExistsConstraint`, `@TelephoneConstraint`) → αποστολή email επαλήθευσης → `GET /register/{code}` για ενεργοποίηση λογαριασμού.

| Φόρμα Εγγραφής | Μηνύματα Validation | Email Επαλήθευσης |
| :---: | :---: | :---: |
| ![Φόρμα Εγγραφής](docs/screenshots/register-form.png) | ![Σφάλματα Εγγραφής](docs/screenshots/register-errors.png) | ![Email Επαλήθευσης](docs/screenshots/register-email.png) |

### 🔑 Σύνδεση Χρήστη (Login)
Ροή: `GET /login` → `POST /login` (Spring Security form login) → επιτυχία (`/login?status=success`) ή αποτυχία (`/login?status=wrongCredentials`) → προαιρετικά "remember me" (cookie 6 μηνών).

| Φόρμα Login | Επιτυχής Σύνδεση | Λάθος Στοιχεία |
| :---: | :---: | :---: |
| ![Φόρμα Login](docs/screenshots/login-form.png) | ![Επιτυχής Σύνδεση](docs/screenshots/login-success.png) | ![Λάθος Στοιχεία](docs/screenshots/login-error.png) |

### 🔁 Ξεχάσατε τον Κωδικό; (Forgot / Reset Password)
Ροή δύο βημάτων: `GET/POST /password-reset` (εισαγωγή e-mail → αποστολή email με τυχαίο κωδικό επιβεβαίωσης) → `GET /password-reset/{code}` (έλεγχος εγκυρότητας κωδικού) → `POST /password-reset2` (ορισμός νέου password, με έλεγχο ότι τα δύο πεδία ταιριάζουν μέσω `@AssertTrue`).

| Αίτημα Επαναφοράς | Email με Κωδικό | Ορισμός Νέου Password |
| :---: | :---: | :---: |
| ![Αίτημα Επαναφοράς](docs/screenshots/password-reset-request.png) | ![Email Επαναφοράς](docs/screenshots/password-reset-email.png) | ![Νέο Password](docs/screenshots/password-reset-new.png) |

### 📞 Φόρμα Επικοινωνίας (Contact)
Ροή: `GET /contact` → συμπλήρωση ονοματεπώνυμου, e-mail, τηλεφώνου, μηνύματος (`@MessageConstraint`: 5–100 χαρακτήρες) → `POST /contact` → αποστολή email τόσο στον διαχειριστή όσο και επιβεβαίωσης στον χρήστη.

| Φόρμα Επικοινωνίας | Επιτυχής Αποστολή |
| :---: | :---: |
| ![Φόρμα Επικοινωνίας](docs/screenshots/contact-form.png) | ![Επιβεβαίωση Αποστολής](docs/screenshots/contact-success.png) |

### 🥧 Κατάλογος & Λεπτομέρειες Πίτας (Pies)
Ροή: `GET /pies` (λίστα όλων των πιτών με εικόνα/τιμή) → `GET /pies/{id}` (λεπτομέρειες, υλικά μέσω `findIngredientsOfPie`, βραβεία) → `POST /pies/{id}` (προσθήκη ποσότητας στο καλάθι της session, redirect σε `/buy`).

| Κατάλογος Πιτών | Λεπτομέρειες Πίτας | Προσθήκη στο Καλάθι |
| :---: | :---: | :---: |
| ![Κατάλογος Πιτών](docs/screenshots/pies-list.png) | ![Λεπτομέρειες Πίτας](docs/screenshots/pie-details.png) | ![Προσθήκη στο Καλάθι](docs/screenshots/pie-add-to-cart.png) |

### 🏬 Κατάστημα (Store)
Ροή: `GET /store` — πληροφορίες καταστήματος (π.χ. διεύθυνση, ωράριο, στοιχεία επικοινωνίας του φυσικού καταστήματος).

| Σελίδα Καταστήματος |
| :---: |
| ![Σελίδα Καταστήματος](docs/screenshots/store-page.png) |

### 🛒 Δημιουργία Παραγγελίας (Order / Buy)
Η πιο σημαντική ροή της εφαρμογής. `GET /buy`:
* Δείχνει το τρέχον καλάθι (`SessionData.order`, `Map<pieId, quantity>`).
* Αν ο χρήστης είναι συνδεδεμένος, δείχνει και τις **5 τελευταίες παραγγελίες** του (`OrderRepository.findTopFiveUserOrderIds`) μέσω του DTO `PreviousOrder`, με δυνατότητα "επανάληψης" προηγούμενης παραγγελίας (`?orderId=...`).
* Επιλογή περιοχής παράδοσης (`Area`), στοιχεία παράδοσης, τρόπος πληρωμής, σχόλια.

`POST /buy` (validation μέσω `FormDataOrder`):
* `@AtLeastOneItemInOrderConstraint` — τουλάχιστον μία πίτα στην παραγγελία.
* `@OrderItemValuesConstraint` — ποσότητα 0–100 ανά πίτα.
* `@TelephoneConstraint`, `@Email` — έγκυρα στοιχεία επικοινωνίας.
* Σε επιτυχία: αποστολή email επιβεβαίωσης στον πελάτη (με αναλυτικό HTML πίνακα παραγγελίας) **και** ειδοποίηση στον διαχειριστή, αποθήκευση της `Order` (+ `OrderItem`s) στη βάση.

| Καλάθι / Φόρμα Παραγγελίας | Ιστορικό Προηγούμενων Παραγγελιών | Validation Σφάλματα | Επιτυχής Παραγγελία | Email Επιβεβαίωσης Παραγγελίας |
| :---: | :---: | :---: | :---: | :---: |
| ![Φόρμα Παραγγελίας](docs/screenshots/order-form.png) | ![Προηγούμενες Παραγγελίες](docs/screenshots/order-history.png) | ![Σφάλματα Παραγγελίας](docs/screenshots/order-errors.png) | ![Επιτυχής Παραγγελία](docs/screenshots/order-success.png) | ![Email Παραγγελίας](docs/screenshots/order-email.png) |

---

## 🛠 Στοίβα Τεχνολογιών (ακριβής)

Η λίστα προκύπτει από τα πραγματικά `import` του κώδικα, όχι από γενική περιγραφή:

| Επίπεδο | Τεχνολογία |
| :--- | :--- |
| Γλώσσα | Java (Jakarta EE 9+ namespaces → Spring Boot 3.x) |
| Web / MVC | Spring Web MVC (`@Controller`, `@RequestMapping`) |
| Views | Thymeleaf (`th:*`, fragments, `spring-security-thymeleaf` για CSRF token injection στις φόρμες) |
| **Persistence** | **Spring Data JDBC** (`org.springframework.data.relational.core.mapping.Table`, `@MappedCollection`, `CrudRepository`/`ListCrudRepository`, native `@Query` SQL) — **όχι JPA/Hibernate** |
| Database driver | MySQL Connector/J (`com.mysql.cj.jdbc.Driver`) |
| Security | Spring Security (form login, BCrypt, remember-me, `UserDetailsService`) |
| Validation | Jakarta Bean Validation (`jakarta.validation.*`) + custom constraints |
| Mail | Spring Mail / `JavaMailSender` (SMTP μέσω Gmail) |
| SMS | **Twilio SDK** (`com.twilio.*`) — μόνη υλοποίηση, με hardcoded placeholder credentials, μη λειτουργική ακόμα |
| Boilerplate | Lombok (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) |
| Στατικά assets | SCSS (`static/sass/**`) compiled σε `static/styles/style.css`, plain JS (`static/js/slider.js`) |
| Build | **Δεν συμπεριλήφθηκε `pom.xml`/`build.gradle` στο αρχείο που ανέβηκε** — μόνο ο φάκελος `src/main`. Bλ. ενότητα Ρύθμισης για προτεινόμενο `pom.xml`. |

---

## 📁 Δομή Project

```text
com.nikolas.app/
├── AppApplication.java                      # Entry point. Εξαιρεί ρητά το πακέτο "excluded" από το component scan
├── beans/
│   └── Counter.java                          # Απλός thread-safe (AtomicInteger) μετρητής επισκέψεων
├── components/
│   ├── EmailTemplates.java                   # Χτίζει το περιεχόμενο (text/HTML) των emails
│   └── SessionData.java                      # @SessionScope bean: τρέχων χρήστης + τρέχον καλάθι (Map<pieId, quantity>)
├── config/
│   ├── PasswordEncoderConfig.java             # Bean BCryptPasswordEncoder
│   ├── WebSecurityConfig.java                 # SecurityFilterChain, authorizeHttpRequests, formLogin, logout, rememberMe
│   └── interceptors/
│       ├── CustomLoginSuccessHandler.java     # Μετά το login: φορτώνει User+roles στο SessionData, redirect σε /login?status=success
│       └── CustomLogoutSuccessHandler.java    # Μετά το logout: καθαρίζει SessionData, redirect σε /do-logout?status=logoutSucceeded
├── controllers/
│   ├── AdminController.java                   # GET /admin, GET /admin/{action}
│   ├── AttrController.java                    # GET /attr — demo/πειραματικό endpoint
│   ├── AuthController.java                    # /login, /do-logout, /register(+/{code}), /password-reset(+/{code}), /password-reset2
│   ├── BuyController.java                     # GET/POST /buy — checkout, ιστορικό 5 τελευταίων παραγγελιών
│   ├── ContactController.java                 # GET/POST /contact
│   ├── IndexController.java                   # GET /
│   ├── PiesController.java                    # GET /pies, GET /pies/{id}, POST /pies/{id} (προσθήκη στο καλάθι)
│   ├── SendSMSController.java                 # GET /send-sms — demo, hardcoded/μη λειτουργικό
│   ├── StoreController.java                   # GET /store
│   ├── dtos/
│   │   └── PreviousOrder.java                 # DTO προβολής παλαιότερης παραγγελίας (inner class OrderItem)
│   └── forms/
│       ├── FormDataContact.java
│       ├── FormDataOrder.java
│       ├── FormLogin.java
│       ├── FormPasswordReset.java
│       ├── FormPasswordReset2.java
│       ├── FormRegister.java
│       └── custom_validators/                 # 7 ζεύγη Constraint/Validator (βλ. ενότητα Validation)
├── excluded/
│   └── ExcludedController.java                 # ΔΕΝ φορτώνεται ποτέ (εξαιρείται ρητά στο @ComponentScan) — νεκρός κώδικας/sandbox
├── models/
│   ├── Area.java, Award.java, Ingredient.java, Order.java, OrderItem.java,
│   │   Pie.java, Role.java, User.java          # Spring Data JDBC entities (@Table, @Id, @MappedCollection)
├── repositories/
│   ├── AreaRepository.java, OrderItemRepository.java, OrderRepository.java,
│   │   PieRepository.java, RoleRepository.java, UserRepository.java
├── services/
│   ├── AuthService.java                        # UserDetailsService + register/registerAdmin (BCrypt hashing)
│   ├── MailService.java                        # Text/HTML emails, inline images, attachments (JavaMailSender)
│   ├── SMSService.java                         # Twilio wrapper (μη λειτουργικό, βλ. σχόλια στον κώδικα)
│   └── VisitsMetricsService.java                # Global μετρητές επισκέψεων ανά controller/ανά πίτα
└── settings/
    ├── ConfigBeans.java                        # Bean Counter("totalVisitsCounter")
    ├── CustomErrorController.java               # /error, χειρισμός 404 κ.λπ.
    └── GlobalExceptionHandler.java              # @ControllerAdvice για MethodArgumentTypeMismatchException

resources/
├── application.properties                      # DB + mail config (βλ. προειδοποίηση ασφαλείας παρακάτω)
├── project.properties                          # mail.admin=...
├── db/
│   └── Dump20260815.sql                        # SQL dump — ΜΕΡΙΚΩΣ ασύμβατο με τα entities (βλ. παρακάτω)
├── messages.properties / messages_el_GR.properties / messages_en_US.properties  # υπάρχουν, αλλά ΔΕΝ χρησιμοποιούνται (δες i18n)
├── static/
│   ├── images/            # spanakopita.jpg, manitaropita.jpg, prasopita.jpg, boureki.jpg, logo.png, store1/2.jpg, social icons...
│   ├── sass/               # πηγαία SCSS (abstracts, components, core, forms, layout, messageboxes, sections)
│   ├── styles/style.css    # compiled CSS
│   └── js/slider.js
└── templates/
    ├── index.html, pies.html, pie.html, buy.html, contact.html, store.html,
    │   login.html, logout.html, register.html, password-reset.html, password-reset2.html,
    │   admin.html, attr.html, excluded.html, error.html
    └── fragments/ (head.html, header.html, footer.html, aside.html)
```

---

## 🗄 Μοντέλο Δεδομένων & Βάση

### ⚠️ Σημαντικό: Spring Data JDBC, όχι JPA/Hibernate
Όλα τα entities (`Pie`, `User`, `Order`, `OrderItem`, `Area`, `Award`, `Role`, `Ingredient`) χρησιμοποιούν annotations του **Spring Data JDBC** (`org.springframework.data.relational.core.mapping.Table`, `org.springframework.data.annotation.Id`, `@MappedCollection`), **όχι** `@Entity`/`@ManyToOne` κ.λπ. του JPA. Αυτό σημαίνει:
* Δεν υπάρχει Hibernate, δεν υπάρχει `spring.jpa.*` config, δεν υπάρχει lazy loading/2nd-level cache.
* Οι σχέσεις υλοποιούνται είτε με `@MappedCollection` (π.χ. `Order.orderItem`, `Pie.awards`) είτε με **χειρόγραφα native SQL queries** μέσω `@Query` στα repositories (π.χ. εύρεση ingredients ή ρόλων μέσω JOIN).
* Τα repositories επεκτείνουν `CrudRepository`/`ListCrudRepository` και δηλώνουν custom finder methods (`findUserByUsername`, `findPieById` κ.λπ.) και custom `@Modifying @Query` για inserts/deletes (π.χ. `UserRepository.addRoleToUser`, `deleteUnverifiedUsers`).

### Οντότητες
* **User** — `id, username, password, fullname, email, tel, status, code` + μη-persisted `roles` (`@Transient`, φορτώνεται χειροκίνητα από `UserRepository.findUserRoles`).
* **Role** — `id, name` (π.χ. `USER`, `ADMIN`). Σχέση User↔Role μέσω ενδιάμεσου πίνακα `user_role` (join γίνεται με native SQL, όχι `@ManyToMany`).
* **Pie** — `id, name, price, filename` + `awards` (`@MappedCollection`) + `ingredients` (`@Transient`, γεμίζει με ξεχωριστό query).
* **Award** — `id, name` (σχετίζεται με `pie_id`, `order`).
* **Ingredient** — `id, name` (σχέση many-to-many με `Pie` μέσω `pie_ingredient`, μόνο σε επίπεδο SQL, όχι στο μοντέλο).
* **Order** — στοιχεία παραγγελίας (`fullname, address, email, tel, comments, offer, payment, stamp, areaId, userId`) + `orderItem` (`@MappedCollection`). Έχει βοηθητικό constructor που χτίζει `Order` από `FormDataOrder` + το καλάθι της session.
* **OrderItem** — `orderId, pieId, quantity`.
* **Area** — `id, description` (περιοχές delivery).

### ⚠️ Ασυμφωνία SQL Dump ↔ Κώδικα (κρίσιμο!)
Το `resources/db/Dump20260815.sql` που περιλαμβάνεται:
* **ΔΕΝ περιέχει** τους πίνακες `area`, `order`, `order_item`, `role`, `user_role` που χρειάζεται η εφαρμογή.
* Ο πίνακας `user` που περιέχει έχει στήλες `id, username, password, session` — **δεν ταιριάζει** με το entity `User` (που χρειάζεται και `fullname, email, tel, status, code`).
* Περιέχει και άσχετους πίνακες (`car`, `degree`, `identity`, `person`, `product`) που δεν χρησιμοποιούνται πουθενά στον κώδικα — μοιάζουν με κατάλοιπα από άλλη άσκηση/course project.

**Συμπέρασμα:** το dump όπως είναι **δεν αρκεί** για να τρέξει η εφαρμογή out-of-the-box. Θα χρειαστεί είτε να ζητήσεις/βρεις το σωστό/πλήρες dump, είτε να φτιάξεις χειροκίνητα schema (`area`, `order`, `order_item`, `role`, `user_role`, και διορθωμένο `user`) πριν την εκκίνηση. Αν θέλεις, μπορώ να σου φτιάξω ένα πλήρες συμπληρωματικό SQL script που δημιουργεί τους πίνακες που λείπουν, με βάση ακριβώς τα entities/queries του κώδικα.

---

## 🔒 Ασφάλεια & Authentication

Ρυθμίζεται στο `WebSecurityConfig.java`:

* **Password hashing:** BCrypt (`PasswordEncoderConfig`).
* **Authentication:** custom `UserDetailsService` (`AuthService`), ο οποίος φορτώνει τον χρήστη + τους ρόλους του από τη βάση και τα μετατρέπει σε `ROLE_<name>` authorities.
* **Authorization rules (ακριβώς όπως είναι σήμερα στον κώδικα):**
  ```java
  .authorizeHttpRequests(authorize -> authorize
      .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
      .anyRequest().permitAll()
  )
  ```
  Δηλαδή **μόνο το ακριβές path `/admin`** απαιτεί `ROLE_ADMIN`. Όλα τα άλλα paths — συμπεριλαμβανομένων των `/admin/{action}`, `/send-sms`, `/buy`, `/pies/**`, `/attr` — είναι **δημόσια προσβάσιμα** στο επίπεδο του security filter chain (δεν υπάρχουν ρόλοι/κανόνες `/buy/**`, `/profile/**`, `/orders/**`, `/sms/**` σήμερα· δεν υπάρχουν καν τα αντίστοιχα routes `/profile` ή `/orders`).
* **Login:** `POST /login` (form login), custom success handler που φορτώνει τον χρήστη+ρόλους στο `SessionData` και κάνει redirect σε `/login?status=success`. Σε αποτυχία, redirect σε `/login?status=wrongCredentials`.
* **Logout:** `/logout` (Spring Security default logout URL), custom handler που καθαρίζει το `SessionData` και κάνει redirect σε `/do-logout?status=logoutSucceeded`.
* **Remember-me:** cookie `remember-cookie`, διάρκεια 6 μήνες, με **hardcoded key `"123456"`** — θα έπρεπε να βγει σε environment variable/secret πριν από production χρήση.
* **CSRF:** δεν απενεργοποιείται πουθενά, άρα είναι ενεργό (Spring Security default). Οι φόρμες `login`, `register`, `password-reset*` έχουν ρητό `_csrf` hidden input στο Thymeleaf template· οι φόρμες `buy`, `contact`, `pie` δεν το δηλώνουν ρητά αλλά χρησιμοποιούν `th:action`, οπότε το token εισάγεται αυτόματα από το Thymeleaf Spring Security dialect (`thymeleaf-extras-springsecurity`) εφόσον υπάρχει στο classpath.

---

## 🌐 Routes / Endpoints (πλήρης λίστα)

| Method | Path | Controller | Περιγραφή |
| :--- | :--- | :--- | :--- |
| GET | `/` | IndexController | Αρχική σελίδα |
| GET | `/pies` | PiesController | Κατάλογος πιτών |
| GET | `/pies/{id}` | PiesController | Λεπτομέρειες πίτας + υλικά |
| POST | `/pies/{id}` | PiesController | Προσθήκη ποσότητας στο καλάθι (session), redirect σε `/buy` |
| GET / POST | `/buy` | BuyController | Καλάθι, checkout, ιστορικό 5 τελευταίων παραγγελιών (αν είναι logged-in) |
| GET / POST | `/contact` | ContactController | Φόρμα επικοινωνίας |
| GET | `/store` | StoreController | Πληροφορίες καταστήματος |
| GET | `/login` | AuthController | Φόρμα login |
| POST | `/login` | Spring Security (form login) | Επεξεργασία login |
| GET | `/do-logout` | AuthController | Σελίδα επιβεβαίωσης logout |
| GET | `/logout` | Spring Security | Πραγματικό logout (invalidate session) |
| GET / POST | `/register` | AuthController | Εγγραφή χρήστη + αποστολή email επαλήθευσης |
| GET | `/register/{code}` | AuthController | Επαλήθευση εγγραφής μέσω κωδικού από email |
| GET / POST | `/password-reset` | AuthController | Αίτημα επαναφοράς password (στέλνει email με κωδικό) |
| GET | `/password-reset/{code}` | AuthController | Επιβεβαίωση κωδικού |
| POST | `/password-reset2` | AuthController | Ορισμός νέου password |
| GET | `/admin` | AdminController | Admin panel (**προστατευμένο**, `ROLE_ADMIN`) — δείχνει πλήθος μη επαληθευμένων χρηστών |
| GET | `/admin/{action}` | AdminController | `action=1` → διαγραφή μη επαληθευμένων χρηστών (⚠️ όχι πραγματικά προστατευμένο, βλ. ενότητα ασφάλειας) |
| GET | `/send-sms` | SendSMSController | Demo αποστολή SMS (hardcoded, μη λειτουργικό) |
| GET | `/attr` | AttrController | Πειραματικό/demo endpoint |
| GET | `/error` | CustomErrorController | Custom error page |
| — | `/excluded` | ExcludedController | **Δεν φορτώνεται ποτέ** — εξαιρείται ρητά από το `@ComponentScan` στο `AppApplication` |

---

## 🧪 Custom Validation Engine

Πακέτο: `com.nikolas.app.controllers.forms.custom_validators`. Κάθε constraint έχει annotation + validator:

1. **`@AtLeastOneItemInOrderConstraint`** — τουλάχιστον μία πίτα με ποσότητα > 0 στο `Map<pieId, quantity>` της παραγγελίας.
2. **`@EmailNotExistsConstraint`** — το email δεν υπάρχει ήδη σε άλλον χρήστη (χρησιμοποιείται σε register **και** σε password-reset — στο reset ελέγχει ότι το email *δεν* υπάρχει, το οποίο ταιριάζει με τη λογική "νέο email" στο register, αλλά σε ένα reset flow θα περίμενε κανείς το αντίστροφο έλεγχο· άξιο προσοχής).
3. **`@UsernameNotExistsConstraint`** — μοναδικότητα username στο register.
4. **`@TelephoneConstraint`** — δέχεται κενό string (προαιρετικό πεδίο) ή αριθμό 10 ψηφίων που ξεκινά από `2` (σταθερό) ή `6` (κινητό) — ελληνικό format.
5. **`@OrderTimestampConstraint`** — **σήμερα επιστρέφει πάντα `true`**· η πραγματική λογική περιορισμού ώρας παράδοσης (18:00–22:00) υπάρχει έτοιμη αλλά **σχολιασμένη (commented out)** μέσα στον validator.
6. **`@OrderItemValuesConstraint`** — κάθε ποσότητα πρέπει να είναι 0–100.
7. **`@MessageConstraint`** — μήνυμα επικοινωνίας μήκους 5–100 χαρακτήρων.

---

## ⚙ Service Layer

* **`AuthService`** — υλοποιεί `UserDetailsService`· `registerUser`/`registerAdmin` κάνουν BCrypt encode του password και αποθηκεύουν χρήστη+ρόλο ατομικά (δύο ξεχωριστά statements μέσω `UserRepository.saveWithRole`, όχι σε transaction — βλ. Γνωστά Θέματα).
* **`MailService`** — γενικό wrapper πάνω στο `JavaMailSender`: text email, HTML email, HTML με inline εικόνες, HTML με attachments, και ένα βοηθητικό `sleep()` (demo/test method για async).
* **`EmailTemplates`** — χτίζει το περιεχόμενο των πραγματικών emails που στέλνει η εφαρμογή (επιβεβαίωση επικοινωνίας, νέα παραγγελία προς admin, επιβεβαίωση παραγγελίας προς πελάτη με HTML πίνακα, email ολοκλήρωσης εγγραφής, email επαναφοράς password). Όλα τα κείμενα είναι hardcoded στα Ελληνικά.
* **`SMSService`** — wrapper πάνω στο **Twilio Java SDK**· έχει `ACCOUNT_SID`/`AUTH_TOKEN = "xxx"` (placeholder) και ένα σχόλιο στον κώδικα ("THE SMS PART IS UNDER CONSTRUCTION... TO REDUCE COSTS") που δηλώνει ρητά ότι δεν είναι ολοκληρωμένο.
* **`VisitsMetricsService`** — απλός μετρητής: αυξάνει έναν κοινό `Counter` bean (singleton, thread-safe μέσω `AtomicInteger`) και θέτει `pageVisits` στο μοντέλο. Κάθε controller κρατά επιπλέον το **δικό του** `private int pageVisits` ως instance field του singleton bean — άρα ο αριθμός είναι κοινός για όλους τους χρήστες, όχι per-session. Το `increasePieCounter` χρησιμοποιεί πίνακα σταθερού μεγέθους `int[4]`, δεμένο στο ότι υπάρχουν ακριβώς 4 πίτες.

---

## ✉️ Email & SMS

* **Email:** μέσω Gmail SMTP (`smtp.gmail.com:587`, STARTTLS), configuration στο `application.properties`. Οι μέθοδοι αποστολής email είναι σημειωμένες με `@Async`.
* **SMS:** μόνο μέσω Twilio, μη λειτουργικό λόγω placeholder credentials.

> ⚠️ **Το `@Async` δεν είναι ενεργό.** Δεν βρέθηκε πουθενά στον κώδικα `@EnableAsync` (ούτε στο `AppApplication` ούτε σε κάποιο `@Configuration`). Χωρίς αυτό, το Spring **αγνοεί** τα `@Async` annotations και οι μέθοδοι εκτελούνται συγχρόνως (blocking) στο thread του request. Αν θες πραγματικά ασύγχρονη αποστολή email, πρέπει να προστεθεί `@EnableAsync` σε κάποια `@Configuration` κλάση.

---

## 🌐 Internationalization (i18n) — πραγματική κατάσταση

Υπάρχουν τα αρχεία `messages.properties`, `messages_el_GR.properties`, `messages_en_US.properties`, αλλά:
* Περιέχουν μόνο 3-4 demo κλειδιά (`welcome.short`, `welcome.message`, `user.greeting`, `typeMismatch.java.lang.Integer`) — **όχι** πλήρη μετάφραση του καταστήματος.
* Δεν βρέθηκε κανένας `LocaleResolver`/`LocaleChangeInterceptor` bean πουθενά στον κώδικα.
* Δεν βρέθηκε ούτε ένα `#{...}` (Thymeleaf message expression) σε κανένα template.

**Συμπέρασμα:** το i18n είναι στην πράξη **ανενεργό scaffolding** — τα properties αρχεία υπάρχουν έτοιμα για μελλοντική χρήση, αλλά η εφαρμογή σήμερα δεν αλλάζει γλώσσα δυναμικά. Όλο το ορατό κείμενο στα templates είναι hardcoded απευθείας στα Ελληνικά μέσα στο HTML.

---

## 📊 Μετρήσεις Επισκεψιμότητας

Το `VisitsMetricsService` + το bean `Counter` (`ConfigBeans.totalVisitsCounter`) προσφέρουν έναν πολύ βασικό, in-memory (όχι persisted στη βάση) global μετρητή επισκέψεων ανά σελίδα και ανά πίτα. Μηδενίζεται σε κάθε restart της εφαρμογής — δεν είναι analytics σε επίπεδο βάσης δεδομένων.

---

## 🚀 Ρύθμιση & Εκτέλεση

### Προαπαιτούμενα
* JDK 17+ (λόγω `jakarta.*` namespaces / Spring Boot 3.x).
* MySQL 8.0+ (ή συμβατή MariaDB).
* Maven ή Gradle (δεν περιλαμβάνεται build file στο υλικό που ανέβηκε — δες παρακάτω προτεινόμενο `pom.xml`).

### 1. Βάση δεδομένων
```sql
CREATE DATABASE pitos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
Το `application.properties` δείχνει ήδη σε βάση `pitos`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pitos
spring.datasource.username=pitos
spring.datasource.password=pitos
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
⚠️ Πριν εισάγεις το `Dump20260815.sql`, διάβασε την ενότητα **"Ασυμφωνία SQL Dump ↔ Κώδικα"** παραπάνω — χρειάζεσαι επιπλέον `area`, `order`, `order_item`, `role`, `user_role` και διορθωμένο `user`.

### 2. Ρύθμιση Email
Το `application.properties` που ανέβηκε περιέχει **πραγματικό Gmail App Password σε καθαρό κείμενο**. Αυτό είναι σοβαρό θέμα ασφάλειας:
* **Ανάκλησε/rotate** αυτό το App Password άμεσα από τις ρυθμίσεις του Google λογαριασμού.
* Μην κάνεις commit τέτοιου είδους credentials σε source control.
* Χρησιμοποίησε environment variables ή ένα secrets manager, π.χ.:
```properties
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_APP_PASSWORD}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

### 3. Προτεινόμενο `pom.xml` (δεν υπήρχε στο υλικό — βασισμένο στα imports του κώδικα)
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.thymeleaf.extras</groupId>
        <artifactId>thymeleaf-extras-springsecurity6</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>com.twilio.sdk</groupId>
        <artifactId>twilio</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### 4. Build & εκτέλεση
```bash
mvn clean package -DskipTests
java -jar target/app-0.0.1-SNAPSHOT.jar
```
ή
```bash
mvn spring-boot:run
```
Η εφαρμογή θα είναι διαθέσιμη στο `http://localhost:8080`.

---

## 🩹 Γνωστά Θέματα / Τεχνικό Χρέος

Συγκεντρωτικά, όσα εντοπίστηκαν στον κώδικα και αξίζει να προσέξεις πριν το πας σε production:

1. **Δεν υπάρχει build file** (`pom.xml`/`build.gradle`) στο υλικό που ανέβηκε — μόνο `src/main`.
2. **SQL dump ασύμβατο** με τα entities (λείπουν πίνακες, ο `user` έχει λάθος στήλες, υπάρχουν άσχετοι πίνακες).
3. **Πραγματικό email password σε καθαρό κείμενο** στο `application.properties` — να ανακληθεί/rotate και να μπει σε env var.
4. **Security rules πολύ χαλαρές**: μόνο το `/admin` προστατεύεται· ακόμα και `/admin/{action}` (διαγραφή χρηστών) είναι στην πράξη δημόσιο.
5. **Remember-me key hardcoded** (`"123456"`).
6. **`@Async` χωρίς `@EnableAsync`** — τα emails στέλνονται συγχρόνως, όχι ασύγχρονα όπως υποδηλώνει ο κώδικας.
7. **`OrderTimestampValidator` απενεργοποιημένος** — δεν υπάρχει πραγματικός έλεγχος ωραρίου παράδοσης παρότι η φόρμα δηλώνει μήνυμα σφάλματος γι' αυτό.
8. **SMS module μη λειτουργικό** (placeholder Twilio credentials, hardcoded τηλέφωνα, sample μήνυμα).
9. **i18n scaffolding χωρίς σύνδεση** — τα properties αρχεία δεν χρησιμοποιούνται πουθενά στα templates.
10. **`VisitsMetricsService`** δίνει global (όχι per-user) μετρητές, μη persisted, και `increasePieCounter` έχει hardcoded μέγεθος πίνακα (4) — δεν κλιμακώνεται αν προστεθούν άλλες πίτες.
11. **`AuthService.registerUser`/`registerAdmin`** κάνουν save χρήστη + insert ρόλου σε δύο ξεχωριστά statements χωρίς ρητό `@Transactional` — σε αποτυχία στο δεύτερο βήμα μπορεί να μείνει χρήστης χωρίς ρόλο.
12. **Password reset codes**: απλοί τυχαίοι ακέραιοι 1000–1999 (`Random`), όχι κρυπτογραφικά ασφαλή tokens — μικρός χώρος τιμών, θεωρητικά brute-forceable.
13. **`ExcludedController`** είναι ρητά εκτός component scan — νεκρός κώδικας, το route `/excluded` δεν λειτουργεί ποτέ όσο κι αν υπάρχει template `excluded.html`.

---

## 📝 Credits
Developed by **Nikolas Poulopoulos**.
