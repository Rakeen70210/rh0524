# Checkout Application

## Overview

This project is a simple command-line application designed to simulate a checkout process. It is structured using a
basic MVC (Model-View-Controller) architecture to separate the concerns of input validation, user interaction, and
process control.

## Components

- **View (`CheckoutView`)**: Handles all user interactions and displays information to the user.
- **Controller (`CheckoutController`)**: Manages the flow of the checkout process, interacting between the view and the
  services.
- **Service (`InputValidationService`, `WeekendHolidayService`, `CheckoutService`)**: Provides validations and business
  logic of the application.
- **Model (`Tool`, `RentalAgreement`, `ToolType`, `ToolBrand`, `ToolCode`)**: Represents the data and their attributes
  for the application.
- **Repository (`ToolMap`)**: Provides a way to store and retrieve tools using a Map data structure.

## Setup and Execution

### Requirements

- Java 11 or higher

### Running the Application

1. Clone the repository to your local machine.
2. Navigate to the root directory of the project.
3. Compile the Java files
4. Under `src/main/java/org/example/Controller` run `Main.java` and follow the prompts on the console

### Testing

1. Navigate to the root directory of the project.
2. Under `src/test/java/org/example` you will find all of the JUnit tests
3. `CheckoutControllerTest` will run a full system test for all scenarios 1-6
4. Output files for test scenarios under `src/test/resources`