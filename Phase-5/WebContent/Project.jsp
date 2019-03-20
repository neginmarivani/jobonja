<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title>Project Page</title>
</head>
<body>
	<c:if test="${not empty msg}">
                <h1><c:out value="${msg}"/></h1>
    </c:if>
    <ul>
		<li>Id: <c:out value="${project.getId()}"/></li> 
       <li> Description: <c:out value="${project.getDescription()}"/><li>
       <li> ImageUrl: <img src= <c:url value="${project.getImageUrl()}" /> style="width: 50px; height: 50px;"></li>
       <li> Deadline: <c:out value="${project.getDeadline()}"/></li> 
       <li>Budget: <c:out value="${project.getBudget()}"/></li>
       <li>Title: <c:out value="${project.getTitle()}"/></li>
       <li>prerequisites
       			<ul>
         	 <c:forEach var="Skill" items="${project.getPrerequisites()}">
         	 	
         	 	<li>SkillName: <c:out value="${Skill.getName()}"/> </li>
         	 	<li> SkillPoint: <c:out value="${Skill.getPoint()}"/></li>
         	 </c:forEach>
         	</ul>

	<form action="addBidController" method=GET>
		<input type="hidden" name="projectId" value="${project.getId()}"/>
	<input type="submit" value="افزودن درخواست  "/>
	</form>
	 <c:url var="redirectUrl" value="ProjectsController">
    </c:url>    
    <a href="${redirectUrl}">back to Projects page </a>
	
</body>
</html>