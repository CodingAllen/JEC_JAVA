<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録</title>
</head>
<body>
	<form action="/kadai1101/login" method="post">
		ログイン<br />
		<hr>
		ユーザID <input type="text" name="userid"><br> パスワード <input
			type="password" name="password"><br> <input
			type="submit" value="ログイン"><br>
		<% if(request.getAttribute("message") != null){ %>
		<%= request.getAttribute("message") %>
		<% } %>
	</form>
</body>
</html>