# 📦 Product Inventory REST API

A **production-style backend REST API** built using **Spring Boot and MySQL** to manage products and categories.  
This project was developed as part of a **10-day backend development challenge**, focusing on **real-world backend practices** rather than just basic CRUD operations.

The goal of this project is to demonstrate how scalable, clean, and maintainable backend APIs are designed in real applications.

---

## 🚀 Key Features

- RESTful APIs for managing **Products** and **Categories**
- Clean layered architecture (Controller → Service → Repository)
- DTO-based request and response handling (no entity exposure)
- Global exception handling with proper HTTP status codes
- Input validation using Bean Validation (`@NotBlank`, `@NotNull`, `@Positive`, etc.)
- JPA relationship between Product and Category
- Pagination and sorting for scalable data retrieval
- ModelMapper integration to reduce boilerplate mapping code
- Dockerized application with MySQL container
- Easy environment setup using Docker Compose
- Clean Git workflow with feature branches and meaningful commits
- Stability-first engineering decisions

---

## 🛠 Tech Stack

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate / JPA**
- **MySQL**
- **Docker and Docker Compose**
- **Maven**
- **ModelMapper**
- **Bean Validation (Jakarta Validation)**

---

## 🧱 Project Architecture

```text
controller  →  service  →  repository
     ↓            ↓           ↓
    DTOs       Business     Database
               Logic
```

---

## 🐳 Docker Setup 
Run the entire application (API + MySQL) using Docker.
### ✅ Prerequisites

- Docker installed
- Docker Compose installed

### ▶️ Run with Docker
```bash
git clone https://github.com/sathishsegu/product-inventory-api.git
cd product-inventory-api

docker compose up --build
```
### This will:

- Build the Spring Boot image
- Start MySQL container
- Connect API to database automatically

### 🛑 Stop Containers
```bash
docker compose down
```
---
## 💻 Run Without Docker (Local Setup)

1. Clone the Repository
   ```Bash
   git clone https://github.com/sathishsegu/product-inventory-api.git
   ```
2. Configure MySQL credentials in application.properties
3. Create database manually
4. Run the application
```Bash
mvn spring-boot:run
```
5. Test APIs using Postman 
---

## 📘 API Overview
### ➕ Create Product
**POST** `/api/products`

Request:
```json
{
    "name":"Vivo X300 Pro",
    "price":"110000",
    "quantity":"5",
    "categoryId":"1" 
}
```
Response:
 - 201 CREATED
```json
{
    "categoryName": "Mobiles",
    "createdAt": "2026-02-08T14:44:33.5438347",
    "id": 6,
    "name": "Vivo X300 Pro",
    "price": 110000.0,
    "quantity": 5
}
```
### 📄 Get All Products (Pagination & Sorting)
**GET** `/api/products`

Query Params:
  - page (default: 0)
  - size (default: 5)
  - sortBy (default: name)
  - direction (asc / desc)

*Example:*
  GET `/api/products?page=0&size=5&sortBy=name&direction=asc`

Response:
 - 200 OK
```json
{
  "content": [
    {
      "productName": "iPhone 15",
      "categoryName": "Electronics"
    }
  ],
  "pageNumber": 0,
  "pageSize": 5,
  "totalElements": 1,
  "totalPages": 1,
  "lastPage": true
}
```
Additional APIs implemented:
- Get product by ID
- Update product
- Delete product
- Category CRUD APIs

---

## ⚠️ Exception Handling
 - Centralized exception handling using `@ControllerAdvice`
 - Proper HTTP status codes:
     - `400 BAD REQUEST`
     - `404 NOT FOUND`
     - `201 CREATED`
     - `204 NO CONTENT`
 - This keeps controllers clean and APIs consistent.

---

## 🧪 Testing
 - APIs tested using Postman
 - Both success and failure scenarios verified
 - Pagination, sorting, and validation thoroughly tested
 - Docker environment tested

--- 

## 🚧 Tooling Note (Swagger)
 - Swagger / OpenAPI integration was evaluated but intentionally skipped due to
compatibility issues with the latest Spring Framework version.
 - Application stability was prioritized over forcing a tool.
 - Swagger can be easily added once ecosystem support stabilizes.

---

## 📈 Learning Outcomes
Through this project, I gained hands-on experience in:
 - Designing production-ready REST APIs
 - Applying clean architecture principles
 - Containerizing applications with Docker
 - Managing services with Docker Compose
 - Handling validation and errors professionally
 - Writing scalable APIs with pagination
 - Using Git in a real project workflow
 - Making practical engineering trade-offs

---

## 👨‍💻 Author
**Sathish Kumar Segu**

**Aspiring Java Backend Developer**

📌 Open to backend developer opportunities
