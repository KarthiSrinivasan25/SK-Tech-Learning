<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Logout</title>
    <link rel="icon" type="image/x-icon" href="img/icon.png">
<style>
body {
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	font-family: 'Jost', sans-serif;
	background: linear-gradient(to bottom, #0f3c29, #306363, #24443e);
	text-align: center;
}

.logout-message {
	background-color: #ffffff;
	border: 1px solid #dddddd;
	padding: 20px;
	max-width: 400px;
	margin: 0 auto;
	border-radius: 10px;
}

.logout-message h2 {
	margin-top: 0;
}

.logout-link {
	margin-top: 20px;
	display: inline-block;
	padding: 10px 20px;
	background-color: #007bff;
	color: #ffffff;
	text-decoration: none;
	border-radius: 5px;
}
</style>
</head>
<body>
	<div class="logout-message">
		<h2>You have been successfully logged out.</h2>
		<p>Thank you for visiting. Come back soon!</p>
		<a href="index.jsp" class="logout-link">Back To Main Page</a>
	</div>
</body>
</html>
