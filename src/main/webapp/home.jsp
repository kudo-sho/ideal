<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Restaurante IDEALLE</title>
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
<iframe src="./debugMode.jsp"></iframe>

<%
//引き渡されたメッセージを表示
//引き渡されたメッセージがnullの場合は非表示
String msg = null;
request.setCharacterEncoding("utf-8");
msg = (String)request.getAttribute("msg");
if(msg != null)
	out.print(msg);

System.out.println("homeセッションの判定");
//セッションの有無を判定
Object adminInfo = session.getAttribute("adminInfo");
Object usrName = session.getAttribute("usrName");
if(adminInfo != null && usrName != null){
	//管理者モード
	System.out.println("管理者モードだよ"); %>
	<h3 align="right">管理者モードです</h3>
	<h4 align="right">ログイン管理者：<%= session.getAttribute("adminInfo") %></h4>
	<h4 align="right">ログインユーザー:<%= session.getAttribute("usrName") %></h4>
	<% }else if(usrName != null){
		//お客様がログイン中はお客様管理画面にフォアード
		System.out.println("お客様ログイン中だよ");
		RequestDispatcher rd = request.getRequestDispatcher("/userIndex.jsp");
		rd.forward(request, response);
		}else if(adminInfo == ""){
			//管理者がログイン中はログインしている名前を表示
			System.out.println("管理者空文字がログイン中");
				}else if(adminInfo != null){
					//管理者がログイン中はログインしている名前を表示
					System.out.println("管理者ログイン中だよ"); %>

				<h4 align="right">お疲れ様です。 <%= session.getAttribute("adminInfo") %> 様</h4>
			<% } %>



<!--
<div text-align="left">
コメントを削除するとセッションが引き継がれているかを確認できます<br />
管理者のセッション確認：<%= session.getAttribute("adminInfo") %><br />
お客様のセッション確認：<%= session.getAttribute("usrName") %>
</div>
-->


 <div>
<h1 class="title">■ Restaurante IDEALLE ■</h1>
<div class="site-logo">
<img src="img/02.jpg" />
</div>
Restaurante IDEALLE へようこそ！<br />
シェフが腕によりをかけてお送りする料理メニューについては、『メニュー紹介』よりご確認ください。
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
<br />
<h5>※サイト管理者はページ下部の『管理者ログイン』よりログインしてください。</h5>
<br/>
		<a href="ShowMenuSvl"><div class="button">メニュー紹介</div></a><br/>
		<a href="./userLogin.jsp"><div class="button">すでに会員のかたはこちら</div></a><br/>
		<a href="./userInsert.jsp"><div class="button">会員ではない方はこちら</div></a><br />
		<a href="./contact.jsp"><div class="button">お問い合わせ</div></a><br />
		<br/><br/>
		<hr />
		<a href="./adminLogin.jsp"><h5>管理者ログイン</h5></a>

</div>
<div class="site-footer">
<div class="copyright">Restaurante IDEALLE</div>
</div>
<% System.out.println("home最後尾"); %>
</body>
</html>