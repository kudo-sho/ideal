<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>顧客脱会画面</title>
</head>
<body>
	<h1 align="center">お客様脱会手続き</h1></br>

	<%
		//引き渡されたメッセージを表示
		//引き渡されたメッセージがnullの場合は非表示
		String msg = null;
		request.setCharacterEncoding("utf-8");
		msg = (String)request.getAttribute("msg");
		if(msg != null) out.print(msg);
	%>

			<form id="f1" name="f1" action="UserOperationSvl" method="post">
			<table align="center" border="1">
				　<tr>
				  	<th>お客様ID</th>
				  	<td colspan="2"> <%= session.getAttribute("usrId") %> </td>
				  </tr>
				  <tr>
				     <th>お名前</th>
				     <td colspan="2"> <%= session.getAttribute("usrName") %> </td>
				  </tr>
				  <tr>
				    <th>住所</th>
				    <td colspan="2"> <%= session.getAttribute("address") %> </td>
				  </tr>
				  <tr>
				    <th>電話番号</th>
				    <td colspan="2"> <%= session.getAttribute("phone") %> </td>
				  </tr>
				  <tr>
				    <th>e-mail</th>
				    <td colspan="2"> <%= session.getAttribute("mail") %> </td>
				  </tr>
				  <tr>
				    <td align="right" colspan="3"> <font color="red">確認し、脱会ボタンをクリックして下さい。</font>
					　　<input type="submit" value="脱会" />
				    </td>
				  </tr>
			</table>
			<input type="hidden" name="mode" value="削除処理"/>
			<input type="hidden" name="usrId" value=<%= session.getAttribute("usrId") %>/>
		</form>

		&emsp;

		<div align="center">
			<a href="userIndex.jsp">
				<u>処理メニューに戻る</u>
			</a>
		</div>
</body>
</html>