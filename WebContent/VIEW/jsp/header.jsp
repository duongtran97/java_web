<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
<body>
	<div>			
			<div>
			<table>
			<tr>
			<td width = "80%"><img src="<c:url value="/VIEW/images/logo-manager-user.gif"></c:url>" alt="Luvina" /><td>
			
			<c:choose>
			    <c:when test="${session.getAttribute('username') != null}">
			        <td align="left"><a href = "${pageContext.request.contextPath}/Logout.do">ログアウト</a> &nbsp; <a href = "${pageContext.request.contextPath }/ListUser.do">トップ</a><td>
			        <br />
    			</c:when>    
    		<c:otherwise>
			        <td align="left"><a href = "${pageContext.request.contextPath}/Login.do">Login</a> <td> 
			        <br />
   			 </c:otherwise>
			</c:choose>
			</tr>
			</table>
			</div>
		</div>
	
</body>
</html>