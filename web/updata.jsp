<%--
  Created by IntelliJ IDEA.
  User: 11967
  Date: 2018/8/13
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/updataServlet" method="post">
    <input type="hidden" name="id" value="${user.id}">
    姓名<input type="text" name="name" placeholder="请输入姓名" value="${user.name}">
    性别<input type="text" name="gender" placeholder="请输入性别"value="${user.gender}">
    年龄<input type="text" name="age" placeholder="请输入年龄"value="${user.age}">
    地址<input type="text" name="address" placeholder="请输入地址"value="${user.address}">
    qq<input type="text" name="qq" placeholder="请输入QQ"value="${user.qq}">
    email<input type="text" name="email" placeholder="请输入Email"value="${user.email}">
    <input type="submit" value="添加">
</form>
</body>
</html>
