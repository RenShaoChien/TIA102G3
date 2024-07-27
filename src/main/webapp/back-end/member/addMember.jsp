<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.tia102g3.member.model.MemberVO" %>

<%
//��com.member.controller.memberServlet.java��238��s�Jreq��memberVO���� (������J�榡�����~�ɪ�memberVO����)
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
--<%=memberVO == null%>--${memberVO.memberID}--
<!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�|����Ʒs�W - addMember.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�|����Ʒs�W - addMember.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
		<table>

			<tr>
				<td>�ӤH�Ӥ�:</td>
				<td><input type="file" name="personalPhotos"
					value="<%=(memberVO == null) ? "" : memberVO.getPersonalPhotos()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�m�W:</td>
				<td><input type="text" name="name"
					value="<%=(memberVO == null) ? "" : memberVO.getName()%>" size="45" /></td>
			</tr>

			<tr>
				<td>�b��:</td>
				<td><input type="text" name="account"
					value="<%=(memberVO == null) ? "" : memberVO.getAccount()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�K�X:</td>
				<td><input type="text" name="password"
					value="<%=(memberVO == null) ? "" : memberVO.getPassword()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�q�l�l��:</td>
				<td><input type="text" name="email"
					value="<%=(memberVO == null) ? "" : memberVO.getEmail()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�ʧO:</td>
				<td><input type="text" name="gender"
					value="<%=(memberVO == null) ? "" : memberVO.getGender()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>���:</td>
				<td><input type="text" name="phone"
					value="<%=(memberVO == null) ? "" : memberVO.getPhone()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�a�}:</td>
				<td><input type="text" name="address"
					value="<%=(memberVO == null) ? "" : memberVO.getAddress()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�ͤ�:</td>
				<td><input id ="f_date1" type="date" name="bD"
					value="<%=(memberVO == null) ? "" : memberVO.getbD()%>" size="45" /></td>
			</tr>

			<tr>
				<td>���U���:</td>
				<td><input type="date" name="regDate"
					value="<%=(memberVO == null) ? "" : memberVO.getRegDate()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�нmID:</td>
				<td><input type="text" name="coachMemberID"
					value="<%=(memberVO == null) ? "" : memberVO.getCoachMemberID()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>����H�m�W:</td>
				<td><input type="text" name="receiver"
					value="<%=(memberVO == null) ? "" : memberVO.getReceiver()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>����a�}:</td>
				<td><input type="text" name="receiverAddress"
					value="<%=(memberVO == null) ? "" : memberVO.getReceiverAddress()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>����H���:</td>
				<td><input type="text" name="receiverPhone"
					value="<%=(memberVO == null) ? "" : memberVO.getReceiverPhone()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�H�Υd�d��:</td>
				<td><input type="text" name="cardName"
					value="<%=(memberVO == null) ? "" : memberVO.getCardName()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�H�Υd���Ĥ��:</td>
				<td><input type="text" name="cardValidTime"
					value="<%=(memberVO == null) ? "" : memberVO.getCardValidTime()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�d����3�X:</td>
				<td><input type="text" name="cardLast3No"
					value="<%=(memberVO == null) ? "" : memberVO.getCardLast3No()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�d�����:</td>
				<td><input type="text" name="cardPhone"
					value="<%=(memberVO == null) ? "" : memberVO.getCardPhone()%>"
					size="45" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%
java.sql.Date regDate = null;
try {
	regDate = memberVO.getRegDate();
} catch (Exception e) {
	regDate = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=regDate%>
	', // value:   new Date(),
	disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	startDate:	            '2017/07/10',  // �_�l��
	minDate:               '-1970-01-01', // �h������(���t)���e
	maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	     1.�H�U���Y�@�Ѥ��e������L�k���
	     var somedate1 = new Date('2024-07-15');
	     $('#f_date1').datetimepicker({
	         beforeShowDay: function(date) {
	       	  if (  date.getYear() <  somedate1.getYear() || 
			           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
			           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	             ) {
	                  return [false, ""]
	             }
	             return [true, ""];
	     }});

	     2.�H�U���Y�@�Ѥ��᪺����L�k���
	     var somedate2 = new Date('2024-07-15');
	     $('#f_date1').datetimepicker({
	         beforeShowDay: function(date) {
	       	  if (  date.getYear() >  somedate2.getYear() || 
			           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
			           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	             ) {
	                  return [false, ""]
	             }
	             return [true, ""];
	     }});

	     3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	     var somedate1 = new Date('2024-07-15');
	     var somedate2 = new Date('2024-07-25');
	     $('#f_date1').datetimepicker({
	         beforeShowDay: function(date) {
	       	  if (  date.getYear() <  somedate1.getYear() || 
			           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
			           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
			             ||
			            date.getYear() >  somedate2.getYear() || 
			           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
			           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	             ) {
	                  return [false, ""]
	             }
	             return [true, ""];
	     }});
</script>
</html>