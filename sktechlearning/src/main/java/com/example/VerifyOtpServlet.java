package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;


public class VerifyOtpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(VerifyOtpServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String otp = request.getParameter("otp");
        logger.info("Verifying OTP for email: " + email);
        
        if (email == null || otp == null) {
            logger.severe("Email or OTP is null");
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

            // Check if OTP is valid
            String query = "SELECT * FROM otp WHERE email = ? AND otp = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, otp);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // OTP is valid, redirect to reset password page
                logger.info("OTP verified successfully for email: " + email);
                response.sendRedirect("resetPassword.jsp?email=" + email);
            } else {
                // Invalid OTP, redirect to error page
                logger.warning("Invalid OTP for email: " + email);
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            logger.severe("Error verifying OTP: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
