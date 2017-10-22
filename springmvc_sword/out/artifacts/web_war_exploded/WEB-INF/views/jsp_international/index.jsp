<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/22
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <!--
        关于国际化：
            1.在页面上能够根据浏览器语言设置的情况对文本（不是内容），时间，数值进行本地化处理。
            2.可以在bean种获取国际化资源文件Locale对应的消息
            3.可以通过超链接切换Locale，而不再以来与浏览器的语言设置情况

        解决：
            1.使用JSTL的fmt标签
            2.在bean中注入ResourceBundleMessageSource的示例，使用其对应的 getMessageFormat方法
            3.配置LocalResolver 和 LocaleChangeIntercepter
    -->
    <<br><br>

    <a href="i18n">I18N PAGE</a>
</body>
</html>
