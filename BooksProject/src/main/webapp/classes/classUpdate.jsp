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
    <link href="/css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="/js/select-ui.min.js"></script>
    <script type="text/javascript" src="/editor/kindeditor.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });
        });
    </script>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>班级管理</span></div>

    <form action="/classServlet" method="post" id="myForm">
        <ul class="forminfo">
            <c:if test="${empty cls}">
                <input type="hidden" name="type" value="save">
            </c:if>
            <c:if test="${not empty cls}">
                <input type="hidden" name="type" value="update">
                <input type="hidden"   name="id" value="${cls.id}">
            </c:if>
            <li>
                <label>班级名称</label>
                <input name="className"  type="text" class="dfinput" value="${cls.className}"/>
                <i id="userNameI">标题不能超过30个字符</i>
            </li>

            <li>
                <label>班级描述</label>
                <input name="classDesc" type="text" class="dfinput" value="${cls.classDesc}" />
                <i>多个关键字用,隔开</i>
            </li>
            <!-- 院系的分配 -->
            <li><label>所属院系</label>
                <div class="vocation">
                    <select class="select1" name="deptId">
                        <c:forEach items="${depts}" var="dept" >
                            <option value="${dept.id}" ${dept.id == cls.deptId?'selected':''}>${dept.department}</option>
                        </c:forEach>
                    </select>
                </div>

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
