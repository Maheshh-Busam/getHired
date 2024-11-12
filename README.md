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

The **getHired** backend is designed to streamline the job hiring process by providing HRs and candidates with a platform to connect and manage job applications. Built with **Spring Boot** and **MySQL**, the backend supports secure authentication, role-based access control, and all essential functionalities needed for a hiring platform.

---

## Features

- **Job Listings**: HRs can create, update, and manage job postings.
- **Candidate Profiles**: Candidates can create, update, and manage their personal profiles and resumes.
- **Job Applications**: Candidates can apply for jobs, and HRs can review and manage applications.
- **Authentication**: Secure login and registration using **JWT** for authentication.
- **Role-based Access Control**: Admins, HRs, and Candidates have different permissions:
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
   cd gethired

2. **Configure MySQL Database**:
   - Create a new database in MySQL (e.g., `gethired_db`).
   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/gethired_db
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     ```

3. **Build the project**:
   - Run the following command to build the project:
     ```bash
     mvn clean install
     ```

4. **Run the application**:
   - Start the backend application with the following command:
     ```bash
     mvn spring-boot:run
     ```

   The backend should now be running on `http://localhost:8080`.

---

# API Documentation

## Authentication

- **POST /auth/register-candidate**  
  Register a new user as a Candidate.

- **POST /auth/register-hr**  
  Register a new user as an HR.

- **POST /auth/login**  
  Log in to the system and receive a JWT token.

---

## Admin

- **GET /admin/all-hr**  
  Retrieve a list of all HR profiles.  
  **Authorization:** Admin only

- **GET /admin/all-candidates**  
  Retrieve a list of all candidate profiles.  
  **Authorization:** Admin only

- **DELETE /admin/delete/{userId}**  
  Delete a user profile by user ID.  
  **Authorization:** Admin only

---

## Users

- **GET /users/all**  
  Retrieve a list of all users.  
  **Authorization:** Admin only

- **GET /users/{id}**  
  Retrieve a specific user profile by user ID.  
  **Authorization:** Candidate or HR only

- **PUT /users/update/{id}**  
  Update a specific user profile by user ID.  
  **Authorization:** Candidate or HR only

- **DELETE /users/delete/{id}**  
  Delete a specific user profile by user ID.  
  **Authorization:** Candidate or HR only

---

## Job Listings

- **POST /jobPost/add**  
  Create a new job listing.  
  **Authorization:** HR only

- **GET /jobPost/all**  
  Retrieve a list of all job postings.  
  **Authorization:** Candidate or HR

- **GET /jobPost/{id}**  
  Retrieve details of a specific job posting by job ID.  
  **Authorization:** Candidate or HR

- **PUT /jobPost/update**  
  Update an existing job listing.  
  **Authorization:** HR only

- **DELETE /jobPost/delete/{id}**  
  Delete a job listing by job ID.  
  **Authorization:** HR only

---

## Job Applications

- **POST /jobApplication/apply**  
  Apply for a job.  
  **Authorization:** Candidate only

- **GET /jobApplication/all/{jobId}**  
  Retrieve all job applications for a specific job by job ID.  
  **Authorization:** HR who posted the job only

- **GET /jobApplication/{id}**  
  Retrieve a specific job application by application ID.  
  **Authorization:** HR who posted the job or Candidate who applied only

- **GET /jobApplication/my-job-applications/{candidateId}**  
  Retrieve a list of jobs applied to by a specific candidate.  
  **Authorization:** Candidate only

- **PUT /jobApplication/update-status/{id}**  
  Update the status of a job application.  
  **Authorization:** HR only

- **DELETE /jobApplication/{id}**  
  Withdraw a job application by application ID.  
  **Authorization:** Candidate only

## Testing

You can test the APIs using **Postman** or **Insomnia**. Example Postman collection files are included in the `postman/` directory.


## Contact

Email Id - mahesh.busam2206@gmail.com
