# DevFix Backend

This is the backend API for **DevFix**, a smart setup-tutorial platform that delivers fast installation guides, setup solutions, and Gemini AI-powered help.

Built with **Spring Boot**, it supports **JWT-based authentication**, **role-based access control**, video+solution search, and setup content management for admins.

---

## 🚀 Features

- ✅ JWT Authentication & Authorization
- ✅ Role-based access (USER / ADMIN)
- ✅ Admin CRUD (Create, Read, Update, Delete) for setup data
- ✅ Search endpoint for setup/issue keywords
- ✅ Gemini AI text query endpoint
- ✅ Error handling with custom exceptions
- ✅ Render deployment ready (`render.yaml` + `mvnw`)

---

## 🛠️ Tech Stack

- ☕ Java 17
- 🌱 Spring Boot
- 🔐 Spring Security (JWT)
- 🗃️ H2 / PostgreSQL (production-ready)
- 📦 Maven
- 🧠 Gemini AI integration (via REST)

---

## 📁 Project Structure
```bash
DevFix-backend/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/devfix/...
│ │ └── resources/
│ │ ├── application.properties
│ │ └── ...
├── pom.xml
├── render.yaml
├── mvnw / mvnw.cmd
└── README.md
```

---

## ⚙️ API Endpoints

| Endpoint              | Method | Access     | Description                          |
|----------------------|--------|------------|--------------------------------------|
| `/api/auth/register` | POST   | Public     | Register user                        |
| `/api/auth/login`    | POST   | Public     | Login user (returns JWT)             |
| `/api/setup`         | GET    | USER/ADMIN | Fetch all setup entries              |
| `/api/setup/{id}`    | PUT    | ADMIN      | Update setup                         |
| `/api/setup/{id}`    | DELETE | ADMIN      | Delete setup                         |
| `/api/setup`         | POST   | ADMIN      | Add new setup                        |
| `/api/setup/search`  | GET    | USER/ADMIN | Search setups by keyword             |
| `/api/gemini`        | POST   | USER/ADMIN | Ask Gemini AI (text prompt)          |

---

## 🧪 Running Locally

```bash
# Clone the repo
git clone https://github.com/rohanmandal341/DevFix-backend.git
cd DevFix-backend

# Make Maven wrapper executable (Linux/macOS)
chmod +x mvnw

# Run the app
./mvnw spring-boot:run
```
### 🌍 Deployment
This backend is preconfigured for deployment on Render:

✅ render.yaml for auto-deploy

✅ Maven wrapper files (mvnw, mvnw.cmd)

✅ Spring Boot port setup & JVM compatibility

---
## 📄 License
MIT License
---
## 👤 Author
Rohan Mandal
GitHub: @rohanmandal341
---

