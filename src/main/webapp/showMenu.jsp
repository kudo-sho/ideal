<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="model.Course"%>
<%@page import="model.Menu"%>
<%@page import="java.util.List"%>
<%@page import="java.text.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メニュー紹介</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style type="text/css">
h1{font-size:60px;}
div{text-align:center;}
</style>
</head>
<body>
<div class="noborder">
<h1>メニュー紹介</h1>
<table>
<tr>
<th>■コース料理</th>
<th>&nbsp;</th>
<td>&nbsp;</td>
</tr>
<%
ArrayList<Course> al1 = (ArrayList<Course>)request.getAttribute("courseList");
int courseId = -1;
String col01;
String col02;
for(int i=0;i<al1.size();i++){
	Course c = al1.get(i);
	if(c.getCourseId() != courseId){
		courseId = c.getCourseId();
		col01 = "<span style='font-weight:bold;'><hr/>"
				+ c.getCourseName()
				+ "</span><br/><span style='font-size:90%;'>&nbsp;&nbsp;"
				+ c.getDetail()
				+ "</span><br/>&nbsp;&nbsp;・"
				+ c.getMenuName();
		NumberFormat nfCur = NumberFormat.getCurrencyInstance();  //通貨形式
		col02 = nfCur.format(c.getPrice());
	}else{
		col01 = "&nbsp;&nbsp;・"+c.getMenuName();
		col02 = "&nbsp;";
	}
%>
	<tr>
	<td>&nbsp;</td>
	<td align="left"><%= col01 %></td>
	<td style="text-align:right;vertical-align:center;"><%= col02 %></td>
	</tr>
<% } %>
<tr>
<th><br/>■一品料理</th>
<th>&nbsp;</th>
<td>&nbsp;</td>
</tr>
<%
ArrayList<Menu> al2 = (ArrayList<Menu>)request.getAttribute("menuList");
int typeId = -1;
String col03;
String col04;
for(int i=0;i<al2.size();i++){
	Menu m = al2.get(i);
	if(m.getTypeId() != typeId){
		typeId = m.getTypeId();
		col03 = "<br/><span style='font-weight:bold;'>"
				+ m.getTypeName()
				+ "</span><hr/>&nbsp;&nbsp;"
				+ m.getMenuName();
	}else{
		col03 = "<hr/>&nbsp;&nbsp;"
				+ m.getMenuName();
	}
	String detail = m.getDetail();
	if(!detail.isEmpty()){
		col03 = col03
				+ "<br/><span style='font-size:90%;'>&nbsp;&nbsp;・"
				+ m.getDetail()
				+ "</span>";
	}
	NumberFormat nfCur = NumberFormat.getCurrencyInstance();  //通貨形式
	col04 = nfCur.format(m.getPrice());
%>
	<tr>
	<td>&nbsp;</td>
	<td align="left"><%= col03 %></td>
	<td style="text-align:right;vertical-align:bottom;"><%= col04 %></td>
	</tr>
<% } %>
</table>


<a href="./home.jsp">戻る</a>


</div>
</body>
</html>
