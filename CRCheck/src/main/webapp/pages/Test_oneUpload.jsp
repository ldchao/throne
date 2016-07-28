<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>单文件 上传 </title>


    <!-- 无阻塞消息提示框 -->
    <link href="../css/toaster.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="../js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<%--<div style="margin: 0 auto;margin-top: 100px; ">--%>
<%----%>
<%--<form action="../oneUpload.action" method="post" enctype="multipart/form-data">--%>
<%--<p>--%>
<%--<span>文件：</span>--%>
<%--<input type="file" name="oneFile">--%>
<%--</p>--%>
<%--<p>--%>
<%--<input type="submit">--%>
<%--</p>--%>
<%--</form>--%>
<%----%>
<%--</div>--%>

<form id="form_file" method="post" action="../oneUpload.action" enctype="multipart/form-data">
    <span id="upload_File">上传文件</span>

    <input type="file" name="oneFile" id="file_input" onchange="uploadFile()">
</form>

<%--<form action="../headPortraitsUpload.action" method="post" name="form1"--%>
      <%--enctype="multipart/form-data" style="margin-top:200px; display: block">--%>
    <%--&lt;%&ndash;<img id="previewImg" width="68" height="68" src=<%=user.getHeadPortrait()%>>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<div style="height: 68px; width: 68px;" id="previewImg"><a href="#"></a></div>&ndash;%&gt;--%>
    <%--<input type="file" name="oneFile" id="file" accept="image/*"--%>
           <%--onchange="uploadFile()" onmouseover="imageEdit(1)"--%>
           <%--onmouseout="imageEdit(0)">--%>
    <%--<div id="portrait-filter"><i class="fa fa-pencil"></i></div>--%>
    <%--&lt;%&ndash;<input type="submit" value="提交"/>&ndash;%&gt;--%>
<%--</form>--%>


<%-- 无阻塞提示框 --%>
<div id="toaster_close">
    <div id="toaster">
        <div id="pic_div" class="green_pic"></div>
        <div id="remind" class="green_word">提示消息</div>
    </div>
</div>

<script>

//    function uploadFile() {
//
//        var imageFile = $('input[name=name_input]').val();
//        $('form').ajaxSubmit({
//            type: 'post', // 提交方式 get/post
//            url: '/oneUpload', // 需要提交的 url
//            data: {
//                "oneFile": imageFile,
//                 "projectId": 30
//            },
//            success: function (result) { // data 保存提交后返回的数据，一般为 json 数据
//                if (result == "SUCCESS") {
//                    slidein(0, "文件上传成功");
//                } else {
//                    slidein(1, "文件上传失败");
//                }
//            },
//            error: function () {
//                slidein(1, "获取数据失败");
//            }
//        });

    }


</script>
<script src="../js/jquery.js"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/common.js"></script>
<script src="../js/toaster.js"></script>
<script src="../js/projectpage.js"></script>
<script src="../js/personalpage.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
<script src="../js/datetimepicker.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>