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
    <script type="text/javascript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $(".click").click(function(){
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function(){
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function(){
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function(){
                $(".tip").fadeOut(100);
            });

        });
        function deleteBook(bookId){
            if(window.confirm("确定要删除这条记录吗?"+bookId)){
                location.href = "/bookServlet?type=delete&id="+bookId;
            }
        }
    </script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">数据表</a></li>
        <li><a href="#">基本内容</a></li>
    </ul>
</div>
<div class="rightinfo">
    <div class="tools">

        <ul class="toolbar">
            <li >
                <a href="/book/bookUpdate.jsp">
                    <span><img src="images/t01.png" />
                    </span>
                    添加
                </a>
            </li>


        </ul>
        <form action="/bookServlet" method="post">
            <input type="hidden" name="type" value="search">
            <input type="text" name="keyword" value="${keyword}" style="height: 33px;border:solid 1px #d3dbde;border-radius: 3px;padding-right:10px; margin-right:5px;" placeholder="输入查询关键字...">
            <input type="submit" value="  查询" style="background:url(/images/toolbg.gif) repeat-x; line-height:33px; height:33px; border:solid 1px #d3dbde;  padding-right:10px; margin-right:5px;border-radius: 3px;">
        </form>


        <ul class="toolbar1">
            <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>

    </div>
    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
            <th>书籍名称</th>
            <th>作者</th>
            <th>出版社</th>
            <th>价格</th>
            <th>状态</th>
            <th>介绍</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="book">
            <tr>
                <td><input name="" type="checkbox" value="" /></td>
                <td>${book.id}</td>
                <td>${book.bookName}</td>
                <td>${book.author}</td>
                <td>${book.publish}</td>
                <td>${book.price}</td>
                <td>
                     <c:if test="${book.state == 0}">
                         空闲
                     </c:if>
                    <c:if test="${book.state == 1}">
                        借阅
                    </c:if>
                    <c:if test="${book.state == 2}">
                        下架
                    </c:if>
                    <c:if test="${book.state == 3}">
                        其他
                    </c:if>
                </td>
                <td>${book.introduction}</td>
                <td>
                    <a href="/bookServlet?type=queryById&id=${book.id}" class="tablelink">修改</a>
                    <a href="JavaScript:deleteBook(${book.id})"  class="tablelink"> 删除</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="javascript:;">1</a></li>
            <li class="paginItem current"><a href="javascript:;">2</a></li>
            <li class="paginItem"><a href="javascript:;">3</a></li>
            <li class="paginItem"><a href="javascript:;">4</a></li>
            <li class="paginItem"><a href="javascript:;">5</a></li>
            <li class="paginItem more"><a href="javascript:;">...</a></li>
            <li class="paginItem"><a href="javascript:;">10</a></li>
            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>


    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>

        <div class="tipinfo">
            <span><img src="images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button"  class="sure" value="确定" />&nbsp;
            <input name="" type="button"  class="cancel" value="取消" />
        </div>

    </div>




</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
