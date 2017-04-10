<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 16/7/15
  Time: 下午3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- 发起项目 styles -->
    <link href="../css/projectpage.css" rel="stylesheet">

    <!-- this page's styles -->
    <link href="../css/newspage.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

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
                <li><a href="ProjectPage.jsp">项目</a></li>
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
                        <img class="image-middle" src=<%=user.getHeadPortrait()%>>
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
    <span id="news_last" onclick="switchTab(1)">新的消息</span>
    <span id="news_history" onclick="switchTab(0)">历史消息</span>
</div>

<div id="last_div">

    <div id="news" class="projects_div" style="display: none;">

        <div class="title_div">
            <div class="title">CRC评审项目</div>

            <div class="kind_div">
                <div class="invitor">邀请人: marioquer</div>
                <div class="kind_img"></div>
                <span>Java</span>
            </div>
        </div>

        <div class="content_describe"></div>

        <div class="bottom_div">
            <div class="date_info">评审日期: 2016.7.14 - 2016.7.31</div>

            <div class="right_corner">

                <button class="continue_btn" style="width:102px; height: 31px; font-size: 16px;">接受邀请</button>

                <button class="refuse_btn">拒绝邀请</button>

                <button class="ignore_btn">忽略</button>

                <button class="delete_btn">删除</button>

            </div>
        </div>
    </div>

</div>

<div id="removeAll" class="close" style="display: none" onclick="removeAll()">
    <i class="fa fa-trash" style="margin-right: 5px;"></i>清空所有历史消息
</div>

<div id="history_div">

    <div id="history" class="projects_div" style="display: none;">

        <div class="title_div">
            <div class="title">CRC评审项目</div>

            <div class="kind_div">
                <div class="invitor">邀请人: marioquer</div>
                <div class="kind_img"></div>
                <span>Java</span>
            </div>
        </div>

        <div class="content_describe">这是一个消息</div>

        <div class="bottom_div">
            <div class="date_info">评审日期: 2016.7.14 - 2016.7.31</div>

            <div class="right_corner">

                <span class="state_style">处理状态: 已处理</span>

            </div>
        </div>
    </div>
</div>

<%@include file="common/Modal.jsp"%>

<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>
<footer>© CRCheck 2016   </footer>

<%-- 用来存放userId --%>
<a id="storage" style="display: none;"><%=userId%>
</a>

<script src="../js/jquery.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/projectpage.js"></script>
<script src="../js/newspage.js"></script>
<script src="../js/common.js"></script>
<script src="../js/toaster.js"></script>
<script src="../js/datetimepicker.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

<script>
    backToTop();

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
