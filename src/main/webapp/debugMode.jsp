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
		//管理者セッションの判定
		String admin;
		String ses = (String)session.getAttribute("adminInfo");
		String par = request.getParameter("admin");
		//セッションがnullまたはパラメータとセッションが一致していない場合は上書き
		if(ses == null || !(ses.equals(par))){
			System.out.println("フェーズ");
			session.setAttribute("adminInfo", request.getParameter("admin") );
			admin = (String)session.getAttribute("adminInfo");
		}else{//パラメータとセッション一致しているときはselected判定に値を渡す
			System.out.println("notフェーズ");
			admin = (String)session.getAttribute("adminInfo");
			System.out.println(admin);

		}

		Integer user;
		try {
			user = Integer.parseInt(request.getParameter("user"));
			User userInfo= User.getUser(Integer.parseInt(request.getParameter("user")));
			session.setAttribute("usrName", userInfo.getUsrName());
			session.setAttribute("usrId", userInfo.getUsrId());
			session.setAttribute("usrName", userInfo.getUsrName());
			session.setAttribute("password", userInfo.getPassword());
			session.setAttribute("address", userInfo.getAddress());
			session.setAttribute("phone", userInfo.getPhone());
			session.setAttribute("mail", userInfo.getMail());
			session.setAttribute("exp", userInfo.getExp());
		}
		catch (NumberFormatException e) {user  = null;}
	%>

デバッグモード設定
<form id="debugAdmin" name="debugAdmin" action="./debugMode.jsp" method="get">
管理者：
<select name="admin">
<option value="">null</option>

<%
							for (Object o : Debug.getAdminList()) {
								Debug deb = (Debug) o;
								String selected = "";
								if(admin != null){
									if(admin.equals(deb.getAdminName())){
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
<option value="">null</option>
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
<br />
セッションにある管理者は：<%= session.getAttribute("adminInfo") %>
セッションにお客様は：<%= session.getAttribute("usrName") %>
</body>
</html>