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
    <!-- 文件上传 -->
    <form action="${pageContext.request.contextPath }/fileupload/testfileupload" method="POST" enctype="multipart/form-data">
        File: <input type="file" name="file" />
        Desc: <input type="text" name="desc" />
        <input type="submit" value="submit" />
    </form>
</body>
</html>
