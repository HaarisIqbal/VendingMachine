# Vending Machine Application

## Wiley Edge Training - Project 3

*Trainee: Haaris Iqbal*

*Date: November 2022*

---

This program is a maven project made in IntelliJ, containing Java files, JUnit tests, .txt files (acting as psedo databases) and this README.md file. The project has been organised in the MVC design pattern.

Test Driven Development methodology was used for multiple components. Appropriate JavaDoc has been applied to all functional files and Java code conforms to Google Checkstyle.

### Quick overview

Thoughtful design desicions were made throughout the process of development. A lot of effort put into UI neatness.

Program features full fledged functionality:
- Displyaing only available items.
- hides items when stock finished.
- showing appropriate change.
- auditing items purchased and recording remaining quantites.

### List of Notable Backend Features

- Big Demical used throughout.
- Using lambda functions to construct HashMap of items with quantity above zero.
- Added Dependency injection throughout.
- Included approriate interfaces, enums, tests.
- Using a service layer.
- Multiple DAOs for different data types.
- New storage package to organise files.
- Cascasing switch statement to calculate change efficently.

### Possible improvements

- Given better time management, would have implemented custom errors. In current implementation, errors are avoided and feedback messages are given in console.
- Audit feature could be improved to log attempted purchases, change, etc.
- Could write more robust tests for Service Layer.
