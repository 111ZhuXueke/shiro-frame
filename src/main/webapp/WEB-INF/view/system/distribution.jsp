<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>权限分配</title>
    <link href="${basePath}/static/public.css" rel="stylesheet" type="text/css">
    <style>
        body{
            position: relative;
            z-index: 1;
        }
        .toolBar{
            margin-top: 20px;
        }
        tr,td,th{
            border: 0px solid black;
            text-align: left;
        }
        td{
            width: 200px;
        }
        .childWin{
            display: none;
            position: absolute;
            left: 20%;
            top: 20%;
            width: 500px;
            height: 300px;
            z-index: 2;
            background: #ffffff;
            border: 1px solid #d8d62b;
        }
        .childWin iframe{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>

    <form>
        <fieldset>
            <input type="text" id="name"/> 用户名称<br/>
            <input type="button" id="sub" class="" value="搜索"/>
            <div class="toolBar"></div>
        </fieldset>
        <table>
            <thead>
                <th>编号</th>
                <th>管理员姓名</th>
                <th>用户名</th>
                <th>更新时间</th>
            </thead>
            <tbody class="tbody">
            </tbody>
        </table>
    </form>
    <div class="childWin">
        <iframe src="" frameborder="0"></iframe>
    </div>
    <script src="${basePath}/static/jquery-2.1.4.js"></script>
    <script>
        var permaryKey;
        /* 初始化加载数据 */
        $(function(){
            getResult("");
            setToolBar();
            $("#sub").click(function(){
                $(".tbody").html("");
                getResult($("#name").val());
            });
        });
        /* ajax 获取数据 */
        function getResult(parms){
            $.post("${basePath}permission/distribution?name=" + parms,{},function (data){
                $(".tbody").html("");
                var obj = eval(data);
                var listText = $(".tbody").html();
                for(var i = 0; i < obj.length; i++){
                    listText = listText + "<tr class='premarykey'>";
                    listText = listText + "<td><input type='button' onclick='setPermaryKey(this)' ></td>";
                    listText = listText + "<td>"+obj[i].id+"</td>";
                    listText = listText + "<td>"+obj[i].realName+"</td>";
                    listText = listText + "<td>"+obj[i].userName+"</td>";
                    listText = listText + "<td>"+obj[i].updateTime+"</td>";
                    listText = listText + "</tr>";
                }
                $(".tbody").html(listText);
            })
        }

        /* 设置功能按钮 */
        function setToolBar(){
            var toolText = $(".toolBar").html();
            toolText = toolText + '<shiro:hasPermission name="system:permissions:disPermission"><input type="button" onclick="distribution()" class="btn" value="分配权限"/></shiro:hasPermission>';
            $(".toolBar").html(toolText);
        }

        /* 分配权限 */
        function distribution(){
            if(permaryKey != '' || permaryKey != null){
                $(".childWin").show();
                $(".childWin iframe").attr("src","${basePath}permission/disPermission?userId="+permaryKey);
                $(".childWin iframe").load();
            }else {
                alert("请选择编号!");
            }

        }

        function setPermaryKey(thi){
            permaryKey = $(thi).parent().next().html();
        }
    </script>
</body>
</html>
