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
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Logger;


public class RequestOtpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RequestOtpServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        logger.info("Request received to send OTP to: " + email);
        try {
            // Database connection
            Connection conn = DBConnection.getConnection();

            // Check if email exists in the database
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Generate OTP
                String otp = generateOtp();
                logger.info("Generated OTP: " + otp);
                
                // Save OTP in the database (you may want to create a separate table for OTPs)
                String insertOtpQuery = "INSERT INTO otp (email, otp) VALUES (?, ?) ON DUPLICATE KEY UPDATE otp=?";
                PreparedStatement insertStmt = conn.prepareStatement(insertOtpQuery);
                insertStmt.setString(1, email);
                insertStmt.setString(2, otp);
                insertStmt.setString(3, otp);
                insertStmt.executeUpdate();

                // Send OTP to the user's email
                boolean emailSent = sendEmail(email, otp);
                if (emailSent) {
                    // Redirect to OTP verification page
                    logger.info("OTP sent successfully to: " + email);
                    response.sendRedirect("verifyOtp.jsp?email=" + email);
                } else {
                    logger.severe("Failed to send OTP email to: " + email);
                    response.sendRedirect("error.jsp");
                }
            } else {
                logger.warning("Email not found: " + email);
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            logger.severe("Error processing OTP request: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private boolean sendEmail(String email, String otp) {
        final String username = "karthikeyan25082000@gmail.com"; // your email
        final String password = "tumatuaplfnfdmfq"; // your email password
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("karthikeyan25082000@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);
            
            Transport.send(message);
            
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
