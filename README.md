# SwiftFood 🍔🚀

A full-stack **Java Web Application** for online food ordering and delivery management.

---

## 🌐 Project Overview

SwiftFood is a web-based food ordering system built using **Java Servlets, JSP, and Oracle Database**.
Customers can browse the menu, place orders, and track their status.
Admins can manage orders and menu items from a dedicated dashboard.

---

## ✅ Features

### Customer
- Register and Login securely
- Browse food menu by category
- Add items to order with quantity
- Place order with delivery address
- View order success confirmation

### Admin
- Secure admin login with role-based access
- View all incoming orders in real time
- Update order status (Placed → Preparing → Out for Delivery → Delivered)
- Add new menu items with category and price

---

## 🛠️ Technologies Used

| Layer | Technology |
|---|---|
| Frontend | JSP, HTML, CSS |
| Backend | Java Servlets, JDBC |
| Database | Oracle Database 21c XE |
| Server | Apache Tomcat 10.1 |
| IDE | IntelliJ IDEA |
| Build Tool | Maven |
| Version Control | Git & GitHub |

---

## 🗄️ Database Tables

- **users** - Stores customer and admin accounts
- **menu_items** - Stores all food items with category and price
- **orders** - Stores placed orders with status and delivery address
- **order_items** - Stores individual items within each order

---

## 🏗️ Project Architecture

Three-tier architecture:
- **Presentation Layer** - JSP pages (index, login, register, menu, admin, orderSuccess)
- **Business Logic Layer** - Java Servlets (Login, Register, Logout, PlaceOrder, UpdateOrder, AddItem)
- **Data Access Layer** - DAO classes (UserDAO, MenuDAO, OrderDAO, DBConnection)

---

## 🚀 How to Run

1. Install JDK 19, Apache Tomcat 10.1, Oracle Database 21c XE
2. Create Oracle user and tables using the SQL scripts
3. Add ojdbc11.jar to WEB-INF/lib
4. Open project in IntelliJ IDEA
5. Configure Smart Tomcat plugin
6. Run the project and open: http://localhost:8080/SwiftFood

---

## 👤 Default Admin Login

- **Username:** kusuma
- **Password:** (your set password)

---

## 🎯 OOP & Java Concepts Used

- Inheritance - HttpServlet extended by all Servlets
- Encapsulation - Private fields with getters/setters in Model classes
- Abstraction - DAO pattern separating database logic
- Polymorphism - doGet() and doPost() method overriding
- Collections - List, ArrayList, Map, LinkedHashMap
- Exception Handling - try-catch-finally, throws
- JDBC - PreparedStatement, ResultSet, Connection
- Session Management - HttpSession for login state

---

## 📈 Future Enhancements

- Online payment integration (UPI, Cards)
- Real-time GPS delivery tracking
- Android and iOS mobile apps
- AI-based food recommendations
- Cloud deployment on AWS

---

## 👩‍💻 Author

**Manoj Kumar Chidama**
- BTech CSE 
- Gayatri Vidya Parishad College of Engineering, Visakhapatnam
GitHub: https://github.com/Manoj14624

---

## 📜 License

This project is for educational purposes - Personal Project.
