<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<h1>ログインページ</h1>
<form action="/kadai1301/login" method="post">
  <label for="memberId">会員ID</label>
  <input type="text" id="memberId" name="memberId"><br>
  <label for="password">パスワード</label>
  <input type="password" id="password" name="password"><br>
  
    <div>${message}</div>

  <input type="submit" value="ログイン">
</form>

</body>
</html>
