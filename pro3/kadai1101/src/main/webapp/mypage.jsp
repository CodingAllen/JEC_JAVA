<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kadai1101.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<body>
	<% User user = (User)session.getAttribute("user"); %>
	<table>
		<tr>
			<td><img
				src="photos/<%= user.getPhoto() %>"></td>
			<td>
			<%= user.getUserName() %>
			</td>
		</tr>
	</table>
	<hr>
	<form action="login.jsp" method="get">
 		<input type="submit" value="ログインへ戻る">
	</form>
</body>
</html>
