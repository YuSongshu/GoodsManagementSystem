<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="page-narrow">
    <div class="card login-card">
        <div class="card-title">登录</div>
        <p class="login-sub">未注册账号默认为普通用户</p>

        <form action="Login" method="post">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" id="username" name="username" placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" id="password" name="password" placeholder="请输入密码">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>

            <c:if test="${param.error != null}">
                <div class="alert alert-error">账号密码错误</div>
            </c:if>
        </form>
    </div>
    <p class="footer"><a href="index.jsp">返回首页</a></p>
</div>
</body>
</html>
