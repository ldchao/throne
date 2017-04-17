<%@ page import="model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>我的项目</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- this page's styles -->
    <link href="../css/projectpage.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <%--<link href="../css/bootstrap.css" rel="stylesheet">--%>

    <!-- Custom styles for login and register -->
    <link href="../css/log_reg.css" rel="stylesheet">

    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">

    <!-- checkbox styles -->
    <link href="../css/mycheckbox.css" rel="stylesheet">

    <!-- combox styles -->
    <link href="../css/mycombox.css" rel="stylesheet">

    <!-- datetimepicker styles -->
    <link href="../css/datetimepicker.css" rel="stylesheet">

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

<%
    UserModel user = (UserModel) request.getSession().getAttribute("User");
    String userId = "";
    if (user != null) {
        userId = user.getId();
    }
%>

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

                <%if (user == null) {%>
                <li><a data-toggle="modal" href="#Login">登录</a></li>
                <li><a data-toggle="modal" href="#Register">注册</a></li>
                <%} else {%>

                <div class="user-block">
                    <div class="inline bell"><a href="NewsPage.jsp">
                        <% if (user.getMessageNum() > 0) { %>
                        <img class="message" src="../image/new-message.svg">
                        <% } else {%>
                        <img class="message" src="../image/message.svg">
                        <% }%>
                    </a></div>
                    <%--用户中心下拉框--%>
                    <div class="popup user-popup" id="js-user-popup">
                        <a class="item" href="/pages/users"><i
                                class="fa fa-user fa-fw"></i>&nbsp&nbsp个人中心</a>
                        <a class="item" href="/Logout" style="margin-bottom: 5px;"><i
                                class="fa fa-sign-out fa-fw"></i>&nbsp&nbsp退出账号</a>
                    </div>
                    <div class="user" onmouseover="popup()">
                        <img class="image-middle" src=<%=user.getHeadPortrait()%> onerror="this.src='../image/portrait.svg'">
                        <div class="inline userName"><%=userId%>
                        </div>
                    </div>
                </div>
                <%}%>

            </ul>
            <button class="nav common-button navbar-right" style="margin-top:10px; margin-right:15px;"
                    onclick="showLaunch('launch')">发起评审
            </button>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="tab_lbl">
    <span id="my_in" onclick="switchTab(1)">我参与的项目</span>
    <span id="my_pub" onclick="switchTab(0)">我发布的项目</span>
</div>

<%-- 我参与的项目 --%>
<div id="parent_div">
    <%-- 参与的项目 --%>
    <div id="partin" class="projects_div" style="display: none">

        <div class="title_div">
            <div class="title">CRC评审项目</div>

            <div class="kind_div">
                <div class="kind_img"></div>
                <span>Java</span>
            </div>
        </div>

        <div class="content_describe"></div>

        <div class="bottom_div">
            <div class="date_info">评审日期: 2016.7.14 - 2016.7.31</div>

            <div class="launcher_info">项目发起者: sure</div>

            <div class="right_corner">
                <div class="ddl_tip">XX天后结束</div>

                <button class="continue_btn">查看项目</button>
            </div>
        </div>
    </div>
</div>

<%-- 我发起的项目 --%>
<div id="publish_div">
    <%-- 发布的项目 --%>
    <div id="mylaunch" class="projects_div" style="display: none">

        <div class="title_div">
            <div class="title">CRC评审项目</div>

            <div class="kind_div">
                <div class="kind_img"></div>
                <span>Java</span>
            </div>
        </div>

        <div class="content_describe"></div>

        <div class="bottom_div">
            <div class="date_info">评审日期: 2016.7.14 - 2016.7.31</div>

            <div class="right_corner">
                <div class="ddl_tip">XX天后结束</div>

                <button class="continue_btn" onclick="addCRCpro()">查看项目</button>
            </div>
        </div>
    </div>
</div>

<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>

<footer> © CRCheck 2016</footer>

<%@include file="common/Modal.jsp"%>

<%-- 用来存放userId --%>
<a id="storage" style="display: none;"><%=userId%>
</a>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../js/jquery.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/projectpage.js"></script>
<script src="../js/common.js"></script>
<script src="../js/toaster.js"></script>
<script src="../js/datetimepicker.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

<script>

    $('#start_date').datetimepicker({
        lang: 'ch',
        timepicker: false,
        format: 'Y-m-d'
    });

    $('#end_date').datetimepicker({
        lang: 'ch',
        timepicker: false,
        format: 'Y-m-d'
    });
</script>

</body>
</html>
