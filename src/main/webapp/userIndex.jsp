<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会員処理選択画面</title>
</head>
<body>
<center>
<h1>■Restaurante IDEALLE■</h1>

<% request.setCharacterEncoding("utf-8");
	String name = request.getParameter("usrId");%>
<p><font size="8">

<%= session.getAttribute("usrName") %>

</font><font size="4">様、いらっしゃいませ。</font></p>


<%
//引き渡されたメッセージを表示
//引き渡されたメッセージがnullの場合は非表示
String msg = null;
request.setCharacterEncoding("utf-8");
msg = (String)request.getAttribute("msg");
if(msg != null)
	out.print(msg);

//else
//	out.print(" ");

%>

<br />
<a href="ShowMenuSvl">●メニュー紹介</a><br/><br/>
<a href="ReserveListSvl">●ご予約</a><br/><br/>
<a href="UserUpdateSvl">●お客様情報変更</a><br/><br/>
<a href="UserDeleteSvl">●お客様脱会手続き</a><br/><br/>
<a href="./contact.jsp"><li>お問い合わせ</li></a><br />
<a href="UserLogoffSvl">●ログオフ</a>
</center>
</body>
</html>