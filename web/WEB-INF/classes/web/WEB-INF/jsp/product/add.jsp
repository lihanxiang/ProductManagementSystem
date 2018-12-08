<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/6/6
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
    <c:import url="head.jsp"/>
</head>
<body>
<div class="container">
    <div class="row myCenter">
        <div class="col-xs-6 col-md-4 col-center-block">
            <form action="${pageContext.request.contextPath}/product/edit" method="post">
                <label for="name" class="sr-only">Name</label>
                <input type="text" id="name" name="name" class="form-control"
                       value="${product.name}">
                <label for="barcode" class="sr-only">Barcode</label>
                <input type="text" id="barcode" name="barcode" class="form-control"
                       value="${product.barcode}">
                <label for="units" class="sr-only">Units</label>
                <input type="text" id="units" name="units" class="form-control"
                       value="${product.units}">
                <label for="purchaseprice" class="sr-only">Purchase Price</label>
                <input type="text" id="purchaseprice" name="purchaseprice" class="form-control"
                       value="${product.purchaseprice}">
                <label for="saleprice" class="sr-only">Sale Price</label>
                <input type="text" id="saleprice" name="saleprice" class="form-control"
                       value="${product.saleprice}">
                <label for="inventory" class="sr-only">Inventory</label>
                <input type="text" id="inventory" name="inventory" class="form-control"
                       value="${product.inventory}">
                <button type="submit" class="btn btn-primary mb-2">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>