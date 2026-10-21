<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
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
        <div class="card-title">添加商品</div>

        <form action="AddGoods" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">商品名称</label>
                <input type="text" id="name" name="name" placeholder="请输入商品名称">
            </div>
            <div class="form-group">
                <label for="price">商品价格</label>
                <input type="text" id="price" name="price" placeholder="例如：99.00">
            </div>
            <div class="form-group">
                <label for="cnt">商品数量</label>
                <input type="text" id="cnt" name="cnt" placeholder="库存数量">
            </div>
            <div class="form-group">
                <label for="pic">商品图片</label>
                <input type="file" id="pic" name="pic">
                <div class="form-hint">支持常见图片格式，可不上传</div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="添加商品">
            </div>
        </form>
    </div>
</div>
</body>
</html>
