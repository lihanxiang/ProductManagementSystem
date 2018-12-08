<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 8/27/2018
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息</title>
    <c:import url="head.jsp"/>
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/user/main">Back to Main Page</a>
            </div>
        </div>
    </nav>
    <form action="${pageContext.request.contextPath}/user/set-info" method="post">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="用户名" name="username" value="${user.username}">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="手机号码" name="phone" value="${user.phone}">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="邮箱" name="email" value="${user.email}">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="性别" name="gender" value="${user.gender}">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="个人简介" name="description" value="${user.description}">
        </div>
        <button type="submit" class="btn btn-primary mb-2">Post</button>
    </form>
</body>
</html>
