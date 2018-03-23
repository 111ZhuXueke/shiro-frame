<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>新增权限</title>
    <link href="${basePath}/static/public.css" rel="stylesheet" type="text/css">
    <style>

    </style>
</head>
<body>


        <p>权限名称：<input type="text" name="name" id="name" /></p>
        <p>状态：可用<input type="radio" name="available" id="available" value="0" checked="checked">不可用<input type="radio" name="available" value="1"><p>
        <p>资源路径：<input type="text" name="url" id="url"/><p>
        <p>权限字符串：<input type="text" name="permission" id="permission"/><p>
        <p>模块ID：<select name="moduleId" id="moduleId">
                    <c:forEach items="${rj_default:modules()}" var="m">
                        <option value="${m.id}">${m.name}</option>
                    </c:forEach>
                </select><p>
        <p>当前权限类型：<select name="type" id="type">
                            <option value="0">请选择权限类型</option>
                            <c:forEach items="${rj_default:enums()}" var="type">
                                <option name="${type}">${type}</option>
                            </c:forEach>
                        </select><p>
        <p>父资源权限类型：<select name="parentId" id="parentId"></select><p>
        <input type="button" id="sub" value="提交" />

    <button class="btn close">关闭</button>
    <script src="${basePath}/static/jquery-2.1.4.js"></script>
    <script>
        $(function(){
            $("#sub").click(function(){
                $.post("${basePath}permission/add",{"name":$("#name").val(),"available":$("#available").val(),"url":$("#url").val(),"permission":$("#permission").val()
                ,"moduleId":$("#moduleId").val(),"type":$("#type").val(),"parentId":$("#parentId").val()},function(data){
                    var obj = eval(data);
                    alert(obj.message);
                    if(obj.ok!=null){
                        $(".close").click();
                    }
                },'json');
            });
            $("#type").change(function(){
                if($(this).val() != 'click'){
                    $.post("${basePath}permission/getParentType",{"lable":$(this).val()},function(data){
                        $("#parentId").html("");
                        var obj = eval(data);
                        var optionLists = $("#parentId").html();
                        for(var i = 0; i < obj.length; i++){
                            optionLists = optionLists + '<option value="'+obj[i].id+'">'+obj[i].name+'('+obj[i].url+')'+'</option>';
                        }
                        $("#parentId").html(optionLists);
                    },"json")
                }else {
                    $("#url").html("#");
                    $("#parentId").html("无父资源！");
                }
            });
            $(".close").click(function(){
                parent.$(".childWin").hide();
            });

        })
    </script>
</body>
</html>
