<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>顧客情報変更画面</title>
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
    	}
	//-->
  </script>
</head>
<body>
	<h1 align="center">お客様情報変更</h1></br>

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
				  	<th>お客様ID</th>
				  	<td colspan="2"> <%= session.getAttribute("usrId") %> </td>
				  </tr>

				  <tr>
				     <th>お名前</th>
				     <td align="left" colspan="2"><input type="text" name="usrName" size="30"
				     					value= <%= session.getAttribute("usrName") %> /></td>
				  </tr>
					<!-- お客様情報変更画面で値のない入力フィールドでは/になってしまう原因
					は値がなかった場合nullが返ってくるがvalueは文字列を認識していると思われる
					ため/>の/が文字列として認識されてしまったものと思われる
					よって、””でくくってnullだった場合は空文字が文字列であることを認識させて
					不具合を解消した -->
				  <tr>
				    <th>住所</th>
				    <td align="left" colspan="2"><input type="text" name="address" size="40"
				    					value= "<%= session.getAttribute("address") %>" /></td>
				  </tr>

				  <tr>
				    <th>電話番号</th>
				    <td align="left" colspan="2"><input type="text" name="phone"
				    				value= "<%= session.getAttribute("phone") %>" /></td>
				  </tr>

				  <tr>
				    <th>e-mail</th>
				    <td align="left" colspan="2"><input type="text" name="mail" size="40"
				    				value= "<%= session.getAttribute("mail") %>" /></td>
				  </tr>

				  <tr>
				    <th>パスワード</th>
				    <td align="left"><input type="password" name="password"
				    				value=<%= session.getAttribute("password") %> />
				    							<font size="2px">※変更時のみ入力してください。</font></td>
				  </tr>

				  <tr>
				    <td align="right" colspan="3"> <font color="red">※は必修入力です。</font>
					　　<input type="submit" value="変更" />
				    </td>
				  </tr>
			</table>

			<input type="hidden" name="mode" value="変更処理"/>
			<input type="hidden" name="usrId" value=<%= session.getAttribute("id") %> />
		</form>

		&emsp;

		<div align="center">
			<a href="userIndex.jsp">
				<u>処理メニューに戻る</u>
			</a>
		</div>
</body>
</html>