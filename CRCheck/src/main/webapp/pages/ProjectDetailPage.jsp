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

<%
    UserModel user = (UserModel) request.getSession().getAttribute("User");
    String userId = "";
    if (user != null) {
        userId = user.getId();
    }
%>
<c:set var="userId" value="<%=userId%>"/>

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
                        <div class="image-middle"></div>
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
        <div class="project-title">${project.name}
        </div>
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
    <div class="project-time time-text">${project.startDate} - ${project.endDate}
    </div>
    <p class="project-detail main-text text-center">${project.discription}
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

    <div id="all-defect">
        <div id="exist_copy" class="exist-form" style="margin-top: 20px; display: none">
            <div class="class-form">
                <%--<div class="class-head">--%>
                <%--</div>--%>
                <div class="class-intent">
                    <div class="info-head text-left">
                        <%--路径--%>
                        <p class="head-text"></p>
                        <%--行数--%>
                        <p class="head-text"></p>
                        <%--错误类型--%>
                        <p class="head-text"></p>
                    </div>
                    <%--错误描述--%>
                    <div class="info-bottom inner-infoText text-left">
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="left-time main-text">${day}
    </div>


    <c:if test="${(project.userID != userId || project.attendReview == 'YES') && project.state != 'Over'}">

        <div id="finish_before">
            <div id="begin" class="submit-button" onclick="beginReview()">立即开始评审</div>
            <div id="review-block" style="display: none;">

                <div id="init-form">
                        <%--单个缺陷表格--%>
                    <div class="form-empty">
                        <div class="form-line row form-title" style="margin-left: 20px;margin-right: 20px;">
                            <div class="col-xs-2 text-left num"
                                 style="padding-left: 5px;padding-right: 5px;margin-top:10px;">1
                            </div>
                            <div class="col-xs-8 text-center"
                                 style="padding-left: 5px;padding-right: 5px;margin-top:10px;">缺陷详细描述
                            </div>
                            <div class="col-xs-2 text-right close"
                                 style="padding-left: 5px;padding-right: 5px;margin-top:10px; "
                                 onclick="deleteForm(this)">
                                <i class="fa fa-times"></i>
                            </div>
                        </div>
                            <%--第一行表格--%>
                        <div class="form-line row" style="margin-left: 20px;margin-right: 20px;">
                            <div class="col-sm-7"
                                 style="padding-left: 5px;padding-right: 5px;margin-top:10px;">
                                <input type="text" placeholder="缺陷代码目录, 用‘/’分开" class="textfield"
                                       style="height: 35px; width: 100%;">
                            </div>
                            <div class="col-sm-2"
                                 style="padding-left: 5px;padding-right: 5px;margin-top:10px;">
                                <input type="text" placeholder="开始行数" class="textfield"
                                       style="height: 35px; width: 100%;">
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

                <div class="control text-center">
                    <div class="submit-button left-button" onclick="publishForm()" style="margin-top:10px;">保存此次评审</div>
                    <div class="cancel-button" onclick="endReview()" style="margin-top:10px;">结束此项目评审</div>
                </div>

            </div>
        </div>
    </c:if>

    <c:if test="${project.state == 'Over'}">
        <div style="margin-top: 30px;">
            <div class="merge_this" onclick="mergeDefects()">合并相同缺陷</div>
            <div class="feedback_btn" onclick="checkQuality()">查看项目评审质量</div>
        </div>
    </c:if>

    <div id="finish_after" style="margin-top: 30px; display: none">
        <div class="merge_this" onclick="mergeDefects()">合并相同缺陷</div>
        <div class="feedback_btn" onclick="checkQuality()">查看项目评审质量</div>
    </div>

</div>


<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>

<footer> © CRCheck 2016</footer>

<%--发起项目评审--%>
<div id="launch">
    <div class="launch_div_left visible-lg"></div>

    <div class="launch_div_right">
        <button class="close close_div_launch" onclick="closeLaunch('launch')">
            <i class="fa fa-times"></i>
        </button>

        <input class="textfield" id="pro_name" type="text" placeholder="项目名称">

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

    <button class="close close_div" data-toggle="modal" data-dismiss="modal"><i
            class="fa fa-times"></i></button>

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

    <button class="close close_div" data-toggle="modal" data-dismiss="modal"><i
            class="fa fa-times"></i></button>

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
<a id="p-state" style="display: none;">${project.state}</a>
<a id="yesNo" style="display: none;">${project.attendReview}</a>
<a id="p-id" style="display: none;">${project.projectID}</a>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="../js/main.js"></script>--%>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/common.js"></script>
<script src="../js/toaster.js"></script>
<script src="../js/ProjectDetailPage.js"></script>
<script src="../js/projectpage.js"></script>
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
