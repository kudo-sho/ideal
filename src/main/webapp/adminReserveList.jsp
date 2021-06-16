<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
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
String message = (String)request.getAttribute("msg");
if(message == null) {message = "";}
%>
<h1>予約状況一覧</h1>
<%= message %>
<table>
<tr><th>予約日</th><th>テーブル名</th><th>予約時刻</th><th>顧客名（ID）</th><th>人数</th><th>コース名</th>
<th>予約No</th><th>登録・更新日</th></tr>
<%
ArrayList<Reserve> al = (ArrayList<Reserve>)request.getAttribute("reserveList");
String k_dateStr = "";
int k_table = 0;
for(int i=0;i<al.size();i++){
	Reserve r = al.get(i);
	Calendar cal = Calendar.getInstance();
	cal.set(r.getRsvYy(), r.getRsvMm()-1, r.getRsvDd());
	Date date = cal.getTime();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String dateStr = dateFormat.format(date);
	if(dateStr != k_dateStr){
		k_dateStr = dateStr;
	}else{
		dateStr = "&nbsp;";
	}

%>
	<tr>
	<td><%= dateStr %></td>
	<td><%= r.getTableName() %></td>
	<td><%= r.getRsvHh() %>:<%= r.getRsvMi() %> 〜 <%= r.getRsvHh()+3 %>:<%= r.getRsvMi() %></td>
	<td><%= r.getUsrName() %> 様（<%= r.getUsrId() %>）</td>
	<td><%= r.getPerson() %></td>
	<td><%= r.getCourseName() %></td>
	<td><%= r.getRsvId() %></td>
	<td><%= r.getAppDate() %></td>
	</tr>
<% } %>
</table>
<br/><br/>
<a href="adminIndex.jsp">管理者メニューに戻る</a>
</body>
</html>