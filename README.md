# 🛒 Java Swing E-Commerce Application (JDBC | MySQL)

A fully functional **desktop-based shopping system** built using **Java Swing** and **MySQL**, designed with a **modular architecture** and enhanced using **JDBC Transaction Management** to ensure data consistency during order placement.

---

## 📌 Overview
This project is a **Java Swing–based E-Commerce Application** that provides a complete shopping workflow including:

- User authentication
- Product browsing
- Cart management
- Checkout & order processing

To align with real-world e-commerce standards and academic evaluation criteria, the **order placement process uses JDBC transactions (commit/rollback)** to ensure that **order creation and inventory updates occur atomically**.

---

## 🎯 Key Academic Enhancements
✔ JDBC Transaction Management  
✔ Atomic order placement (Order + Stock update)  
✔ Clear separation of UI and business logic  
✔ DAO-based database access  
✔ Industry-standard backend design  

---

## 🚀 Features

### 🔐 User Authentication
- User Registration (username, email, password)
- Secure login using **SHA-256 password hashing**
- Input validation & error handling

---

### 🛍️ Product Management
- Product listing fetched from MySQL database
- Dynamic card-based UI
- Sorting options:
  - Price: Low → High
  - Price: High → Low
  - Name: A → Z
- Stock availability with low-stock indicator

---

### 🛒 Cart System
- Add to cart with selected quantity
- Buy Now option
- Cart preview including:
  - Product name
  - Quantity
  - Unit price
  - Total price
- Item remove & quantity update support (extendable)

---

### 💳 Checkout & Order Processing
- Billing details form
- Order summary screen
- Mock payment simulation
- Order success confirmation

📌 **Important:**  
The checkout process is handled through a **service layer**, not directly inside UI classes, ensuring proper separation of concerns.

---

## 🔐 JDBC Transaction Management (Core Highlight)

### Why Transaction Management?
In an e-commerce system:
- An order must be created
- Inventory must be reduced  

These operations **must succeed or fail together**.

---

### 🔄 Order Placement Workflow
1. Disable auto-commit (`setAutoCommit(false)`)
2. Insert order into `Orders` table
3. Reduce product stock in `Products` table
4. If all steps succeed → `commit()`
5. If any step fails → `rollback()`

This prevents:
- Orders without stock updates
- Inventory inconsistencies
- Partial database writes

---

### 🧠 Transaction Logic Placement
- ✔ Implemented in **Service / Controller logic**
- ❌ Not inside Swing UI classes
- ❌ Not inside DAO classes

DAO classes strictly perform SQL operations only.

---

## 🎨 UI / UX
- Modern Java Swing UI
- Splash screen with loader
- Light / Dark theme manager
- Toast-style notifications
- Clean and responsive layouts

---

## 🗄️ Database Integration
MySQL database stores:
- Users
- Products
- Orders
- OrderItems

Database access is handled via **optimized DAO classes**, ensuring maintainability and scalability.

---

## 🏗️ Project Structure
