<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login / Signup</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
    <div class="main">  
        <input type="checkbox" id="chk" aria-hidden="true">

        <div class="login">
            <form id="loginForm" action="login" method="post">
                <label for="chk" aria-hidden="true">Login</label>
                <input type="email" name="email" placeholder="Email" required="">
                <input type="password" name="pswd" placeholder="Password" required="">
                <button type="submit">Login</button>
            </form>
            
            <a href="forgetpassword.jsp" class="forget-password">Forget Password?</a>
        </div>

        <div class="signup">
            <form id="signupForm" action="signup" method="post">
                <label for="chk" aria-hidden="true">Sign up</label>
                <input type="text" name="username" placeholder="User name" required="">
                <input type="email" name="email" placeholder="Email" required="">
                <input type="password" name="password" placeholder="Password" required="">
                <button type="submit">Sign up</button>
            </form>
        </div>
    </div>
</body>
</html>
