<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<form action="/kadai1201/toroku" method="post">
		<table>
			<tr>
				<td>学生</td>
				<td><select name="gakuseiNo">
						<c:forEach var="student" items="${students}">
							<option value="${student.gakuseiNo}">${student.gakuseiName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>科目</td>
				<td><select name="kamokuNo">
						<c:forEach var="kamoku" items="${kamokus}">
							<option value="${kamoku.kamokuNo}">${kamoku.kamokuName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>成績</td>
				<td><input type="text" name="seiseki"></td>
			</tr>
		</table>
		<input type="submit" value="登録">
	</form>
</body>
</html>
