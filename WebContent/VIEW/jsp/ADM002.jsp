<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- format theo jstl -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value="/VIEW/css/style.css"></c:url>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/user.js"></script>
<title>List User</title>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<form action="${pageContext.request.contextPath }/ListUser.do"
		method="get" name="search">
		<table class="tbl_input auto" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input autofocus class="txBox" type="text"
								name="fullName" value='${fn:escapeXml(fullName)}' size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td><input class="type" type="hidden" name="type"
								value="search"></td>
						</tr>

						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="groupId">
									<option value="0">全て</option>
									<c:forEach items="${mstGroup }" var="gr">
										<option value="${gr.groupId }"
											${gr.groupId == groupId ? 'selected="selected"' : ''}>${fn:escapeXml(gr.groupName)}</option>
									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input
									class="btn" type="button" value="新規追加" onclick="location.href='${pageContext.request.contextPath}/AddUserInput.do'"/></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<table cols="*,*,*,*,*,*,*,*,*" class="tbl_list auto" border="1" cellpadding="4" cellspacing="0"
		width="80%">

		<tr class="tr2">
			<th align="center" width="20px">ID</th>
			<th align="left">氏名 <c:url value="/ListUser.do" var="sortUrl">
					<c:param name="type" value="sort"></c:param>
					<c:param name="name" value="${name}"></c:param>
					<c:param name="groupId" value="${groupId }"></c:param>
					<c:param name="currentPage" value="${currentPage}"></c:param>
				</c:url> <!-- link sắp xếp trường full_name --> <c:if
					test="${sortByFullName eq 'asc' }">
					<a href="${sortUrl }&sortByFullName=desc&sortType=full_name">▲▽</a>
				</c:if> <c:if test="${sortByFullName eq 'desc' }">
					<a href="${sortUrl }&sortByFullName=asc&sortType=full_name">△▼</a>
				</c:if>
			</th>
			<th align="left">生年月日</th>
			<th align="left">グループ</th>
			<th align="left">メールアドレス</th>
			<th align="left" >電話番号</th>
			<th align="left">日本語能力 <!-- link sắp xếp trường name_level --> <c:if
					test="${sortByCodeLevel eq 'asc' }">
					<a href="${sortUrl }&sortByCodeLevel=desc&sortType=name_level">▲▽</a>
				</c:if> <c:if test="${sortByCodeLevel eq 'desc' }">
					<a href="${sortUrl }&sortByCodeLevel=asc&sortType=name_level">△▼</a>
				</c:if>
			</th>
			<th align="left">失効日 <c:if test="${sortByEndDate eq 'desc'}">
					<a href="${sortUrl }&sortByEndDate=asc&sortType=end_date">△▼</a>
				</c:if> <c:if test="${sortByEndDate eq 'asc'}">
					<a href="${sortUrl}&sortByEndDate=desc&sortType=end_date">▲▽</a>
				</c:if>
			</th>
			<th align="left">点数</th>
		</tr>
		
		<c:forEach items="${listUs}" var="uLi">
			<tr>
				<td align="right"><a
					href="${pageContext.request.contextPath }/ViewDetailUser.do?user_id=${uLi.userId}">${uLi.userId }</a>
				</td>
				<td>${fn:escapeXml(uLi.fullName)}</td>
				<td align="center"><fmt:formatDate pattern = "yyyy/MM/dd" value="${uLi.birthday}"/> </td>
				<td><c:out value="${uLi.groupName }"></c:out></td>
				<td>${fn:escapeXml(uLi.email)}</td>
				<td>${fn:escapeXml(uLi.tel )}</td>
				<td>${fn:escapeXml(uLi.nameLevel )}</td>
				<td align="center"><fmt:formatDate pattern = "yyyy/MM/dd" value="${uLi.startDate }"/></td>
				<td align="right"><c:if test="${uLi.total ne 0 }">${uLi.total}</c:if></td>
			</tr>
		</c:forEach>



	</table>
	${note}
	<!-- End vung hien thi danh sach user -->

	<!-- Begin vung paging -->
	<table class="lbl_paging">
		<tr>
			<td><c:url value="/ListUser.do" var="pagining">
					<c:param name="type" value="paging"></c:param>
					<c:param name="fullName" value="${fullName}"></c:param>
					<c:param name="groupId" value="${groupId }"></c:param>
					<c:param name="sortType" value="${sortType}"></c:param>
					<c:param name="sortByFullName" value="${sortByFullName}"></c:param>
					<c:param name="sortByCodeLevel" value="${sortByCodeLevel}"></c:param>
					<c:param name="sortByEndDate" value="${sortByEndDate }"></c:param>
				</c:url> 
				<c:if test="${checkCurrentBack eq 0 }">
					<a href="${pagining}&currentPage=${currentPageBack}"> &lt;&lt;</a>&nbsp;
			</c:if> 
			<c:if test="${totalPage != 1}">
					<c:forEach items="${listPage}" var="page">
						<td><c:if test="${currentPage eq page }">
							${ currentPage}&nbsp;
			 </c:if> 
			 <c:if test="${currentPage ne page }">
								<a href="${pagining}&currentPage=${page}">${page}</a> &nbsp;
			 </c:if>
					</c:forEach>
				</c:if> 
				<c:if test="${checkCurrentNext eq 0 }">
					<a href="${pagining}&currentPage=${currentPageNext}">&gt;&gt;</a>
				</c:if></td>
		</tr>
	</table>


	<!-- End vung paging -->
	<!-- Vùng footer -->
	<c:import url="footer.jsp"></c:import>
</body>
</html>