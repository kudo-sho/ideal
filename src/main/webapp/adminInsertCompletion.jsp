<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>管理者登録完了画面</title>
</head>

<body align="center">
<%
	Admin adm = (Admin)request.getAttribute("insertAdmInfo");


%>
	<h1> 管理者登録が完了いたしました。</h1>

	<h3>
		<% request.setCharacterEncoding("utf-8"); %>

		<br/>
		新たに登録された管理者情報は以下の通りです
		<table border="1" align="center">
		<tr>
		<td>管理者ID</td><td>管理者氏名</td><td>パスワード</td><td>詳細</td>
		</tr>
		<tr>
		<td><%= adm.getAdmId() %></td>
		<td><%= adm.getAdmName() %></td>
		<td><%= adm.getPassword() %></td>
		<td><%= adm.getExp() %></td>
		</tr>
		</table>
		<br /><br/>
		現在は <%= session.getAttribute("adminInfo") %> 様でログインされています
		<br/>



	</h3>

	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

		<div align="center">
			<a href="adminMaintenance.jsp">
				<u>戻る</u>
			</a>
		</div>

</body>
</html>