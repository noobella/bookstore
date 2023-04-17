# bookstore-noobella

This project contains a Java Dynamic Web app for a book shop. Its functionalities include:
- Ability to view list of books
- Ability to search books based on author name or ISBN
- Ability to display specific book information
- Ability to add book to shopping cart, view and modify shopping cart
- Ability to proceed and checkout shopping card
- Ability to register and log into account


Before you run this project, please refer to the following requirements below:

 - Eclipse v4.26.0 
 - Eclipse Enterprise Java and Web Developer Tools 3.28
 - Dynamic Web Module v4
 - JavaSE-17
 - Apache Tomcat v10.1

Ensure that the following libraries are in webapp/WEB-INF and add them to the Java Build Path under Libraries via 'Add External Jars':

- jakarta.servlet.jsp.jstl-2.0.0.jar
- jakarta.servlet.jsp.jstl-api-2.0.0.jar
- mysql-connector-j-8.0.31
- taglibs-standard-impl-1.2.5

To run this project:

 1. Run the database file E.G.
`\. [PATH-TO-PROJECT]\BookStore\src\main\resources\bookstore_db.txt`
 2. Using Eclipse, run the project using 'Run on Server' option
 3. Access web app via `http://localhost:8080/BookStore/HomeServlet`
