<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 16/7/20
  Time: 上午10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.UserModel" %>
<%@ page import="model.UserInf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
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
    <link href="../css/personalPage.css" rel="stylesheet">

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
    String src = "../HeadPortraits/" + userId + ".png";

    UserInf userInf = (UserInf) session.getAttribute("inf");
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


<div class="backimage"></div>
<div class="container all-info">
    <div class="row">
        <div class="col-sm-3">
            <div class="person-block block-shadow">
                <div class="person-info">
                    <div class="portrait">
                        <form action="../headPortraitsUpload.action" method="post" name="form1"
                              enctype="multipart/form-data" style="margin-bottom:0;">
                            <img id="previewImg" width="68" height="68" src=<%=user.getHeadPortrait()%>>
                            <%--<div style="height: 68px; width: 68px;" id="previewImg"><a href="#"></a></div>--%>
                            <input type="file" name="imageFile" id="file" accept="image/*"
                                   onchange="change('previewImg','file')" onmouseover="imageEdit(1)"
                                   onmouseout="imageEdit(0)">
                            <div id="portrait-filter"><i class="fa fa-pencil"></i></div>
                            <%--<input type="submit" value="提交"/>--%>
                        </form>


                        <div class="info info-username"><%=userId%>
                        </div>
                    </div>
                    <div class="info-item" style="margin-top: 0;">
                        <div class="info-name">个人主页</div>
                        <div class="info">${inf.blog}</div>
                    </div>
                    <div class="info-item">
                        <div class="info-name">邮箱</div>
                        <div class="info">${inf.email}</div>
                    </div>
                    <div class="info-item">
                        <div class="info-name">联系方式</div>
                        <div class="info">${inf.phone}</div>
                    </div>
                    <div class="info-item">
                        <div class="info-name">单位/学校</div>
                        <div class="info">${inf.address}</div>
                    </div>
                    <button id="edit-button" class="common-button" style="margin-top: 10px;" onclick="editInfo()">修改资料
                    </button>
                </div>

                <div class="person-nav text-center">
                    <div class="nav-text" onclick="slideTo('friend')">我的好友 ›</div>
                    <div class="nav-text" onclick="slideTo('achieve')">我的成就 ›</div>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <div class="list-block">
                <div class="history-info">
                    <div class="history-block">
                        <div class="history-item">
                            <div class="history-name">评审行数</div>
                            <div class="history"></div>
                        </div>
                        <div class="history-item">
                            <div class="history-name">评审时间</div>
                            <div class="history"></div>
                        </div>
                        <div class="history-item">
                            <div class="history-name">发现缺陷</div>
                            <div class="history"></div>
                        </div>
                        <div class="history-item">
                            <div class="history-name">正确率</div>
                            <div class="history"></div>
                        </div>
                        <div class="history-item">
                            <div class="history-name">平均覆盖率</div>
                            <div class="history"></div>
                        </div>
                    </div>
                </div>
                <%--我的好友--%>
                <div id="friend" class="friend-block block-shadow">
                    <div class="achieve-name">我的好友</div>
                    <div class="myfriend">
                        <%--好友列表--%>
                        <%--<div class="result-item inline">--%>
                            <%--<img class="result-image inline" src="../image/portrait.svg">--%>
                            <%--<div class="result-name inline">marioquer</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
                <div class="friend-block block-shadow">
                    <div class="achieve-name">添加好友</div>
                    <div class="search-row">
                        <div style="margin-bottom: 15px;">
                            <input id="searchField" type="text" class="textfield inline"
                                   style="width: 540px;height: 40px; background-color: #F1F2FA;" onkeyup="keySearch()">
                            <button class="common-button inline" style="width:167px;height: 40px;margin-left: 8px;"
                                    onclick="searchUser()"><i class="fa fa-search"></i> 搜索好友
                            </button>
                        </div>


                        <%--好友列表框--%>
                        <div id="user-list" class="result-item">
                            <img class="result-image inline" src="../image/portrait.svg">
                            <div class="result-name inline">marioquer</div>
                            <a href="#" onclick="addFriend(this)"><i class="fa fa-plus result-plus"></i></a>
                        </div>

                        <div id="list"></div>

                    </div>
                </div>
                <%--我的成就--%>
                <div id="achieve" class="achieve-block block-shadow">
                    <div class="achieve-name">我的成就</div>
                    <div class="line">
                        <div class="lines"></div>
                        <div class="lines"></div>
                        <div class="lines"></div>
                        <div class="lines"></div>
                        <div class="lines"></div>
                    </div>
                    <div class="line-text">
                        <div class="text-Name">1024行</div>
                        <div class="text-Name">8192行</div>
                        <div class="text-Name">20000行</div>
                        <div class="text-Name">40000行</div>
                        <div class="text-Name">100000行</div>
                    </div>
                    <div class="time">
                        <div class="times"></div>
                        <div class="times"></div>
                        <div class="times"></div>
                        <div class="times"></div>
                        <div class="times"></div>
                    </div>
                    <div class="line-text">
                        <div class="text-Name">256分钟</div>
                        <div class="text-Name">1024分钟</div>
                        <div class="text-Name">7200分钟</div>
                        <div class="text-Name">20000分钟</div>
                        <div class="text-Name">40000分钟</div>
                    </div>
                    <div class="defect">
                        <div class="defects"></div>
                        <div class="defects"></div>
                        <div class="defects"></div>
                        <div class="defects"></div>
                        <div class="defects"></div>
                    </div>
                    <div class="line-text">
                        <div class="text-Name">64个</div>
                        <div class="text-Name">256个</div>
                        <div class="text-Name">1024个</div>
                        <div class="text-Name">4096个</div>
                        <div class="text-Name">16384个</div>
                    </div>
                    <div class="rightRate">
                        <div class="rights"></div>
                        <div class="rights"></div>
                        <div class="rights"></div>
                        <div class="rights"></div>
                        <div class="rights"></div>
                    </div>
                    <div class="line-text">
                        <div class="text-Name">50%</div>
                        <div class="text-Name">60%</div>
                        <div class="text-Name">70%</div>
                        <div class="text-Name">80%</div>
                        <div class="text-Name">90%</div>
                    </div>
                    <div class="cover">
                        <div class="covers"></div>
                        <div class="covers"></div>
                        <div class="covers"></div>
                        <div class="covers"></div>
                        <div class="covers"></div>
                    </div>
                    <div class="line-text">
                        <div class="text-Name">5%</div>
                        <div class="text-Name">10%</div>
                        <div class="text-Name">20%</div>
                        <div class="text-Name">30%</div>
                        <div class="text-Name">50%</div>
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

<script>

    loadAchieve();

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
