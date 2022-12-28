<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value="/VIEW/css/style.css"></c:url>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/VIEW/js/user.js"></c:url>"></script>
<title>ADM003</title>
</head>
<body onload="hiddenJapan()">
	<c:import url="header.jsp"></c:import>
	<!-- Begin vung input-->
	<form
		action="${pageContext.request.contextPath}/${userInfor.userId == 0 ? 'AddUserValidate.do':'EditUserValidate.do'}?type=validate&user_id=${userInfor.userId}"
		method="post" name="inputform">
		<div style="padding-left: 100px;">
			<input type="hidden" name="type" value="validate" />
			<table class="tbl_input" border="0" width="75%" cellpadding="4"
				cellspacing="0">
				<tr>
					<th align="left" colspan="2">会員情報編集</th>
				</tr>
				<tr>
					<td class="errMsg" align="left" colspan="2"
						style="text-align: left; padding-left: 100px;"><c:forEach
							items="${ listError}" var="e">
					${e} <br>
						</c:forEach></td>
				</tr>
				<tr>
					<td align="left">
				<tr>
					<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
					<td align="left"><input class="txBox" type="text"
						id="accountName" name="accountName"
						value="${userInfor.accountName}" size="15"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';"
						${userInfor.userId != 0 ?'readonly':''} /></td>
				</tr>
				<tr>
					<td class="lbl_left"><font color="red">*</font> グループ:</td>
					<td align="left"><select name="groupId">
							<option value="0">選択してください</option>
							<c:forEach items="${ mstGroup}" var="gr">
								<option value="${gr.groupId}"
									${gr.groupId == userInfor.groupId ? 'selected="selected"' : ''}>${fn:escapeXml(gr.groupName)}</option>
							</c:forEach>
					</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<td class="lbl_left"><font color="red">*</font> 氏名:</td>
					<td align="left"><input class="txBox" type="text"
						name="fullName" value="${fn:escapeXml(userInfor.fullName)}"
						size="30" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<td class="lbl_left">カタカナ氏名:</td>
					<td align="left"><input class="txBox" type="text"
						name="kanaName" value="${fn:escapeXml(userInfor.kanaName)}"
						size="30" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
					<td align="left"><select name="yearBirthday">
							<c:forEach items="${year}" var="y">
								<option value="${y}"
									${y == userInfor.lsBirthday.get(0) ? 'selected="selected"' : ''}>${y }</option>

							</c:forEach>
					</select>年 <select name="monthBirthday">
							<c:forEach items="${month }" var="m">
								<option value="${m}"
									${m == userInfor.lsBirthday.get(1) ? 'selected="selected"' : ''}>${m }</option>
							</c:forEach>
					</select>月 <select name="dayBirthday">
							<c:forEach items="${day }" var="d">
								<option value="${d}"
									${d == userInfor.lsBirthday.get(2) ? 'selected="selected"' : ''}>${d }</option>
							</c:forEach>
					</select>日</td>
				</tr>
				<tr>
					<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
					<td align="left"><input class="txBox" type="text" name="email"
						value="${fn:escapeXml(userInfor.email)}" size="30"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<td class="lbl_left"><font color="red">*</font>電話番号:</td>
					<td align="left"><input class="txBox" type="text" name="tel"
						value="${fn:escapeXml(userInfor.tel)}" size="30"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr id="password"
					${userInfor.userId != 0 ?'style="display:none;"':''}>
					<td class="lbl_left"><font color="red">*</font> パスワード:</td>
					<td align="left"><input class="txBox" type="password"
						name="password" value="${fn:escapeXml(userInfor.password)}"
						size="30" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr id="passwordConfirm"
					${userInfor.userId != 0 ?'style="display:none;"':''}>
					<td class="lbl_left">パスワード（確認）:</td>
					<td align="left"><input class="txBox" type="password"
						name="passwordConfirm"
						value="${fn:escapeXml(userInfor.passwordConfirm)}" size="30"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<th align="left" colspan="2"><a href="#"
						onclick="appearLevelJapan()">日本語能力</a></th>
				</tr>

				<tr id="levelJapan" class="levelJa">
					<td class="lbl_left">資格:</td>
					<td align="left"><select name="kyu_id">
							<option value="N0">選択してください</option>
							<c:forEach items="${mstJapan }" var="jp">
								<option value="${fn:escapeXml(jp.codeLevel)}"
									${jp.codeLevel == userInfor.codeLevel ? 'selected="selected"' : ''}>${fn:escapeXml(jp.nameLevel)}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr id="startDate" class="levelJa">
					<td class="lbl_left">資格交付日:</td>
					<td align="left"><select name="yearStartDate">
							<c:forEach items="${year}" var="y">
								<option value="${y}"
									${y == userInfor.lsStartDate.get(0) ? 'selected="selected"' : ''}>${y }</option>

							</c:forEach>
					</select>年 <select name="monthStartDate">
							<c:forEach items="${month }" var="m">
								<option value="${m}"
									${m == userInfor.lsStartDate.get(1) ? 'selected="selected"' : ''}>${m }</option>
							</c:forEach>
					</select>月 <select name="dayStartDate">
							<c:forEach items="${day }" var="d">
								<option value="${d}"
									${d == userInfor.lsStartDate.get(2) ? 'selected="selected"' : ''}>${d }</option>
							</c:forEach>
					</select>日</td>
				</tr>
				<tr id="endDate" class="levelJa">
					<td class="lbl_left">失効日:</td>
					<td align="left"><select name="yearEndDate">
							<c:forEach items="${year}" var="y">
								<option value="${y}"
									${y == userInfor.lsEndDate.get(0) ? 'selected="selected"' : ''}>${y }</option>

							</c:forEach>
					</select>年 <select name="monthEndDate">
							<c:forEach items="${month }" var="m">
								<option value="${m}"
									${m == userInfor.lsEndDate.get(1) ? 'selected="selected"' : ''}>${m }</option>
							</c:forEach>
					</select>月 <select name="dayEndDate">
							<c:forEach items="${day }" var="d">
								<option value="${d}"
									${d == userInfor.lsEndDate.get(2) ? 'selected="selected"' : ''}>${d }</option>
							</c:forEach>
					</select>日</td>
				</tr>
				<tr id="totalPoint" class="levelJa">
					<td class="lbl_left">点数:</td>
					<td align="left"><input class="txBox" type="text" name="total"
						value='${fn:escapeXml(userInfor.totalPoint) }'
						size="5" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
			</table>
		</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><c:url
							value="/${userInfor.userId == 0 ? 'ListUser.do':'ViewDetailUser.do'}"
							var="backUrl">
							<c:param name="type" value="back"></c:param>
							<c:param name="user_id" value="${userInfor.userId }"></c:param>
						</c:url> <input class="btn" type="button"
							value="戻る" onclick="location.href='${backUrl}'"/></td>
				</tr>
			</table>

		</div>
		<!-- End vung button -->
	</form>

	<!-- End vung input -->
	<c:import url="footer.jsp"></c:import>

</body>
</html>