<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Restaurante IDEALLE</title>
<style type="text/css">
h1{font-size:60px;}
div{text-align:center;}
</style>
</head>
<body>
<div>
<h1>■ Restaurante IDEALLE ■</h1></div>
<br/>
<div>

<%
//引き渡されたメッセージを表示
//引き渡されたメッセージがnullの場合は非表示
String msg = null;
request.setCharacterEncoding("utf-8");
msg = (String)request.getAttribute("msg");
if(msg != null)
	out.print(msg);



%>
<!-- la・Dio feniceへようこそ！<br />
シェフが腕によりをかけてお送りする料理メニューについては、『メニュー』よりご確認ください。
<br /><br/>
当店ご利用の際は事前予約が必要となっております。
<br />
会員の方は『すでに会員の方はこちら』よりログインを、
<br />
初めてご利用の方は『会員ではない方はこちら』より会員登録後、
<br />
ログインを行い予約システムをご利用ください。
<br /><br />
お問い合わせにつきましては『お問い合わせ』よりご連絡ください。
<br /><br />
<h5>※サイト管理者はページ下部の『管理者はこちら』よりログインしてください。</h5>
</div>  -->
<hr />
<br/>
<!--
<%= session.getAttribute("adminInfo") %>
 -->
		<a href="ShowMenuSvl"><li>メニュー紹介</li></a><br />
		<a href="./userLogin.jsp"><li>ログイン</li></a><br />
		<a href="./userInsert.jsp"><li>お客様新規登録</li></a><br />

		<br/><br/><br/>
		<hr />
		<a href="./adminLogin.jsp"><h5>管理者ログイン</h5></a>

</div>

</body>
</html>