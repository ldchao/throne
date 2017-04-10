<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 16/7/20
  Time: 上午10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
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

    <link href="../css/reviewpage.css" rel="stylesheet">

    <link href="../css/feedbackpage.css" rel="stylesheet">

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
    <script src="../js/echarts.min.js"></script>
    <script src="../js/jquery.min.js"></script>
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

<div class="head_div">
    <div>
        <div class="title_img"></div>
        <div class="title_name">${project.name}</div>
        <div class="title_ddl">${day}</div>
    </div>

    <div class="head_div2">

        <div class="div2_left">

            <div class="hr_info">
                <div>项目信息</div>
                <hr class="hr_separate">

                <div class="pro_info">
                    <div class="title_img"></div>
                    <div class="pro_launcher">${project.userID}</div>
                    <div class="type_img"></div>
                    <div class="pro_launcher" style="left:35px">${project.type}</div>
                    <div class="pro_time visible-lg-inline-block">${project.startDate} - ${project.endDate}</div>
                </div>

            </div>

            <div class="hr_info" style="margin-top: 29px">
                <div>项目描述</div>
                <hr class="hr_separate">
                <div>${project.discription}</div>
            </div>
        </div>

        <div class="div2_right">
            <div class="hr_info">
                <span>参与者</span>
                <hr class="hr_separate">

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
                                    <div class="image-middle" style="">
                                        <div class="image-other"></div>
                                    </div>
                                    <div>${list.userID}</div>
                                </div>
                            </c:when>
                        </c:choose>

                    </c:forEach>
                </div>

            </div>
        </div>
    </div>

</div>

<div class="table_div">

    <div class="back_div" onclick="backToList()"><i class="fa fa-angle-double-left">&nbsp;</i>返回项目缺陷列表</div>

    <div class="charts_div container">
        <div class="row">
            <div class="col-sm-7" style="margin-top: 15px;">
                <div class="history_div">
                    <div class="top_title">历史提交轨迹图</div>

                    <%--历史提交轨迹图--%>
                    <div class="commit-item" id="commit_item" style="display: none;">
                        <i class="fa fa-code-fork"
                           style="font-size:28px;margin-left:10px;color:#838D9E;line-height: 90px;"></i>
                        <div class="commit-content inline">
                            <div class="first-line">
                                <img class="image-small">
                                <div class="userName-small inline">marioquer</div>
                                <div class="commit-time inline">10:00</div>
                            </div>
                            <div class="second-line">
                                <div class="discription">送来产额穿就飞龙拆饭五车间分欧外</div>
                            </div>
                        </div>
                        <div class="num1 inline">
                            <div class="num1-name">Method 1</div>
                            <div class="num1-content text-center">111个</div>
                        </div>
                        <div class="num2 inline">
                            <div class="num2-name">Method 2</div>
                            <div class="num2-content text-center">101个</div>
                        </div>
                    </div>


                </div>
            </div>

            <div class="col-sm-5">
                <div class="row">
                    <div class="echarts_div">
                        <div class="top_title">预测缺陷数变化折线图</div>

                        <div class="charts" id="lineChart"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="echarts_div">
                        <div class="top_title">个人评审质量统计图</div>

                        <div class="charts" id="statisticsChart"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="echarts_div">
                        <div class="top_title">项目发现缺陷详情散点图</div>

                        <div class="charts" id="scatterDiagram"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>

<footer>© CRCheck 2016</footer>

<%@include file="common/Modal.jsp"%>

<%-- 用来存放userId --%>
<a id="storage" style="display: none;"><%=userId%></a>
<a id="p-id" style="display: none;">${project.projectID}</a>

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
<script src="../js/FeedbackPage.js"></script>
<script src="../js/personalpage.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
<script src="../js/datetimepicker.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
<script src="../jsChart/ScatterDiagram.js"></script>

<script src="../jsChart/StatisticsChart.js"></script>
<script src="../jsChart/LineChart.js"></script>
<script></script>
<script></script>
<script></script>
<script>
    showScatterDiagram(${project.projectID});
    getStatisticsChart(${project.projectID});
    getLineChart(${project.projectID});
    loadRecord();
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
