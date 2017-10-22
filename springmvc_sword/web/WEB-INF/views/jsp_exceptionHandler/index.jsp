<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/21
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Index</title>
</head>
<body>

    <a href="${pageContext.request.contextPath }/exceptionResolver/testExceptionHandlerExceptionResolver?i=10" id="test">Test ExceptionHandlerExceptionResolver</a>
    <br><br>

    <a href="${pageContext.request.contextPath }/exceptionResolver/testResponseStatusExceptionResolver?i=10" id="test">Test ResponseStatusExceptionResolver</a>
    <br><br>

    <a href="${pageContext.request.contextPath }/exceptionResolver/testDefaultHandlerExceptionResolver?i=10" id="test">Test DefaultHandlerExceptionResolver</a>
    <br><br>

    <a href="${pageContext.request.contextPath }/exceptionResolver/testSimpleMappingExceptionResolver?i=10" id="test">Test DefaultHandlerExceptionResolver</a>
    <br><br>
</body>
</html>
