# TechCart Ecommerce API

TechCart is a RESTful e-commerce backend built with Spring Boot. The current version provides product management, product-image uploads, keyword search, and consistent handling for missing resources.

> **Project status:** actively under development. This README is maintained as features are added.

## Features

- Create, read, update, and delete products
- Upload and retrieve product images
- Search products by name, description, brand, or category
- Persist data with Spring Data JPA and PostgreSQL
- Return a structured `404 Not Found` response when a product does not exist
- Interactive API documentation through Swagger UI

## Tech stack

| Area | Technology |
| --- | --- |
| Language | Java 21 |
| Framework | Spring Boot |
| Web/API | Spring MVC |
| Database access | Spring Data JPA / Hibernate |
| Database | PostgreSQL |
| API documentation | Springdoc OpenAPI (Swagger UI) |
| Build tool | Maven |

## Project structure

```text
TechCart-Ecommerce/
├── Backend/
│   ├── src/main/java/com/abdallah/TechCart_Ecommerce/
│   │   ├── controller/    # REST endpoints
│   │   ├── service/       # Business logic
│   │   ├── repo/          # Database repositories
│   │   ├── model/         # JPA entities
│   │   └── exception/     # Error handling
│   └── src/main/resources/
│       └── application.properties
├── Frontend/              # Reserved for the future client application
└── README.md
```

## Prerequisites

- JDK 21
- Maven 3.9+ (or a working Maven Wrapper)
- PostgreSQL

## Getting started

### 1. Create the database

Create a PostgreSQL database named `TechCart-Ecommerce`:

```sql
CREATE DATABASE "TechCart-Ecommerce";
```

### 2. Configure the database connection

In `Backend/src/main/resources/application.properties`, set your local database connection values:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/TechCart-Ecommerce
spring.datasource.username=YOUR_POSTGRES_USERNAME
spring.datasource.password=YOUR_POSTGRES_PASSWORD
```

Do not commit real passwords. For production, use environment variables or a secrets manager.

### 3. Run the backend

```bash
cd Backend
mvn spring-boot:run
```

The API starts on `http://localhost:8080` by default.

## API endpoints

Base URL: `/api/products`

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/products` | Get all products |
| `GET` | `/api/products/{id}` | Get one product by ID |
| `GET` | `/api/products/{id}/image` | Get a product image |
| `GET` | `/api/products/search?keyword={keyword}` | Search products |
| `POST` | `/api/products` | Create a product with an image |
| `PUT` | `/api/products/{id}` | Update a product |
| `DELETE` | `/api/products/{id}` | Delete a product |

## Create or update a product

The create and update endpoints accept `multipart/form-data` with:

- `product`: product data as JSON
- `imageFile`: the product image file

Example `product` JSON:

```json
{
  "name": "Wireless Headphones",
  "description": "Over-ear Bluetooth headphones",
  "brand": "TechCart",
  "price": 99.99,
  "category": "Electronics",
  "releaseDate": "20-09-2026",
  "productAvailable": true,
  "stockQuantity": 25
}
```

## API documentation

When the application is running, open Swagger UI to explore and test the endpoints:

`http://localhost:8080/swagger-ui/index.html`

## Error response example

When a product is not found, the API returns:

```json
{
  "status": 404,
  "message": "Product not found with id: 999",
  "timestamp": "2026-09-21T12:00:00"
}
```

## Roadmap

- [x] Product CRUD
- [x] Product image upload and retrieval
- [x] Product keyword search
- [x] Request validation and product DTOs
- [ ] Pagination, sorting, and product filters
- [ ] Authentication and role-based authorization
- [ ] Shopping cart and wishlist
- [ ] Orders, payment integration, and addresses
- [ ] Product categories and reviews
- [ ] Frontend application
- [ ] Automated unit and integration tests
- [ ] Production-ready database migrations and deployment configuration

## Contributing / updating this README

Update this document whenever a feature is completed. In particular:

1. Move its item in **Roadmap** from `[ ]` to `[x]`.
2. Add or update its endpoints in **API endpoints**.
3. Document new setup variables, services, or commands in **Getting started**.
4. Add screenshots, a frontend demo link, or deployment URL once available.

## License

This project is currently for learning and portfolio purposes. Add a license here before distributing or accepting external contributions.
