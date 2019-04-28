<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
	     msg : <c:out value="${msg}"/><p> 
		
		<c:url var="redirectUrl" value="project">
			<c:param name="id" value="${projectId}"/>
        </c:url>    
         <a href="${redirectUrl}">back to Project page </a>
         </body>
</html>