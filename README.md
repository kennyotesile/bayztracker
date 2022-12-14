## Scenario
BayzTracker is a cryptocurrency tracker app which allows its users to create alerts to be notified when a price of a coin reaches the price user determines.

User can create multiple alerts and can track the alert status anytime (triggered or waiting).

There is also currency list page where all coins with their current prices are listed.

The admin user also manages the currencies that will be listed on the app.

## Tech stack
- Spring Boot
- Java
- Spring Data JPA
- Hibernate
- MySQL
- Docker
- Docker Compose

## General Application Constraints
- Users are using BayzTracker mobile app, assume that the API is only consumed by mobile
- Data is only accepted from the registered users with their ownership rights.
- There are two types of users: Admin and User.
    - Both user types can create alerts.
    - Both users can query currencies.
    - Only admin can manage the currency types in the system.

## Features
1. API endpoints for managing the CRUD operations for the Currency types
    - Currency Entity: Some properties may be `name`, `symbol`, `currentPrice`, `createdTime`, `enabled` etc.
    - If a request arrives to create one of the coins below then `UnsupportedCurrencyCreationException` is thrown. Unsupported coins: [ ETH, LTC, ZKN, MRD, LPR, GBZ ]
    - Admin user can add/remove currencies
    - All users can query currencies
2. API endpoints for maintaining the CRUD operations for alerts.
    - Alert Entity: `currency`, `targetPrice`, `createdAt`, `status(NEW, TRIGGERED, ACKED, CANCELLED)`
    - The status of the alert
        - NEW if the price is not in the target price
        - TRIGGERRED if the pice is reached
        - ACKED if the user closes the alert
        - CANCELLED if the user cancels the alert
    - User can create/edit/delete the alerts
    - User can cancel the alert if it is not triggered yet
    - User can acknowledge the alert when he is notified. (The target price was reached)
3. A ScheduleTask class that checks the alerts and notifies the users if the target price is reached
    - A simple log on console for notification. Email or push notification is not implemented.
    - ScheduledTask runs every 30 seconds.
