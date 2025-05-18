# 🔐 Spring Boot JWT Authentication Project

Questo progetto dimostra come implementare un sistema di **autenticazione basato su JWT** con **Spring Boot 3**, **Java 21**, **Spring Security**, e database **H2 in-memory**.

## 🚀 Funzionalità

* Registrazione utente con ruolo e password hashata (BCrypt)
* Login con generazione di **token JWT**
* Accesso a endpoint protetti tramite `Authorization: Bearer <token>`
* Validazione del token su ogni richiesta tramite filtro personalizzato
* Architettura pulita e modulare
* Validazione dei campi lato backend

---

## 💠 Stack Tecnologico

* Java 21
* Spring Boot 3.4.5
* Spring Security
* JWT (`jjwt` 0.11.5)
* Spring Data JPA
* H2 database
* Maven

---

## 📦 Endpoint REST

| Metodo | Endpoint    | Autenticazione | Descrizione                      |
| ------ | ----------- | -------------- | -------------------------------- |
| POST   | `/register` | ❌ No           | Registra un nuovo utente         |
| POST   | `/login`    | ❌ No           | Autentica l’utente e ritorna JWT |
| GET    | `/me`       | ✅ Sì (JWT)     | Ritorna l’utente autenticato     |

---

## 📄 Esempio JSON per `/register`

```json
{
  "username": "MarioRossi",
  "password": "Password123",
  "role": "USER"
}
```

## 📄 Esempio JSON per `/login`

```json
{
  "username": "MarioRossi",
  "password": "Password123"
}
```

## 📄 Risposta di successo `/login`

```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "MarioRossi",
  "role": "USER"
}
```

---

## 🗭️ JWT Header per accedere a rotte protette

```
Authorization: Bearer <token>
```

---

## ⚙️ Configurazione `application.properties`

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update

jwt.secret=UnaChiaveSuperSegreta
jwt.expiration=86400000
```

---

## 🧠 Concetti chiave implementati

* Pattern MVC completo con `DTO`, `Service`, `Repository`, `Security`, `Controller`
* Validazione campi con `@NotBlank`, `@Pattern`, `@NotNull`
* Sicurezza stateless con `JwtAuthenticationFilter` e `SecurityContext`
* Separazione delle responsabilità per massima chiarezza del codice

---

## 🧪 Come eseguire

1. Clona la repo
2. Importa in IntelliJ/Eclipse come progetto Maven
3. Esegui `AuthJwtApplication`
4. Usa Postman o Curl per testare gli endpoint

---

## 📛 A chi è rivolto

Sviluppatori backend in formazione o junior developer che vogliono comprendere in modo **chiaro e guidato** come funziona JWT Authentication con Spring Security.

---

## 👤 Autore

Progetto sviluppato da **@ianniciellotommaso** come parte di un percorso formativo professionale su Java e Spring Boot.
