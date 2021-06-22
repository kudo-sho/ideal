<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理者ログイン</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style type="text/css">
h1{font-size:60px;}
div{text-align:center;}
</style>
<script type="text/javascript">
<!--
	//check関数を定義
	function check() {
		if (document.login.admName.value == "") {
			window.alert("管理者氏名が未入力です");
			return false;
		} else if (document.login.password.value == "") {
			window.alert("パスワードが未入力です");
			return false;
		} else {
			return (window.confirm('ログインしてもよろしいですか？'))
		}
	}
//-->
</script>
</head>
<body>
<a href="home.jsp">
<div class="toplink">IDEALLE</div>
</a>
<div>
		<h1>管理者ログイン</h1>
<%
//引き渡されたメッセージを表示
//引き渡されたメッセージがnullの場合は非表示
String msg = null;
request.setCharacterEncoding("utf-8");
msg = (String)request.getAttribute("msg");
if(msg != null)
	out.print(msg);

//ログイン中に再度ログイン画面を開くと再度ログイン処理を促されるため
//ログイン中に再度ログイン画面を開いたら管理画面にフォアードするように改良した
Object adminInfo = session.getAttribute("adminInfo");
if(adminInfo != null){
	System.out.print("管理者ログイン中だよ");
	RequestDispatcher rd = request.getRequestDispatcher("/adminIndex.jsp");
	rd.forward(request, response);
}

%>
		<form id="login" name="login" action="AdminLoginSvl" method="post"
			onsubmit="return check();">
			<table border="1" width="500" cellspacing="4" cellpadding="4" style="width:50%;">
				<tr>
					<th>管理者氏名</th>
					<td colspan="3"><input type="text" name="admName" size="40" /></td>
				</tr>

				<tr>
					<th>パスワード</th>
					<td colspan="3"><input type="password" name="password" size="40" /></td>
				</tr>

				<tr>
					<td colspan="4" class="cent"><input type="submit" value="送信" /></td>
				</tr>
			</table>
		</form>
	</div>
			<br />
			<a href="./home.jsp">ホームに戻る</a>
<div class="site-footer">
<div class="copyright">Restaurante IDEALLE</div>
</div>
</body>
</html>