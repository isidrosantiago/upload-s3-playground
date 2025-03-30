<h1 align="center">Upload S3 Playground</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot Logo">
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="Postgresql Logo">
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://img.shields.io/badge/Amazon%20S3-FF9900?logo=amazonaws&logoColor=white&style=for-the-badge" alt="AWS S3">
</p>
<p align="center">
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker Logo">
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://img.shields.io/badge/Swagger-89bf04?style=for-the-badge&logo=swagger&logoColor=white" alt="Swagger Logo">
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://img.shields.io/badge/Angular-DD0031?logo=angular&logoColor=white&style=for-the-badge&logoColor=white" alt="Angular Logo">
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://img.shields.io/badge/PrimeNG-0078D6?logo=primeng&logoColor=white&style=for-the-badge&logoColor=white" alt="Primeng Logo">
</p>

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [API Documentation](#api-documentation)
4. [Installation](#installation)

## Overview

This repository, **Upload S3 Playground**, is a **learning project** designed to explore and demonstrate the integration of spring boot and S3. It is built with **Angular 18**, **PrimeNG**, **Spring Boot**, **PostgreSQL**, and **Amazon S3**. The application allows users to upload files, manage them via pagination, and store them securely in an S3 bucket.

---

### Key Learning Objectives:

- Building a RESTful API with **Spring Boot**.
- Integrating **Amazon S3** for file storage.
- Using **Angular 18** and **PrimeNG** for a responsive and feature-rich frontend.
- Managing databases with **PostgreSQL** and visualizing them using **pgAdmin**.

## Features

### Frontend (Angular 18 + PrimeNG)

- Built with **Angular 18** for a modern and responsive user interface.
- Utilizes **PrimeNG** components for enhanced UI/UX.
- File upload functionality integrated with the backend API.
- Pagination support for listing files.

### Backend (Spring Boot)

- RESTful API built with **Spring Boot**.
- File upload endpoint that supports `multipart/form-data`.
- Pagination support for retrieving files (`GET /file`).
- Integration with **Amazon S3** for secure file storage.
- Database operations handled with **PostgreSQL**.

### Database

- **PostgreSQL** as the primary database.
- Dockerized setup for PostgreSQL and **pgAdmin** for easy database management.

## API Documentation

### File Management

- **Upload File**: Upload a file to the system using `multipart/form-data`.
- **Retrieve Files**: Fetch a paginated list of all uploaded files. Pagination can be controlled using `page` and `size` query parameters.

---

### API Endpoints

#### File Operations

- `POST /file`: Upload a new file to the system.
- `GET /file`: Retrieve a paginated list of all uploaded files.
  - Query Parameters:
    - `page` (optional): Page number (default: 0).
    - `size` (optional): Number of items per page (default: 10).

## Installation

### Prerequisites

- **Node.js**
- **Angular CLI** (v18.2.16)
- **Java 17**
- **Docker** and **Docker Compose**

### Steps

#### 1. Clone the Repository

```bash
git clone https://github.com/your-username/upload-s3-playground.git
cd upload-s3-playground
```

#### 2. Docker Setup (PostgreSQL + pgAdmin)

1. Navigate to the `docker` directory:
   ```bash
   cd backend
   ```
2. Start the Docker containers:
   ```bash
   docker-compose up -d
   ```
3. Access pgAdmin at `http://localhost:5050` and configure the PostgreSQL connection.

#### 3. Backend Setup

1. Navigate to the backend directory:

   ```bash
   cd backend
   ```

2. Create a copy of the example configuration file:
   The repository includes an example configuration file named `application-dev.example.yml`. You need to create a copy of this file and rename it to `application-dev.yml` before running the application.

   Run the following command to create the copy:

   ```bash
   cp src/main/resources/application-dev.example.yml src/main/resources/application-dev.yml
   ```

3. Configure the `application-dev.yml` file:
   Open the newly created `application-dev.yml` file in your preferred text editor and update the following fields with your own credentials and configurations

   > **Note**: Ensure that your AWS credentials have the necessary permissions to access the S3 bucket. For local development.

4. Build and run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

#### 4. Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Angular development server:
   ```bash
   ng serve
   ```
