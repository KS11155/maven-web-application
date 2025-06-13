<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.*, javax.servlet.http.*, javax.servlet.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>SampatSiva Technologies - Home Page</title>
    <link href="images/siva.jpg" rel="icon">
</head>
<body>

    <h1 align="center">Welcome to SampatSiva Technologies</h1>
    <h3 align="center">Trainer Name: Sivaiah Kallagunta</h3>

    <% String loggedUser = (String) session.getAttribute("username"); %>
    <% if (loggedUser != null) { %>
        <h2>Logged in as: <%= loggedUser %></h2>
        <a href="LogoutServlet">Logout</a>
    <% } else { %>
        <p><a href="login.jsp">Login Here</a></p>
    <% } %>

</body>
</html>
