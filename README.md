# Semester Project - 2024: Quiz Bank Web Application
**Quizzzyyy webapp**
## Authors

List of individuals who have contributed to the development of Quizzzyyy:
- **Julia Vik Remøy** 
- **Andrea Amundsen** 

## Introduction

At Quizzzyyy, we believe simplicity is the key to fun when it comes to testing your knowledge. Our platform offers a clean, intuitive interface that allows you to effortlessly create and enjoy quizzes on any topic under the sun. Gone are the days of navigating through complicated settings or dealing with cluttered interfaces.

Just a few clicks and you're ready to go – select a quiz, hit play, and immerse yourself in the seamless quizzing experience.

Simplicity is our specialty, making Quizzzyyy the perfect place for quick entertainment and easy learning. Let’s get quizzing!

Get ready to explore, learn, and challenge yourself. It's time to quiz your way to fun!

## Project Overview
This project involves developing a full-stack web application designed to provide a comprehensive and user-friendly platform for creating and taking quizzes. It's aimed at educational, training, or entertainment purposes. The application will leverage a Vue.js frontend, Java and Spring Boot backend, and a MySQL and H2 database.

## Features
- **User Authentication**: Secure registration and login management.
- **Quiz Creation**: Tools for users to create quizzes (questions and answers).
- **Quiz tags** Category and difficulty
- **Search and Filter**: To find quizzes based on various criteria.
- **Scoring**: Automatic scoring.
- **Activity log** Quiz activity.
- **Deleting quiz** Deleting quizzes.

## Technologies Used

- **Frontend**: Vue.js v3.
- **Backend**: Java v17 with Spring Boot v3.
- **Database**: MySQL v8 and H2 (testing).
- **Authentication/Authorization**: JWT with Spring Security.
- **Database Communication**: Spring JDBC and JPA.
- **Testing**: Minimum code coverage of 50%.
- **CI/CD**: Implementation throughout development.
- **Design**: Adherence to OWASP and universal design principles.
- **Session Storage**: For short-lived login sessions.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
To get a local copy up and running, follow these simple steps.

### Prerequisites
Before you begin, ensure you have the following installed:
- Git
- Docker
- npm

### Installation
**Clone the repository**
Start by cloning the repository to your local machine:
git clone (https://github.com/juliavikr/fullstack-project.git)

### Start the Backend

Navigate to the quizbank package:
```bash
cd backend/quizbank
```

Then, start the services using Docker:

```bash
docker-compose up -d
```
After the services are up, run the QuizBankWebApplication class to start the backend server.

```bash
mvn spring-boot:run
```

### Set up the Frontend

Now, move to the quizzzyyy-frontend package:

```bash
cd frontend/quizzzyyy-frontend
```
Install the necessary npm packages:

```bash
npm install
```
After installation, start the development server:

```bash
npm run dev
```
After completing these steps, the Quizzzyyy platform should be running locally on your machine, and you can access the frontend in your web browser.

## Testing

This section explains how to run the tests for this application.

### Prerequisites for Testing

Ensure that all dependencies are installed, as specified in the installation section.

### Running Tests

To run the tests and verify the system, follow these steps:

**Backend Tests**

Navigate to the backend directory where the `pom.xml` file is located, and use the following command to run the backend tests:
```bash
./mvnw test
```
This command will execute all the tests in the backend module and provide a report of the tests passed, failed, and the code coverage.

**Frontend Tests**

Navigate to the frontend directory where the `package.json` file is located, and use the following command to run the frontend tests:
```bash
npm run test:unit
```

**End-to-End Tests**

Cypress e2e tests:
```bash
npx cypress run
```





