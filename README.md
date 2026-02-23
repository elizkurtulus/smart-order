## Smart Order

Smart Order is a Java/Spring Boot microservices system for managing products and orders.  
The project demonstrates a typical cloud‑native architecture with centralized configuration, service discovery, API gateway, BFF, Kafka‑based communication, and PostgreSQL persistence.

### Architecture

The system is composed of several Spring Boot services:

- **config-server**: Spring Cloud Config Server that serves configuration from this Git repository.
- **discovery-server**: Eureka server for service discovery.
- **gateway-server**: Spring Cloud Gateway that routes external traffic to backend services.
- **product-service**: Product domain service exposing product APIs, using PostgreSQL and Kafka consumer.
- **order-service**: Order domain service exposing order APIs, using PostgreSQL and Kafka producer.
- **bff-service**: Backend‑for‑Frontend that talks to downstream services and integrates with Keycloak via OAuth2/OpenID Connect.

### Tech Stack

- **Language**: Java 21
- **Build**: Maven (per‑service `pom.xml`)
- **Frameworks**:
  - Spring Boot 3.x
  - Spring Cloud 2025.x (Config, Eureka, Gateway, Stream)
  - Spring Web / WebFlux
  - Spring Security / OAuth2 Client & Resource Server
  - Spring Data JPA
- **Databases**: PostgreSQL
- **Messaging**: Apache Kafka, Spring Cloud Stream
- **Auth**: Keycloak (OpenID Connect)

### Prerequisites

- **Java 21** installed and on your `PATH`.
- **Maven 3.9+** installed.
- **Docker** and **Docker Compose** installed and running.
- Network ports available (at least): `5432`, `5433`, `8079`, `8585`, `8787`, `9092`, `9094`, plus the service ports defined in `application.yml` / `configurations/`.

### Running the Infrastructure (Docker)

From the project root:

```bash
docker compose up -d
```

This will start:

- `productsv_db` (PostgreSQL for product-service, DB `product_service`)
- `ordersv_db` (PostgreSQL for order-service, DB `order_service`)
- `kafka` (Kafka broker, internal `kafka:9092`, external `localhost:9094`)
- `kafka-ui` (Kafka UI at `http://localhost:8079`)
- `keycloak_db` and `keycloak` (Keycloak at `http://localhost:8585`)

- Each service’s `src/main/resources/application*.yml`.
- The centralized config files under `configurations/`.

### Running the Services Locally

After bringing up Docker infrastructure:
Alternatively, you can build jars first with `mvn clean package` in each service and run them via `java -jar target/<artifact>.jar`.

### Project Structure

```text
smart-order/
  bff-service/           # WebFlux BFF, OAuth2 client
  config-server/         # Spring Cloud Config Server
  discovery-server/      # Eureka server
  gateway-server/        # API gateway
  order-service/         # Order microservice (Kafka producer)
  product-service/       # Product microservice (Kafka consumer)
  configurations/        # Centralized config repo served by config-server
  docker-compose.yml     # Databases, Kafka, Keycloak, Kafka UI
```

### Notes

- This repository serves both as application source code and as the configuration Git backend for `config-server`.
- Any changes under `configurations/` will be picked up by the running services via `config-server` after refresh/restart.

