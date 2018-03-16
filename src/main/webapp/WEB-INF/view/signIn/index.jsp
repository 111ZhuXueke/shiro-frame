<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="rj_default" uri="http://rj.com/tags/default-functions" %>
<html>
<body>
<head>
    <meta charset="utf-8"/>
    <title>主页面</title>
</head>

    <h2>模块</h2>
    欢迎<shiro:principal/>。
    <c:forEach items="${rj_default:modules()}" var="m">
        <li>
            <a href="${basePath }/menu?module=${m.lable }" data-toggle="sidenav" data-id-key="targetid">${m.name }</a>
        </li>
    </c:forEach>

</body>
</html>
<script>

</script>