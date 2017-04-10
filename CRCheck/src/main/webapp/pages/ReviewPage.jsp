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

    <!-- 当前页面 styles -->
    <link href="../css/reviewpage.css" rel="stylesheet">

    <link href="../css/launcherpage.css" rel="stylesheet">

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


<div class="head_div">
    <div>
        <div class="title_img"></div>
        <div class="title_name">${project.name}</div>
        <div class="title_ddl">2天后结束</div>
        <div id="finishbtn_launcher" class="finish_review" onclick="finishPro()">下架此项目</div>
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

<div id="dir_id" class="dir_div">

    <%--<div class="dir_word" style="font-weight: 400"></div>--%>
    <%--<div class="dir_word"></div>--%>
    <%--<div class="dir_word_last"></div>--%>
    <%--&nbsp;/&nbsp;--%>
</div>

<i onclick="downAllFile()" class="fa fa-download download_file">&nbsp;&nbsp;下载项目压缩包</i>

<%-- 文件/文件夹 --%>
<div class="table_div">

    <table id="file_table_id" class="file_table" style="display: block;">

        <tr class="table_head">
            <td style="width: 42%;  text-align: left;" onclick="backLast()">
                <div class="back_dot"><i class="fa fa-circle" style="margin-right: 4px;"></i><i
                        class="fa fa-circle"></i></div>
            </td>

            <%-- 判断是文件或文件夹 --%>
            <td style="width: 19%">评审情况</td>

            <td style="width: 19%;">文件数 / 大小</td>
            <td style="width: 20%">最近评审时间</td>
        </tr>

        <tr class="table_body" style="display: none;">
            <td class="td1_style"></td>

            <td style="width: 19%">
                <%-- 判断是文件夹或文件 --%>
                <%-- 判断是否已评审 --%>
                <i class="fa fa-check green_check"></i>
            </td>
            <td style="width: 19%;">520KB</td>
            <td style="width: 20%">2016/07/20</td>
        </tr>

    </table>

    <%-- 评审者代码文件 --%>
    <table id="code_file" class="file_table" style="display: none">

        <tr class="top_bottom">
            <td>
                <input type="checkbox" id="selectAll_top" onclick="selectAll('code_file', 0)">
                <span style="position: relative;top: -1px;left: 0; font-size: 12px">全选</span>
            </td>
            <td>
                <div class="del_btn" onclick="delAll('code_file')">删除</div>
            </td>
            <td></td>
        </tr>


        <%--<tr style="height: 22px; vertical-align: middle" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)">--%>
        <%--<td class="code_td">--%>
        <%--<i class="fa fa-pencil pencil_style"></i>--%>
        <%--</td>--%>
        <%--<td class="code_td"><%= 1%>--%>
        <%--</td>--%>
        <%--<td style="border-right: 1px solid #dfe0e2">--%>
        <%--<pre><%//filelist[i]%></pre>--%>
        <%--</td>--%>
        <%--</tr>--%>

        <tr class="top_bottom">
            <td>
                <input type="checkbox" id="selectAll" onclick="selectAll('code_file', 1)">
                <span style="position: relative;top: -1px;left: 0; font-size: 12px">全选</span>
            </td>
            <td>
                <div class="del_btn" onclick="delAll('code_file')">删除</div>
            </td>
            <td></td>
        </tr>
    </table>

    <%-- 发起者代码文件-合并 --%>
    <table id="launcher_merge" class="file_table" style="display: none">

        <tr class="top_bottom">
            <td>
                <input type="checkbox" id="selectAll_merge_top" onclick="selectAll('launcher_merge', 0)">
                <span style="position: relative;top: -1px;left: 0; font-size: 12px">全选</span>
            </td>
            <td>
                <div class="del_btn" onclick="CodeMerge()">合并</div>
            </td>
            <td></td>
        </tr>

        <%--<tr style="height: 22px; vertical-align: middle">--%>
        <%--<td style="border-right: 1px solid #dfe0e2; text-align: center;">--%>
        <%--<i class="fa fa-pencil" style="cursor: pointer;display: none"></i>--%>
        <%--</td>--%>
        <%--<td style="border-right: 1px solid #dfe0e2;text-align: center"><%=i + 1%>--%>
        <%--</td>--%>
        <%--<td style="border-right: 1px solid #dfe0e2">--%>
        <%--<pre></pre>--%>
        <%--</td>--%>
        <%--</tr>--%>

        <tr class="top_bottom">
            <td>
                <input type="checkbox" id="selectAll_merge" onclick="selectAll('launcher_merge', 1)">
                <span style="position: relative;top: -1px;left: 0; font-size: 12px">全选</span>
            </td>
            <td>
                <div class="del_btn" onclick="CodeMerge()">合并</div>
            </td>
            <td></td>
        </tr>
    </table>

    <%-- 文档评审 --%>
    <div id="review_div" style="display: none;">

        <div class="top_bottom"></div>

        <div class="middle_body">
            <div class="doc_preview"></div>

            <div class="doc_right">

                <div class="doc_topside">
                    <div class="doc_img"></div>
                    <div class="doc_userId visible-lg-inline-block">marioquer</div>
                    <div class="doc_download"><i class="fa fa-download"></i> &nbsp;下载</div>
                </div>

                <div id="doc_bugs"></div>

                <div class="docbug_addbtn" onclick="addDocdiv()"><i class="fa fa-pencil"></i> &nbsp;添加缺陷</div>
            </div>

            <%-- 文档的提交评审 --%>
            <div class="publish_doc">保存</div>
        </div>

        <div class="top_bottom"></div>

    </div>

    <%-- 除了文档的提交评审 --%>
    <div class="publish_review" style="display: block;" onclick="finishCodeReview()">保存</div>
    <div id="finishbtn_reviewer" class="finish_review" style="margin-left: 155px;margin-top: -63px; display: none"
         onclick="completeReview()">结束
    </div>
    <div id="feed_btn" class="checkfeedback_btn" style="display: none" onclick="checkQuality()">查看项目评审质量</div>
</div>

<%-- 缺陷块 --%>
<div id="bugdiv_id" class="bug_div" style="display: none;">
    <div class="top_side">
        <input type="checkbox" style="position: relative; top:-6px;">
        <div class="del_btn each_del">
            <i class="fa fa-trash"></i></div>
        <div class="title_img" style="margin-left: 20px; width: 20px; height: 20px;"></div>
        <div class="userId_div"> marioquer | 2016-07-21 |</div>

        <span class="merge_span"
              style="font-size: 14px;position: relative;left: 25px; top: -5px; display: none">合并共3个缺陷&nbsp;<i
                class="fa fa-angle-double-down"></i></span>
    </div>

    <div>
        <div class="selectStyle bug_type_div">
            <select class="mycombox bug_type">
                <option>缺陷类型</option>
                <option>语法错误</option>
                <option>死循环</option>
                <option>空指针</option>
                <option>其他</option>
            </select>
        </div>

        <input class="bug_desc" type="text" placeholder="缺陷描述">

        <div style="background-color: #fff; width: 100%; padding: 2px auto;">
            <div class="bug_add">再加一条</div>
        </div>

        <%-- 缓存位置 --%>
        <a class="pos_rec" style="display: none;"></a>
        <%-- 缓存记录id --%>
        <a class="pos_rec" style="display: none;"></a>
    </div>
</div>

<%-- 文档缺陷快 --%>
<div id="docbug_id" class="docbug_div" style="display: none">
    <input class="docbug_input" type="text" placeholder="页数">

    <input class="docbug_input" type="text" placeholder="行数">

    <div class="doc_del"><i class="fa fa-times"></i></div>

    <textarea class="docbug_desc" placeholder="缺陷描述"></textarea>

    <hr class="doc_hr">
</div>

<%-- 选取合并结果项目 --%>
<div id="choose">
    <div class="choose_title">选取合并结果项</div>

    <div id="defects_parent">
        <div id="defect_copy" class="def_div" style="display: none">
            <div class="line_def">111 行</div>
            <div class="type_def">语法错误</div>
            <div class="describe_def">这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这
                个缺陷好傻啊啊这个缺陷好傻啊啊
            </div>
        </div>
    </div>

    <hr class="hr_choose">
    <div class="feedback_btn" style="width: 87%; margin-bottom: 47px">手动输入结果项</div>

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
<%-- 用来存放项目id --%>
<a id="storage_proId" style="display: none;">${project.projectID}
</a>
<%-- 用来存放项目name --%>
<a id="storage_proName" style="display: none;">${project.name}
</a>
<%-- 用来存放项目发起者id --%>
<a id="storage_pro_userId" style="display: none;">${project.userID}
</a>
<%-- 用来存放自己是否参与 --%>
<a id="storage_attendReview" style="display: none;">${project.attendReview}
</a>
<%-- 用来存放评审是否结束 --%>
<a id="storage_state" style="display: none;">${project.state}
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
<script src="../js/reviewpage.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
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
