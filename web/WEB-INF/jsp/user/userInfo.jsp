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
    <title>UserInfo</title>
    <link type="text/css" rel="stylesheet" href="../../../css/login.css">
</head>
<body>
<form action="setUserInfo.action" method="post">
    <input type="hidden" name="method" value="login">
    <div id="login">
        <h1 style="text-align: center ">用户信息</h1>
        <p style="text-align: center">${message}</p>
        <input type="text" name="username" placeholder="姓名（无法更改）" value="${user.username}">
        <input type="text" name="phone" placeholder="手机号码" value="${user.phone}"/><br>
        <input type="text" name="email" placeholder="邮箱" value="${user.email}"/><br>
        <input type="text" name="gender" placeholder="性别" value="${user.gender}">
        <input type="text" name="description" placeholder="描述" value="${user.description}"/><br>
        <input class="button" type="submit" value="保存">
        <input class="button" type="reset" value="重置">
    </div>
</form>
</body>
</html>
