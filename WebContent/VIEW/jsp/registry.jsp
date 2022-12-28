<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- format theo jstl -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADM005</title>
<link href="<c:url value="/VIEW/css/style.css"></c:url>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/VIEW/js/user.js"></c:url>"></script>
</head>
<body onload="hiddenJapan()">
	<c:import url="header.jsp"></c:import>
	<!-- Begin vung input-->
	<table class="tbl_input" border="0" width="75%" cellpadding="0"
		cellspacing="0">
		<tr>
			<th align="left">
				<div style="padding-left: 100px;">&nbsp;</div>
				<div style="padding-left: 100px;">&nbsp;</div>
			</th>
		</tr>
		<tr>
			<td align="left">
				<div style="padding-left: 100px;">
					<table border="1" width="70%" class="tbl_input" cellpadding="4"
						cellspacing="0">
						<form action="${pageContext.request.contextPath}/Registry.do" method="post">
						<!--nhập username-->	
						<tr>
							<td>
								Username
							</td>
							<td>
								<input type="text" name="username" id="username">
							</td>
						</tr>
						<!--nhập password-->	
						<tr>
							<td>
								Password
							</td>
							<td>
								<input type="password" name="password" id="password">
							</td>
						</tr>
						<!--nhập lai password-->	
						<tr>
							<td>
								Password confirm
							</td>
							<td>
								<input type="password" name="password_confirm" id="password_confirm">
							</td>
						</tr>
						<!--nhập fullname-->	
						<tr>
							<td>
								Fullname
							</td>
							<td>
								<input type="text" name="fullname" id="fullname">
							</td>
						</tr>
						<!--nhập fulname_kana-->	
						<tr>
							<td>
								Fullname _Kana
							</td>
							<td>
								<input type="text" name="fullname_kana" id="fullname_kana">
							</td>
						</tr>
						<!--nhập email-->	
						<tr>
							<td>
								Email
							</td>
							<td>
								<input type="email" name="email" id="email">
							</td>
						</tr>
						<!--nhập telephone-->	
						<tr>
							<td>
								Phone
							</td>
							<td>
								<input type="text" name="phone" id="phone">
							</td>
						</tr>
						<!--nhập password-->	
						<tr>
							<td>
								Birthday
							</td>
							<td>
								<input type="date" name="birthday" id="birthday">
							</td>
						</tr>
						<!--nhập group-->	
						<tr>
							<td>
								Group name
							</td>
							<td>
								<select name="groupname" id="group_name">
									<option value="0" selected>Choose group</option>
									<c:forEach items="${lstMstGroup }" var="mstGroup">
									<option value="${mstGroup.groupId }">${mstGroup.groupName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><button type="submit">Registry</button></td>
						</tr>
						</form>
					</table>
				</div>
			</td>
		</tr>
	</table>
	<div style="padding-left: 100px;">&nbsp;</div>
	
	<!-- End vung input -->
	<c:import url="footer.jsp"></c:import>
</body>
</html>