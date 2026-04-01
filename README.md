# ⚡ Currency Converter

> A full-stack currency conversion app built with **Java Spring Boot** and live exchange rates from the **Frankfurter API**.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=flat-square&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?style=flat-square&logo=apachemaven)
![Railway](https://img.shields.io/badge/Deployed-Railway-0B0D0E?style=flat-square&logo=railway)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

**🌐 Live Demo → [currency-converter-production-7b51.up.railway.app](https://currency-converter-production-7b51.up.railway.app/)**

---

## 📸 Preview

> A clean, responsive UI served directly from Spring Boot static resources — no separate frontend server needed.

---

## ✨ Features

- 💱 Real-time currency conversion using live mid-market exchange rates
- 🌍 REST API built with Spring Boot
- ✅ Input validation with meaningful error messages
- 🛡️ Global exception handling
- 🧪 Unit tests (service layer) and controller tests (MockMvc)
- 🚀 Deployment-ready for Railway and Render

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot |
| Build Tool | Maven |
| Frontend | HTML, CSS, JavaScript |
| Testing | JUnit 5, MockMvc |
| External API | [Frankfurter API](https://www.frankfurter.app/) |
| Deployment | Railway |

---

## 📁 Project Structure

```
src
├── main
│   ├── java/com/syed/currencyconverter
│   │   ├── client/           # Frankfurter API HTTP client
│   │   ├── config/           # App configuration
│   │   ├── controller/       # REST controllers
│   │   ├── dto/              # Request/Response DTOs
│   │   ├── exception/        # Global exception handler
│   │   ├── service/          # Business logic
│   │   └── CurrencyconverterApplication.java
│   └── resources
│       ├── static/
│       │   ├── index.html
│       │   ├── style.css
│       │   └── script.js
│       └── application.properties
└── test
    └── java/com/syed/currencyconverter
        ├── service/
        │   ├── CurrencyServiceTest.java
        │   └── ExchangeRateApiServiceTest.java
        ├── CurrencyControllerTest.java
        └── CurrencyconverterApplicationTests.java
```

---

## 🔌 API Reference

### Health Check

```
GET /api/health
```

**Response**
```
Currency Converter API is running
```

---

### Convert Currency

```
GET /api/convert?amount=100&from=USD&to=EUR
```

| Parameter | Type | Required | Description |
|---|---|---|---|
| `amount` | `number` | ✅ | Amount to convert (must be > 0) |
| `from` | `string` | ✅ | Source currency code (e.g. `USD`) |
| `to` | `string` | ✅ | Target currency code (e.g. `EUR`) |

**Success Response `200 OK`**
```json
{
  "amount": 100.0,
  "from": "USD",
  "to": "EUR",
  "rate": 0.92,
  "convertedAmount": 92.0
}
```

**Error Response `400 Bad Request`**
```json
{
  "message": "Amount must be greater than zero",
  "status": 400
}
```

---

## ⚠️ Validation Rules

The API rejects requests that are invalid. Examples of handled error cases:

- Amount is missing or zero or negative
- Currency code is missing or malformed
- Source and target currency are the same
- External Frankfurter API is unavailable

---

## 🚀 Running Locally

### Prerequisites

- Java 21+
- Git

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/syxd24/currency-converter.git
cd currencyconverter

# 2. Run the application
.\mvnw spring-boot:run        # Windows
./mvnw spring-boot:run        # macOS / Linux
```

The app starts on **http://localhost:9090**

The frontend UI is available at **http://localhost:9090/**

---

## 🧪 Running Tests

```bash
.\mvnw test        # Windows
./mvnw test        # macOS / Linux
```

---

## 📦 Building the JAR

```bash
.\mvnw clean package        # Windows
./mvnw clean package        # macOS / Linux
```

Output: `target/currencyconverter-*.jar`

---

## ☁️ Deployment

This project is deployed on **Railway**. The port is configured to use the platform-provided `$PORT` environment variable with a local fallback:

```properties
# application.properties
server.port=${PORT:9090}
```

To deploy your own instance, push to a connected GitHub repository on Railway or Render — no additional configuration needed.

---

## 🗺️ Roadmap

- [ ] Add more supported currencies dynamically
- [ ] Historical exchange rate chart
- [ ] Improve result formatting and rounding
- [ ] Add integration tests
- [ ] PWA support (offline mode)

---

## 🎯 Learning Goals

This project was built to practice real backend and full-stack development:

- Building REST APIs with Spring Boot
- Structuring code with controllers, services, DTOs, and exceptions
- Consuming third-party APIs (Frankfurter)
- Writing unit and controller tests with JUnit & MockMvc
- Connecting a frontend and backend in one Spring Boot app
- Deploying a production-ready Java application to the cloud

---

## 👤 Author

**Syed** · [@syxd24](https://github.com/syxd24)

---

*Built with ☕ Java and Spring Boot*
