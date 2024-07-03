package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("pswd");

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    
                    HttpSession session = request.getSession();
                    String name = resultSet.getString("username");
                    session.setAttribute("name", name);

                    session.setAttribute("email", email);

                    response.sendRedirect("home1.jsp");
                } else {
                    // Login failed, redirect back to login page with error message
                    response.sendRedirect("loginsignup.jsp?error=invalid_credentials");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("loginsignup.jsp?error=database_error");
        }
    }
}
