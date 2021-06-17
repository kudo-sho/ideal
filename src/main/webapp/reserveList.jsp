<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>予約情報一覧</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style type="text/css">
h1{font-size:60px;}
td{text-align:center;}
</style>
</head>
<body>
<%
HttpSession usrInfo = request.getSession(false);
String name = (String)usrInfo.getAttribute("usrName");
String message = (String)request.getAttribute("msg");
if(message == null) {message = "";}
%>
<h1><%= name %>様、ご予約一覧</h1>
<%= message %>
<table>
<tr><th>NO</th><th>ご予約日時</th><th>人数</th><th>コース名</th><th>テーブル名</th>
<th>登録日時</th><th colspan="2">&nbsp;</th></tr>
<%
ArrayList<Reserve> al = (ArrayList<Reserve>)request.getAttribute("reserveList");
for(int i=0;i<al.size();i++){
	Reserve r = al.get(i);
%>
	<tr>
	<td><%= r.getRsvId() %></td>
	<td><%= r.getRsvYy() %>年<%= r.getRsvMm() %>月<%= r.getRsvDd() %>日　<%= r.getRsvHh() %>時<%= r.getRsvMi() %>分</td>
	<td><%= r.getPerson() %></td>
	<td><%= r.getCourseName() %></td>
	<td><%= r.getTableName() %></td>
	<td><%= r.getAppYy() %>年<%= r.getAppMm() %>月<%= r.getAppDd() %>日　<%= r.getAppHh() %>時<%= r.getAppMi() %>分</td>
	<td>
		<form name="update" action="ReserveUpdateSvl" method="post">
		<input type="hidden" name="rsvId" value="<%= r.getRsvId() %>" />
		<input type="submit" value="変更" />
		</form>
	</td>
	<td>
		<form name="dele" action="ReserveDeleteSvl" method="post">
		<input type="hidden" name="rsvId" value="<%= r.getRsvId() %>" />
		<input type="submit" value="取消" />
		</form>
	</td>
	</tr>
<% } %>
</table>
<form id="frm1" name="frm1" action="ReserveInsertSvl">
<input type="submit" value="新規ご予約" />
</form>
<br/><br/>
<a href="userIndex.jsp">処理メニューに戻る</a>
</body>
</html>