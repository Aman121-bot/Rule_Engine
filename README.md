
# Rule Engine with AST

> A powerful rule engine built in Java that leverages Abstract Syntax Trees (AST) for defining, evaluating, and executing complex rules. Designed for dynamic business logic in applications like financial systems, e-commerce, and automated decision-making processes.

## Table of Contents
1. [About the Project](#about-the-project)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Installation](#installation)
5. [Usage](#usage)
   - [Defining a Rule](#defining-a-rule)
   - [Evaluating Rules](#evaluating-rules)
   - [Retrieving Rules](#retrieving-rules)
   - [AST Representation](#ast-representation)
6. [Configuration](#configuration)
7. [Contributing](#contributing)
8. [License](#license)

## About the Project

This Rule Engine utilizes AST to parse, represent, and evaluate rules dynamically. By representing rules in a tree structure, it enables efficient evaluation and modification of business logic. Users can define rules that dictate how different conditions lead to specific actions without altering the core application code.

## Features

- **Dynamic Rule Definition**: Define rules using a clear syntax that is converted into an Abstract Syntax Tree.
- **Complex Rule Evaluation**: Evaluate complex rules based on incoming data or events.
- **Logical Operations Support**: Support for logical operations, comparisons, and nested rules.
- **Efficient Execution**: Efficient rule execution through AST traversal.
- **RESTful API**: API for managing rules (create, update, delete).
- **Comprehensive Logging**: Logging and reporting of rule evaluation outcomes.

## Technologies Used

- **Java**: Core programming language
- **Spring Boot**: Framework for building RESTful APIs
- **Maven**: Dependency management and build tool
- **ANTLR**: For parsing rule syntax (if applicable)
- **JUnit**: Testing framework for unit tests
- **Log4j**: Logging framework for application logs

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/rule-engine-ast.git
   cd rule-engine-ast
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**:
   - The application will be available at `http://localhost:8080`.

## Usage

### Defining a Rule
You can create rules using the API. For example, to create a new rule:
```http
POST /rules
Content-Type: application/json

{
  "name": "Discount Rule",
  "expression": "purchaseAmount > 100 AND customerType == 'VIP'",
  "action": "applyDiscount(10)"
}
```

### Evaluating Rules
Send data to evaluate against the defined rules:
```http
POST /evaluate
Content-Type: application/json

{
  "purchaseAmount": 150,
  "customerType": "VIP"
}
```

### Retrieving Rules
Get all defined rules:
```http
GET /rules
```

### AST Representation
When a rule is defined, it is converted into an AST that represents its structure for evaluation.

## Configuration

Configure the application settings in `application.properties`:
```properties
server.port=8080
logging.level.root=INFO
```

## Contributing

1. **Fork the project**
2. **Create your feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit your changes** (`git commit -m 'Add AmazingFeature'`)
4. **Push to the branch** (`git push origin feature/AmazingFeature`)
5. **Open a pull request**

## License

Distributed under the MIT License. See `LICENSE` for more information.
