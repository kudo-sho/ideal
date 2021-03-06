<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.*"%>
<%@ page import="controller.*"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メニューメンテナンス</title>
<style type="text/css">
<!--
* {
	padding: 5px;
	margin: 0px
}

body {
	text-align: center
}

table#menu {
	width: 160px;
}

table#main {
	border-collapse: collapse;
}

td {
	text-align: center;
}

td.id {
	width: 25px;
	text-align: center;
}

td.menuname {
	width: 250px;
	text-align: left;
}

td.price {
	width: 60px;
	text-align: right;
}

td.detail {
	width: 500px;
	text-align: left;
}

td.orderflg {
	width: 65px;
	text-align: center;
}

td.botan {
	width: 40px;
	text-align: center;
}


th.id {
	width: 25px;
}

th.menuname {
	width: 250px;
}

th.price {
	width: 60px;
}

th.detail {
	width: 500px;
}

th.orderflg {
	width: 65px;
}

th.botan {
	width: 80px;
}


tr.tr1 {
	background-color: aquamarine;
}

tr.tr0 {
	background-color: lightgreen;
}

tr.tr3 {
	background-color: green;
}

tr.tr4 {
	background-color: bisque;
}

th {
	border: 1px black solid;
}

th.th0 {
	width: 30px;
	background-color: green;
}

th.th1 {
	width: 30px;
	background-color: lime;
}
-->
</style>
</head>


<body>
	<center>
		<!-- ヘッダー -->
		<div style="background-color: green">
			<h1>メニューメンテナンス</h1>
		</div>
		<br />
		<div align="left">
			<font color="red" size="5">

<%
//引き渡されたメッセージを表示
//引き渡されたメッセージがnullの場合は非表示
String msg = null;
request.setCharacterEncoding("utf-8");
msg = (String)request.getAttribute("msg");
if(msg != null)
	out.print(msg);
%>

</font>
		</div>



		<%
		String thstyle = "1";

				MenuType mt;
				String typeName = "一覧";
				int typeID = 100;

				if(request.getParameter("typeID") != null){
					typeID = Integer.parseInt(request.getParameter("typeID"));
				}
		%>

		<!-- サイドメニュー -->
		<div style="float: left; width: 15%; height: 600px;">

			<table id="menu" cellspacing="5">

				<%
				ArrayList<MenuType> almt = (ArrayList<MenuType>)request.getAttribute("mType");

				for (int x = 0; x < almt.size(); x++) {
					mt = (MenuType)almt.get(x);
					if (mt.getTypeId() == typeID) { //選択した分類と一致する時のみ緑色にする
						//if (x == 1) {
						thstyle = "0";
						typeName = mt.getTypeName();
					} else {
						thstyle = "1";
					}

				%>


				<tr>
					<!-- getしてくる -->
					<form id="fm<%= x %>" name="fm<%= x %>" action="MenuMaintenanceSvl"
						method="post">
						<input type="hidden" name="typeID" value="<%= mt.getTypeId() %>" />
					<th class="th<%= thstyle %>" onclick="document.fm<%= x %>.submit();"><%= mt.getTypeName() %></th>
					</form>
				</tr>

				<%
				}
				%>

			</table>
		</div>




		<%
		Locale jp = new Locale("ja","JP");
		NumberFormat nfc = NumberFormat.getCurrencyInstance(jp);
		String trstyle = "";
		String[] orderFlg = { "不可", "可" }; //getしてくるorderFlgで表示を変える
		ArrayList<Menu> alm = (ArrayList<Menu>)request.getAttribute("menu");
		%>
		<!-- メイン -->
		<div style="width: 85%; height: 600px;">
			<table id="main" border="1" width="85%" cellspacing="0">
				<tr class="tr4">
				<td colspan="7">＜＜＜<font size="5" color="green">

				<%
				if(alm.size()==0){
					out.print("メニューがありません！");
					}else{
						out.print(typeName);
					}
				 %> </font>＞＞＞</td>
				</tr>
				<tr class="tr3">
					<th class="id">ID</th>
					<th class="menuname">メニュー</th>
					<th class="price">価格</th>
					<th class="detail">コメント</th>
					<th class="orderflg">オーダー</th>
					<th class="botan" colspan="2">
						&nbsp;
					</th>
				</tr>
				<%

				//ArrayList<Menu> alm = (ArrayList<Menu>)request.getAttribute("menu");

				for (int i = 0; i < alm.size(); i++) {
					Menu menu = alm.get(i);
				//for (int i = 0; i < 5; i++) {
					if (i % 2 == 1) { //テーブルの色を交互に変える
						trstyle = "0";
					} else {
						trstyle = "1";
					}
				%>
				<tr class="tr<%=trstyle%>">
					<td class="id"><%= menu.getMenuId() %></td>
					<td class="menuname"><%= menu.getMenuName() %></td>
					<td class="price"><%= nfc.format(menu.getPrice()) %></td>
					<td class="detail"><%= menu.getDetail() %></td>
					<td class="orderflg"><%= orderFlg[menu.getOrderFlg()] %></td>
					<form id="fchange" action="MenuUpdateSvl" method="post">
						<td class="botan"><input type="submit" value="変更" /></td>
						<input type="hidden" name="typeID" value="<%= menu.getTypeId() %>" />
						<input type="hidden" name="menuID" value="<%= menu.getMenuId() %>" />
					</form>
					<form id="fdelete" action="MenuDeleteSvl" method="post">
						<td class="botan"><input type="submit" value="削除" /></td>
						<input type="hidden" name="typeID" value="<%= menu.getTypeId() %>" />
						<input type="hidden" name="menuID" value="<%= menu.getMenuId() %>" />
					</form>
				</tr>

				<%
				}
				%>
				<tr class="tr3">
					<form id="fdelete" action="MenuInsertSvl" method="post">
					<%
					String add = "新しいメニューの追加";
					if(typeID == 100) add = "新しいコースの追加";
					%>
						<td align="center" colspan="7"><input type="submit"
							value="<%= add %>" /></td>
						<input type="hidden" name="typeID" value="<%= typeID %>" />
					</form>
				</tr>

			</table>

			<div>
				<br /> <br /> <a href="adminIndex.jsp"><h4>処理メニューに戻る</h4></a>

			</div>
		</div>
	</center>
</body>
</html>