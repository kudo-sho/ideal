<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,controller.*,model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>コースメニュー登録</title>
<style>
<!--
h1 {
	text-align: center;
	background: #007B66;
	color: #ECFFF3;
}

table {width ="600px;height ="400px;

}

th {
	text-align: center;
	background: #A4FFDB;
	color: #007B66;
}

td {
	background: #E3FFE3;
}

th#bottom {
	text-align: right;
}

p {
	text-align: center;
	font-size: 18pt;
}
-->
</style>

<script type="text/javascript">
<!--
	function check(obj) {
		var msg = "";
		if (obj.courseName == null) {
			System.out.print("");
		}
		if (obj.courseName.value.length < 1) {
			msg += "コース名を入力してください。\n";
			return false;
		}
		if (obj.price.value.length < 1) {
			msg += "価格を入力してください。\n";
			return false;
		}
		if (!obj.price.value.match(/^[0-9]+$/g)) {
			msg += "価格を数値で入力してください。\n";
			return false;
		}
	}
//-->
</script>
</head>
<body>
	<%
		int typeID;
		try {
			typeID = Integer.parseInt(request.getParameter("typeID"));
		} catch (NumberFormatException e) {
			typeID = 100;
		}
	%>
	<jsp:useBean id="mType" class="java.util.ArrayList" scope="request" />
	<jsp:useBean id="menu" class="java.util.ArrayList" scope="request" />

	<table border="1" , align="center">
		<h1>コース登録</h1>
		<caption>
			<tr align="left">
				<font color="red">メッセージ</font>
			</tr>
		</caption>
		<br />
		<%
			String msg = null;
			request.setCharacterEncoding("utf-8");
			msg = (String) request.getAttribute("msg");
			if (msg != null)
				out.print(msg);
		%>

		<form action="MenuOperationSvl" method="post"
			onSubmit="return dataCheck(obj);">
			<tr>
				<th>コース名※</th>
				<td><input type="text" name="menuName" size="50" /></td>
			</tr>
			<tr>
				<th>価格※</th>
				<td><input type="text" name="price" size="30" /></td>
			</tr>
			<tr>
				<th>オーダー可※</th>
				<td><input type="radio" name="orderFlg" value="1" />可 <input
					type="radio" name="orderFlg" value="0" />不可</td>
			<tr>
				<th>コメント</th>
				<td><textarea name="detail" cols="80" rows="10"></textarea></td>
			</tr>
			</tr>
			<tr>
				<th>前菜</th>
				<td><select name="typeId">
						<%
							for (Object o : mType) {
								MenuType mt = (MenuType) o;
								String selected = "";
								if (typeID == mt.getTypeId()) {
									selected = "selected";
								} else {
									selected = "";
								}
						%>
						<option value="<%=mt.getTypeId()%>" selected="<%=selected%>">
							<%=mt.getTypeName()%></option>
						<%
							}
						%>
				</select></td>
				</tr>
				<tr>
				<th>スープ</th>
				<td><select name="typeId">
						<%
							for (Object o : mType) {
								MenuType mt = (MenuType) o;
								String selected = "";
								if (typeID == mt.getTypeId()) {
									selected = "selected";
								} else {
									selected = "";
								}
						%>
						<option value="<%=mt.getTypeId()%>" selected="<%=selected%>">
							<%=mt.getTypeName()%></option>
						<%
							}
						%>
				</select></td>
				<tr>
				<th>パスタ</th>
				<td><select name="typeId">
						<%
							for (Object o : mType) {
								MenuType mt = (MenuType) o;
								String selected = "";
								if (typeID == mt.getTypeId()) {
									selected = "selected";
								} else {
									selected = "";
								}
						%>
						<option value="<%=mt.getTypeId()%>" selected="<%=selected%>">
							<%=mt.getTypeName()%></option>
						<%
							}
						%>
				</select></td>
				</tr>
				<tr>
				<th>肉料理</th>
				<td><select name="typeId">
						<%
							for (Object o : mType) {
								MenuType mt = (MenuType) o;
								String selected = "";
								if (typeID == mt.getTypeId()) {
									selected = "selected";
								} else {
									selected = "";
								}
						%>
						<option value="<%=mt.getTypeId()%>" selected="<%=selected%>">
							<%=mt.getTypeName()%></option>
						<%
							}
						%>
				</select></td>
				</tr>
				<tr>
				<th>魚料理</th>
				<td><select name="typeId">
						<%
							for (Object o : mType) {
								MenuType mt = (MenuType) o;
								String selected = "";
								if (typeID == mt.getTypeId()) {
									selected = "selected";
								} else {
									selected = "";
								}
						%>
						<option value="<%=mt.getTypeId()%>" selected="<%=selected%>">
							<%=mt.getTypeName()%></option>
						<%
							}
						%>
				</select></td>
				</tr>
				<tr>
				<th>デザート</th>
				<td><select name="typeId">
						<%
							for (Object o : mType) {
								MenuType mt = (MenuType) o;
								String selected = "";
								if (typeID == mt.getTypeId()) {
									selected = "selected";
								} else {
									selected = "";
								}
						%>
						<option value="<%=mt.getTypeId()%>" selected="<%=selected%>">
							<%=mt.getTypeName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<input type="hidden" name="mode" value="MenuUpdateSvl.java" />
			<tr>
				<td align="right" colspan="3"><font color="red">※は必修入力です。</font>
					<input type="submit" value="登録" />
			</tr>
		</form>
		&emsp;
	</table>
	<p>
		<a href="menuMaintenance?typeID=<%=typeID%>">メニューメンテナンスに戻る</a>
	</p>
</body>
</html>