<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>权限管理列表</title>
</head>
<body>

    <form>
        <fieldset>
            <input type="text" id="name"/> 名称<br/>
            <input type="button" id="sub" value="搜索"/>
        </fieldset>
        <table border="1">
            <thead>
                <th>编号</th>
                <th>名称</th>
                <th>类型</th>
                <th>路径</th>
                <th>权限字符串</th>
                <th>所属模块</th>
                <th>更新时间</th>
            </thead>
            <tbody class="tbody">
            </tbody>
        </table>
    </form>

    <script src="${basePath}/static/jquery-2.1.4.js"></script>
    <script>
        /* 初始化加载数据 */
        $(function(){
            getResult(null);
            $("#sub").click(function(){
                if($("#name").val() != null && $("#name").val() != ''){
                    $(".tbody").html("");
                    var str = {
                        name:$("#name").val()
                    };
                    getResult(str);
                }
            });
        });
        /* ajax 获取数据 */
        function getResult(parms){
            $.post("${basePath}permission/index?" + parms,{},function (data){
                $(".tbody").html("");
                var obj = eval(data);
                var listText = $(".tbody").html();
                for(var i = 0; i < obj.length; i++){
                    listText = listText + "<tr>";
                    listText = listText + "<td>"+obj[i].id+"</td>";
                    listText = listText + "<td>"+obj[i].name+"</td>";
                    listText = listText + "<td>"+obj[i].type+"</td>";
                    listText = listText + "<td>"+obj[i].url+"</td>";
                    listText = listText + "<td>"+obj[i].permission+"</td>";
                    listText = listText + "<td>"+obj[i].moduleId+"</td>";
                    listText = listText + "<td>"+obj[i].updateTime+"</td>";
                    listText = listText + "</tr>";
                }
                $(".tbody").html(listText);
            })
        }
    </script>
</body>
</html>
