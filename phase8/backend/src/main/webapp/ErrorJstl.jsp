<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
		Error msg : <c:out value="${requestScope.errormsg}"/><p> 
		
		<c:url var="redirectUrl" value="${requestScope.redirect}">
        </c:url>    
         <a href="${redirectUrl}">back</a>
</body>
</html>