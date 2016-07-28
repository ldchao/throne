<%@ page import="model.UserModel" %>
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

<div id="introduce_parent" onclick="showIntroduce()"></div>
<div id="introduce_child">
    <h1>介绍部分</h1>
</div>

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

<%-- 发起项目 --%>
<div class="invite_div">
    <div class="left_div visible-lg">
        <span class="text_style_left">邀请你的项目伙伴</span>
        <span class="text_style_left">定时高效</span>
    </div>

    <div class="circle_div" onclick="showLaunch('launch')">
        <hr class="add_style_1">
        <hr class="add_style_2">
    </div>

    <div class="right_div visible-lg">
        <span class="text_style_right">从这里开始添加你的</span>
        <span class="text_style_right">评审项目</span>
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

        <input class="textfield row-fluid" id="pro_name" type="text" placeholder="项目名称">

        <div class="upload">

            <form id="form_file" method="post" action="../oneUpload.action" enctype="multipart/form-data">
                <span id="upload_File">上传文件</span>

                <input type="file" name="oneFile" id="file_input" onchange="setFile()">
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

            <div class="selectStyle start_date_div">
                <input class="date_style textfield" type="text" id="start_date" placeholder="开始时间" readonly>
            </div>

            <input class="date_style textfield" type="text" id="end_date" placeholder="结束时间" readonly>

            <span class="limit_tip">评审项目是否公开可见</span>

            <span class="invitation_tip">选择您的项目参与者</span>

            <div class="img_div"></div>
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
