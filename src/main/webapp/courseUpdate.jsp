<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,java.text.*,controller.*,model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>コース情報変更</title>
<style>
<!--
*{padding:5px;}

h1 {
	text-align: center;
	background: #007B66;
	color: #ECFFF3;
}

table {
	width: 600px;
	height: 450px;
	border-collapse: collapse;
}

th {
	width:20%;
	text-align: center;
	background: #A4FFDB;
	color: #007B66;
}

td {
	width:80%;
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
		if (obj.courseName.value.length < 1) {
			msg += "メニュー名を入力してください。\n";
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
			typeID = Integer.parseInt(request.getParameter("CtypeID"));
		} catch (NumberFormatException e) {
			typeID = 100;
		}

		ArrayList<ArrayList<Menu>> al = (ArrayList<ArrayList<Menu>>)request.getAttribute("typeMenuList");
		ArrayList<Course> alc = (ArrayList<Course>)request.getAttribute("oneCourse");

		for (Object o : alc) {
			ArrayList<Course> alc1 = (ArrayList<Course>) o;
			}

	%>


	<table border="1"  align="center">
		<h1>コース情報変更</h1>
		<caption>
			<tr align="left">
				<font color="red" size="5">
		<%
		String msg = null;
		request.setCharacterEncoding("utf-8");
		msg = (String) request.getAttribute("msg");
		if (msg != null)
			out.print(msg);
		%>
				</font>
			</tr>
		</caption>
		<br />


		<form action="CourseOperationSvl" method="post"
			onsubmit="return check(this);">
			<tr>
				<th>コース名 ※</th>
				<td><input type="text" name="menuName" size="50" /><%= alc.getCourseName %></td>
			</tr>
			<tr>
				<th>価格 ※</th>
				<td><input type="text" name="price" size="20" />??????????</td>
			</tr>
			<tr>
				<th>オーダー ※</th>
				<td><input	type="radio" name="orderFlg" value="0" />不可
				<input type="radio" name="orderFlg" value="1" />可
				</td>
			<tr>
				<th>コメント</th>
				<td><textarea name="detail" cols="55" rows="5" value="??????????"></textarea></td>
			</tr>
			</tr>
			<tr>
				<th>前菜</th>
				<td><select name="typeId">
						<%
							for (Object o : al) {
								ArrayList<Menu> al2 = (ArrayList<Menu>)o;
								for(Object o2 : al2){
									Menu m = (Menu)o2;
								String selected = "";
								if (typeID == m.getTypeId()) {
									selected = "selected";
								} else {
									selected = "";
								}
						%>
						<option value="<%= m.getMenuId() %>" <%= selected %>>
							<%= m.getTypeName() %></option>
						<%
								}
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
					<input type="submit" value="変更" />
			</tr>
		</form>
		&emsp;
	</table>
	<p>
		<a href="menuMaintenance?typeID=<%=typeID%>">メニューメンテナンスに戻る</a>
	</p>
</body>
</html>