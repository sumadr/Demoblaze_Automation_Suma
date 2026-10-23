DemoBlaze UI & Restful-Booker API Automation Framework
Overview
This repository contains an end-to-end automation framework developed as part of a technical assessment.

The framework covers:

UI Automation for DemoBlaze Product Store
API Automation for Restful Booker
TestNG Test Execution
Page Object Model (POM)
Reusable Utilities
Configuration Management
Reporting and Logging
Technology Stack
Java 27
Selenium WebDriver
TestNG
Maven
Rest Assured
WebDriverManager
Jackson
Log4j
Extent Reports
Applications Under Test
UI Application
https://www.demoblaze.com

API Application
https://restful-booker.herokuapp.com

Framework Design
UI Automation
Implemented using:

Page Object Model (POM)
Explicit Waits
Reusable Components
Centralized Configuration
Covered Scenarios:

User Login
User Registration
Product Navigation
Add Product to Cart
Remove Product from Cart
Place Order
End-to-End Purchase Flow
API Automation
Covered Scenarios:

Authentication
Create Booking
Retrieve Booking
Update Booking
Delete Booking
Booking CRUD Workflow
Negative Test Cases

roject Structure
src ├── main │ └── java │ ├── pages │ ├── api │ ├── utils │ ├── factory │ └── constants │ ├── test │ └── java │ ├── ui │ └── api │ ├── resources │ ├── config.properties │ └── testdata

Execution
Run all tests:

mvn clean test
Run UI tests only:

mvn test -Dgroups=ui
Run API tests only:

mvn test -Dgroups=api
Configuration
All environment-specific values are maintained in:

src/main/resources/config.properties
Examples:

Base URLs
Browser Configuration
API Endpoints
Credentials
Reporting
Reports generated under:

test-output/
Assumptions
Stable internet connection available.
DemoBlaze test data is publicly accessible.
Restful Booker environment is available for CRUD operations.

Author
Suma D R Senior QA Automation Engineer
