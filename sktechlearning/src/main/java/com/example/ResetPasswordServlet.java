package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;


public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ResetPasswordServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("password");
        logger.info("Attempting to reset password for email: " + email);

        if (email == null || newPassword == null) {
            logger.severe("Email or new password is null");
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            // Database connection
            Connection conn = DBConnection.getConnection();
            if (conn == null) {
                logger.severe("Failed to establish database connection");
                response.sendRedirect("error.jsp");
                return;
            }

            // Update user's password
            String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, newPassword);
            stmt.setString(2, email);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Password updated successfully
                logger.info("Password updated successfully for email: " + email);
                response.sendRedirect("passwordresetsuccess.jsp");
            } else {
                // Password update failed
                logger.warning("Failed to update password for email: " + email);
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            logger.severe("Error resetting password: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
