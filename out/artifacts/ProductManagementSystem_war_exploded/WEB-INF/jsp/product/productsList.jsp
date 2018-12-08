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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商品列表</title>
    <c:import url="head.jsp"/>
</head>
<body>
    <div id="table">
        <table class="table table-hover">
            <caption>Product List</caption>
            <thead>
            <tr>
                <th>Barcode</th>
                <th>Name</th>
                <th>Units</th>
                <th>PurchasePrice</th>
                <th>SalePrice</th>
                <th>Inventory</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${productList}" var="item">
                <tr>
                    <td>${item.barcode }</td>
                    <td>${item.name }</td>
                    <td>${item.units }</td>
                    <td>${item.purchaseprice }</td>
                    <td>${item.saleprice }</td>
                    <td>${item.inventory }</td>
                    <td>
                        <a class="btn btn-default" role="button"
                           href="${pageContext.request.contextPath}/product/${item.id}/pre-edit">
                            Edit</a>
                    </td>
                    <td>
                        <a class="btn btn-default" role="button"
                           href="${pageContext.request.contextPath}/product/${item.id}/delete">
                            Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
