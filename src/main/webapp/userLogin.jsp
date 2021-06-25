<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>お客様ログイン</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style type="text/css">
h1{font-size:60px;}
div{text-align:center;}
</style>
<script type="text/javascript">
<!--
	function checkFrm() {
		if(document.frm1.usrId.value == ""){
			alert("顧客様IDを入力してください。");
			document.frm1.usrId.focus();
			return false;
		}else if(! document.frm1.usrId.value.match(/^[0-9]+$/g)){
			alert("顧客様IDは半角数字を入力してください。");
			document.frm1.usrId.focus();
			return false;
		}else if(document.frm1.password.value == ""){
			alert("パスワードを入力してください。");
			document.frm1.password.focus();
			return false;
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
		<h1>お客様ログイン</h1>
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
Object userInfo = session.getAttribute("usrName");
if(userInfo != null){
	System.out.print("お客様はログイン中だよ");
	RequestDispatcher rd = request.getRequestDispatcher("/userIndex.jsp");
	rd.forward(request, response);
}

%>
		<form id="frm1" name="frm1" action="UserLoginSvl" method="post"
			onsubmit="return checkFrm();">
			<table border="1" cellspacing="4" cellpadding="4" style="width:50%;">
				<tr>
					<th>お客様ID</td>
					<td><input type="text" name="usrId" size="40" /></td>
				</tr>

				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password" size="40" /></td>
				</tr>

				<tr>
					<td colspan="2" class="cent"><input type="submit" value="送信" /></td>
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