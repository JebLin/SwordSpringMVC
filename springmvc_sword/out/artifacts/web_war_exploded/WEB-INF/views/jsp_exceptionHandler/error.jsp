<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/22
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    Error jsp
    <br><br>

    <%--
        异常配置在 MyControllerAdvice，
        或者在其他当前handler的 @ExceptionHandler({ArithmeticException.class}) 的方法里面
    --%>
    exceptionDetail：  ${exceptionDetail}
    <br>

    exception : ${exception}


</body>
</html>
