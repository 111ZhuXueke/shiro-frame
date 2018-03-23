<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>修改权限</title>
    <link href="${basePath}/static/public.css" rel="stylesheet" type="text/css">
    <style>

    </style>
</head>
<body>

    <form action="${basePath}permission/update" method="post">
        <input type="hidden" name="id" value="${domain.id}">
        <p>权限名称：<input type="text" name="name" value="${domain.name}"/></p>
        <p>状态：可用<input type="radio" name="available" value="0" <c:if test="${domain.available == 0}">checked="checked"</c:if> >不可用<input type="radio" name="available" value="1" <c:if test="${domain.available == 1}">checked="checked"</c:if>><p>
        <p>资源路径：<input type="text" name="url" id="url" value="${domain.url}"/><p>
        <p>权限字符串：<input type="text" name="permission"  value="${domain.permission}"/><p>
        <p>模块ID：<select name="moduleId">
                    <option name="-1">请选择模块id</option>
                    <c:forEach items="${rj_default:modules()}" var="m">
                        <option value="${m.id}">${m.name}</option>
                    </c:forEach>
                </select><p>
        <p>当前权限类型：<select name="type" id="type">
                            <option value="-1">请选择权限类型</option>
                            <c:forEach items="${rj_default:enums()}" var="type">
                                <option value="${type}">${type}</option>
                            </c:forEach>
                        </select><p>
        <p>父资源权限类型：<select name="parentId" id="parentId"></select><p>
        <input type="submit" value="提交" />
    </form>
    <button class="btn close">关闭</button>
    <script src="${basePath}/static/jquery-2.1.4.js"></script>
    <script>
        $(function(){
            $("#type").change(function(){
                if($(this).val() != 'click'){
                    $.post("${basePath}permission/getParentType",{"lable":$(this).val()},function(data){
                        $("#parentId").html("");
                        var obj = eval(data);
                        var optionLists = $("#parentId").html();
                        optionLists = optionLists + '请选择权限类型';
                        for(var i = 0; i < obj.length; i++){
                            optionLists = optionLists + '<option value="'+obj[i].id+'">'+obj[i].name+'('+obj[i].url+')'+'</option>';
                        }
                        $("#parentId").html(optionLists);
                    },"json")
                }else {
                    $("#url").html("#");
                }
            });
            $(".close").click(function(){
                parent.$(".childWin").hide();
            });
        })
    </script>
</body>
</html>
