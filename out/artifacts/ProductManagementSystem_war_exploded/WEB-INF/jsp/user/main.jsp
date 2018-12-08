<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/11/25
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Main</title>
    <c:import url="head.jsp"/>
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/user/main">Product Management System</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="">
                            Product
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/product/pre-add">
                                Add Item</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/product/show">
                                Products List</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/product/pre-find">
                                Find items</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="">
                            ${username}
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/user/user-info">Show Info</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/user/logout">Log out</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="">
                            What's New
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="https://github.com/lihanxiang/ProductManagementSystem">New Features</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown"
                           href="">
                            GitHub
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="https://github.com/lihanxiang/ProductManagementSystem">About this project</a></li>
                            <li class="divider"></li>
                            <li><a href="https://github.com/lihanxiang">About me</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <h3 style="text-align: center">Welcome to Product Management System</h3>
</body>
</html>
