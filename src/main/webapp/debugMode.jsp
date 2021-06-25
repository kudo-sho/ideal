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
		String admin=null;
		String ses = (String)session.getAttribute("adminInfo");
		String par = request.getParameter("admin");
		System.out.println("セッションは"+ses);
		System.out.println("パラメータは"+par);

		if(ses == null){//セッションがnull
			System.out.println("sesはnull");
			if(par != null){//パラメータはnullじゃない
				System.out.println("parはnullじゃない");
				session.setAttribute("adminInfo", request.getParameter("admin") );
				admin = (String)session.getAttribute("adminInfo");
			}//parもnullなら何もしない

		}else{//セッションはnullじゃない
			if(par == null){//パラメータはnull
				admin = ses;
				System.out.println(admin);
			}else{//パラメータはnullじゃない
				System.out.println("parはnullじゃない");
				if(par.equals("")){//parは空文字
					System.out.println("parは空文字");
					session.removeAttribute("adminInfo");
					admin = "null";
				}else{//parは空文字じゃない
					if(ses.equals(par)){//parとsesは一致
						System.out.println("parとsesは一致");
						admin = ses;
						System.out.println(admin);
					}else{
						System.out.println("parとsesは一致しないから上書き");
						session.setAttribute("adminInfo", request.getParameter("admin") );
						admin = (String)session.getAttribute("adminInfo");
					}
				}
			}


		}

		//ユーザーセッションの判定
		//ユーザーセッションはuser(int)で判定する
		Integer user = null;
		Integer usrSes;
		Integer usrPar;
		//リクエストやセッションに値がない場合はnullを渡されてヌルポになる
		try{
			usrSes = (Integer)session.getAttribute("usrId");
			System.out.println("取得したセッションのidは"+usrSes);
		}catch(Exception e){
			usrSes = null;
		}
		try{
			usrPar = Integer.parseInt(request.getParameter("user"));
		}catch(Exception e){
			usrPar = null;
		}
		System.out.println("usrSesセッションは"+usrSes);
		System.out.println("usrParパラメータは"+usrPar);

		if(usrSes == null){//セッションがnull
			System.out.println("usrSesはnull");
			if(usrPar != null){//パラメータはnullじゃない
				System.out.println("usrParはnullじゃない");
				session.setAttribute("user", request.getParameter("user") );
				User userInfo= User.getUser(usrPar);
				session.setAttribute("usrName", userInfo.getUsrName());
				session.setAttribute("usrId", userInfo.getUsrId());
				session.setAttribute("usrName", userInfo.getUsrName());
				session.setAttribute("password", userInfo.getPassword());
				session.setAttribute("address", userInfo.getAddress());
				session.setAttribute("phone", userInfo.getPhone());
				session.setAttribute("mail", userInfo.getMail());
				session.setAttribute("exp", userInfo.getExp());
				user = Integer.parseInt(request.getParameter("user"));
			}//parもnullなら何もしない

		}else{//セッションはnullじゃない
			if(usrPar == null){//パラメータはnull
				user = usrSes;
				System.out.println(user);
			}else{//パラメータはnullじゃない
				System.out.println("usrParはnullじゃない");
				if(usrPar == 0){
					System.out.println("usrParは0");
					session.removeAttribute("usrName");
					session.removeAttribute("usrId");
					session.removeAttribute("password");
					session.removeAttribute("address");
					session.removeAttribute("phone");
					session.removeAttribute("mail");
					session.removeAttribute("exp");
					user = 0;
				}else{
					if(usrSes.equals(usrPar)){
						System.out.println("usrParとusrSesは一致");
						user = usrSes;
						System.out.println(user);
					}else{
						System.out.println("usrParとusrSesは一致しないから上書き");
						session.setAttribute("user", Integer.parseInt(request.getParameter("user")));
						User userInfo= User.getUser(usrPar);
						session.setAttribute("usrName", userInfo.getUsrName());
						session.setAttribute("usrId", userInfo.getUsrId());
						session.setAttribute("usrName", userInfo.getUsrName());
						session.setAttribute("password", userInfo.getPassword());
						session.setAttribute("address", userInfo.getAddress());
						session.setAttribute("phone", userInfo.getPhone());
						session.setAttribute("mail", userInfo.getMail());
						session.setAttribute("exp", userInfo.getExp());
						user = Integer.parseInt(request.getParameter("user"));
					}
				}
			}

		}

	%>

デバッグモード設定
<form id="debugAdmin" name="debugAdmin" action="./debugMode.jsp" method="get">
管理者：
<select name="admin">
<option value="">null</option>

<%
							for (Debug deb : Debug.getAdminList()) {
								String selected = "";
								if(admin != null){
									if(admin.equals(deb.getAdmName())){
										selected ="selected= 'selected'";
									}else{
										selected = "";
									}
								}
								System.out.println(selected);

						%>
						<option value="<%= deb.getAdmName() %>" <%= selected %>>
							<%= deb.getAdmName() %></option>
						<%
							}
						%>
</select>
<input type="submit" value="セット" />
</form>

<form id="debugUser" name="debugUser" action="./debugMode.jsp" method="get" >
お客様：
<select name="user">
<option value="0">null</option>
<%
							for (Debug deb : Debug.getUserList()) {
								String selected = "";
								if(user != null){ //nullの場合はヌルポになるためnull判定
									if(user.equals((Integer)deb.getUsrId())){
										selected ="selected= 'selected'";
									}else{
										selected = "";
									}
								}

							System.out.println(selected);

						%>
						<option value="<%= deb.getUsrId()%>" <%= selected %>>
							<%= deb.getUsrName() %></option>
						<%
							}
						%>


</select>

<input type="submit" value="セット" />
</form>

<br />
  設定された管理者は：<%= admin %><br />
設定されたお客様IDは：<%= user %><br />
セッションにある管理者は：<%= session.getAttribute("adminInfo") %><br />
セッションにお客様は：<%= session.getAttribute("usrName") %>
</body>
</html>