<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<%
//String admin = request.getParameter("admin");
//String user = request.getParameter("user");
%>
	<%
		Integer admin;
		try {admin = Integer.parseInt(request.getParameter("admin"));}
		catch (NumberFormatException e) {admin  = null;}
		Integer user;
		try {user = Integer.parseInt(request.getParameter("user"));}
		catch (NumberFormatException e) {user  = null;}
	%>

<h5>デバッグモード設定</h5>
<form id="debugAdmin" name="debugAdmin" action="./debugMode.jsp" method="get">
管理者：
<select name="admin">
<option value="null">null</option>

<%
//admin.javaにgetAllAdminメソッドを作る必要がありそう

							for (Object o : Debug.getAdminList()) {
								Debug deb = (Debug) o;
								String selected = "";
								if(admin != null){
									if(admin.equals((Integer)deb.getAdminId())){
										selected ="selected= 'selected'";
									}else{
										selected = "";
									}
								}
								System.out.println(selected);

						%>
						<option value="<%= deb.getAdminName() %>" <%= selected %>>
							<%= deb.getAdminName() %></option>
						<%
							}
						%>
</select>
<br />
お客様：
<select name="user">
<option value="null">null</option>
<%
							for (Object o : Debug.getUserList()) {
								Debug deb = (Debug) o;
								String selected = "";
								if(user != null){ //nullの場合はヌルポになるためnull判定
									if(user.equals((Integer)deb.getUserId())){
										selected ="selected= 'selected'";
									}else{
										selected = "";
									}
								}

							System.out.println(selected);

						%>
						<option value="<%= deb.getUserId()%>" <%= selected %>>
							<%= deb.getUserName() %></option>
						<%
							}
						%>


</select>
<br />
<input type="submit" value="セット" />
<input type="reset" value="リセット" />
</form>

設定された管理者は：<%= admin %>
設定されたお客様は：<%= user %>
</body>
</html>