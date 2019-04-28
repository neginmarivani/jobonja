<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>

<title> Add Bid</title>
</head>
<body>
<c:if test="${not empty msg}">
                <h1><c:out value="${msg}"/></h1>
    </c:if>
	   	 
	<form action="saveBidController" method=GET>
	
		<input type="hidden" name="project" value="${project}"/>
		مبلغ پیشنهادی : <input type="number" name="bidAmount" /><br/>
	<input type="submit" value="تایید درخواست "/>
	</form>

</body>
</html>