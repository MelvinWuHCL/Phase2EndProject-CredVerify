<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Register</title>
	</head>
	<body>
		<form action = "register" method = "post">
		
			<label for = "name">Name: </label>
			<input type = "text" id = "name" name = "name"><br><br>
			
			<label for="n">Email:</label>
			 <input type="text" id="email" name="email"><br><br>
			
			<label for="password">Password:</label>
			<input type="text" id="password" name="password"><br><br>
			 
			<input type="submit" value="Register">
		</form>
		<a href="index.jsp">Main Menu</a>
	</body>
</html>