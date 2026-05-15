# Oivan Assignment

## Introduction
This project is an automation testing framework for EstateFinder website.

## Technologies
- Java
- Selenium WebDriver
- TestNG
- Maven
- Cucumber

## 📁 Project Structure
- `src/test/java/common`     	    -> core functions
- `src/test/java/pageObject`  	-> contain UI locators and reusable page actions
- `src/test/java/stepsDefinition`	-> Cucumber step implementations mapped with feature files
- `src/test/resources`	 	    -> feature files
- `src/main/resources`-> properties files

## How to Run
1. Clone project
2. Install Maven dependencies from `pom.xml`.
3. Modify important configurations if needed (e.g. test environment, browser, etc.) in:
   `src/main/resources/application.properties`
4. Run tests by configuring:
   `src/test/java/common/TestRunner`
- Run all feature files: `features = "src/test/resources/feature"`
- Run a specific feature file: `features = "src/test/resources/feature/SignIn.feature"`
- Run by Annotation: `tags = "@SmokeTest"`

## A short note
- I chose to use a **BDD framework** because it makes test scenarios easier to understand and maintain. The tests are written in **Gherkin** language, which is highly readable and allows manual testers to quickly participate and contribute to the automation process.

- For locating web elements, I mainly used **XPath**. Although XPath may be slower than some other locator strategies, it provides greater flexibility for building dynamic locators. For example, text values or parameters can easily be passed into XPath expressions, making them reusable across multiple test cases.

- Due to time constraints, I mainly focused on happy-path scenarios. If I had more time, I would further improve the framework by refactoring reusable components (for example, creating common page initialization methods). I would also separate test data (such as city names and other input values) into a dedicated `data.properties` file for better maintainability and easier test data management.