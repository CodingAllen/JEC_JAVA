<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Task</title>
</head>
<body>
    <h2>Add New Task</h2>
    <form action="<%=request.getContextPath()%>/insert" method="post">
        期限  
        <input type="text" id="deadline" name="deadline"><br>

        やること  
        <input type="text" id="subject" name="subject"><br>

        優先度  
        <input type="radio" id="priorityLow" name="priority" value="1">
        低  
        <input type="radio" id="priorityMedium" name="priority" value="2">
        中  
        <input type="radio" id="priorityHigh" name="priority" value="3">
        高  
        <br>
        状態  
        未着手<br>
        <input type="hidden" id="state" name="state" value="0"><br>

        <input type="submit" value="追加">
    </form>
</body>
</html>

