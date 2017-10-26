<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/26
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ServletContextAttributeListener监听器测试</title>
</head>

<body>
<%
    //往application域对象中添加属性
    application.setAttribute("name", "孤独的一匹狼");
    //替换application域对象中name属性的值
    application.setAttribute("name", "ljb");
    //移除application域对象中name属性
    application.removeAttribute("name");
%>
</body>
</html>
