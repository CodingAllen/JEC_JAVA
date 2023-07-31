<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Task</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <style>
        body {
            padding: 20px;
        }
    </style>

</head>
<body>
    <h2 class="mb-4">Add New Task</h2>
    <form action="<%=request.getContextPath()%>/insert" method="post" class="form-inline">
        <label for="deadline" class="mr-2">期限</label>
        <input type="text" id="deadline" name="deadline" class="form-control mr-2">

        <label for="subject" class="mr-2">やること</label>
        <input type="text" id="subject" name="subject" class="form-control mr-2">

        <label for="priority" class="mr-2">優先度</label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" id="priorityLow" name="priority" value="1">
            <label class="form-check-label" for="priorityLow">低</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" id="priorityMedium" name="priority" value="2">
            <label class="form-check-label" for="priorityMedium">中</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" id="priorityHigh" name="priority" value="3">
            <label class="form-check-label" for="priorityHigh">高</label>
        </div>
        
        <input type="hidden" id="state" name="state" value="0">

        <button type="submit" class="btn btn-primary ml-2">追加</button>
    </form>
</body>
</html>