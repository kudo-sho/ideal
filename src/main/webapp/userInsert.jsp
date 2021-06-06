<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>お客様情報登録</title>

	<script type="text/javascript">
     <!--
		function check(){

    		if(document.f1.usrName.value == ""){
    			window.alert("名前が未入力です。");
    			return false;
    		}

    		var phone = document.f1.phone.value;
    		if(phone != ""){
				if(phone.match(/^\d{10}$/) != null){

				}else if(phone.match(/^\d{11}$/) != null){

				}else{
					window.alert("電話番号を正しく入力してください。");
					return false;
				}
   			}

			var mail = document.f1.mail.value;
			if(mail != ""){
				if(mail.match(/^\w+?@\w+?$/) == null){
					window.alert("メールアドレスを正しく入力してください。");
					return false;
				}
			}

    		if(document.f1.password.value == ""){
    			window.alert("パスワードが未入力です。");
				return false;
    		}
    	}
	//-->
  </script>
</head>

<body>
	<h1 align="center">お客様情報登録</h1></br>

	<%
		//引き渡されたメッセージを表示
		//引き渡されたメッセージがnullの場合は非表示
		String msg = null;
		request.setCharacterEncoding("utf-8");
		msg = (String)request.getAttribute("msg");
		if(msg != null) out.print(msg);
	%>

		<form id="f1" name="f1" action="UserOperationSvl" method="post" onsubmit="return check();">
			<table align="center" border="1">
				  <tr>
				     <th>お名前※</th>
				     <td align="left" colspan="2"><input type="text" name="usrName" size="30"/></td>
				  </tr>

				  <tr>
				    <th>住所</th>
				    <td align="left" colspan="2"><input type="text" name="address" size="40"/></td>
				  </tr>

				  <tr>
				    <th>電話番号</th>
				    <td align="left" colspan="2"><input type="text" name="phone"/></td>
				  </tr>

				  <tr>
				    <th>e-mail</th>
				    <td align="left" colspan="2"><input type="text" name="mail" size="40"/></td>
				  </tr>

				  <tr>
				    <th>パスワード※</th>
				    <td align="left" colspan="2"><input type="password" name="password"/></td>
				  </tr>

				  <tr>
				    <td align="right" colspan="3"> <font color="red">※は必修入力です。</font>
					　　<input type="submit" value="登録" />
				    </td>
				  </tr>
			</table>
			<input type="hidden" name="mode" value="登録処理"/>
		</form>

		&emsp;

		<div align="center">
			<a href="home.jsp">
				<u>ホームページに戻る</u>
			</a>
		</div>
</body>
</html>