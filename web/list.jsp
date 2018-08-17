<%--
  Created by IntelliJ IDEA.
  User: 11967
  Date: 2018/8/11
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>表格查询</title>
</head>

<body>
<center>
    <form action="${pageContext.request.contextPath}/findMorePageServlet" method="post">
    姓名:<input type="text" name="checkName" placeholder="请输入姓名">
    性别:<input type="text" name="checkGender" placeholder="请输入性别">
    <input type="submit" value="查询">
    </form>
    <a href="${pageContext.request.contextPath}/add.jsp" id="add" name="add">添加联系人</a>
    <a href="#" id="remove" name="delect">删除联系人</a>
    <table bgcolor="#faebd7" border="1px" width="900px">
        <tr>
            <th><input type="checkbox"  id="check"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>地址</th>
            <th>qq</th>
            <th>email</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${page.list}" var="user" varStatus="us">
            <tr>
                <td><input type="checkbox" name="uid" value="${user.id}"></td>
                <td>${us.count}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/selectIdServlet?id=${user.id}">修改</a>
                    <a href="#" name="delect" id="delect" onclick="del(${user.id})">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    总页数${page.totalPage}/当前页码${page.currentPage}
        <a href="${pageContext.request.contextPath}/findMorePageServlet?currentPage=1&checkName=${name}&checkGender=${gender}">首页</a>

    <c:if test="${page.currentPage>1}">
    <a href="${pageContext.request.contextPath}/findMorePageServlet?currentPage=${page.currentPage-1}&checkName=${name}&checkGender=${gender}"> 上一页</a>
    </c:if>
    <c:forEach end="${page.totalPage}" var="i" begin="1" step="1">
        <a href="${pageContext.request.contextPath}/findMorePageServlet?currentPage=${i}&checkName=${name}&checkGender=${gender}">${i}</a>
    </c:forEach>
    <c:if test="${page.currentPage<page.totalPage}">
        <a href="${pageContext.request.contextPath}/findMorePageServlet?currentPage=${page.currentPage+1}&checkName=${name}&checkGender=${gender}"> 下一页</a>
    </c:if>
        <a href="${pageContext.request.contextPath}/findMorePageServlet?currentPage=${page.totalPage}&checkName=${name}&checkGender=${gender}">尾页</a>
</center>
<script>
   var check_id=document.getElementById("check");
   var uid_id=document.getElementsByClassName("uid")
   // check_id.onclick=function () {
       if (uid_id.checked){
        for(var i=0;i<uid_id.length;i++){
            uid_id(i).checked=true;
        }
       // }
   }
</script>
<script>
    var check_id=document.getElementById("check");
    var uid_id=document.getElementsByName("uid");
    function del(id) {
        if (confirm("是否确定删除")){
            location.href="${pageContext.request.contextPath}/delectOneServlet?id="+id;
        }
    }
    document.getElementById("remove").onclick=function () {
        var s="";
        //comfirm()方法用于提示,在没有点击确定前会对数据进行拦截
           if(confirm("是否确定删除他们")){
               for(var i=0;i<uid_id.length;i++){
                    if(uid_id[i].checked){
                        //获取选中的值,进行拼接
                       s+= "-"+uid_id[i].value;
                    }
               }
               location.href="${pageContext.request.contextPath}/deleteMoreServlet?id="+s;
           }
    }
    check_id.onclick=function(){
        if(check_id.checked) {
            for (var i = 0; i < uid_id.length; i++) {
                uid_id[i].checked=true;
            }
        }else {
            for (var i = 0; i < uid_id.length; i++) {
                uid_id[i].checked=false;
            }
        }
    }
</script>
</body>
</html>
