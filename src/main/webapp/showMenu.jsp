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
<a href="home.jsp">
<div class="toplink">IDEALLE</div>
</a>
<div class="noborder">
<h1>メニュー紹介</h1>
<table>
<tr>
<th style="border-bottom:1px #333 solid;">■コース料理</th>
<th>&nbsp;</th>
<td>&nbsp;</td>
</tr>
<%
ArrayList<Course> al1 = (ArrayList<Course>)request.getAttribute("courseList");
int courseId = -1;
String col01;
String col02;
String tdbd = "";
for(int i=0;i<al1.size();i++){
	Course c = al1.get(i);
	if(c.getCourseId() != courseId){
		courseId = c.getCourseId();
		col01 = "<span style='font-weight:bold;font-size:120%;'><br/>"
				+ c.getCourseName()
				+ "</span><br/><span style='font-size:90%;'>&nbsp;&nbsp;"
				+ c.getDetail()
				+ "</span><br/><br/>&nbsp;&nbsp;・"
				+ c.getMenuName()
				+ "<br/>";
		NumberFormat nfCur = NumberFormat.getCurrencyInstance();  //通貨形式
		col02 = nfCur.format(c.getPrice());
		tdbd = "border-top:1px #333 solid;";
	}else{
		col01 = "&nbsp;&nbsp;・"+c.getMenuName();
		col02 = "&nbsp;";
		tdbd = "";
	}
%>
	<tr>
	<td>&nbsp;</td>
	<td style="text-align:left;<%= tdbd %>"><%= col01 %></td>
	<td style="text-align:right;vertical-align:center;<%= tdbd %>"><%= col02 %></td>
	</tr>
<% } %>
<tr>
<th style="border-bottom:1px #333 solid;"><br/><br/>■一品料理</th>
<th>&nbsp;</th>
<td>&nbsp;</td>
</tr>
<%
ArrayList<Menu> al2 = (ArrayList<Menu>)request.getAttribute("menuList");
int typeId = -1;
String col03;
String col04;
tdbd = "border-top:1px #333 solid;";
for(int i=0;i<al2.size();i++){
	Menu m = al2.get(i);
	if(m.getTypeId() != typeId){
		typeId = m.getTypeId();
		col03 = "<br/><br/><span style='font-weight:bold;font-size:120%;'>"
				+ m.getTypeName()
				+ "</span><br/><br/>&nbsp;&nbsp;"
				+ m.getMenuName();
	}else{
		col03 = "<br/>&nbsp;&nbsp;"
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
	<td style="text-align:left;<%= tdbd %>"><%= col03 %></td>
	<td style="text-align:right;vertical-align:bottom;<%= tdbd %>"><%= col04 %></td>
	</tr>
<% } %>
</table>
<a href="./home.jsp">戻る</a>
</div>
<div class="site-footer">
<div class="copyright">Restaurante IDEALLE</div>
</div>
</body>
</html>
