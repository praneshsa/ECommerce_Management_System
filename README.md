# E-Commerce Management System

A console-based E-Commerce Management System developed using **Core Java, JDBC, and MySQL** to manage users, products, categories, shopping carts, and customer orders.

## Features

- **User Management:** User registration details and authentication.
- **Product Management:** Add, view, update, and delete product records.
- **Category Management:** Organize products into categories.
- **Shopping Cart:** Manage cart records and product quantities.
- **Order Management:** Create and manage customer orders and order items.
- **Database Operations:** Perform CRUD operations using JDBC and SQL.
- **Relational Database:** Maintain data relationships using primary keys, foreign keys, and SQL joins.

## Technologies Used

- **Programming Language:** Java
- **Database:** MySQL
- **Database Connectivity:** JDBC
- **Concepts:** Object-Oriented Programming (OOP), CRUD Operations, SQL Joins, Relational Database Design

## Database Design

The project uses seven relational tables:

| Table Name | Purpose |
|---|---|
| `user` | Stores user information and roles |
| `categories` | Stores product categories |
| `products` | Stores product details |
| `cart` | Stores shopping cart information |
| `cart_item` | Stores products and quantities in a cart |
| `orders` | Stores customer order information |
| `order_item` | Stores individual products within an order |

## Project Structure

```text
ECommerce_Management_System/
├── src/
│   ├── DB/
│   │   └── DBConnection.java
│   ├── DAO/
│   ├── DTO/
│   └── Main.java
└── README.md
```

*Note: Update the project structure above if your actual package names or main class location differ.*

## Getting Started

### Prerequisites

- Java JDK
- MySQL Server
- IntelliJ IDEA or another Java IDE
- MySQL Connector/J

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/praneshsa/ECommerce_Management_System.git
   ```

2. Open the project in IntelliJ IDEA.

3. Create the MySQL database:

   ```sql
   CREATE DATABASE e_commerce_db;
   ```

4. Create the seven tables using your project’s SQL schema.

5. Add MySQL Connector/J to the project dependencies.

6. Update the database URL, username, and password in your database connection class.

7. Run the main Java class to start the application.

## Learning Outcomes

- Practiced JDBC connectivity between Java and MySQL.
- Gained experience implementing CRUD operations.
- Learned relational database design and SQL joins.
- Applied OOP and DAO/DTO-based code organization.
- Practiced managing related e-commerce data through a console application.

## Author

**Pranesh S A**

GitHub: [@praneshsa](https://github.com/praneshsa)

---

*This project was developed for learning and practicing Java database application development.*
