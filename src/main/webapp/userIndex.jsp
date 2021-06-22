<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会員処理選択画面</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<a href="home.jsp">
<div class="toplink">IDEALLE</div>
</a>
<center>
<h1>■Restaurante IDEALLE■</h1>

<% request.setCharacterEncoding("utf-8");
	String name = request.getParameter("usrId");%>
<h1><%= session.getAttribute("usrName") %>
<span style="font-size:50%;">様、いらっしゃいませ。</span></h1>


<%
//引き渡されたメッセージを表示
//引き渡されたメッセージがnullの場合は非表示
String msg = null;
request.setCharacterEncoding("utf-8");
msg = (String)request.getAttribute("msg");
if(msg != null)
	out.print(msg);

//お客様セッションを確認
		Object usrName = session.getAttribute("usrName");
		if(usrName == null){
			//お客様がログインしていない時はお客様ログインにフォアード
			System.out.println("お客様ログイン中だよ");
			RequestDispatcher rd = request.getRequestDispatcher("/userLogin.jsp");
			rd.forward(request, response);
		}

%>

<a href="ShowMenuSvl"><div class="button">メニュー紹介</div></a><br/>
<a href="ReserveListSvl"><div class="button">ご予約</div></a><br/>
<a href="UserUpdateSvl"><div class="button">お客様情報変更</div></a><br/>
<a href="UserDeleteSvl"><div class="button">お客様脱会手続き</div></a><br/>
<a href="./contact.jsp"><div class="button">お問い合わせ</div></a><br />
<a href="UserLogoffSvl"><div class="button">ログオフ</div></a>
</center>
<div class="site-footer">
<div class="copyright">Restaurante IDEALLE</div>
</div>
</body>
</html>