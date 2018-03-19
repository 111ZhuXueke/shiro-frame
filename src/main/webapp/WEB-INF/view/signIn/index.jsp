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
            width: 600px;
            height:500px;
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
    <p></p>
    <c:forEach items="${rj_default:modules()}" var="m">
        <li>
            <a onclick="getClicks('${basePath }module/click?moduleId=${m.id }')" data-toggle="sidenav" data-id-key="targetid">${m.name }</a>
        </li>
    </c:forEach>
    <ul class="modules"></ul>
    <div id="iframe">
        <iframe src=""></iframe>
    </div>
    <script src="${basePath}static/jquery-2.1.4.js"></script>
    <script src="${basePath}static/String.js"></script>
    <script>
        /* 获取所有一级菜单 */
        function getClicks(url){
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
                var lists = eval(data);
                var liText = $(thi).next("ul").html();
                for(var i = 0; i < lists.length; i++){
                    var urlss = lists[i].url;
//                    liText = liText + '<li><a onclick="setFrame("'+urlss+'")">'+lists[i].name+'</a></li>';
                    liText = liText + '<li><a data-url="'+urlss+'" onclick="setFrame(this)">'+lists[i].name+'</a></li>';
                }
                $(thi).next("ul").html(liText);
            });
        }

        /* 设置 iframe 的加载窗口路径 */
        function setFrame(thi) {
            var url = $(thi).attr("data-url");
            var path = "${basePath }".substr(0,"${basePath }".length - 1) + url;
            console.log(path)
            $("#iframe iframe").attr("src","${baesPath}"+url);
            $("#iframe iframe").load();
        }
    </script>
</body>
</html>
