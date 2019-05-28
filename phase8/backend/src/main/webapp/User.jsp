<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>

<head>
    <title>User Page</title>
</head>
<body>
	<c:if test="${not empty msg}">
                <h1><c:out value="${msg}"/></h1>
    </c:if>
	<ul>
        <li>Id: <c:out value="${requestScope.user.getId()}"/></li>
        <li>first name: <c:out value="${requestScope.user.getUserName()}"/></li>
        <li>last name: <c:out value="${requestScope.user.getUserFamilyName()}"/></li>
        <li>jobTitle: <c:out value="${requestScope.user.getJobTitle()}"/></li>
        <li>bio: <c:out value="${requestScope.user.getBio()}"/></li>
        <li>
        skills:
            <ul>
	         <c:forEach var="Skill" items="${requestScope.user.getSkills()}">
	         	<li>
	         	 	SkillName: <c:out value="${Skill.getName()}"/>
	         	 	SkillPoint: <c:out value="${Skill.getPoint()}"/><p>
	         	 	
	         	 	 <c:url var="EndorseCtrl" value="EndorseController">
                                <c:param name="id" value="${user.getId()}"/>
                                 <c:param name="skillName" value="${Skill.getName()}"/>
                      </c:url>    
                     <a href="${EndorseCtrl}">تایید مهارت</a>
	         	 </li>
	        </c:forEach>
            </ul>
        </li>
    </ul>
	
    <c:url var="redirectUrl" value="users">
    </c:url>    
    <a href="${redirectUrl}">back to users page </a>
        
</body>
</html>