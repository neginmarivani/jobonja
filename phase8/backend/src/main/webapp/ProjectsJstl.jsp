<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title>Projects</title>
</head>
<body>

    <ul>
    <c:forEach var="project" items="${requestScope.projectsList}">
    	
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
          <c:url var="projectCtrl" value="project">
                  <c:param name="id" value="${project.getId()}"/>
           </c:url>    
          <a href="${projectCtrl}">مشاهده پروژه</a>
                            
    </c:forEach>
					
	</ul>

</body>
</html>
