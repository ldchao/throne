<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/4/10
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" language="java" %>

<%-- 选取合并结果项目 --%>
<div id="choose">

    <button class="close_div_launch close" onclick="closeLaunch('choose')">
        <i class="fa fa-times"></i>
    </button>

    <div class="choose_title">选取一项作为结果</div>

    <hr class="hr_choose">

    <div id="defects_parent">
        <div id="defect_copy" class="def_div" style="display: none">
            <div class="line_def">111 行</div>
            <div class="type_def">语法错误</div>
            <div class="describe_def">这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷好傻啊啊这
                个缺陷好傻啊啊这个缺陷好傻啊啊
            </div>
            <hr class="hr_choose hr_each">
        </div>
    </div>

    <%--<hr class="hr_choose">--%>
    <%--<div class="feedback_btn" style="width: 87%; margin-bottom: 47px">手动输入结果项</div>--%>

</div>
