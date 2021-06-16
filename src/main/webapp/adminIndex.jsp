<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.awt.dnd.DropTargetAdapter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理者処理選択画面</title>
<style type="text/css">
h1{font-size:60px;}
div{text-align:center;}
</style>
</head>
<body>
		<div>
		<h1>処理選択</h1>
		<h4 align="right">お疲れ様です。<%= session.getAttribute("adminInfo") %>　様</h4>
		<hr />
<%

//引き渡されたメッセージを表示
//引き渡されたメッセージがnullの場合は非表示
String msg = null;
request.setCharacterEncoding("utf-8");
msg = (String)request.getAttribute("msg");
if(msg != null)
	out.print(msg);

//管理者セッションを確認
		Object usrName = session.getAttribute("adminInfo");
		if(usrName == null){
			//管理者がログインしていない時は管理者ログインにフォアード
			System.out.println("管理者ログインしてないのでログインしてね");
			RequestDispatcher rd = request.getRequestDispatcher("/adminLogin.jsp");
			rd.forward(request, response);
		}
%>

<!--


-->
		<hr />
		<table width="100%">
		<td width="33%"></td>
		<td width= "33%">
		<l>
			<a href = "AdminReserveListSvl"><li>予約状況確認（工事中）</li></a><br/>
			<a href = "MenuMaintenanceSvl"><li>メニューメンテナンス</li></a><br />
			<a href = "AdminMaintenanceSvl"><li>管理者情報メンテナンス（工事中）</li></a><br />
			<a href = "AdminLogoffSvl"><li>ログアウト</li></a><br />

		</l>
		</td>
		<td width= "33%"></td>

		</table>
</div>
</body>
</html>