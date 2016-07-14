<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <%--<link href="../css/bootstrap.css" rel="stylesheet">--%>

    <!-- Custom styles for login and register -->
    <link href="../css/log_reg.css" rel="stylesheet">

    <!-- 无阻塞消息提示框 -->
    <link href="../css/toaster.css" rel="stylesheet">

    <!-- checkbox styles -->
    <link href="../css/mycheckbox.css" rel="stylesheet">

    <!-- specific styles -->
    <link href="../css/component.css" rel="stylesheet">
    <link href="../css/projectDetailPage.css" rel="stylesheet">

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
                <li class="active"><a href="HomePage.jsp">首页</a></li>
                <li><a href="ProjectPage.jsp">项目</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a data-toggle="modal" href="#Login">登录</a></li>
                <li><a data-toggle="modal" href="#Register">注册</a></li>
            </ul>
            <button class="nav common-button navbar-right" style="margin-top:10px; margin-right:15px;">发起评审</button>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->


<div class="container text-center" style="width:65%;margin:100px auto;">
    <div class="row">
        <div class="project-title">CRC评审项目</div>
    </div>
    <div class="project-char text-center">
        <div class="project-owner">
            <div class="image-small"
                 style="margin-right:12px;">
            </div>
            <div class="sub-title">marioquer</div>
        </div>
        <div class="project-type" style="display: inline-block;">
            <div class="type-image" style=""></div>
            <div class="sub-title">Java</div>
        </div>
    </div>
    <div class="project-time time-text">2016.7.7-2016.8.1</div>
    <p class="project-detail main-text text-left">
        这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目这是个很帅很帅的项目
    </p>
    <div class="member">
        <div class="middle-title">参与者</div>
        <div class="member-list">
            <div class="image-middle" style="margin-right: 15px;"></div>
            <div class="image-middle" style="margin-right: 15px;"></div>
            <div class="image-middle" style=""></div>
        </div>
    </div>
    <div class="left-time main-text">XX天后结束</div>

    <div id="begin" class="submit-button">立即开始评审</div>

    <div id="init-form">
        <div class="form-empty">
            <div class="form-line row" style="margin-left: 20px;margin-right: 20px;">
                <div class="col-sm-7"
                     style="padding-left: 5px;padding-right: 5px;margin-top:10px;">
                    <input type="text" placeholder="缺陷代码目录, 用‘/’分开" class="textfield"
                           style="height: 35px; width: 100%;">
                </div>
                <div class="col-sm-2"
                     style="padding-left: 5px;padding-right: 5px;margin-top:10px;">
                    <input type="text" placeholder="开始行数" class="textfield" style="height: 35px; width: 100%;">
                </div>
                <div class="col-sm-3"
                     style="padding-left: 5px;padding-right: 5px;margin-top:10px;">
                    <input type="text" placeholder="选取缺陷类型" class="textfield"
                           style="height: 35px; width: 100%;">
                </div>
            </div>
            <div class="form-line row" style="margin-top: 10px;height: 69px;">
            <textarea class="textfield" placeholder="缺陷详细描述"
                      style="height: 69px;display: block;width: 100%;"></textarea>
            </div>
        </div>
    </div>

    <div class="addItem-button" onclick="addForm()">添加新的缺陷</div>
</div>


<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>

<footer> © CRCheck 2016</footer>

<%--登录--%>
<div id="Login" class="modal hide fade in" style="display: none;">

    <div class="log_reg_div">
        <span class="log_reg">登录CRC平台</span>
    </div>

    <button class="close close_div" data-toggle="modal" data-dismiss="modal">x</button>

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

    <button class="close close_div" data-toggle="modal" data-dismiss="modal">x</button>

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
    <div id="toaster">
        <div id="pic_div" class="green_pic"></div>
        <div id="remind" class="green_word">提示消息</div>
    </div>
</div>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="../js/main.js"></script>--%>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/common.js"></script>
<script src="../js/toaster.js"></script>
<script src="../js/ProjectDetailPage.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
