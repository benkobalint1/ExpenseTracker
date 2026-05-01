# ExpenseTracker

A personal finance web application for tracking expenses across personal and shared household accounts.

Built as a portfolio project to demonstrate backend development skills with Java and Spring Boot.

## Features

- User registration and JWT-based authentication
- Personal expense tracking with categories
- Shared household accounts visible to all members
- Expense categorisation (Food, Transport, Utilities, Healthcare, Entertainment, Housing)

## Tech Stack

| Layer          | Technology                  |
|----------------|-----------------------------|
| Language       | Java 21                     |
| Framework      | Spring Boot 4               |
| Security       | Spring Security + JWT       |
| Persistence    | Spring Data JPA + Hibernate |
| Database       | PostgreSQL                  |
| Frontend       | Thymeleaf                   |
| Build tool     | Maven                       |
| Containerisation | Docker + Docker Compose   |

## Package Structure

```
com.benkobalint1.expensetracker
├── domain/      JPA entities (User, Account, Expense, Household, Category)
├── repository/  Spring Data JPA interfaces — database access only
├── service/     Business logic — all rules and decisions live here
├── controller/  HTTP layer — thin, delegates to services
├── security/    JWT filter, UserDetailsService, authentication
├── config/      Spring beans and app-wide configuration
├── dto/         Data Transfer Objects — what crosses HTTP boundaries
└── exception/   Custom exceptions and global error handling
```

## Running Locally

*Setup instructions coming soon.*