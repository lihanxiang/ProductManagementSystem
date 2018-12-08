<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/30
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign in</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
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
                <form class="form-signin" action="${pageContext.request.contextPath}/user/login" method="post">
                    <h2 class="form-signin-heading" style="text-align: center">Sign in</h2><br>
                    <h3 class="form-signin-heading" style="text-align: center">${message}</h3>
                    <label for="username" class="sr-only">Username</label>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Username"
                           required value="${user.username}"><br>
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password"
                           required value="${user.password}"><br>
                    <label for="verifyCode" class="sr-only">Verify Code</label>
                    <input type="text" id="verifyCode" name="verifyCode" class="form-control" placeholder="Verify Code"/>
                    <img src="${pageContext.request.contextPath}/user/verify_code" title="点击更换" onclick="RefreshCode(this)"
                         class="img-thumbnail"/>
                    <br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">login</button>
                    <br>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
