<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/6/4
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../../css/content.css">
    <title>Product List</title>
</head>
<body>
<form method="post">
    商品列表：
    <table width="100%" border=1>
        <tr>
            <td>Barcode</td>
            <td>Name</td>
            <td>Units</td>
            <td>PurchasePrice</td>
            <td>SalePrice</td>
            <td>Inventory</td>
        </tr>
        <c:forEach items="${productList }" var="item">
            <tr>
                <td>${item.barcode }</td>
                <td>${item.name }</td>
                <td>${item.units }</td>
                <td>${item.purchaseprice }</td>
                <td>${item.saleprice }</td>
                <td>${item.inventory }</td>
                <td>
                    <a href="${pageContext.request.contextPath}/product/preEdit.action?id=${item.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/product/deleteProduct.action?id=${item.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</form>
</body>

</html>
