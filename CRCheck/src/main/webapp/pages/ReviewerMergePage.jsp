<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 16/7/20
  Time: 上午10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <!-- combox styles -->
    <link href="../css/mycombox.css" rel="stylesheet">

    <!-- datetimepicker styles -->
    <link href="../css/datetimepicker.css" rel="stylesheet">

    <!-- 发起项目评审 styles -->
    <link href="../css/projectpage.css" rel="stylesheet">

    <link href="../css/projectDetailPage.css" rel="stylesheet">

    <link href="../css/launcherpage.css" rel="stylesheet">

    <!-- specific styles -->
    <link href="../css/component.css" rel="stylesheet">

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

<div id="auto-container" class="container text-center">

    <div class="row">
        <div class="project-title" style="background-color: #6b6f78">${project.name}</div>
    </div>

    <div class="project-char text-center">
        <div class="project-owner">
            <div class="image-small"
                 style="margin-right:12px;">
            </div>
            <div class="sub-title" id="owner">${project.userID}</div>
        </div>
        <div class="project-type" style="display: inline-block;">
            <div class="type-image"></div>
            <div class="sub-title">${project.type}
            </div>
        </div>
    </div>
    <div class="project-time time-text">${project.startDate} - ${project.endDate}</div>
    <p class="project-detail main-text text-center">
        ${project.discription}
    </p>

    <div class="member">
        <div class="middle-title">参与者</div>
        <div class="member-list">
            <c:forEach items="${project.invitationList}" var="list">

                <c:choose>

                    <c:when test="${list.accepting_state=='Agree'}">
                        <div class="images_div">
                            <div class="image-middle"></div>
                            <div>${list.userID}</div>
                        </div>
                    </c:when>

                    <c:when test="${list.accepting_state=='Refuse'}">
                        <div class="images_div">
                            <div class="image-middle">
                                <div class="image-refuse"></div>
                            </div>
                            <div>${list.userID}</div>
                        </div>
                    </c:when>

                    <c:when test="${list.accepting_state=='NotHandle'}">
                        <div class="images_div">
                            <div class="image-middle">
                                <div class="image-other"></div>
                            </div>
                            <div>${list.userID}</div>
                        </div>
                    </c:when>
                </c:choose>

            </c:forEach>
        </div>
    </div>

    <div id="all-defect"></div>

    <div style="margin-top: 50px">
        <div class="merge_this" style="width: 100px" onclick="Merge()">合并</div>
        <div class="refuse_merge" style="width: 100px" onclick="UndoMerge()">解开</div>
        <div class="finish_btn finish_this" style="width: 125px" onclick="finishMerge()">结束合并</div>
    </div>
</div>

<%-- 缺陷块 --%>
<div id="exist_copy" style="display: none">

    <div class="exist-form" style="margin-top: 20px;">

        <div class="left_slide" style="height: 57px">
            <input class="merge_box" type="checkbox">
        </div>

        <div class="class-form">
            <%--<div class="class-head">--%>
            <%--</div>--%>
            <div class="class-intent">
                <div class="info-head text-left">
                    <%--路径--%>
                    <p class="head-text">CRC/src/java/a.java</p>
                    <%--行数--%>
                    <p class="head-text">111 行</p>
                    <%--错误类型--%>
                    <p class="head-text">语法错误</p>

                    <div class="who_div">
                        <div class="image-small who_pic"></div>
                        <div class="who_name">marioquer</div>
                    </div>
                </div>
                <%--错误描述--%>
                <div class="info-bottom inner-infoText text-left">
                    这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这
                    个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这个
                    缺陷好傻啊啊这个缺陷好傻啊
                </div>
                <%-- 用来存放记录的id --%>
                <div class="recordId"></div>
                <%-- 用来存放父节点的id --%>
                <div class="recordId"></div>
            </div>
        </div>
    </div>
</div>

<div id="exist_copy_2" style="display: none">

    <div class="exist-form" style="margin-top: 20px;">

        <div class="left_slide" style="height: 57px">
            <input class="merge_box" type="checkbox">
        </div>

        <div class="class-form">
            <div class="class-intent_2">
                <div class="info-head_2 text-left" style="padding:-16px -26px">
                    <%--路径--%>
                    <p class="head-text" style="color: #585841">CRC/src/java/a.java</p>
                    <%--行数--%>
                    <p class="head-text" style="color: #585841">111 行</p>
                    <%--错误类型--%>
                    <p class="head-text" style="color: #585841">语法错误</p>

                            <span class="merge_span">合并共3个缺陷&nbsp;<i
                                    class="fa fa-angle-double-down"></i></span>

                    <div class="who_div">
                        <div class="image-small who_pic"></div>
                        <div class="who_name">marioquer</div>
                    </div>
                </div>
                <%--错误描述--%>
                <div class="info-bottom_2 inner-infoText text-left">
                    这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这
                    个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这个
                    缺陷好傻啊啊这个缺陷好傻啊
                </div>
                <%-- 用来存放记录的id --%>
                <div class="recordId"></div>
                <%-- 用来存放父节点的id --%>
                <div class="recordId"></div>
            </div>
        </div>
    </div>
</div>

<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>

<footer>© CRCheck 2016</footer>

<%@include file="common/MergeChoose.jsp"%>
<%@include file="common/Modal.jsp"%>

<%-- 用来存放userId --%>
<a id="storage" style="display: none;"><%=userId%>
</a>
<%-- 用来存放projectId --%>
<a id="storage_proId" style="display: none;">${project.projectID}
</a>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="../js/main.js"></script>--%>
<script src="../js/jquery.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/common.js"></script>
<script src="../js/toaster.js"></script>
<script src="../js/projectpage.js"></script>
<script src="../js/personalpage.js"></script>
<script src="../js/reviewermergepage.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
<script src="../js/datetimepicker.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]>
<script src="../js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

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
