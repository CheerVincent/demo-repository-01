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

    <div class="formtitle"><span>基本信息</span></div>

    <form action="/userServlet" method="post" id="myForm">
        <ul class="forminfo">
            <input type="hidden" name="type" value="save">
            <c:if test="${not empty user}">
                <input type="hidden" id="userId"  name="id" value="${user.id}">
            </c:if>
            <li>
                <label>账号</label>
                <input name="userName" id="userName" type="text" class="dfinput" value="${user.userName}"/>
                <i id="userNameI">标题不能超过30个字符</i>
            </li>
            <li>
                <label>密码</label>
                <input name="password" type="text" class="dfinput" value="${user.password}" />
                <i>多个关键字用,隔开</i>
            </li>
            <li>
                <label>手机</label>
                <input name="phoneNum" type="text" class="dfinput" value="${user.phoneNum}" />
                <i>多个关键字用,隔开</i>
            </li>
            <li>
                <label>邮箱</label>
                <input name="email" type="text" class="dfinput" value="${user.email}" />
                <i>多个关键字用,隔开</i>
            </li>
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
            var userId = $("#userId").val()
            if(userId != null){
                // 说明是更新
                flag = true
            }else{
                // 把录入的账号通过Ajax的方式提交到后端，校验该账号是否存在
                var userName = $(this).val();
                $.get("/userServlet?type=check&userName="+userName,function(data){
                    //alert(data);
                    if(data.trim() === 'success'){
                        flag = true
                        // 表示账号可用
                        $("#userNameI").html("<span style='color:green'>账号不存在，可用使用</span>")
                    }else{
                        flag = false;
                        // 表示账号不可用
                        $("#userNameI").html("<span style='color:red'>账号已存在，请更换</span>")
                    }
                })
            }

        });
        $("#btn").click(function(){
            if(flag == false){
                alert("账号存在不能提交，请核对信息!!!");
            }else{
                $("#myForm").submit();
            }

        })
    })
</script>


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
