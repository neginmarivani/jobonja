<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>
	<head>
		<title>Login Page</title>
	</head>

	<body>
		<form action="LoginServlet">

			Please enter your username 		
			<input type="text" name="un"/><br>		
			
			<input type="submit" value="submit">			
		
		</form>
	</body>
</html>