<%--
  weChat:boge_java
  QQ:279583842
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <script src="/js/jquery.js" ></script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>院系管理</span></div>

    <form action="/departmentServlet" method="post" id="myForm">
        <ul class="forminfo">
            <c:if test="${empty dept}">
                <input type="hidden" name="type" value="save">
            </c:if>
            <c:if test="${not empty dept}">
                <input type="hidden" name="type" value="update">
                <input type="hidden"   name="id" value="${dept.id}">
            </c:if>
            <li>
                <label>院系名称</label>
                <input name="department"  type="text" class="dfinput" value="${dept.department}"/>
                <i id="userNameI">标题不能超过30个字符</i>
            </li>

            <li>
                <label>院系介绍</label>
                <input name="deptDesc" type="text" class="dfinput" value="${dept.deptDesc}" />
                <i>多个关键字用,隔开</i>
            </li>

            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" id="btn" class="btn" value="确认保存"/>
            </li>
        </ul>
    </form>

</div>

<script type="application/javascript">

</script>


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
