<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/30
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../../css/login.css">
    <script type="text/javascript">
        function RefreshCode(obj){
            obj.src = obj.src + "?code=" + Math.random();
        }
    </script>
</head>
<body>
<form action="login.action" method="post">
    <input type="hidden" name="method" value="login">
    <div id="login">
        <h1 style="text-align: center ">登陆</h1>
        <p align="center" style="color:red;font-weight: 800">${message}</p>
        <input type="text" name="username" placeholder="用户名" value="${user.username}"/><br>
        <input type="password" name="password" placeholder="密  码" value="${user.password}"/><br>
        <input type="text" name="verifyCode" placeholder="验证码" size="10"/><br>
        <img id="verifyCode" src="getVerifyCode.action" title="点击更换" onclick="RefreshCode(this)"/><br>
        <input class="button" type="submit" value="登录">
        <input class="button" type="reset" value="重置">
        <br><br><br>还没有账号？<a href="preAdd.action">快速注册</a>
    </div>
</form>
</body>
</html>
