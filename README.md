A fully functional desktop-based shopping system built using Java Swing and MySQL.

📌 Overview

This project is a Java Swing based E-Commerce Application that provides a complete shopping workflow including user authentication, product browsing, cart management, checkout, and order processing.
It is designed following modular and scalable architecture, ideal for academic submission and real-world extension.

🚀 Features
🔐 User Authentication

User Registration (username, email, password)

Secure login using SHA-256 password hashing

Input validation & error handling

🛍️ Product Management

Product listing pulled from MySQL database

Dynamic card-based UI

Sorting by:

Price: Low → High

Price: High → Low

Name: A → Z

Stock status with low-stock indicator

🛒 Cart System

Add to cart with selected quantity

Buy Now option

Cart preview with:

Product name

Quantity

Unit price

Total price

Item remove & update support (extendable)

💳 Checkout & Payment Simulation

Billing details section

Order summary

Simple mock payment screen

Order success notification

🎨 UI/UX

Modern Java Swing UI

Splash screen with loader

Light/Dark theme manager

Toast notifications

🗄️ Database Integration

MySQL database stores:

Users

Products

Orders

Order Items

Optimized DAO structure for clean database operations.

🏗️ Architecture
src/
 ├── ecommerce/
 │    ├── App.java
 │    └── Main.java
 ├── ui/
 │    ├── LoginFrame.java
 │    ├── RegisterFrame.java
 │    ├── MainFrame.java
 │    ├── ProductsPanel.java
 │    ├── CheckoutFrame.java
 │    └── SplashScreen.java
 ├── dao/
 │    ├── UserDAO.java
 │    ├── ProductDAO.java
 │    └── OrderDAO.java
 ├── models/
 │    ├── User.java
 │    ├── Product.java
 │    └── Order.java
 └── utils/
      ├── Validator.java
      ├── PasswordHasher.java
      ├── CartManager.java
      ├── ThemeManager.java
      └── Toast.java

🗃️ Database Setup

Create a MySQL database:

CREATE DATABASE ecommerce_db;
USE ecommerce_db;


Import the SQL tables (Users, Products, Orders, OrderItems).

Update your DB credentials in App.java or DBConnection.java (depending on your version).

▶️ How to Run

Install JDK 17+

Add MySQL Connector/J to project libraries

Connect MySQL Database

Run:

Main.java


The splash screen will load → then login page.


