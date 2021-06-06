<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>顧客登録完了画面</title>
</head>

<body align="center">
	<h1> 会員登録が完了いたしました。</h1>

	<h3>
		<%
			request.setCharacterEncoding("utf-8");
		%>

		<br/>

		<%= request.getParameter("usrName") %>
		様の会員IDは
		<%=
			session.getAttribute("usrId")
		%>
		でございます。
		<br/><br/>

		ログインの際に必要となりますので、<br/><br/>


		大切に保管してください。<br/>

	</h3>

	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

		<div align="center">
			<a href="userIndex.jsp">
				<u>戻る</u>
			</a>
		</div>

</body>
</html>