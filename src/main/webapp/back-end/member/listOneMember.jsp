<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member.controller.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java(Concroller), 存入req的memberVO物件
%>

<html>
<head>
<title>會員資料 - listOneMember.jsp</title>

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
	width: 600px;
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料 - listOneMember.jsp</h3>
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
		
		<tr>
			<td><%=memberVO.getMemberID()%></td>
			<td><img src="<%=request.getContextPath()%>/MemberDBGifReader4?memberID=${memberVO.memberID}"  width="100" height="100" /></td>
			<td><%=memberVO.getName()%></td>
			<td><%=memberVO.getAccount()%></td>
			<td><%=memberVO.getPassword()%></td>
			<td><%=memberVO.getEmail()%></td>
			<td><%=memberVO.getGender()%></td>
			<td><%=memberVO.getPhone()%></td>
			<td><%=memberVO.getAddress()%></td>
			<td><%=memberVO.getbD()%></td>
			<td><%=memberVO.getRegDate()%></td>
			<td><%=memberVO.getCoachMemberID()%></td>
			<td><%=memberVO.getReceiver()%></td>
			<td><%=memberVO.getReceiverAddress()%></td>
			<td><%=memberVO.getReceiverPhone()%></td>
			<td><%=memberVO.getCardName()%></td>
			<td><%=memberVO.getCardValidTime()%></td>
			<td><%=memberVO.getCardLast3No()%></td>
			<td><%=memberVO.getCardPhone()%></td>
		</tr>
	</table>

</body>
</html>