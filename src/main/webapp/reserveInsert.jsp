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
<title>新規予約</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type = "text/javascript">
	<!--
		function check(){
			//window.alert("SUBMIT CHECK START!");
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
<%
String name = (String)session.getAttribute("usrName");
String message = (String)request.getAttribute("msg");
if(message == null) {message = "&nbsp;";}
%>

<h1><%= name %>様ご予約</h1>
<%= message  %>
<%
	Reserve re = (Reserve)request.getAttribute("reserve") ;
	ArrayList<Course> al = (ArrayList<Course>)request.getAttribute("courseList");
%>


<form name ="f1" id = "f1" action="ReserveOperationSvl"  method = "post" onsubmit = "return check();">
	<table boder="1">
		<tr>
			<th>日付</th>
			<td>
			<select name="rsvYy">
			<%	Date date = new Date();
	       		Calendar calendar = Calendar.getInstance();
	        	calendar.setTime(date);
	        	int year = calendar.get(Calendar.YEAR);
	        	int month = calendar.get(Calendar.MONTH)+1;
	        	int day = calendar.get(Calendar.DATE);
	        	int hour = 0;
	        	int min = 0;
	        	int usrId = 0;
	        	int person = 0;
	        	int courseId = 0;
	        	if(re != null){
		        	year = re.getRsvYy();
		        	month = re.getRsvMm();
		        	day = re.getRsvDd();
		        	hour = re.getRsvHh();
		        	min = re.getRsvMi();
		        	usrId = re.getUsrId();
		        	person = re.getPerson();
		        	courseId = re.getCourseId();
	        	}
//	        	System.out.println("jsp:"+year+" "+month+" "+day+" "+hour+" "+min+
//	        			" "+usrId+" "+person+" "+courseId);
	        %>
			<% for(int i=0;i<2;i++){
				String sel=""; 
				if(i==year){sel="selected";} %>
				<option value="<%= year + i %>" <%= sel %>><%= year + i %></option>
			<% } %>
			</select>年
			<select name="rsvMm">
			<% for(int i=1;i<=12;i++){ 
				String sel=""; 
				if(i==month){sel="selected";} %>
				<option value="<%= i %>" <%= sel %>><%= i %></option>
			<% } %>
			</select>月
			<select name="rsvDd">
			<% for(int i=1;i<=31;i++){
				String sel=""; 
				if(i==day){sel="selected";} %>
				<option value="<%= i %>" <%= sel %>><%= i %></option>
			<% } %>
			</select>日
			</td>
		</tr>
		<tr>
			<th>時刻</th>
			<td>
			<select name="rsvHh">
			<% for(int i=17;i<=21;i++){
				String sel=""; 
				if(i==hour){sel="selected";} %>
				<option value="<%= i %>" <%= sel %>><%= i %></option>
			<% } %>
			</select>時
			<select name="rsvMi">
			<% for(int i=0;i<=45;i+=15){
				String sel=""; 
				if(i==min){sel="selected";} %>
				<option value="<%= i %>" <%= sel %>><%= i %></option>
			<% } %>
			</select>分
			</td>
		</tr>
		<tr>
			<th>人数</th>
			<td>
			<select name="person">
			<% for(int i=1;i<=6;i++){
				String sel=""; 
				if(i==person){sel="selected";} %>
				<option value="<%= i %>" <%= sel %>><%= i %></option>
			<% } %>
			</select>人
			</td>
		</tr>
		<tr>
			<th>コース</th>
			<td><select name="courseId">
				<% for(int i=0;i<al.size();i++){
					Course c = al.get(i);
					int cid = c.getCourseId();
					String sel=""; 
					if(cid==courseId){sel="selected";} %>
					<option value="<%= cid %>" <%= sel %>><%= c.getCourseName() %> </option>
				<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<td class="cent" colspan="2">
				<input type="submit" value="予約" />
				<input type = "hidden"  name = "usrId" value ="<%= usrId %>"/>
				<input type = "hidden"  name = "mode" value ="登録処理"/>
			</td>
		</tr>
	</table>
</form>
<a href="ReserveListSvl">予約一覧に戻る</a>
</body>
</html>