package com.mt.services;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mt.database.DBConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredUsername = request.getParameter("username");
        String enteredPassword = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT password FROM users WHERE username = ?")) {

            stmt.setString(1, enteredUsername);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String enteredHash = hashPassword(enteredPassword);

                if (storedHash.equals(enteredHash)) {
                    request.getSession().setAttribute("username", enteredUsername);
                    response.sendRedirect("home.jsp");
                } else {
                    response.getWriter().println("<h2>Invalid Credentials</h2><a href='login.jsp'>Try Again</a>");
                }
            } else {
                response.getWriter().println("<h2>User Not Found</h2><a href='login.jsp'>Try Again</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Server Error</h2>");
        }
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}
