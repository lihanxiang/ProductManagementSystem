<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/20
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <base target="main">
    <script type="text/javascript">
        function main() {
            window.top.location = '${pageContext.request.contextPath}/getMain.action'
        }
    </script>
</head>
<body style="text-align: center">
        <h1>Product Management System</h1>
        <a style="text-align: center" href="${pageContext.request.contextPath}/product/preAdd.action" >Add Product</a>
        <a style="text-align: center" href="${pageContext.request.contextPath}/product/showProducts.action">Show Products</a>
        <a style="text-align: center" href="${pageContext.request.contextPath}/product/preFind.action">Find Product</a>
        <a style="text-align: center" onclick="main()" href="">退出管理系统</a>
</body>
</html>
