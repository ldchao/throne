<%--
  Created by IntelliJ IDEA.
  User: lvdechao
  Date: 2016/7/27
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/text.css" rel="stylesheet">
</head>
<body>

<center>
    <form action="../headPortraitsUpload.action"method="post" name="form1" enctype="multipart/form-data"><table><tr>
        <img id="previewImg"src=""width="80"height="80">
        <td> 请选择头像：</td>
        <td >
            <input type="file" name="imageFile" id="file" accept="image/*" onchange="change('previewImg','file')">
            <input type="submit"value="提交"/>

        </td>
    </table>
    </form>
</center>

<div>
    <img class="text" src="../HeadPortraits/chao.jpg">
</div>
   <script src="../js/personalpage.js"></script>
</body>
</html>
