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
	if (obj.price.value.length < 1) {
		msg += "価格を入力してください。\n";
	}else if (!obj.price.value.match(/^[0-9]+$/g)) {
		msg += "価格を数値(半角)で入力してください。\n";
	}

	var i;
	for (i = 0; i < obj.orderFlg.length; i++) {
		if (obj.orderFlg[i].checked)
			break;
	}
	if (i >= obj.orderFlg.length) {
		msg += "オーダーの可否をチェックしてください。\n";
	}

	if(msg != ""){
		window.alert(msg);
		return false;
	}
	return true;
}
//-->
</script>
</head>
<body>

	<%
	int typeID;
	Course c = null;
	Course course = null;

	//各メニューネームを入れる
	String[] tmn = {"","","","","",""};
	int[] tid = {200,210,220,300,310,400};


		try {
			typeID = Integer.parseInt(request.getParameter("typeID"));
		} catch (NumberFormatException e) {
			typeID = 100;
		}

		ArrayList<ArrayList<Menu>> al = (ArrayList<ArrayList<Menu>>)request.getAttribute("typeMenuList");
		ArrayList<Course> oneCourse = (ArrayList<Course>)request.getAttribute("oneCourse");
		//仕様書通りだと、コースに何もメニューが登録されてない時コース名やらも持ってこれないので↓追加
		course = (Course)request.getAttribute("course");

		if(oneCourse.size() != 0){
			for(int x = 0 ;x < oneCourse.size(); x++){
				c = oneCourse.get(x);
				for(int y = 0;y < tmn.length; y++){
				if(c.getTypeId() == tid[y]){
					tmn[y] = c.getMenuName();
				}
				}
			}
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

			<%
			//各項目がnullの時、空白を表示
			if (course.getCourseName() == null) {
				course.setCourseName("");
			}
			if (course.getDetail() == null) {
				course.setDetail("");
			}
			%>

			<tr>
				<th>コース名<font color="red">※</font></th>
				<td><input type="text" name="courseName" size="50" value="<%= course.getCourseName() %>" /></td>
			</tr>
			<tr>
				<th>価格<font color="red">※</font></th>
				<td><input type="text" name="price" size="20" value="<%= course.getPrice() %>"/></td>
			</tr>
			<tr>
				<th>オーダー<font color="red">※</font></th>
				<td>
	<%
	String[] order = { "不可", "可" };
	for (int i = 0; i < order.length; i++) {
	String checked = "";
	if(order[i].equals("不可") && course.getOrderFlg() != 1){
		checked = "checked";
	}
	if(order[i].equals("可") && course.getOrderFlg() == 1){
		checked = "checked";
	}
	%>
				<input	type="radio" name="orderFlg" value="<%= i %>" <%= checked %> /><%= order[i] %> &nbsp;
				<%
	}
				%>
				</td>
			<tr>
				<th>コメント</th>
				<td><textarea name="detail" cols="55" rows="5"><%= course.getDetail() %></textarea></td>
			</tr>
			</tr>

			<%
			String[] type = { "appetizerID", "soupID", "pastaID",
					"meatID", "fishID", "dessertID" };
			String[] typeName = { "前菜", "スープ", "パスタ",
					"肉料理", "魚料理", "デザート" };
			int x = 0;

			for (ArrayList<Menu> alm : al) {
				int y = 0;

				for (Menu m : alm) {
					String selected = "";
					if (tmn[x].equals(m.getMenuName())) {
				selected = "selected";
					} else {
				selected = "";
					}
					if(y == 0){
			%>

			<tr>
				<th><%= typeName[x] %></th>
				<td><select name="<%= type[x] %>">
						<option value="0" selected></option>
						<%
						}
						%>
						<option value="<%=m.getMenuId()%>" <%=selected%>>
							<%=m.getMenuName()%></option>
						<%
						y++;
						}
						%>
				</select></td>
			</tr>
			<%
			x++;
			}
			%>

			<input type="hidden" name="mode" value="12" />
			<input type="hidden" name="courseID" value="<%= course.getCourseId() %>" />
			<input type="hidden" name="typeID" value="<%= course.getTypeId() %>" />
			<tr>
				<td align="right" colspan="3"><font color="red">※は必修入力です。</font>
					<input type="submit" value="   変更   " /></td>
			</tr>
		</form>
		&emsp;
	</table>
	<p>
		<a href="MenuMaintenanceSvl?typeID=<%= course.getTypeId() %>">メニューメンテナンスに戻る</a>
	</p>
</body>
</html>