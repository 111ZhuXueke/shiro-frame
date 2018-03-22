<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>主页面</title>
    <style type="text/css">
        #iframe{
            margin-top: 50px;
            margin-left: 100px;
            border: 1px solid black;
            width: 80%;
            height:600px;
        }
        #iframe iframe{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <h2>模块</h2>
    欢迎<shiro:principal/>。
    <p><a onclick="logout()">退出</a></p>
    <c:forEach items="${rj_default:modules()}" var="m">
        <li>
            <a onclick="getClicks('${basePath }module/click?moduleId=${m.id }')" data-toggle="sidenav" data-id-key="targetid">${m.name }</a>
        </li>
    </c:forEach>
    <ul class="modules"></ul>
    <div id="iframe">
        <iframe src="" frameborder="0"></iframe>
    </div>
    <script src="${basePath}/static/jquery-2.1.4.js"></script>
    <script src="${basePath}/static/ajaxStatus.js"></script>
    <script>
        /* 获取所有一级菜单 */
        function getClicks(url){
            $(".modules").html("");
            $.post(url,{},function(data){
                var lists = eval(data);
                var liText = $(".modules").html();
                for(var i = 0; i < lists.length; i++){
                    liText = liText + '<li><a onclick="getMenus(\'${basePath }module/menu?parentId='+lists[i].id+'\',this)">'+lists[i].name+'</a><ul></ul></li>';
                }
                $(".modules").html(liText);
            });
        }

        /* 获取所有二级菜单 */
        function getMenus(url,thi){
            $.post(url,{},function(data){

                $(thi).next("ul").html("");
                var lists = eval(data);
                var liText = $(thi).next("ul").html();
                for(var i = 0; i < lists.length; i++){
                    liText = liText + '<li><a data-url="'+lists[i].url+'" onclick="setFrame(this)">'+lists[i].name+'</a></li>';
                }
                $(thi).next("ul").html(liText);
            },'json');
        }

        /* 设置 iframe 的加载窗口路径 */
        function setFrame(thi) {
            var url = $(thi).attr("data-url");
            var path = "${basePath }".substr(0,"${basePath }".length - 1) + url;
            $("#iframe iframe").attr("src","${baesPath}"+url);
            $("#iframe iframe").load();
        }

        /* 退出 */
        function logout(){
            $.get("${basePath}tuichu",null,function(data){
                var obj = eval(data);
                if(obj.ok != null){
                    window.location.href = "${basePath}logout";
                }
            },'json');
        }
    </script>
</body>
</html>