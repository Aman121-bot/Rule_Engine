# Rule Engine Application

## Overview

This is a 3-tier rule engine application that allows dynamic creation, combination, and evaluation of rules based on user attributes like age, department, income, etc. The application uses Abstract Syntax Tree (AST) to represent conditional rules.

### Features:

- Create, combine, and evaluate rules dynamically.
- Secure and optimized rule engine with PostgreSQL as the backend.
- API endpoints for rule creation and evaluation.

## Non-Functional Enhancements (Bonus):

### 1. **Security:**

- **Authentication & Authorization:** Integrated **Spring Security** for securing endpoints using **JWT**. Only authorized users can create rules, while all users can evaluate them.
- **HTTPS:** Ensured secure communication by enforcing HTTPS for all API traffic.
- **Input Validation:** Applied stringent input validation on rule strings and user data to prevent malicious injections.
- **SQL Injection Protection:** Used parameterized queries and JPA to avoid any risk of SQL injection.
- **CORS Protection:** Configured CORS settings to limit access to trusted origins.

### 2. **Performance Optimizations:**

- **Database Indexing:** Indexed critical fields such as `rule_name` and `created_at` for efficient querying.
- **Caching:** Implemented **Redis** for caching frequently used rules to speed up rule evaluation.
- **Connection Pooling:** Configured HikariCP for efficient database connections and improved response times under load.
- **Asynchronous Processing:** Used **@Async** annotation to process long-running tasks without blocking main threads.

### 3. **Logging and Monitoring:**

- **Logging:** Applied structured logging with **SLF4J** and **Logback** for tracking rule operations (creation, evaluation, errors).
- **Monitoring:** Integrated **Prometheus** and **Grafana** to monitor application performance metrics.
- **Error Tracking:** Set up **Sentry** to capture and report production errors for better debugging.

### 4. **Scalability:**

- **Dockerized Application:** The entire application can be containerized using Docker, allowing it to scale easily in cloud environments.
- **Kubernetes Ready:** Configured to run on **Kubernetes** for container orchestration and scaling across multiple instances.

### 5. **API Rate Limiting:**

- Applied rate limiting on rule creation APIs to prevent abuse and ensure fair usage.

---

## Database

The application uses **PostgreSQL** for storing rules and metadata. The rule AST is stored in a JSONB column for efficient querying and modification.

Sample SQL schema:

```sql
CREATE TABLE rules (
    id SERIAL PRIMARY KEY,
    rule_name VARCHAR(255) NOT NULL,
    rule_ast JSONB NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
