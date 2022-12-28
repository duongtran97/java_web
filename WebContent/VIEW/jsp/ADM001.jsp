<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value="/VIEW/css/style.css"></c:url>"
	rel="stylesheet" type="text/css" />
<title>Log In</title>
</head>
<body align="center">
	<div align="center">
		<form action="${pageContext.request.contextPath}/Login.do"
			method="post">

			<table class="tbl_input" cellpadding="4" cellspacing="0"
				width="400px">
				<tr>
					<th width="120px">&nbsp;</th>
					<th></th>
				</tr>
				<tr>
					<th colspan="2" align="left">アカウント名およびパスワードを入力してください</th>
				</tr>

				<tr>
					<td class="errMsg" colspan="2"><c:forEach items="${errorList}"
							var="er">
			${er }<br />
						</c:forEach></td>
				</tr>

				<tr align="left">
					<td class="lbl_left">アカウント名:</td>
					<td align="left"><input autofocus class="txBox" type="text"
						name="loginName" value="${fn:escapeXml(loginName)}" size="20"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr align="left">
					<td class="lbl_left">パスワード:</td>
					<td align="left"><input class="txBox" type="password"
						name="password" value="" size="22"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<td><a href="${pageContext.request.contextPath}/Registry.do"><input class="btn btn_wider" value="Registry" /></a></td>
					<td align="left"><input class="btn btn_wider" type="submit"
						value="ログイン" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>