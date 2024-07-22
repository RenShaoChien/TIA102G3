<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有會員資料 - listAllMember.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>會員ID</th>
			<th>個人照片</th>
			<th>姓名</th>
			<th>帳號</th>
			<th>密碼</th>
			<th>電子郵件</th>
			<th>性別</th>
			<th>手機</th>
			<th>地址</th>
			<th>生日</th>
			<th>註冊日期</th>
			<th>教練ID</th>
			<th>收件人姓名</th>
			<th>收件地址</th>
			<th>收件人手機</th>
			<th>信用卡卡號</th>
			<th>信用卡有效日期</th>
			<th>卡片後3碼</th>
			<th>卡片手機</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${memberVO.memberID}</td>
				<td><img src="<%=request.getContextPath()%>/MemberDBGifReader4?memberID=${memberVO.memberID}"  width="100" height="100" /></td>
				<td>${memberVO.name}</td>
				<td>${memberVO.account}</td>
				<td>${memberVO.password}</td>
				<td>${memberVO.email}</td>
				<td>${memberVO.gender}</td>
				<td>${memberVO.phone}</td>
				<td>${memberVO.address}</td>
				<td>${memberVO.bD}</td>
				<td>${memberVO.regDate}</td>
				<td>${memberVO.coachMemberID}</td>
				<td>${memberVO.receiver}</td>
				<td>${memberVO.receiverAddress}</td>
				<td>${memberVO.receiverPhone}</td>
				<td>${memberVO.cardName}</td>
				<td>${memberVO.cardValidTime}</td>
				<td>${memberVO.cardLast3No}</td>
				<td>${memberVO.cardPhone}</td>

				<td>
					<FORM METHOD="post"
						ACTION="member.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="memberID" value="${memberVO.memberID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="member.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="memberID" value="${memberVO.memberID}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>