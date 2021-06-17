<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,controller.*,model.*,java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>コース情報削除</title>
<style>
<!--
	*{padding:5px;}
	h1{text-align:center; background:#007B66; color:#ECFFF3;}
	table{width:600px; height:450px; border-collapse:collapse}
	th{text-align:center; width:20%; background:#A4FFDB; color:#007B66;}
	td{background:#E3FFE3; width:80%;}
	th#bottom{text-align:right;}
	p{text-align:center; font-size:18pt;}
	-->
</style>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
%>

<table border="1" align="center">
	<h1>コース情報削除</h1>
	<caption><tr align="left"><font color="red" size="5">
		<%
	String msg = null;
	request.setCharacterEncoding("utf-8");
	msg = (String)request.getAttribute("msg");
	if(msg != null)
	out.print(msg);
	%>
	</font></tr></caption>
	<br/>

<%
int menuID;
//各メニューネームを入れる
String t200 = "";
String t210 = "";
String t220 = "";
String t300 = "";
String t310 = "";
String t400 = "";

Course c;
try {
	menuID = Integer.parseInt(request.getParameter("DmenuID"));
	} catch (Exception e) {
		menuID = 0;
	}

Course course = Course.getCourse(menuID);
String cName = course.getCourseName();
int cPrice = course.getPrice();
int cOrderFlg = course.getOrderFlg();
String cDetail = course.getDetail();

NumberFormat nfc = NumberFormat.getCurrencyInstance();

ArrayList<Course> oneCourse = Course.getOneCourse(menuID);

//コースにメニューが一つでもあれば各分類ごとにmenuNameを入れる
if(oneCourse.size() != 0){
for(int x = 0 ;x < oneCourse.size(); x++){
	c = oneCourse.get(x);
	if(c.getTypeId() == 200){
		t200 = c.getMenuName();
	}
	if(c.getTypeId() == 210){
		t210 = c.getMenuName();
	}
	if(c.getTypeId() == 220){
		t220 = c.getMenuName();
	}
	if(c.getTypeId() == 300){
		t300 = c.getMenuName();
	}
	if(c.getTypeId() == 310){
		t310 = c.getMenuName();
	}
	if(c.getTypeId() == 400){
		t400 = c.getMenuName();
	}
}
}
%>

	<tr>
	<th>コース名</th>
	<td><%= cName %></td>
	</tr>
	<tr>
	<th>価格</th>
	<td><%= nfc.format(cPrice) %></td>
	</tr>
	<%
	String[] orderFlg = { "不可", "可" };
	%>

	<tr>
		<th>オーダー</th>
		<td><%= orderFlg[cOrderFlg] %>
		</td>
	</tr>
	<tr height="100px">
		<th>コメント</th>
		<td><%= cDetail %>
		</td>
	</tr>
	<tr>
		<th>前菜</th>
		<td><%= t200 %>
		</td>
	</tr>
	<tr>
		<th>スープ</th>
		<td><%= t210 %>
		</td>
	</tr>
	<tr>
		<th>パスタ</th>
		<td><%= t220 %>
		</td>
	</tr>
	<tr>
		<th>肉料理</th>
		<td><%= t300 %>
		</td>
	</tr>
	<tr>
		<th>魚料理</th>
		<td><%= t310 %>
		</td>
	</tr>
	<tr>
		<th>デザート</th>
		<td><%= t400 %>
		</td>
	</tr>
	<tr>

		<td align="right" colspan="3">
		<form id="frmdel" name="frmdel" action="CourseOperationSvl" method="post">
		<input type="hidden" name="mode" value="13" />
		<input type="hidden" name="courseID" value="<%= course.getCourseId() %>" />
		<input type="hidden" name="typeID" value="<%= course.getTypeId() %>" />
		<input type="submit" value="   削除   " />
		</form></td>
	</tr>
</table>
<p><a href="MenuMaintenanceSvl">メニューメンテナンスに戻る</a> </p>
</body>
</html>