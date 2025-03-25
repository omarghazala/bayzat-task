# Bayz Delivery Application Documentation

## Overview

Bayz Delivery is a Spring Boot application that manages delivery operations with customers and delivery personnel. This document provides steps for setting up, building, and running the application.

## Prerequisites

- Java 17
- Gradle
- Docker and Docker Compose
- PostgreSQL database

## Setup and Installation

### 1. Database Setup with Docker Compose

Create a `docker-compose.yml` file:

Start the containers:

```bash
docker compose up -d
```

### 2. Building the Application

```bash
docker build -t bayzdelivery:latest .
```

### 3. Running the Application

#### Option 1: Running locally

```bash
java -jar build/libs/bayzdelivery-0.0.1-SNAPSHOT.jar
```

#### Option 2: Running with Docker

Create a Docker network:

```bash
docker network create bayz-network
```

Connect PostgreSQL to the network:

```bash
docker network connect bayz-network bayzdelivery_db
```

Run the application container:

```bash
docker run -d -p 8081:8081 --name bayzdelivery-app \
  --network bayz-network \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://bayzdelivery_db:5432/bayzdelivery \
  -e SPRING_DATASOURCE_USERNAME=db_user \
  -e SPRING_DATASOURCE_PASSWORD=123qwe \
  bayzdelivery:latest
```

### Check Application Logs

```bash
docker logs -f bayzdelivery-app
```

### Access Swagger 

Navigate to http://localhost:8081/api/swagger-ui/index.html#/

### Access PostgreSQL Database

```bash
docker exec -it bayzdelivery_db psql -U db_user -d bayzdelivery
```

### Access PgAdmin

Navigate to http://localhost:8080 in your browser and log in with:
- Email: oghazala@bayzat.com
- Password: toolS585

To connect to the database in pgAdmin:
- Host: bayzdelivery_db
- Port: 5432
- Database: bayzdelivery
- Username: db_user
- Password: 123qwe