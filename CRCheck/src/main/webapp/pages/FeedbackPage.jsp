<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 16/7/20
  Time: 上午10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.UserModel" %>
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
                        <i
                                class="fa fa-bell" style="font-size:25px;"></i>
                        <% } else {%>
                        <i
                                class="fa fa-bell-o" style="font-size:25px;"></i>
                        <% }%>
                    </a></div>
                    <%--用户中心下拉框--%>
                    <div class="popup user-popup" id="js-user-popup">
                        <a class="item" href="PersonalPage.jsp"><i
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
        <div class="title_name">CRC评审项目</div>
        <div class="title_ddl">2天后结束</div>
    </div>

    <div class="head_div2">

        <div class="div2_left">

            <div class="hr_info">
                <div>项目信息</div>
                <hr class="hr_separate">

                <div class="pro_info">
                    <div class="title_img"></div>
                    <div class="pro_launcher">marioquer</div>
                    <div class="type_img"></div>
                    <div class="pro_launcher" style="left:35px">Java</div>
                    <div class="pro_time visible-lg-inline-block">2016-7-19 — 2016-8-1</div>
                </div>

            </div>

            <div class="hr_info" style="margin-top: 29px">
                <div>项目描述</div>
                <hr class="hr_separate">
                <div>这是一个很帅很帅很帅很帅很帅很帅很帅很帅很
                    很帅很帅很帅很帅帅很帅很帅很帅很帅很帅很帅
                    很帅很帅很帅很帅很帅很帅很帅很帅很帅的项目
                </div>
            </div>
        </div>

        <div class="div2_right">
            <div class="hr_info">
                <span>参与者</span>
                <hr class="hr_separate">

                <% for (int i = 0; i < 5; i++) {%>
                <div class="partner"></div>
                <%}%>

            </div>
        </div>
    </div>

</div>

<div class="table_div">

    <div class="back_div"><i class="fa fa-angle-double-left">&nbsp;</i>返回项目缺陷列表</div>

    <div class="charts_div container">
        <div class="row">
            <div class="col-sm-7" style="margin-top: 15px;">
                <div class="history_div">
                    <div class="top_title">历史提交轨迹图</div>

                    <%--历史提交轨迹图--%>
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

<%--发起项目评审--%>
<div id="launch">
    <div class="launch_div_left visible-lg"></div>

    <div class="launch_div_right">
        <button class="close close_div_launch" onclick="closeLaunch('launch')">
            <i class="fa fa-times"></i>
        </button>

        <input class="textfield" id="pro_name" type="text" placeholder="项目名称">

        <div class="upload">

            <form id="form_file" method="post" action="../oneUpload.action" enctype="multipart/form-data">
                <span id="upload_File">上传文件</span>

                <input type="file" name="oneFile" id="file_input" onchange="uploadFile()">
            </form>

            <div id="prog_div" class="progress">
                <div id="inner_prog" class="progress-bar progress-bar-success"></div>
            </div>
        </div>

        <textarea class="textfield" id="pro_describe" placeholder="项目描述"></textarea>

        <div class="selectStyle code_language_div textfield">
            <select id="code_language" class="mycombox">
                <option>编程语言</option>
                <option>Java</option>
                <option>Cpp</option>
                <option>Python</option>
                <option>WebApp</option>
                <option>Android</option>
                <option>iOS</option>
            </select>
        </div>

        <div class="selectStyle end_date_div">
            <input class="date_style textfield" type="text" id="end_date" placeholder="结束时间" readonly>

            <div class="selectStyle start_date_div ">
                <input class="date_style textfield" type="text" id="start_date" placeholder="开始时间" readonly>
            </div>

            <div class="img_div"></div>

            <span class="limit_tip">评审项目是否公开可见</span>

            <span class="invitation_tip">选择您的项目参与者</span>
        </div>

        <input class="mui-switch mui-switch-animbg" type="checkbox" id="limit">

        <hr class="hr_decorate">

        <button class="invitation_list_btn" onclick="showLaunch('reviewer_div')">评审者列表</button>

        <div class="shadow" onclick="publishPro()">
            发起项目评审
        </div>

        <%-- 邀请 --%>
        <div id="reviewer_div">
            <button class="close_div_launch close" onclick="closeLaunch('reviewer_div')">
                <i class="fa fa-times"></i>
            </button>

            <%-- 上半部分 --%>
            <div class="above_part">
                <div id="above_div" class="imgs_div">
                    <%
                        for (int i = 0; i < 8; i++) {
                            String imgId = "img" + (i + "");
                            String idId = "id" + (i + "");
                    %>

                    <div class="div_each" onclick="addIds(<%= i%>)">
                        <div class="img_each" id="<%= imgId%>"></div>
                        <div class="id_each" id="<%= idId%>">name</div>
                    </div>

                    <% } %>
                </div>

                <div class="refresh" onclick="setIds()">随机更换一批</div>

                <hr class="hr_decorate_2">
            </div>

            <span class="selected_tip">已选评审者</span>

            <%-- 下半部分 --%>
            <div class="below_part">
                <%-- 选择的用户 --%>
                <div id="selectedIds_div" class="selected_divs"></div>

                <%-- 分页 --%>
                <div class="pages_depart">

                    <span class="previous" onclick="prevPage()">
                        <i class="fa fa-angle-double-left"></i>
                    </span>

                    <div id="pages">
                        <span class="dot" onclick="gotoPage(this)"></span>
                    </div>

                    <span class="next" onclick="nextPage()">
                        <i class="fa fa-angle-double-right"></i>
                    </span>
                </div>

            </div>

            <div class="checkbox_div">
                <input id="self_in" type="checkbox">
                <label class="tip_1" for="self_in">自己参与评审</label>
            </div>

        </div>

    </div>
</div>

<%--登录--%>
<div id="Login" class="modal hide fade in" style="display: none;">

    <div class="log_reg_div">
        <span class="log_reg">登录CRC平台</span>
    </div>

    <button class="close close_div" data-toggle="modal" data-dismiss="modal"><i class="fa fa-times"></i></button>

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

    <button class="close close_div" data-toggle="modal" data-dismiss="modal"><i class="fa fa-times"></i></button>

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

<%-- 用来存放userId --%>
<a id="storage" style="display: none;"><%=userId%>
</a>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="../js/main.js"></script>--%>
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
<script src="../jsChart/ScatterDiagram.js"></script>

<script src="../jsChart/StatisticsChart.js"></script>
<script src="../jsChart/LineChart.js"></script>
<script>showScatterDiagram(30)</script>
<script>getStatisticsChart(30)</script>
<script>getLineChart(30)</script>
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
