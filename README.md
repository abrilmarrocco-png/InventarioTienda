# Inventory Management System

A console-based inventory management application developed in **Java 21**, using Maven and JUnit 5.

This project was built to practice object-oriented programming, data persistence, input validation, and automated testing.

## Features

- Add new products to the inventory.
- List all available products.
- Search for products by code.
- Delete existing products.
- Update product stock quantities.
- Validate product information and prevent duplicate product codes.
- Save and load inventory data using CSV files.

## Technologies

- **Java 21**
- **Apache Maven**
- **JUnit 5**
- **CSV file persistence**
- **Visual Studio Code**

## Project Structure

```text
InventarioTienda/
├── pom.xml
├── README.md
├── data/
│   └── productos.csv
└── src/
    ├── main/
    │   └── java/
    │       ├── Main.java
    │       ├── Producto.java
    │       ├── InventarioService.java
    │       └── ArchivoCSV.java
    └── test/
        └── java/
            └── InventarioServiceTest.java
```

## How to Run

**Requirements:** Java 21 and Apache Maven.

Clone the repository and open a terminal in the project directory.

Compile the application:

```bash
mvn clean compile
```

Run the application:

```bash
java -cp target/classes Main
```

## Automated Tests

The project includes six automated unit tests using JUnit 5.

Run the tests with:

```bash
mvn clean test
```

The tests cover product creation, searching, duplicate prevention, deletion, stock updates, and invalid stock quantities.

## Future Improvements

- Graphical user interface.
- Database integration.
- Product categories.
- Low-stock alerts.
- Inventory reports.

## About This Project

This project was developed as part of my learning journey in Software Engineering, with a focus on Java development, clean code, and software testing.