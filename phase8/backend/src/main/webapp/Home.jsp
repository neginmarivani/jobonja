<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
		welcome user <c:out value="${requestScope.currentUserId}"/><p> 
        <c:url var="ProjectsCtrl" value="ProjectsController">
        </c:url>    
        <a href="${ProjectsCtrl}">مشاهده پروژه ها</a>

		 <c:url var="viewUsers" value="users">
		 <c:param name="id" value="${currentUserId}"/>
        </c:url>    
        <a href="${viewUsers}">مشاهده کاربرها</a>
        
		 <c:url var="UserCtrl" value="UserController">
		 	<c:param name="id" value="${currentUserId}"/>
        </c:url>    
        <a href="${UserCtrl}">مشاهده صفحه شخصی</a>       
		
		
</body>
</html>