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
    <script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#testJson").click(function () {
                var url = this.href;
                var args = {};
                $.post(url,args,function(data){
                    for(var i = 0;i < data.length;i++){
                        var id = data[i].id;
                        var lastname = data[i].lastName;
                        alert(id + ": " + lastname);
                    }
                });
                return false;
            })
        })

    </script>
</head>
<body>

    <a href="${pageContext.request.contextPath }/json/testJson" id="testJson">List All employee</a>
    <br><br>

    <!-- 文件上传，测试 @RequestBody 与 @ResponseBody -->
    <form action="${pageContext.request.contextPath }/json/testHttpMessageConverter" method="POST" enctype="multipart/form-data">
        File: <input type="file" name="file" />
        Desc: <input type="text" name="desc" />
        <input type="submit" value="submit" />
    </form>
</body>
</html>
