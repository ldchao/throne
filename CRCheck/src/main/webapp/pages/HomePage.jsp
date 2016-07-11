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

    <!-- checkbox styles -->
    <link href="../css/mycheckbox.css" rel="stylesheet">

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

<div class="jumbotron" style="height: 720px;background-color: #8a6d3b;">
    <div class="container" style="margin-top: 0">
        <div class="row">
            <div class="col-sm-6" style="background-color: #7595e0; height: 672px;">

            </div>
            <div class="col-sm-6" style="background-color: #5e5e5e; height: 672px;">

            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-6" style="background-color: #5e5e5e; height: 300px;">

        </div>
        <div class="col-sm-6 hidden-sm" style="background-color: #7595e0; height:300px;">

        </div>
    </div>

    <div class="row">
        <div class="col-sm-6 hidden-sm" style="background-color: #5e5e5e; height: 300px;">

        </div>
        <div class="col-sm-6" style="background-color: #7595e0; height:300px;">

        </div>
    </div>

    <div class="row">
        <div class="col-sm-6" style="background-color: #5e5e5e; height: 300px;">

        </div>
        <div class="col-sm-6 hidden-sm" style="background-color: #7595e0; height:300px;">

        </div>
    </div>

    <div class="row">
        <div class="col-sm-6 hidden-sm" style="background-color: #5e5e5e; height: 300px;">

        </div>
        <div class="col-sm-6" style="background-color: #7595e0; height:300px;">

        </div>
    </div>
</div>


<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>


<footer> © CRCheck 2016</footer>

<%--登录--%>
<div id="Login" class="modal hide fade in" style="display: none;">

    <div class="log_reg_div">
        <span class="log_reg">登录CRC平台</span>
    </div>

    <div class="input_field_div">
        <input class="input_field" type="text" placeholder="键入您的用户名">
        <input class="input_field" type="password" placeholder="键入您的密码">
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
        <button class="logbtn">登录</button>
    </div>

</div>

<%--注册--%>
<div id="Register" class="modal hide fade in" style="display: none;">

    <div class="log_reg_div">
        <span class="log_reg">立即加入CRC评审</span>
    </div>

    <div class="input_field_div">
        <input class="input_field" type="text" placeholder="键入您的用户名">
        <input class="input_field" type="password" placeholder="键入您的密码">
    </div>

    <div class="switchbtn_div">
        <input class="mui-switch mui-switch-animbg" type="checkbox">
        <span class="logtip">是否允许任何人邀请你</span>
    </div>

    <div class="switchtip_div">
        <a class="switchtip" data-toggle="modal" href="#Login"
           data-dismiss="modal">登录已有帐号</a>
    </div>

    <div class="logbtn_div">
        <button class="logbtn">注册</button>
    </div>

</div>


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="../js/main.js"></script>--%>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.js"></script>
<script type="text/javascript">
    /**
     * Created by marioquer on 16/7/11.
     */
    function backToTop() {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 150) {
                $("#back-to-top").fadeIn();
            } else {
                $("#back-to-top").fadeOut();
            }
        });
        $("#back-to-top").click(function () {
            $("html, body").animate({scrollTop: 0}, 500);
        });
    }
    $(document).ready(function () {
        backToTop();
    })

</script>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
