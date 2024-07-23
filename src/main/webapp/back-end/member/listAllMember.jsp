<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member.controller.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ��|����� - listAllMember.jsp</title>

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

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ��|����� - listAllMember.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�|��ID</th>
			<th>�ӤH�Ӥ�</th>
			<th>�m�W</th>
			<th>�b��</th>
			<th>�K�X</th>
			<th>�q�l�l��</th>
			<th>�ʧO</th>
			<th>���</th>
			<th>�a�}</th>
			<th>�ͤ�</th>
			<th>���U���</th>
			<th>�нmID</th>
			<th>����H�m�W</th>
			<th>����a�}</th>
			<th>����H���</th>
			<th>�H�Υd�d��</th>
			<th>�H�Υd���Ĥ��</th>
			<th>�d����3�X</th>
			<th>�d�����</th>
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
						<input type="submit" value="�ק�"> <input type="hidden"
							name="memberID" value="${memberVO.memberID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="member.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
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