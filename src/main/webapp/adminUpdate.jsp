<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者情報変更</title>

	<script type="text/javascript">
     <!--
		function check(){

    		if(document.admUpdate.admName.value == ""){
    			window.alert("名前が未入力です。");
    			return false;
    		}

    		if(document.admUpdate.password.value == ""){
    			window.alert("パスワードが未入力です。");
				return false;
    		}

    		if(!(document.admUpdate.password.value
    				== document.admUpdate.confPass.value)){
    			window.alert("パスワードが一致しません。正しいパスワードを入力してください");
				return false;
    		}
    	}
	//-->
  </script>

<style type="text/css">
h1 {
	font-size: 60px;
}

div {
	text-align: center;
}

table {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<div>
		<h1>管理者情報変更</h1>
		<h4 align="right">
			現在ログインしている管理者は
			<%=session.getAttribute("adminInfo")%>
			様です
		</h4>
		<hr>
		<%
		//リクエストセッションにあるUpdateAdmInfoを取得

		//引き渡されたメッセージを表示
		//引き渡されたメッセージがnullの場合は非表示
		String msg = null;
		request.setCharacterEncoding("utf-8");
		msg = (String) request.getAttribute("msg");
		if (msg != null)
			out.print(msg);

		//管理者セッションを確認
		Object usrName = session.getAttribute("adminInfo");
		if (usrName == null) {
			//管理者がログインしていない時は管理者ログインにフォアード
			System.out.println("管理者ログインしてないのでログインしてね");
			RequestDispatcher rd = request.getRequestDispatcher("/adminLogin.jsp");
			rd.forward(request, response);
		}
		%>
		<hr />

		<form id="admUpdate" name="admUpdate" action="AdminOperationSvl"
			method="post" onsubmit="return check();">
			<table border="1">
				<tr>
					<td>管理者ID</td>
					<td><%= session.getAttribute("admId") %></td>
				</tr>
				<tr>
					<td>氏名(※)</td>
					<td><input type="text" name="admName"
						value="<%= session.getAttribute("admName") %>" /></td>
				</tr>
				<tr>
					<td>パスワード(※)</td>
					<td><input type="password" name="password"
						value="<%= session.getAttribute("password") %>" /></td>
				</tr>
				<tr>
					<td>パスワード再入力(※)</td>
					<td><input type="password" name="confPass" /></td>
				</tr>
				<tr>
					<td>役職等詳細</td>
					<td><input type="text" name="exp"
						value="<%= session.getAttribute("admExp") %>" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="変更" /> <input
						type="reset" value="リセット" /></td>
				</tr>
			</table>
			<input type="hidden" name="admId" value="<%= session.getAttribute("admId") %>" />
			<input type="hidden" name="mode" value="変更処理" />
		</form>
		<br>
		<br>
		<br>
		<br>
		<br>
		<a href="./adminMaintenance.jsp">戻る</a>
	</div>
</body>
</html>