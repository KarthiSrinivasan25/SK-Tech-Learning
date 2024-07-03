<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forget Password</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="assets/css/forget.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
    <div class="main">  
        <div class="forget-password-container">
            <form id="forgetPasswordForm" action="requestOtp" method="post">
                <label for="chk" aria-hidden="true">Forget Password</label>
                <input type="email" name="email" placeholder="Enter your email" required="">
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
</body>
</html>
