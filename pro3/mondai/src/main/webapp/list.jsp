<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<body>
<h1 class="mb-4">Todo List</h1>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>期限</th>
            <th>やること</th>
            <th>優先度</th>
            <th>状態</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="todo" items="${todos}">
            <tr>
                <td><c:out value="${todo.deadline}"/></td>
                <td><c:out value="${todo.subject}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${todo.priority == 1}">低</c:when>
                        <c:when test="${todo.priority == 2}">中</c:when>
                        <c:otherwise>高</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${todo.state == 0}">
                            <input type="radio" name="state" value="0" form="form${todo.no}" checked>未着手
                            <input type="radio" name="state" value="1" form="form${todo.no}">着手済
                            <input type="radio" name="state" value="9" form="form${todo.no}">完了
                        </c:when>
                        <c:when test="${todo.state == 1}">
                            <input type="radio" name="state" value="0" form="form${todo.no}">未着手
                            <input type="radio" name="state" value="1" form="form${todo.no}" checked>着手済
                            <input type="radio" name="state" value="9" form="form${todo.no}">完了
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="state" value="0" form="form${todo.no}" disabled>未着手
                            <input type="radio" name="state" value="1" form="form${todo.no}" disabled>着手済
                            <input type="radio" name="state" value="9" form="form${todo.no}" checked disabled>完了
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form action="<%=request.getContextPath()%>/update" method="get" id="form${todo.no}" class="form-inline">
                        <input type="hidden" name="no" value="${todo.no}">
                        <c:if test="${todo.state != 9}">
                            <button type="submit" class="btn btn-primary btn-sm">状態の変更</button>
                        </c:if>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<form action="<%=request.getContextPath()%>/insert" method="get">
    <button type="submit" class="btn btn-primary">やること追加</button>
</form>

</body>
</html>
