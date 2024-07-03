<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify OTP</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="assets/css/forget.css">
</head>
<body>
    <div class="main">
        <div class="verify-otp">
            <form id="verifyOtpForm" action="verifyOtp" method="post">
                <label for="otp" aria-hidden="true">Verify OTP</label>
                <input type="hidden" name="email" value="<%= request.getParameter("email") %>" required>
                <input type="text" name="otp" placeholder="Enter OTP" required>
                <button type="submit">Verify</button>
            </form>
        </div>
    </div>
</body>
</html>
