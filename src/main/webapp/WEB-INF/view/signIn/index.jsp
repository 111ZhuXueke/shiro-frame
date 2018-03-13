<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/taglib.jsp" flush="true"/>
        <title>主页面</title>
    </head>
<body>
<h2>模块</h2>
    <c:forEach items="module">
        <li><a href="${basePath}">${module.name}</a></li>
    </c:forEach>
</body>
</html>
<script>

</script>