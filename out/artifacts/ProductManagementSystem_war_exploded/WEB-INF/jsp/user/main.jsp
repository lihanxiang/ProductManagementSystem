<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/2/26
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../../css/background.css">
    <title>Title</title>
</head>
<body>
    <div style="text-align: right; margin-top: 10px; margin-right: 100px">
        <a href="showInfo.action">${username}</a>
        <a href="${pageContext.request.contextPath}/product/getFrame.action">进入管理系统</a>
        <a href="userStatus.action">查看登陆状态</a>
        <a href="logout.action">登出</a>
        <h3 style="text-align: center">${message}</h3>
    </div>
</body>
</html>
