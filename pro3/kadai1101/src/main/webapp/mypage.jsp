<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kadai1101.User"%>
<%@ page import="kadai1101.Goods"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<body>
    <%
    User user = (User) session.getAttribute("user");
    %>
    <table>
        <tr>
            <td><img src="photos/<%=user.getPhoto()%>"></td>
            <td><%=user.getUserName()%>のマイページ</td>
        </tr>
    </table>
    <hr>
    <form action="/kadai1101/goodslist" method="get">
        価格<input type="text" name="price" /><input type="submit" value="検索" />
    </form>

    <%
    List<Goods> list = (List<Goods>) session.getAttribute("list");
    if (list != null) {
    %>
    <form action="/kadai1101/konyu" method="post">
        <table>
            <tr>
                <th>商品名</th>
                <th>価格</th>
                <th>商品イメージ</th>
                <th>購入数量</th>
            </tr>
            <%
            for (Goods goods : list) {
            %>
            <tr>
                <td><%=goods.getGoodsName()%></td>
                <td><%=goods.getPrice()%></td>
                <td><img src="images/<%=goods.getImageFile()%>" /></td>
                <td><input type="text" name="quantity_<%=goods.getGoodsCode()%>" /></td>
                <input type="hidden" name="goodsId_<%=goods.getGoodsCode()%>" value="<%=goods.getGoodsCode()%>">
            </tr>
            <%
            }
            %>
        </table>
      
        <input type="hidden" name="userId" value="<%=user.getUserId()%>">
        <input type="submit" value="購入">
    </form>
    <%
    }
    %>

    <form action="login.jsp" method="get" style="display: inline;">
        <input type="submit" value="ログインへ戻る">
    </form>
</body>



</html>
