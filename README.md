# getHired - Hiring Platform Backend

This is the backend for the **getHired** Hiring Platform, built using **Java** and **Spring Boot**. It provides RESTful APIs for job listings, job applications, candidate profiles, and employer accounts.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Steps to Run Locally](#steps-to-run-locally)
- [API Documentation](#api-documentation)
  - [Authentication](#authentication)
  - [Job Listings](#job-listings)
  - [Candidates](#candidates)
  - [Job Applications](#job-applications)
- [Testing](#testing)
- [Docker Setup](#docker-setup)
- [License](#license)
- [Contact](#contact)

---

## Project Overview

The **getHired** backend is designed to streamline the job hiring process by providing employers (HRs) and candidates with a platform to connect and manage job applications. Built with **Spring Boot** and **MySQL**, the backend supports secure authentication, role-based access control, and all essential functionalities needed for a hiring platform.

---

## Features

- **Job Listings**: Employers can create, update, and manage job postings.
- **Candidate Profiles**: Candidates can create, update, and manage their personal profiles and resumes.
- **Job Applications**: Candidates can apply for jobs, and employers can review and manage applications.
- **Authentication**: Secure login and registration using **JWT** for authentication.
- **Role-based Access Control**: Admins, Employers, and Candidates have different permissions:
  - **Admin**: Full access to the system.
  - **Employer**: Can create, update, and delete job listings, and manage job applications.
  - **Candidate**: Can apply for jobs and manage their profile.

---

## Technologies Used

- **Java 17**
- **Spring Boot 2.x**
- **Spring Security** for secure authentication and authorization
- **JPA / Hibernate** for ORM (Object-Relational Mapping)
- **MySQL 8.0+** for database management
- **JWT (JSON Web Token)** for secure user authentication
- **Maven** for build and dependency management
- **Postman** for API testing (optional)

---

## Installation

### Prerequisites

Ensure you have the following installed on your machine:

- **Java 17** or later
- **Maven 3.8+**
- **MySQL 8.0+**
- (Optional) **Docker** for containerization

### Steps to Run Locally

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/gethired-backend.git
   cd gethired-backend
