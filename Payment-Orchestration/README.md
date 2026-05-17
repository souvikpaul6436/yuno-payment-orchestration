# Payment Orchestration System

## Overview

This project implements a simplified payment orchestration system inspired by Yuno.

Features:
- Create Payment API
- Fetch Payment API
- Routing Engine
- Retry & Failover
- Idempotency
- Payment Status Tracking
- PostgreSQL Persistence
- Redis Idempotency Store

---

## Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- Redis
- Docker
- Gradle

---

## Architecture

Client
→ Controller Layer
→ Service Layer
→ Routing Engine
→ Provider Connector
→ PostgreSQL + Redis

---

## Routing Logic

- CARD → Provider-A
- UPI → Provider-B

---

## APIs

### Create Payment

POST /payments

### Fetch Payment

GET /payments/{id}

---

## Idempotency

Duplicate requests are prevented using:
Idempotency-Key header

---

## Retry & Failover

If provider processing fails:
- Retry logic executes
- Failover provider is triggered

---

## Running Project

### Start Docker

docker compose up -d

### Run Application

gradlew.bat bootRun

---

## Swagger

http://localhost:8080/swagger-ui/index.html