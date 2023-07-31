<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<body>
<h1>${member.memberName }さんの MyPage</h1>
<hr>
カートの商品
<c:if test="${cartList != null}">
<table>
    <tr>
        <th>商品画像</th>
        <th>商品名</th>
        <th>単価</th>
        <th>数量</th>
    </tr>
    <c:forEach var="cart" items="${cartList}">
        <tr>
            <td><img src="images/${cart.goodsImage}"></td>
            <td>${cart.goodsName}</td>
            <td>${cart.price}</td>
            <td>${cart.num}</td>
        </tr>
    </c:forEach>
</table></c:if>

</body>
</html>
