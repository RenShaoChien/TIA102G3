<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member.controller.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java(Concroller), �s�Jreq��memberVO����
%>

<html>
<head>
<title>�|����� - listOneMember.jsp</title>

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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�|����� - listOneMember.jsp</h3>
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