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

    <div class="formtitle"><span>书籍信息</span></div>

    <form action="/bookServlet" method="post" id="myForm">
        <ul class="forminfo">
            <c:if test="${empty book}">
                <input type="hidden" name="type" value="save">
            </c:if>
            <c:if test="${not empty book}">
                <input type="hidden" name="type" value="update">
                <input type="hidden" name="id" value="${book.id}">
            </c:if>

            <li>
                <label>书名</label>
                <input name="bookName"  type="text" class="dfinput" value="${requestScope.book.bookName}"/>
                <i id="userNameI">标题不能超过30个字符</i>
            </li>
            <li>
                <label>作者</label>
                <input name="author" type="text" class="dfinput"  value="${requestScope.book.author}"/>
                <i>多个关键字用,隔开</i>
            </li>
            <li>
                <label>出版社</label>
                <input name="publish" type="text" class="dfinput" value="${requestScope.book.publish}"/>
                <i>多个关键字用,隔开</i>
            </li>
            <li>
                <label>书籍编码</label>
                <input name="isbn" type="text" class="dfinput" value="${requestScope.book.isbn}" />
                <i>多个关键字用,隔开</i>
            </li>
            <li><label>书籍语言</label>
                <div class="vocation">
                    <select class="select1" name="language">
                        <option ${requestScope.book.language == '中文' ?'selected':''}>中文</option>
                        <option ${requestScope.book.language == '英语' ?'selected':''}>英语</option>
                        <option ${requestScope.book.language == '日文' ?'selected':''}>日文</option>
                        <option ${requestScope.book.language == '韩文' ?'selected':''}>韩文</option>
                    </select>
                </div>

            </li>
            <li>
                <label>书籍价格</label>
                <input name="price" type="text" class="dfinput" value="${requestScope.book.price}" />
                <i>多个关键字用,隔开</i>
            </li>
            <li>
                <label>发布日期</label>
                <input name="pubdate" type="date" class="dfinput"  value="${requestScope.book.pubdate}"/>
                <i>多个关键字用,隔开</i>
            </li>
            <li>
                <label>书架号</label>
                <input name="pressmark" type="text" class="dfinput" value="${requestScope.book.pressmark}" />
                <i>多个关键字用,隔开</i>
            </li>
            <li><label>书籍介绍</label>
                <textarea name="introduction" cols="" rows="" class="textinput">
                    ${requestScope.book.introduction}
                </textarea></li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="button" id="btn" class="btn" value="确认保存"/>
            </li>
        </ul>
    </form>

</div>

<script type="application/javascript">
    var flag = true;
    $(function(){
        $("#userName").blur(function(){


        });
        $("#btn").click(function(){
            $("#myForm").submit();
        })
    })
</script>


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
