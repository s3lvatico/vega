<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Access Vega</title>
</head>
<body>
<h1>Access</h1>
<p>Use your credentials to access the application services</p>
<h2>Identification</h2>
<form method="POST" action="j_security_check">
	<div>
		user id : <input type="text" name="j_username"/>
	</div>
	<div>
		password : <input type="password" name="j_password"/>
	</div>
	<input type="submit" value="log in"/>
</form>

</body>
</html>

