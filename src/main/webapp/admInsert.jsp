<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者新規登録</title>
<style type="text/css">
h1{font-size:60px;}
div{text-align:center;}
table{margin-left:auto; margin-right:auto;}
</style>
</head>
<body>
<div>
<h1>管理者新規登録</h1>
		<h4 align="right">現在ログインしている管理者は　<%= session.getAttribute("adminInfo") %>　様です</h4>
<hr>
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
<form id="admInsert" name="admInsert" action="admInsert" method="post">
<table border="1">
<tr>
<td>氏名</td><td><input type="text" name="admName" /></td>
</tr>
<tr>
<td>パスワード</td><td><input type="password" name="password" /></td>
</tr>
<tr>
<td>パスワード再入力</td><td><input type="password" name="password" /></td>
</tr>
<tr>
<td>役職等詳細</td><td><input type="text" name="exp" /></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="登録" />
<input type="reset" value="リセット" />
</td>
</tr>
</table>
</form>
<br><br><br><br><br><a href="./adminMaintenance.jsp">戻る</a>
</div>
</body>
</html>