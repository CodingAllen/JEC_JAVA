<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JEC Shopping</h1>
	<br>
	<form action="/kadai1303/search" method="get">
		検索キーワード<input type="text" name="keyword"> <input type="submit"
			value="検索">
	</form>
	<c:choose>
		<c:when test="${member == null}">
			<a href="login.jsp">ログイン</a>
		</c:when>
		<c:otherwise>
			<a href="/kadai1303/mypage">マイページ</a>
		</c:otherwise>
	</c:choose>


	<hr>
	<c:if test="${goodsList != null}">
		<table>
			<tr>
				<th>商品コード</th>
				<th>商品名</th>
				<th>商品単価</th>
				<th>商品イメージ</th>
				<th></th>
			</tr>
			<c:forEach var="goods" items="${goodsList}">
				<tr>
					<td>${goods.goodsCode}</td>
					<td>${goods.goodsName}</td>
					<td>${goods.price}</td>
					<td><img src="images/${goods.goodsImage}"></td>
					<td>
						<form action="/kadai1303/cart" method="post">
							<input type="hidden" name="goodsCode" value="${goods.goodsCode}">
							<input type="submit" value="カートに追加する">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
