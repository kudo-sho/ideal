<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<%@ page import = "javax.naming.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>予約変更画面</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type = "text/javascript">
	<!--
		function check(){
			//現時刻のインスタンスを生成し、そこから年月日時分を変数に格納
			var now = new Date();
			var Year = now.getFullYear();
	      	var Month = now.getMonth()+1;
	    	var Day = now.getDate();
	    	var Hour = now.getHours();
	      	var Min = now.getMinutes();

			//フォームに入力された値を取得し変数に格納
			var RevYy = document.f1.rsvYy.value;
			var RevMm = document.f1.rsvMm.value;
	      	var RevDd = document.f1.rsvDd.value;
	      	var RevHh = document.f1.rsvHh.value;
	      	var RevMi = document.f1.rsvMi.value;

	      //４月３１日など、ありえない日付けの入力を弾く
			switch(RevMm){
				case '4':
				case '6':
				case '9':
				case '11':
					if(RevDd !=31){
						break;
					}
				case '2':
					if( ((RevYy % 4 == 0) & (RevDd < 30) ) || ((RevYy % 4 != 0) & (RevDd < 29)) ){
						break;
					}
					window.alert("該当する日付はございません。日付を確認してください。");
					return false;
			}


	      //９時が最終なので、２１時１５分～２１時４５分の入力を弾く
	      	if( RevHh == 21 && RevMi > 0 ){
	      		window.alert("申し訳ございませんが、各日最終は２１時となっております。時刻を変更してください。");
				return false;
	      	}


	      //過去日時と１年後以降の日付けを弾く

			if( (Year > RevYy)
				||((Year == RevYy)&&(Month >RevMm ))
				||((Year == RevYy)&&(Month==RevMm)&&(Day >RevDd))
				||((Year == RevYy)&&(Month==RevMm)&&(Day==RevDd)&&(Hour >RevHh))
				||((Year == RevYy)&&(Month==RevMm)&&(Day==RevDd)&&(Hour==RevHh)&&(Min > RevMi))
			){
				window.alert("過去日時での予約はできません");
				return false;
			}else if((Year< RevYy) && ((Year+1 < RevYy)|| (Month < RevMm) || ((Month==RevMm)&&(Day < RevDd)))){
				window.alert("予約可能なのは本日より1年以内です。日付けを変更してください。");
				return false;
			}else{
				//後でtrueに書き換える
				return true;
			}
		}

	-->
</script>
</head>
<body>
<a href="home.jsp">
<div class="toplink">IDEALLE</div>
</a>
<%
HttpSession usrInfo = request.getSession(false);
String name = (String)usrInfo.getAttribute("usrName");
String message = (String)request.getAttribute("msg");
if(message == null) {message = "&nbsp;";}
%>

<h1><%= name %>様ご予約変更</h1>
<%= message  %>
<form name ="f1" id = "f1" action="ReserveOperationSvl"  method = "post" onsubmit = "return check();">

<%
Reserve re = (Reserve)request.getAttribute("reserve") ;
ArrayList<Course> al = (ArrayList<Course>)request.getAttribute("courseList");
%>

	<table boder="1">
		<tr>
			<th>予約番号</th>
			<td><%= re.getRsvId() %></td>
		</tr>
		<tr>
			<th>日付</th>
			<td>
			<select name="rsvYy">

<%-- for文で選択肢を作成し、選択肢の中から予約内容に合致する項目にのみselectedを設定する--%>

			<% for(int i=0;i<2;i++){
				int year = 2021+i;
				if( re.getRsvYy()==(year)){
			%>
				<option value="<%= year %>" selected><%= year %></option>
			<% }else{ %>
				<option value="<%= year %>"><%= year %></option>

			<% }} %>
			</select>年
			<select name="rsvMm">
			<% for(int i=1;i<=12;i++){
				if( re.getRsvMm()== i){
			%>
				<option value="<%= i %>" selected><%= i %></option>
			<%}else{ %>
				<option value="<%= i %>"><%= i %></option>
			<% }} %>
			</select>月
			<select name="rsvDd">
			<% for(int i=1;i<=31;i++){
				if( re.getRsvDd()== i){
			%>
				<option value="<%= i %>" selected><%= i %></option>
			<% }else{ %>
				<option value="<%= i %>"><%= i %></option>
			<% }} %>
			</select>日
			</td>
		</tr>
		<tr>
			<th>時刻</th>
			<td>
			<select name="rsvHh">
			<% for(int i=17;i<=21;i++){
				if( re.getRsvHh()== i){
			%>
				<option value="<%= i %>" selected><%= i %></option>
			<% }else{ %>
				<option value="<%= i %>"><%= i %></option>
			<% }}%>
			</select>時
			<select name="rsvMi">
			<% for(int i=0;i<=45;i+=15){
				if( re.getRsvMi()== i){
			%>
				<option value="<%= i %>" selected><%= i %></option>
			<% }else{ %>
				<option value="<%= i %>"><%= i %></option>
			<% }} %>
			</select>分
			</td>
		</tr>
		<tr>
			<th>人数</th>
			<td>
			<select name="person">
			<% for(int i=1;i<=6;i++){
				if( re.getPerson()== i){
			%>
				<option value="<%= i %>" selected><%= i %></option>
			<% }else{ %>
				<option value="<%= i %>"><%= i %></option>
			<% }} %>
			</select>人
			</td>
		</tr>

		<tr>
			<th>コース</th>
			<td>
				<select name="courseId">
				<% for(int i=1;i<=al.size();i++){
					Course c = al.get(i-1);
					if( re.getCourseId()== i){
				%>
					<option value="<%= c.getCourseId()%>" selected><%= c.getCourseName() %></option>
				<% }else{ %>
					<option  value="<%= c.getCourseId()%>"><%= c.getCourseName() %></option>
				<% }} %>

				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="cent">
			<input type="submit" value="変更" />
			<input type = "hidden" name = "rsvId" value ="<%= re.getRsvId() %>"/>
			<input type = "hidden" name = "usrId" value ="<%= re.getUsrId() %>"/>
			<input type = "hidden" name = "tableId" value ="<%= re.getTableId() %>"/>
			<input type = "hidden" name = "mode" value ="変更処理"/>
			</td>
		</tr>
	</table>
</form>
<a href="ReserveListSvl">予約一覧に戻る</a>
<div class="site-footer">
<div class="copyright">Restaurante IDEALLE</div>
</div>
</body>
</html>