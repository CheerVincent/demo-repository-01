<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2022/11/11
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>

<table border="1" align="center">
    <tr>
        <th>用户 ID</th>
        <th>用户姓名</th>
        <th>用户年龄</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.userid}</td>
            <td>${user.username}</td>
            <td>${user.userage}</td>
            <td><a href="">更新</a> <a href="/user/deleteById?userid=${user.userid}">删除</a></td>
        </tr>

    </c:forEach>

</table>
</body>
</html>
