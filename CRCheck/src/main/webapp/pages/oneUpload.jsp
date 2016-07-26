<%--
  Created by IntelliJ IDEA.
  User: mm
  Date: 2016/7/25
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>单文件上传</title>
</head>
<body>
<div style="margin: 0 auto;margin-top: 100px;">
    <form action="oneUpload.html" method="post" enctype="multipart/form-data">
        <p>
            <span>文件：</span>
            <input type="file" name="imageFile">
        </p>
        <p>
            <input type="submit">
        </p>
    </form>
</div>
</body>
</html>
