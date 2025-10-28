#  Sauce Demo - Selenium Automation Framework

A robust and scalable **UI Automation Testing Framework** built using **Java, Selenium, and TestNG**, following the **Page Object Model (POM)** design pattern.  
This project automates the functional testing of the [SauceDemo](https://www.saucedemo.com) web application with detailed reporting and flexible configuration.

---

##  Features

- Built with **Java + Selenium + TestNG**
- Designed using **Page Object Model (POM)**
- Configurable environment through `config.properties`
- Logs and detailed **Allure Reports**
- Supports **Data-Driven Testing**
- Reusable utilities and driver management
- Cross-browser support (Chrome, Firefox, Edge)
- Easy integration with CI tools (Jenkins, GitHub Actions)

---

##  Project Structure

```
SauceDemo/
│
├── pom.xml # Maven configuration and dependencies
│
├── src/
│ ├── main/
│ │ └── java/
│ │ ├── utilities/ # Utility classes (driver setup, config, etc.)
│ │ │ ├── selenium/driver/ # Driver factories for Chrome, Firefox, etc.
│ │ │ └── common/ # Common utilities and helpers
│ │ └── base/ # Base test setup and teardown
│ │
│ └── test/
│ └── java/
│ ├── pages/ # Page Object Model classes
│ ├── tests/ # Test classes with TestNG annotations
│ └── data/ # Test data and data providers
│
├── drivers/ # Browser drivers (e.g. chromedriver.exe)
│
├── reports/ # Allure and other test reports
│
├── testData/ # External test data files
│
├── config.properties # Environment configurations
│
└── README.md # Project documentation
```

---

##  Prerequisites

Make sure you have the following installed:

- **Java JDK 11+**
- **Maven 3.6+**
- **Git**
- **Allure Commandline** (for generating reports)
- **Chrome / Firefox browsers**

---

##  Configuration

Update the `config.properties` file to match your test environment:

```properties
baseUrl=https://www.saucedemo.com
browser=chrome
headless=false
```
## How to Run Tests
## Clone the repository
git clone https://github.com/Amrrustom/Sauce-Demo.git
cd Sauce-Demo

## Run using Maven
mvn clean test

## Generate Allure Report

After running tests:

allure serve allure-results


This will open an interactive HTML report in your browser.

 ## Reporting

After each run, an Allure Report is generated under:

/reports/allure-results

To view it:
allure serve allure-results

The report includes:
Test execution summary
Screenshots for failed tests
Logs and test steps

## Technologies Used
```
Tool / Library	Purpose
☕ Java	Programming language
🧭 Selenium WebDriver	Web automation
🧪 TestNG	Test framework
🏗️ Maven	Build and dependency management
📊 Allure	Test reporting
🧱 Page Object Model	Design pattern for maintainability
```
## 👨‍💻 Author
```
Amr Gamal Rustom
💼 QA Automation Engineer
```
