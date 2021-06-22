<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<%@ page import = "javax.naming.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>予約取消画面</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<a href="home.jsp">
<div class="toplink">IDEALLE</div>
</a>
<%
HttpSession usrInfo = request.getSession(false);
String name = (String)usrInfo.getAttribute("usrName");
String message = (String)request.getAttribute("msg");
if(message == null) {message = "&nbsp;";}
%>

<h1><%= name %>様ご予約取消</h1>
<%= message %>

<%
	Reserve re = (Reserve)request.getAttribute("reserve") ;
%>


<form action="ReserveOperationSvl" method = "post" >
	<table boder="1">
		<tr>
			<th>予約番号</th>
			<td><%= re.getRsvId() %></td>
		</tr>
		<tr>
			<th>日付</th>
			<td><%= re.getRsvYy() %>年<%= re.getRsvMm() %>月<%= re.getRsvDd() %>日</td>
		</tr>
		<tr>
			<th>時刻</th>
			<td><%= re.getRsvHh() %>時<%= re.getRsvMi() %>分</td>
		</tr>
		<tr>
			<th>人数</th>
			<td><%= re.getPerson() %>人</td>
		</tr>
		<tr>
			<th>コース</th>
			<td><%= re.getCourseName() %></td>
		</tr>
		<tr>
			<td colspan="2" class="cent">
			<input type="submit" value="取消" />
			<input type = "hidden" name = "rsvId" value ="<%= re.getRsvId() %>"/>
			<input type = "hidden" name = "mode" value ="削除処理"/>
			</td>
		</tr>
	</table>
</form>
<a href="ReserveListSvl">予約一覧に戻る</a>

<div class="site-footer">
<div class="copyright">Restaurante IDEALLE</div>
</div>
</body>
</html>