<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>添加用户</title>
    <c:import url="head.jsp"/>
    <script type="text/javascript">
        function RefreshCode(obj){
            obj.src = obj.src + "?code=" + Math.random();
        }
    </script>
    <style>
        body{
            background-color: #CDC9C9;
        }
        .container{
            float: none;
            display: block;
            margin-top: 10%;
            margin-left: auto;
            margin-right: auto;
        }
        .col-center-block{
            float: none;
            display: block;
            margin-left: auto;
            margin-right: auto;
            background-color: #DCDCDC;
            border-radius: 20px;
        }
    </style>

</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <div class="container">
        <div class="row myCenter">
            <div class="col-xs-6 col-md-4 col-center-block">
                <br><br>
                <form class="form-signin" action="${pageContext.request.contextPath}/user/add" method="post">
                    <h2 class="form-signin-heading" style="text-align: center">Sign up</h2><br>
                    <h3 class="form-signin-heading" style="text-align: center">${message}</h3>
                    <label for="username" class="sr-only">Username</label>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Username"
                           required value="${user.username}"><br>
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password"
                           required value="${user.password}"><br>
                    <label for="verifyCode" class="sr-only">Password</label>
                    <label for="phone" class="sr-only">Username</label>
                    <input type="text" id="phone" name="phone" class="form-control" placeholder="Phone"
                           required value="${user.phone}"><br>
                    <label for="email" class="sr-only">Username</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="Email"
                           required value="${user.email}"><br>
                    <input type="text" id="verifyCode" name="verifyCode" class="form-control" placeholder="Verify Code"/>
                    <img src="${pageContext.request.contextPath}/user/verify_code" title="点击更换" onclick="RefreshCode(this)"
                         class="img-thumbnail"/>
                    <br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
                    <br>
                </form>
            </div>
        </div>
    </div>
</body>
</html>