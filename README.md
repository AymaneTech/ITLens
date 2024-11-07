# Survey IT API Overview

**Survey IT** is a RESTful application built with **Spring Boot** that facilitates the creation, management, and participation in IT-focused surveys. The platform, **ITLens**, enables users to engage with structured surveys and view detailed statistical results of responses.

## 🚀 Features

- **Survey Management**: Create and manage surveys with hierarchical structures (Chapters → Sub-Chapters → Questions).
- **Question Types**: 
  - 📋 **Single Choice Questions** 
  - 📝 **Multiple Choice Questions**
- **Survey Participation**: Collect user responses for active surveys.
- **Results Analysis**: View statistical insights and percentage breakdowns of survey responses.

## 🛠️ Technical Stack

- **Backend**: Spring Boot, Spring Web (REST API), Spring Data JPA, Spring Validation, Spring Context
- **Database**: PostgreSQL
- **Documentation**: Swagger/OpenAPI
- **Testing**: JUnit 5, Mockito
- **Utilities**: Lombok, MapStruct (DTO Mapping), @RestControllerAdvice (Centralized Exception Handling)

## 🧳 Data Model

### Core Entities

#### **Survey**  
- `id`: Integer  
- `title`: String  
- `description`: String  
- `owner`: Owner (reference)

#### **SurveyEdition**  
- `id`: Integer  
- `creationDate`: Date  
- `startDate`: Date  
- `year`: Integer  
- `survey`: Survey (reference)

#### **Subject/Chapter**  
- `id`: Integer  
- `title`: String

#### **SubSubject/SubChapter**  
- `id`: Integer  
- `title`: String  
- `subject`: Subject (reference)

#### **Question**  
- `id`: Integer  
- `text`: String  
- `answerCount`: Integer  
- `type`: QuestionType (SINGLE_CHOICE, MULTIPLE_CHOICE)  
- `subSubject`: SubSubject (reference)

#### **Answer**  
- `id`: Integer  
- `text`: String  
- `selectionCount`: Integer  
- `question`: Question (reference)

#### **Owner**  
- `id`: Integer  
- `name`: String

## 📁 Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com.wora.itlens/
│   │       ├── config/
│   │       ├── controllers/
│   │       ├── exceptions/
│   │       ├── mappers/
│   │       ├── models/
│   │       │   ├── dtos/
│   │       │   ├── entites/
│   │       │   └── enumes/
│   │       ├── repositories/
│   │       ├── services/
│   │       └── ItLensApplication.java
│   └── resources/
│       ├── static/
│       ├── templates/
│       ├── application.properties
│       └── application-test.properties
└── test/
    └── java/
        └── com.wora.itlens/
