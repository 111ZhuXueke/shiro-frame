<%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 2018/1/12
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <script src="${basePath}static/jquery-2.1.4.js"></script>
    <style>
        .msg{
            display: none;
            font-size: 14px;
            color: red;
        }
    </style>
</head>
<body>
    <form>
        <input type="hidden" name="ssss"  value="sss"/>
        <input type="text" id="userName" name="userName"><br/>
        <input type="text" id="passwprd" name="password"><br/>
        <p class="msg"></p>
        <button type="button" id="sub">提交</button>
    </form>
<script>
    $(function(){
        $("#sub").click(function(){
            $.post("${basePath}signIn",{"userName":$("#userName").val(),"password":$("#passwprd").val()},function(result){
                var obj = eval('(' + result + ')');
                $(".msg").show();
                $(".msg").text(obj.message);
                if(obj.ok != null){
                    window.location.href="${basePath}index";

                }
            });
        });
    })


</script>
</body>
</html>
