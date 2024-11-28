# Social Media Backend

This is the backend service for a social media web application, built using **Spring Boot**. It provides RESTful APIs for user authentication, managing posts, comments, messaging, and user profiles. The backend ensures secure data handling with **JWT authentication**.

---

## Features

- **User Authentication:**
  - Register and login with JWT token-based security.
  - Logout functionality by invalidating tokens.

- **Post Management:**
  - View all posts.
  - Create, edit, and delete posts.
  - Like and comment on posts.

- **Messaging:**
  - Send and receive messages.
  - View conversation history with other users.

- **Search Users:**
  - Search for users by email or name.

- **User Profile Management:**
  - View and edit your profile.
  - View other users' profiles.
  - Follow/unfollow users.

---

## Tech Stack

- **Backend Framework:** Spring Boot
- **Database:** MySQL
- **Security:** JWT Authentication
- **Other Tools:** Spring Data JPA, Spring Security, Maven

---

## Prerequisites

Before running this project, ensure you have the following installed:

- Java 17 or higher
- Maven 3.8 or higher
- MySQL 8.0 or higher

---

## Installation and Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Viviorigi/social-media-be-springboot.git
   cd social-media-be-springboot

properties
spring.datasource.url=jdbc:mysql://localhost:3306/social_media
spring.datasource.username=your_username
spring.datasource.password=your_password

mvn clean install

mvn spring-boot:run

The application will run at http://localhost:8080.
