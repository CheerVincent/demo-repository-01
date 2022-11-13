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

    <div class="formtitle"><span>借书卡管理</span></div>

    <form action="/readerCardServlet" method="post" id="myForm">
        <ul class="forminfo">
                <input type="hidden" name="type" value="save">
            <!-- 院系的分配 -->
            <li><label>分配学生</label>
                <div class="vocation">
                    <select class="select1" name="stuId">
                        <c:forEach items="${stus}" var="stu" >
                            <option value="${stu.id}" >${stu.stuName}</option>
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
