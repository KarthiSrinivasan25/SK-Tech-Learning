<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="assets/css/forget.css">
</head>
<body>
    <div class="main">
        <div class="reset-password">
            <form id="resetPasswordForm" action="resetPassword" method="post">
                <label for="password" aria-hidden="true">Reset Password</label>
                <input type="hidden" name="email" value="<%= request.getParameter("email") %>" required>
                <input type="password" name="password" placeholder="New Password" required>
                <button type="submit">Reset Password</button>
            </form>
        </div>
    </div>
</body>
</html>
