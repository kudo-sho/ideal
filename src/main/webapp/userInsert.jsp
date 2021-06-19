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

    		if(document.f1.usrName1.value == ""){
    			window.alert("姓が未入力です。");
    			return false;
    		}
    		
    		if(document.f1.usrName2.value == ""){
    			window.alert("名が未入力です。");
    			return false;
    		}
    		

    		//電話番号チェックの正規表現を見直し、ハイフンなし、ハイフン入り両方に対応させました。
    		//固定電話は2桁-4桁-4桁、3桁-3桁-4桁、4桁-2桁-4桁、5桁-1桁-4桁の4種のみ想定
    		//携帯電話は3桁-4桁-4桁のみ想定
    		//先頭が０以外の場合は弾きます
    		
    		var phone = document.f1.phone.value;
    		if(phone != ""){
				if(phone.match(/^0\d{9}$/) != null){
				
				}else if(phone.match(/^0\d{1}-\d{4}-\d{4}$/) != null){
				
				}else if(phone.match(/^0\d{2}-\d{3}-\d{4}$/) != null){
				
				}else if(phone.match(/^0\d{3}-\d{2}-\d{4}$/) != null){
					
				}else if(phone.match(/^0\d{4}-\d{1}-\d{4}$/) != null){

				}else if(phone.match(/^0\d{10}$/) != null){
				
				}else if(phone.match(/^0\d{2}-\d{4}-\d{4}$/) != null){
					
    			}else{
					window.alert("電話番号を正しく入力してください。");
					return false;
				}
   			}
    		
    		//メールアドレスチェックの正規表現を見直し、「_」、「.」、「-」が弾かれる不具合を解消しました
    		//※先頭と@の直前は上記の文字を使用できません.
    		//＠以降は「(文字列).(文字列)」となるようにしました。

    		var mail = document.f1.mail.value;
			if(mail != ""){
				if(mail.match(/^[a-zA-Z0-9]{1}[a-zA-Z0-9_.-]+[a-zA-Z0-9]{1}@[a-zA-Z0-9.]+\.[a-zA-Z0-9]+$/) != null){

				}else{
					window.alert("メールアドレスを正しく入力してください。");
					return false;
				}
			}

    		if(document.f1.password.value == ""){
    			window.alert("パスワードが未入力です。");
				return false;
    		}
    		
    		//苗字と名前の結合処理　半角スペースを挟むと上手くいかなかったので全角にしてあります
    		
    		f1.usrName.value = document.f1.usrName1.value + " " + document.f1.usrName2.value ;
    		
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
		//お客様セッションを確認
		Object usrName = session.getAttribute("usrName");
		if(usrName != null){
			//お客様がログイン中はお客様管理画面にフォアード
			System.out.println("お客様ログイン中だよ");
			RequestDispatcher rd = request.getRequestDispatcher("/userIndex.jsp");
			rd.forward(request, response);
		}

	%>

		<form id="f1" name="f1" action="UserOperationSvl" method="post" onsubmit="return check();">
			<table align="center" border="1">
				  <tr>
				     <th>お名前※</th>
				     <td align="left" colspan="1">姓<input type="text" name="usrName1" size="15"/></td>
				     <td align="left" colspan="1">名<input type="text" name="usrName2" size="15"/></td>
				     
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
				    <td align="right" colspan="3"> <font color="red">※は必須入力です。</font>
					　　<input type="submit" value="登録" />
				    </td>
				  </tr>
			</table>
			
			<input type="hidden" name="usrName" />
			<input type="hidden" name="mode" value="登録処理" />
		</form>

		&emsp;

		<div align="center">
			<a href="home.jsp">
				<u>ホームページに戻る</u>
			</a>
		</div>
</body>
</html>