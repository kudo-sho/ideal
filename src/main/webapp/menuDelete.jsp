<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,controller.*,model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メニュー情報削除</title>
<style>
<!--
	h1{text-align:center; background:#007B66; color:#ECFFF3;}
	table{width="600px; height="400px;}
	th{text-align:center; background:#A4FFDB; color:#007B66;}
	td{background:#E3FFE3;}
	th#bottom{text-align:right;}
	p{text-align:center; font-size:18pt;}
	-->
</style>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id ="oneMenu" class="model.Menu" scope="request" />
<%
	int typeId = oneMenu.getTypeId();
%>
<table border="1" align="center">
	<h1>メニュー情報削除</h1>
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


	<form id="frmdel" name="frmdel" action="MenuOperationSvl" method="post">
	<tr>
	<th>メニュー名</th>
	<td><jsp:getProperty name="oneMenu" property="menuName" /></td>
	</tr>
	<tr>
	<th>価格</th>
	<td><jsp:getProperty name="oneMenu" property="price" /></td>
	</tr>
	<tr>
	<th>オーダー</th>
				<%
				String[] order = { "不可", "可" };
					int of = oneMenu.getOrderFlg();
				%>
	<td><%= order[of] %></td>
	</tr>
	<tr>
		<th>分類</th>
		<td><jsp:getProperty name="oneMenu" property="typeName" />
		</td>
	</tr>
	<tr>
	<th>コメント</th>
	<td>
		<jsp:getProperty name="oneMenu" property="detail" />
	</td>
	</tr>
	<tr>
		<td align="right" colspan="3"> <font color="red">※は必修入力です。</font>
		<input type="hidden" name="mode" value="13" />
		<input type="hidden" name="menuId" value="<jsp:getProperty name="oneMenu" property="menuId" />" />
		<input type="hidden" name="typeId" value="<jsp:getProperty name="oneMenu" property="typeId" />" />
		<input type="hidden" name="DtypeID" value="<jsp:getProperty name="oneMenu" property="typeId" />" />
		<input type="submit" value="削除" />
	</tr>
	</form>
	&emsp;
</table>
<p><a href="MenuMaintenanceSvl">メニューメンテナンスに戻る</a> </p>
</body>
</html>