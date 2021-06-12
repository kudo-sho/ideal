<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>お問い合わせフォーム</title>
<style type="text/css">
div{text-align:center;}

</style>
</head>
<body>
<div>
<h1>お問い合わせ</h1>
<hr />


<table align="center" border="1">

<form id="contact" name="contact" action="contactCompletion.jsp" method="post">
<tr>
<td>氏名</td><td><input type="text" name="name" size="50%" /></td>
</tr>

<tr>
<td>選択</td><td>
<input type="radio" name="statas" value="会員">会員
<input type="radio" name="statas" value="未登録" checked>未登録
</td>
</tr>

<tr>
<td>件名</td><td><input type="text" name="object" size="50%" /></td>
</tr>

<tr>
<td>内容</td>
<td>
<textarea name="detail" rows="20" cols="53"  placeholder=
"ここに問い合わせ内容を記入してください。"></textarea>
</td>
</tr>
</table>
<input type="submit" value="送信" />
<input type="reset" value="消去" />
</form>
<br />

<a href="./home.jsp">戻る</a>
</div>
</body>
</html>