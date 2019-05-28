<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title>Users</title>
    <style>
        table {
            text-align: center;
            margin: 0 auto;
        }
        td {
            min-width: 300px;
            margin: 5px 5px 5px 5px;
            text-align: center;
        }
    </style>
</head>
<body>
    <table border=1>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>job title</th>          
                    <th>&nbsp;</th>
                </tr>
                <c:forEach var="user" items="${users}">
                	<c:if test="${not user.ifHidden()}">
				   
	                    <tr>
                        <td><c:out value="${user.getId()}"/></td>
                        <td><c:out value="${user.getUserName()}"/></td>
                        <td><c:out value="${user.getJobTitle()}"/></td>
                        <td>
                            <c:url var="UserCtrl" value="UserController">
                                <c:param name="id" value="${user.getId()}"/>
                            </c:url>    
                            <a href="${UserCtrl}">مشاهده کاربر</a>
                            
                        </td>
                    </tr>
                     </c:if>
                </c:forEach>
            </table>

</body>
</html>