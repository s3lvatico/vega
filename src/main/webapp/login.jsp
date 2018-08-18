<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>access Vega</title>
</head>
<body>
	<h1>Access</h1>
	<p>Use your credentials to access the application services</p>
	<h2>Identification</h2>
	<form method="POST" action="j_security_check">
		<div>
			user id : <input type="text" name="j_username" />
		</div>
		<div>
			password : <input type="password" name="j_password" />
		</div>
		<input type="submit" value="log in" />
	</form>
</body>
</html>

