# TrackHire API

A Spring Boot REST API for tracking and managing job applications.

TrackHire was originally built as a Core Java console application. This version rebuilds the project using modern backend development practices, including RESTful APIs, Spring Boot, database persistence, and layered architecture.

---

## Overview

Job searching can quickly become difficult to manage when applications are spread across emails, spreadsheets, and multiple job portals.

TrackHire helps users:

* Track job applications in one place
* Monitor application status
* Search and filter applications
* Analyze job search progress
* Manage application data through a REST API

---

## Features

### Job Application Management

* Create job applications
* View all applications
* View a specific application
* Update application details
* Delete applications

### Search & Filtering

* Filter by application status
* Search by company name

### Statistics Dashboard

* Total applications
* Applications by status
* Offer rate
* Interview rate

### Data Persistence

* PostgreSQL database
* Spring Data JPA
* Automatic entity mapping

---

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Maven

### Database

* PostgreSQL

### Testing

* JUnit 5
* Mockito

---

## Project Structure

src/main/java

├── controller

├── service

├── repository

├── model

├── dto

├── exception

└── config

---

## Planned API Endpoints

### Job Applications

| Method | Endpoint       | Description           |
| ------ | -------------- | --------------------- |
| GET    | /api/jobs      | Get all applications  |
| GET    | /api/jobs/{id} | Get application by ID |
| POST   | /api/jobs      | Create application    |
| PUT    | /api/jobs/{id} | Update application    |
| DELETE | /api/jobs/{id} | Delete application    |

### Search

| Method | Endpoint                    |
| ------ | --------------------------- |
| GET    | /api/jobs/status/{status}   |
| GET    | /api/jobs/company/{company} |

### Statistics

| Method | Endpoint   |
| ------ | ---------- |
| GET    | /api/stats |

---

## Learning Goals

This project is being built to gain hands-on experience with:

* Spring Boot
* REST API development
* Dependency Injection
* Layered Architecture
* Database Design
* JPA & Hibernate
* Exception Handling
* Validation
* Backend Testing

---

## Roadmap

### Phase 1

* [ ] Spring Boot setup
* [ ] REST controllers
* [ ] CRUD endpoints

### Phase 2

* [ ] PostgreSQL integration
* [ ] Spring Data JPA
* [ ] Entity persistence

### Phase 3

* [ ] Search & filtering endpoints
* [ ] Validation
* [ ] Global exception handling

### Phase 4

* [ ] Statistics dashboard endpoints
* [ ] DTO implementation
* [ ] Unit & integration testing

### Phase 5

* [ ] Authentication & authorization
* [ ] Docker support
* [ ] Deployment

---

## Previous Version

The original TrackHire project was built as a Core Java console application featuring:

* Object-Oriented Programming
* CRUD Operations
* JSON Persistence using Gson
* Search & Filtering
* Statistics Dashboard
* Unit Testing

This repository represents the next stage in the project's evolution toward a production-style backend application.

---

## Author

Akuthi April

Aspiring Java Backend Developer focused on Spring Boot, APIs, and scalable software development.
