# iOS Appium Tests (Java)

A sample project for writing and running automated UI tests on iOS devices using **Appium** and **TestNG**.

## Project Overview
- **Language:** Java 17
- **Build tool:** Maven
- **Testing framework:** TestNG
- **Mobile automation:** Appium Java client
- **Other libs:** SLF4J for logging, Rest-Assured for API interactions

## Running Tests
`mvn test -DsuiteXmlFile=src/test/resources/testng.xml`

## Continuous Integration
A GitHub Action (`.github/workflows/maven.yml`) builds the project and runs the tests automatically on pull requests and pushes to `main`.
