<%@ page import="model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>项目详情</title>
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

<%@include file="common/Nav.jsp"%>

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
            <div id="end" class="cancel-button" onclick="endReview()">结束此项目评审</div>
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

                    <div id="timeline">

                    <%--时间和行数--%>
                    <div class="form-line row" style="margin-left: 20px;margin-right: 20px;">
                        <div class="col-sm-4"
                             style="padding-left: 5px;padding-right: 5px;margin-top:10px;">
                            <input id="line-num" type="text" placeholder="此次评审代码行数" class="textfield"
                                   style="height: 35px; width: 100%;">
                        </div>
                        <div class="col-sm-4"
                             style="padding-left: 5px;padding-right: 5px;margin-top:10px;">
                            <input id="time-num" type="text" placeholder="所用时间(min)" class="textfield"
                                   style="height: 35px; width: 100%;">
                        </div>
                        <div class="col-sm-4">
                            <div class="selectStyle code_language_div textfield">
                                <select id="type-select" class="mycombox"
                                        style="height:25px;padding-left: 5px;padding-right: 5px;margin-top:10px;margin-top:0;">
                                    <option>评审类型</option>
                                    <option>代码评审</option>
                                    <option>文档评审</option>
                                </select>
                            </div>


                            <%--<input type="text" placeholder="评审类型" class="textfield"--%>
                                   <%--style="height: 35px; width: 100%;">--%>
                        </div>
                    </div>
                    </div>


                    <div class="submit-button left-button" onclick="publishForm()" style="margin-top:10px;">保存此次评审</div>
                    <div class="cancel-button" onclick="endReview()" style="margin-top:10px;">结束此项目评审</div>
                </div>

            </div>
        </div>
    </c:if>

    <div id="endButtons" style="margin-top: 30px;display: none;">
        <div class="submit-button" onclick="beginReview()">补充新的缺陷</div>
        <div class="merge_this" onclick="mergeDefects()">合并相同缺陷</div>
        <div class="feedback_btn" onclick="checkQuality()">查看项目评审质量</div>
    </div>

    <div id="finish_after" style="margin-top: 30px; display: none">
        <div class="merge_this" onclick="mergeDefects()">合并相同缺陷</div>
        <div class="feedback_btn" onclick="checkQuality()">查看项目评审质量</div>
    </div>

</div>


<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>

<footer> © CRCheck 2016</footer>

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
