# Vending Machine Application

## Wiley Edge - Advanced Java Training Project

*Trainee: Haaris Iqbal*

*Date: November 2022*

---

This application is a maven project made in IntelliJ, containing a POM file, an XML file, Java files, JUnit tests, .txt files (acting as psedo databases) and this README.md file. The project has been organised in the MVC design pattern.

Test Driven Development methodology was used for multiple components. Appropriate JavaDoc has been applied to all functional files and Java code conforms to Google Checkstyle.

The application has incorperated the Spring framework and is using Spring for dependency injection. There is a previous version of this application (which incorperated dependency injection without Spring) that can be found as Release v1.0 on this repository.

The updates in this version include:
- Modified POM for Spring framework dependency.
- Created a new Application Context xml file with Bean definitions.
- Updated App class to use Spring for dependency injection.
- Updated Service Layer unit test to create a 'test' service layer using Spring.
- Refactored project for appropriate use of 'com.domain.package.Class' convension.
- General updates and cleanup to JavaDoc and code.

### Quick Overview

Thoughtful design desicions were made throughout the process of development. A lot of effort put into UI neatness and helpfulness.

Program features all functionality required from specification (and more):
- Custom commands to exit, to list items again, and for help.
- Displaying Items that are in stock.
- Appropriate error handling for money, purchase attempts, inputs.
- Only dispenses one item at a time.
- Items out of stock are no longer available.
- Showing appropriate change in quarters, dimes, nickels and pennies.
- Auditing items purchased and recording remaining quantites.

### List of Notable Backend Features

- Big Demical used throughout.
- Using lambda functions to construct HashMap of items with quantity above zero.
- Incorperation of Dependency injection - now with Spring.
- Included approriate interfaces, enums, tests.
- Using a service layer.
- Multiple DAOs for different data types.
- New storage package to organise files.
- Cascasing switch statement to calculate change efficently.

### Possible improvements

- With better time management, would have implemented custom Vending Machine errors. In current implementation, standard Java Exceptions are used.
- Audit feature could be extended to log attempted purchases, change given, etc.
- Service layer could be tested in greater depth with more unit testing.
