<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>分配权限</title>
    <link href="${basePath}/static/public.css" rel="stylesheet" type="text/css">
    <style>
        li{
            list-style-type: none;
        }
    </style>
</head>
<body>
    <input type="hidden" name="userId" id="userId" value="${userId}">
    <ul>
        <c:forEach items="${rj_default:modules()}" var="module">
            <li>${module.name}
                <ul>
                    <c:forEach items="${click}" var="click">
                        <c:if test="${click.moduleId == module.id}">
                            <li><input type="checkbox" name="perid" <c:if test="${fn:contains(userPermissions,click.permission) }">checked="checked"</c:if> value="${click.id}">${click.name}
                                <ul>
                                    <c:forEach items="${menu}" var="menu">
                                        <c:if test="${menu.parentId == click.id}">
                                            <li><input type="checkbox" name="perid" <c:if test="${fn:contains(userPermissions,menu.permission) }">checked="checked"</c:if> value="${menu.id}">${menu.name}
                                                <ul>
                                                    <c:forEach items="${button}" var="btn">
                                                        <c:if test="${btn.parentId == menu.id}">
                                                            <input type="checkbox" name="perid" <c:if test="${fn:contains(userPermissions,btn.permission) }">checked="checked"</c:if> value="${btn.id}">${btn.name}
                                                        </c:if>
                                                    </c:forEach>
                                                </ul>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>


    <button class="btn" id="sub">保存</button>
    <button class="btn close">关闭</button>
    <script src="${basePath}/static/jquery-2.1.4.js"></script>
    <script>
        $(function(){
            $("#sub").click(function(){
                var allCheck = $("input[name='perid']");
                var arrays = new Array(allCheck.length);
                for(var i = 0; i < allCheck.length; i++){
                    arrays[i] = $(allCheck[i]).val();
                }
                $.post("${basePath}permission/disPermission",{"permissionIds":arrays.toString(),"userId":$("#userId").val()},function(data){
                    var obj = eval(data);
                    alert(obj.message);
                    $(".close").click();
                },'json');
            });

            $(".close").click(function(){
                parent.$(".childWin").hide();
            });

        })
    </script>
</body>
</html>
