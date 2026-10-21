<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="mvc.bean.Goods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="topbar">
    <div class="brand">商品<span>管理</span>系统</div>
    <div class="nav">
        <a class="btn-nav" href="add_goods.jsp">添加商品</a>
        <a href="index.jsp">退出</a>
    </div>
</div>

<div class="page">
    <div class="card">
        <div class="toolbar">
            <h2 class="card-title">商品列表</h2>
            <a class="btn btn-success" href="add_goods.jsp">+ 添加商品</a>
        </div>

        <c:choose>
            <c:when test="${list == []}">
                <div class="alert-empty">暂无数据，点击右上角「添加商品」开始录入</div>
            </c:when>

            <c:otherwise>
                <div class="table-wrap">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>商品名称</th>
                            <th>商品价格</th>
                            <th>商品库存</th>
                            <th>商品图片</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%int i = 1;%>
                        <c:forEach items="${list}" var="goods">
                            <tr>
                                <td><%=i++%></td>
                                <td>${goods.name}</td>
                                <td class="price">${goods.price}</td>
                                <td>${goods.cnt}</td>
                                <td>
                                    <c:if test="${goods.pic != '没有上传文件'}">
                                        <img class="thumb" src="img/${goods.pic}" alt="">
                                    </c:if>
                                    <c:if test="${goods.pic == '没有上传文件'}">
                                        <span class="thumb-empty">无封面</span>
                                    </c:if>
                                </td>
                                <td class="row-actions">
                                    <a class="btn-edit" href="QueryGoodsById?id=${goods.id}">编辑</a>
                                    <a class="btn-danger-ghost" href="DeleteGoods?id=${goods.id}"
                                       onclick="return confirm('删除后不可恢复，确定删除？')">下架</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <p class="footer">Goods Management System · Admin</p>
</div>
</body>
</html>
