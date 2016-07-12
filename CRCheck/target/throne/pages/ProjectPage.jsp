<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- this page's styles -->
    <link href="../css/projectpage.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <%--<link href="../css/bootstrap.css" rel="stylesheet">--%>

    <!-- Custom styles for login and register -->
    <link href="../css/log_reg.css" rel="stylesheet">

    <!-- checkbox styles -->
    <link href="../css/mycheckbox.css" rel="stylesheet">

    <!-- 无阻塞消息提示框 -->
    <link href="../css/toaster.css" rel="stylesheet">

    <!-- specific styles -->
    <link href="../css/component.css" rel="stylesheet">

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
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="HomePage.jsp"><img src="../image/logo.png" id="logo" title="返回首页"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="HomePage.jsp">首页</a></li>
                <li class="active"><a href="ProjectPage.jsp">项目</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a data-toggle="modal" href="#Login">登录</a></li>
                <li><a data-toggle="modal" href="#Register">注册</a></li>
            </ul>
            <button class="nav common-button navbar-right" style="margin-top:10px; margin-right:15px;">发起评审</button>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div id="introduce_parent" onclick="showIntroduce()"></div>
<div id="introduce_child">
    <h1>介绍部分</h1>
</div>

<div class="invite_div">

    <div class="left_div">
        <span class="text_style_left">邀请你的项目伙伴</span>
        <span class="text_style_left">定时高效</span>
    </div>

    <div class="circle_div">
        <hr class="add_style_1">
        <hr class="add_style_2">
    </div>

    <div class="right_div">
        <span class="text_style_right">从这里开始添加你的</span>
        <span class="text_style_right">评审项目</span>
    </div>

</div>


<%--登录--%>
<div id="Login" class="modal hide fade in" style="display: none;">

    <div class="log_reg_div">
        <span class="log_reg">登录CRC平台</span>
    </div>

    <div class="input_field_div">
        <input class="input_field" id="userId_log" type="text" placeholder="键入您的用户名">
        <input class="input_field" id="password_log" type="password" placeholder="键入您的密码">
    </div>

    <div class="switchbtn_div">
        <input class="mui-switch mui-switch-animbg" type="checkbox">
        <span class="logtip">记住密码</span>
    </div>

    <div class="switchtip_div">
        <a class="switchtip" data-toggle="modal" href="#Register"
           data-dismiss="modal">注册新帐号</a>
    </div>

    <div class="logbtn_div">
        <button class="logbtn" onclick="Login()">登录</button>
    </div>

</div>

<%--注册--%>
<div id="Register" class="modal hide fade in" style="display: none;">

    <div class="log_reg_div">
        <span class="log_reg">立即加入CRC评审</span>
    </div>

    <div class="input_field_div">
        <input class="input_field" id="userId_reg" type="text" placeholder="键入您的用户名">
        <input class="input_field" id="password_reg" type="password" placeholder="键入您的密码(不少于6位)">
    </div>

    <div class="switchbtn_div">
        <input class="mui-switch mui-switch-animbg" id="power_reg" type="checkbox">
        <span class="logtip">是否允许任何人邀请你</span>
    </div>

    <div class="switchtip_div">
        <a class="switchtip" data-toggle="modal" href="#Login"
           data-dismiss="modal">登录已有帐号</a>
    </div>

    <div class="logbtn_div">
        <button class="logbtn" onclick="Register()">注册</button>
    </div>

</div>

<%-- 无阻塞提示框 --%>
<div id="toaster_close">
    <div id="toaster">提示消息</div>
</div>

<a onclick="slidein()">滑入</a>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/projectpage.js"></script>
<script src="../js/Log_Reg.js"></script>
<script src="../js/toaster.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>
