<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者情報メンテナンス画面</title>
<style type="text/css">
h1{font-size:60px;}
div{text-align:center;}
table{margin-left:auto; margin-right:auto;}
</style>
</head>
<body>

		<div>
		<h1>処理選択</h1>
		<h4 align="right">現在ログインしている管理者は　<%= session.getAttribute("adminInfo") %>　様です</h4>
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

<hr />

<table border="1">
<tr>
<td>ID</td><td>管理者名</td><td>詳細</td><td colspan="2">処理選択</td>
</tr>
	<%
	for(Admin am : Admin.getAdminList()){%>
	<tr>
	<td><%= am.getAdmId() %></td><td><%= am.getAdmName() %></td><td><%= am.getExp() %></td>
	<td><form id="admUpdate" name="admUpdate" action="AdminUpdateSvl" method="post">
	<input type="submit" value="変更" />
	<input type="hidden" name="admId" value="<%= am.getAdmId() %>" />
	</form></td>
	<td><form id="admDelete" name="admDelete" action="AdminDeleteSvl" method="post">
	<input type="submit" value="削除" />
	<input type="hidden" name="admId" value="<%= am.getAdmId() %>" />
	</form>
	</td>
	</tr>
	<% }  %>
</table>

<form id="admInsert" name="admInsert" action="./adminInsert.jsp" method="post">
<input type="submit" value="新規登録" />
</form>

<br /><br /><br /><br /><br /><a href="adminIndex.jsp">処理メニューに戻る</a>
</div>
</body>
</html>