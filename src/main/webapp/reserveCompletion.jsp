<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import = "javax.naming.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>予約完了画面</title>
</head>
<body>

<%
	Reserve re = (Reserve)request.getAttribute("rsvId") ; 
%>

<h1><%= session.getAttribute("name") %>様、ご予約が完了いたしました。</h1>

<h2><%= re.getRsvYy() %>年<%= re.getRsvMm() %>月<%= re.getRsvDd() %>日  <%= re.getRsvHh() %>時<%= re.getRsvMi() %>分より</h2>
<h2><%= re.getCourseName() %>　<%= re.getPerson() %>名様</h2>
<h2>ご予約番号は<%= re.getRsvId() %>番です。</h2>
<h2>ご来店の際に、受付にお申し付けください。</h2>


<a href="ReserveListSvl">予約一覧に戻る</a>


</body>
</html>