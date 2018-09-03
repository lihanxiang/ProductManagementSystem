<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/30
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../../css/addUser.css">
    <script type="text/javascript">
        function RefreshCode(obj){
            obj.src = obj.src + "?code=" + Math.random();
        }
    </script>
</head>
<body>
<form action="addUser.action" method="post">
    <input type="hidden" name="method" value="register">
    <div id="register">
        <h1 style="text-align: center ">注册</h1>
        <p align="center" style="color:red;font-weight: 800">${message}</p>
        <input type="text" name="username" placeholder="用户名" value="${user.username}"/><br>
        <input type="password" name="password" placeholder="密  码" value="${user.password}"/><br>
        <input type="text" name="phone" placeholder="手机号码" value="${user.phone}"/><br>
        <input type="text" name="email" placeholder="邮箱" value="${user.email}"/><br>
        <input type="text" name="verifyCode" placeholder="验证码" size="10"/><br>
        <img id="verifyCode" src="getVerifyCode.action" title="点击更换" onclick="RefreshCode(this)"/><br>
        <input class="button" style="text-align: center" type="submit" value="注册">
        <input class="button" style="text-align: center" type="reset" value="重置">
    </div>
</form>
</body>
</html>