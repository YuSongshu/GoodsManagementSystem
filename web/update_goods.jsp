<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑商品</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="topbar">
    <div class="brand">商品<span>管理</span>系统</div>
    <div class="nav">
        <a href="QueryGoodsAll">返回列表</a>
        <a href="index.jsp">退出</a>
    </div>
</div>

<div class="page-narrow">
    <div class="card">
        <div class="card-title">编辑商品</div>

        <form action="UpdateGoodsById" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${goods.id}">
            <%--每次修改 原来图片 删除--%>
            <input type="hidden" name="oldPic" value="${goods.pic}">

            <div class="form-group">
                <label for="name">商品名称</label>
                <input type="text" id="name" name="name" value="${goods.name}">
            </div>
            <div class="form-group">
                <label for="price">商品价格</label>
                <input type="text" id="price" name="price" value="${goods.price}">
            </div>
            <div class="form-group">
                <label for="cnt">商品数量</label>
                <input type="text" id="cnt" name="cnt" value="${goods.cnt}">
            </div>

            <div class="form-group">
                <label>当前图片</label>
                <c:if test="${goods.pic != '没有上传文件'}">
                    <div class="pic-box">
                        <img class="thumb-preview" src="img/${goods.pic}" alt="">
                    </div>
                </c:if>
                <c:if test="${goods.pic == '没有上传文件'}">
                    <div class="pic-box muted">暂无封面</div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="pic">更换图片</label>
                <input type="file" id="pic" name="pic">
                <div class="form-hint">不选择文件则保留原图</div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="修改商品">
            </div>
        </form>
    </div>
</div>
</body>
</html>
