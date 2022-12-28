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
				<div style="padding-left: 100px;">情報確認</div>
				<div style="padding-left: 100px;">&nbsp;</div>
			</th>
		</tr>
		<tr>
			<td align="left">
				<div style="padding-left: 100px;">
					<table border="1" width="70%" class="tbl_input" cellpadding="4"
						cellspacing="0">
						<tr style="display: none;">
							<td id="user_id">${userInfor.userId }</td>
						</tr>
						<tr>
							<td class="lbl_left">アカウント名:</td>
							<td align="left">${userInfor.accountName }</td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left">${userInfor.groupName }</td>
						</tr>
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left">${userInfor.fullName }</td>
						</tr>
						<tr>
							<td class="lbl_left">カタカナ氏名:</td>
							<td align="left">${userInfor.kanaName }</td>
						</tr>
						<tr>
							<td class="lbl_left">生年月日:</td>
							<td align="left"><fmt:formatDate pattern="yyyy/MM/dd"
									value="${userInfor.birthday }" /></td>
						</tr>
						<tr>
							<td class="lbl_left">メールアドレス:</td>
							<td align="left">${userInfor.email }</td>
						</tr>
						<tr>
							<td class="lbl_left">電話番号:</td>
							<td align="left">${userInfor.tel }</td>
						</tr>
						<tr>
							<th colspan="2"><a href="#" onclick="appearLevelJapan()">日本語能力</a></th>
						</tr>

						<tr class="levelJa">
							<td class="lbl_left">資格:</td>
							<td align="left"><c:if
									test="${not empty userInfor.codeLevel}">${userInfor.nameLevel }</c:if></td>
						</tr>
						<tr class="levelJa">
							<td class="lbl_left">資格交付日:</td>
							<td align="left"><c:if
									test="${not empty userInfor.codeLevel }">
									<fmt:formatDate pattern="yyyy/MM/dd"
										value="${userInfor.startDate }" />
								</c:if></td>
						</tr>
						<tr class="levelJa">
							<td class="lbl_left">失効日:</td>
							<td align="left"><c:if
									test="${not empty userInfor.codeLevel }">
									<fmt:formatDate pattern="yyyy/MM/dd"
										value="${userInfor.endDate }" />
								</c:if></td>
						</tr>
						<tr class="levelJa">
							<td class="lbl_left">点数:</td>
							<td align="left"><c:if
									test="${not empty userInfor.codeLevel }">${userInfor.total }</c:if></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
	<div style="padding-left: 100px;">&nbsp;</div>
	<!-- Begin vung button -->
	<div style="padding-left: 100px;">
		<table border="0" cellpadding="4" cellspacing="0" width="300px">
			<tr>
				<th width="200px" align="center">&nbsp;</th>
				<td><input class="btn" type="button" value="編集"
					onclick="location.href='${pageContext.request.contextPath}/EditUserInput.do?type=default&user_id=${userInfor.userId }'" />
				</td>
				<td>
					<form action="${pageContext.request.contextPath}/DeleteUser.do?user_id=${userInfor.userId}"
						onsubmit="return confirm('削除しますが、よろしいでしょうか。')" name="deleteUser" method="post">
					<input class="btn" type="submit" value="削除"/>
						
					</form>
				</td>
				<td><input class="btn" type="button" value="戻る"
					onclick="location.href='${pageContext.request.contextPath}/ListUser.do?type=back'" />
				</td>
			</tr>
		</table>
	</div>
	<!-- End vung button -->
	<!-- End vung input -->
	<c:import url="footer.jsp"></c:import>
</body>
</html>