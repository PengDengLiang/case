<%--
  Created by IntelliJ IDEA.
  User: 11967
  Date: 2018/8/11
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入页面</title>
</head>
<style>
     td {
        text-align: center;
    }
</style>
<body>
<center>
    <h1>登入界面</h1>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        <table bgcolor="#ffe4c4" align="center">
            <tr>
                <td>账号</td>
                <td><input type="text" name="username" placeholder="请输入账号"> </td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password" placeholder="请输入密码"> </td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCoke" placeholder="请输入验证码"> </td>
            </tr>
            <tr>
                <td colspan="2"><img src="${pageContext.request.contextPath}/checkImgServlet" id="img"> </td>
            </tr>
            <tr >
                <td colspan="2" ><input type="submit" value="登入"> </td>
            </tr>
            <tr>
                <td colspan="2">${login_error}</td>
            </tr>
        </table>
    </form>
</center>
<script>
    window.onload=function () {
        var img_id=document.getElementById("img");
        img_id.onclick=function () {
            img_id.src="${pageContext.request.contextPath}/checkImgServlet?time="+new Date().getTime();
        }
    }
</script>
</body>
</html>
