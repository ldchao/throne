/**
 * Created by L.H.S on 16/7/20.
 */

var file_table_id = document.getElementById("file_table_id");
var code_table_id = document.getElementById("code_file");   // 在线评审代码
var code_merge_id = document.getElementById("launcher_merge");  // 在线合并
var doc_id = document.getElementById("review_div");
var dir_id = document.getElementById("dir_id");
var userId = document.getElementById("storage").innerHTML.trim();
var PROJECT_ID = document.getElementById("storage_proId").innerHTML.trim();
var PROJECT_NAME = document.getElementById("storage_proName").innerHTML.trim();
var PATH;  // 用来记录路径
var reviewState = "";  // 审批状态
var PRO_USERID = document.getElementById("storage_pro_userId").innerHTML.trim();  // 项目发起者
var ATTEND = document.getElementById("storage_attendReview").innerHTML.trim();  // 自己是否参与
var STATE = document.getElementById("storage_state").innerHTML.trim();  // 项目是否结束

var recordID;
var recordIDlist = new Array();
var rec_count = 1;

// 代码评审合并
window.onload = function () {

    $.ajax({
        type: "post",
        async: false,
        url: "/checkReviewState",
        data: {
            "userID": userId,
            "projectID": PROJECT_ID
        },
        success: function (result) {
            reviewState = result
        },
        error: function () {
            slidein(1, "加载评审状态失败");
        }
    });

    if (userId != PRO_USERID || (userId == PRO_USERID && ATTEND == "YES")) {
        document.getElementById("finishbtn_reviewer").style.display = "";
    }

    if (STATE == "Over" || reviewState == "Done" || (userId == PRO_USERID && ATTEND == "NO")) {
        document.getElementById("feed_btn").style.display = "";
    }

    if (userId != PRO_USERID) {
        document.getElementById("finishbtn_launcher").style.display = "none";
    }

    getFile(PROJECT_ID);
};

function mouseOver(tr) {
    tr.style.backgroundColor = "#f3faff";
    tr.getElementsByTagName("i")[0].style.display = "block";

    tr.getElementsByTagName("i")[0].onclick = function () {
        addDiv(tr, "code_file");
    }
}

function mouseOut(tr) {
    tr.style.backgroundColor = "transparent";
    tr.getElementsByTagName("i")[0].style.display = "none";
}

function addDiv(tr, parentId) {
    var index = $(tr).parents(".file_table").find("tr").index($(tr));

    var row = document.getElementById(parentId).insertRow(index + 1);
    row.style.height = "97px";
    row.style.width = "100%";

    var td = document.createElement("td");
    td.setAttribute("colspan", "3");

    var div = document.createElement("div");
    div.setAttribute("class", "bug_div");
    div.innerHTML = document.getElementById("bugdiv_id").innerHTML;
    var day = new Date();
    var today = day.getFullYear() + "-" + day.getMonth() + "-" + day.getDate();
    div.getElementsByClassName("userId_div")[0].innerHTML = userId + "&nbsp;|&nbsp" + today;
    td.appendChild(div);

    if (tr.getElementsByClassName("pos_rec").length > 0) {
        div.getElementsByClassName("pos_rec")[0].innerHTML = tr.getElementsByClassName("pos_rec")[0].innerHTML;
    } else {
        div.getElementsByClassName("pos_rec")[0].innerHTML = tr.getElementsByTagName("td")[1].innerHTML;
    }

    var addbtn = div.getElementsByClassName("bug_add")[0];
    addbtn.onclick = function () {
        addDiv(row, parentId);
    };

    var checkbox = div.getElementsByTagName("input")[0];
    checkbox.onclick = function () {
        if (checkbox.checked == false) {
            document.getElementById("selectAll").checked = false;
            document.getElementById("selectAll_top").checked = false;
        }
    };

    var delbtn = div.getElementsByClassName("del_btn")[0];
    delbtn.onclick = function () {
        var t = $(this).parents("tr");
        var pos = $(t).parents(".file_table").find("tr").index($(t));
        document.getElementById(parentId).deleteRow(pos);
    };

    row.appendChild(td);
    document.getElementById("selectAll").checked = false;
    document.getElementById("selectAll_top").checked = false;


    return td;
}

// top 0,上； top1,下
function selectAll(parentId, top) {

    var codeFile = ["selectAll_top", "selectAll"];
    var launchMerge = ["selectAll_merge_top", "selectAll_merge"];

    var divs = document.getElementById(parentId).getElementsByClassName("bug_div");
    for (var i = 0; i < divs.length; i++) {
        var box = divs[i].getElementsByTagName("input")[0];
        if (parentId == "code_file") {
            box.checked = document.getElementById(codeFile[top]).checked;
            document.getElementById(codeFile[(top + 1) % 2]).checked = box.checked;
        } else {
            box.checked = document.getElementById(launchMerge[top]).checked;
            document.getElementById(launchMerge[(top + 1) % 2]).checked = box.checked;
        }
    }
}

function delAll(parentId) {

    var divs = document.getElementById(parentId).getElementsByClassName("bug_div");
    var n = divs.length - 1;
    for (var i = n; i > -1; i--) {
        var box = divs[i].getElementsByTagName("input")[0];
        if (box.checked == true) {
            var t = $(divs[i]).parents("tr");
            var pos = $(t).parents(".file_table").find("tr").index($(t));
            document.getElementById(parentId).deleteRow(pos);
        }
    }
    document.getElementById("selectAll").checked = false;
    document.getElementById("selectAll_top").checked = false;
}

// 文档评审
function addDocdiv() {

    var div = document.createElement("div");
    div.setAttribute("class", "docbug_div");
    div.innerHTML = document.getElementById("docbug_id").innerHTML;

    var delbtn = div.getElementsByClassName("doc_del")[0];
    delbtn.onclick = function () {
        document.getElementById("doc_bugs").removeChild(div);
    };

    document.getElementById("doc_bugs").appendChild(div);
}

// 添加已有缺陷
function addReDefects(pos, defect, parentId) {

    var table = document.getElementById(parentId);
    var trs = table.getElementsByTagName("tr");
    for (var i = 0; i < trs.length; i++) {
        var tds = trs[i].getElementsByTagName("td");
        if (tds.length > 1 && tds[1].innerHTML.trim() == pos) {
            var retd = addDiv(trs[i], parentId);
            retd.getElementsByClassName("del_btn")[0].style.display = "none";
            retd.getElementsByTagName("select")[0].value = defect.type;
            retd.getElementsByTagName("select")[0].disabled = true;
            retd.getElementsByClassName("bug_desc")[0].value = defect.description;
            retd.getElementsByClassName("bug_desc")[0].readOnly = true;
            retd.getElementsByClassName("pos_rec")[0].innerHTML = pos;
            retd.getElementsByClassName("pos_rec")[1].innerHTML = defect.id;
            retd.getElementsByClassName("userId_div")[0].innerHTML = defect.userId + "&nbsp;|&nbsp" + defect.commitTime;
            retd.getElementsByClassName("bug_add")[0].style.display = "none";
            return retd;
        }
    }
}

function CodeMerge() {

    var defects = new Array();
    var count = 0;

    var divs = document.getElementById("launcher_merge").getElementsByClassName("bug_div");
    var n = divs.length;
    for (var i = 0; i < n; i++) {
        var box = divs[i].getElementsByTagName("input")[0];
        if (box.checked == true) {
            defects[count] = new Array();
            var userIdDiv = divs[i].getElementsByClassName("userId_div")[0].innerHTML;
            var sps = userIdDiv.split("|");
            defects[count][0] = sps[0]; // 评审者
            defects[count][1] = divs[i].getElementsByTagName("select")[0].value;      // 缺陷类型
            defects[count][2] = divs[i].getElementsByClassName("bug_desc")[0].value;  // 描述
            defects[count][3] = divs[i].getElementsByClassName("pos_rec")[0].innerHTML; // 行数位置
            defects[count][4] = sps[1];  // commit time
            defects[count][5] = divs[i].getElementsByClassName("pos_rec")[1].innerHTML; // id
            count++;
        }
    }

    if (defects.length > 1) {

        var childs = document.getElementById("defects_parent").getElementsByClassName("def_div");
        if (childs.length > 1) {
            var childlen = childs.length - 1;
            for (var ci = childlen; ci > 0; ci--) {
                childs[ci].parentNode.removeChild(childs[ci]);
            }
        }

        for (var i = 0; i < count; i++) {
            var eachdiv = document.createElement("div");
            eachdiv.innerHTML = document.getElementById("defect_copy").innerHTML;
            eachdiv.setAttribute("class", "def_div");
            eachdiv.getElementsByClassName("line_def")[0].innerHTML = defects[i][3] + "行";
            eachdiv.getElementsByClassName("type_def")[0].innerHTML = defects[i][1];
            eachdiv.getElementsByClassName("describe_def")[0].innerHTML = defects[i][2];

            document.getElementById("defects_parent").appendChild(eachdiv);
            showLaunch("choose");

            eachdiv.onclick = function () {
                var index = $(this).parent().find(".def_div").index($(this)) - 1;
                var jsondef = {
                    "type": defects[index][1], "description": defects[index][2],
                    "userId": defects[index][0], "commitTime": defects[index][4],
                    "id": defects[index][5]
                };
                var retd = addReDefects(defects[index][3], jsondef, "launcher_merge");

                rec_count = 1;
                recordID = defects[index][5];
                recordIDlist[0] = recordID;
                for (var m = 0; m < defects.length; m++) {
                    if (m != index) {
                        recordIDlist[rec_count] = defects[m][5];
                        rec_count++;
                    }
                }

                var mergespan = retd.getElementsByClassName("merge_span")[0]
                mergespan.style.display = "";
                mergespan.innerHTML = "合并共" + count + "缺陷&nbsp;";
                var elemi = document.createElement("i");
                elemi.setAttribute("class", "fa fa-angle-double-down");
                mergespan.appendChild(elemi);

                var bodydiv = document.createElement("div");
                for (var k = 0; k < defects.length; k++) {
                    if (k != index) {
                        var div = document.createElement("div");
                        div.setAttribute("class", "bug_div");
                        div.innerHTML = document.getElementById("bugdiv_id").innerHTML;
                        div.getElementsByClassName("del_btn")[0].style.display = "none";
                        div.getElementsByTagName("select")[0].value = defects[k][1];
                        div.getElementsByTagName("select")[0].disabled = true;
                        div.getElementsByClassName("bug_desc")[0].value = defects[k][2];
                        div.getElementsByClassName("bug_desc")[0].readOnly = true;
                        div.getElementsByClassName("pos_rec")[0].innerHTML = defects[index][3];
                        div.getElementsByClassName("userId_div")[0].innerHTML = defects[k][0] + "&nbsp;|&nbsp" + defects[k][4];
                        div.getElementsByClassName("bug_add")[0].style.display = "none";
                        bodydiv.appendChild(div);
                    }
                }

                mergespan.onclick = function () {
                    var lines = $(this).parents("table").find("tr").index($($(this).parents("tr"))) + 1;

                    if (code_merge_id.getElementsByTagName("tr")[lines].getElementsByClassName("bug_div").length > 0) {
                        code_merge_id.getElementsByTagName("tr")[lines].parentNode.removeChild(code_merge_id.getElementsByTagName("tr")[lines]);
                    } else {
                        var bodyrow = code_merge_id.insertRow(lines);
                        bodyrow.style.width = "100%";
                        bodyrow.style.height = 97 * (defects.length - 1) + "px";
                        var td = document.createElement("td");
                        td.setAttribute("colspan", "3");
                        td.appendChild(bodydiv);
                        bodyrow.appendChild(td);
                    }
                };

                closeLaunch("choose");
            }
        }
    }

    delAll("launcher_merge");
}

// 显示合并的文件
function showMerges(defect, merges, parentId) {

    var retd = addReDefects(defect.lineNum, defect, parentId);

    var mergespan = retd.getElementsByClassName("merge_span")[0]
    mergespan.style.display = "";
    mergespan.innerHTML = "合并共" + merges.length + "缺陷&nbsp;";
    var elemi = document.createElement("i");
    elemi.setAttribute("class", "fa fa-angle-double-down");
    mergespan.appendChild(elemi);

    var bodydiv = document.createElement("div");
    for (var k = 0; k < merges.length; k++) {
        var div = document.createElement("div");
        div.setAttribute("class", "bug_div");
        div.innerHTML = document.getElementById("bugdiv_id").innerHTML;
        div.getElementsByClassName("del_btn")[0].style.display = "none";
        div.getElementsByTagName("select")[0].value = merges[k].type;
        div.getElementsByTagName("select")[0].disabled = true;
        div.getElementsByClassName("bug_desc")[0].value = merges[k].description;
        div.getElementsByClassName("bug_desc")[0].readOnly = true;
        div.getElementsByClassName("pos_rec")[0].innerHTML = merges[k].lineNum;
        div.getElementsByClassName("userId_div")[0].innerHTML = merges[k].userId + "&nbsp;|&nbsp" + merges[k].commitTime;
        div.getElementsByClassName("bug_add")[0].style.display = "none";
        bodydiv.appendChild(div);
    }

    mergespan.onclick = function () {
        var lines = $(this).parents("table").find("tr").index($($(this).parents("tr"))) + 1;

        if (code_merge_id.getElementsByTagName("tr")[lines].getElementsByClassName("bug_div").length > 0) {
            code_merge_id.getElementsByTagName("tr")[lines].parentNode.removeChild(code_merge_id.getElementsByTagName("tr")[lines]);
        } else {
            var bodyrow = code_merge_id.insertRow(lines);
            bodyrow.style.width = "100%";
            bodyrow.style.height = 97 * (merges.length - 1) + "px";
            var td = document.createElement("td");
            td.setAttribute("colspan", "3");
            td.appendChild(bodydiv);
            bodyrow.appendChild(td);
        }
    };
}


// 读取文件夹
function getFile(path) {

    $.ajax({
        type: "post",
        async: false,
        url: "/dir",
        data: {
            "path": path,
        },
        success: function (result) {
            clearTable("file_table_id", 1);
            createFileTable(result);
            refreshDir(result[0].path);
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });
}

// 代码文件
function createCodeTable(result, parentId) {

    clearTable(parentId, 2);

    for (var i = 0; i < result.length; i++) {

        var tr = document.getElementById(parentId).insertRow(document.getElementById(parentId).getElementsByTagName("tr").length - 1);
        tr.style.height = "22px";
        tr.style.verticalAlign = "middle";

        if (parentId == "code_file") {
            tr.setAttribute("onmouseover", "mouseOver(this)");
            tr.setAttribute("onmouseout", "mouseOut(this)");
        }

        var td1 = document.createElement("td");
        td1.setAttribute("class", "code_td");

        if (parentId == "code_file") {
            var elemi = document.createElement("i");
            elemi.setAttribute("class", "fa fa-pencil pencil_style");
            td1.appendChild(elemi);
        }

        tr.appendChild(td1);

        var td2 = document.createElement("td");
        td2.setAttribute("class", "code_td");
        td2.innerHTML = i + 1;
        tr.appendChild(td2);

        var td3 = document.createElement("td");
        td3.style.borderRight = "border-right: 1px solid #dfe0e2";
        var pre = document.createElement("pre");
        pre.innerHTML = result[i];
        td3.appendChild(pre);
        tr.appendChild(td3);
    }
}

// 读取文件内容
function getFileContent(path, parentId) {
    $.ajax({
        type: "post",
        async: false,
        url: "/file",
        data: {"path": path},
        success: function (result) {
            createCodeTable(result, parentId);

            var pathdirs = path.split("/");
            var dir = "";
            for (var i = 1; i < pathdirs.length; i++) {
                dir += (pathdirs[i] + "/");
            }
            refreshDir(dir);
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });
}

// 宏观文件夹
function createFileTable(result) {

    file_table_id.style.display = "";
    code_table_id.style.display = "none";
    code_merge_id.style.display = "none";
    doc_id.style.display = "none";

    for (var i = 0; i < result.length; i++) {

        var tr = document.createElement("tr");
        tr.setAttribute("class", "table_body");

        var td1 = document.createElement("td");
        td1.setAttribute("class", "td1_style");
        var pathname = result[i].path.split("/");
        td1.innerHTML = pathname[pathname.length - 1];
        td1.fileType = result[i].type;
        tr.appendChild(td1);
        td1.onclick = function () {
            gotoDir(this);
        };

        var td2 = document.createElement("td");
        td2.style.width = "19%";
        if (result[i].type != "Dir" && result[i].content == "REVIEWED") {
            var elem_i = document.createElement("i");
            elem_i.setAttribute("class", "fa fa-check green_check");
            td2.appendChild(elem_i);
        } else if (result[i].type == "Dir") {
            td2.innerHTML = result[i].content + "/" + result[i].n;
        }
        tr.appendChild(td2);

        var td3 = document.createElement("td");
        td3.style.width = "19%";
        td3.innerHTML = result[i].n;
        tr.appendChild(td3);

        var td4 = document.createElement("td");
        td4.style.width = "19%";
        td4.innerHTML = result[i].time;
        tr.appendChild(td4);

        file_table_id.appendChild(tr);
    }
}

// 清空表格原有内容 last - 2不删除最后一行; 1删除
function clearTable(elemId, last) {

    var elem = document.getElementById(elemId);
    var trs = elem.getElementsByTagName("tr");
    if (trs.length > last) {
        var n = trs.length - last;
        for (var i = n; i > 0; i--) {
            trs[i].parentNode.removeChild(trs[i]);
        }
    }
}

// 目录
function refreshDir(path) {
    PATH = path;

    var divs = dir_id.getElementsByTagName("div");
    var n = divs.length - 1;
    for (var i = n; i > -1; i--) {
        divs[i].parentNode.removeChild(divs[i]);
    }

    var headdiv = document.createElement("div");
    headdiv.setAttribute("class", "dir_word");
    headdiv.style.fontWeight = "400";
    headdiv.innerHTML = PROJECT_NAME;
    dir_id.appendChild(headdiv);

    headdiv.onclick = function () {
        getFile(PROJECT_ID);
    };

    var divi = document.createElement("div");
    divi.innerHTML = "&nbsp;/&nbsp;";
    divi.style.display = "inline-block";
    dir_id.appendChild(divi);

    var dirs = path.split("/");

    if (dirs.length > 1) {
        for (var i = 0; i < dirs.length - 1; i++) {
            var div = document.createElement("div");

            if (i < dirs.length - 2) {
                div.setAttribute("class", "dir_word");

                div.onclick = function () {
                    gotoWithDir(this.innerHTML)
                }
            } else {
                div.setAttribute("class", "dir_word_last");
            }

            div.innerHTML = dirs[i];
            dir_id.appendChild(div);

            var divi2 = document.createElement("div");
            divi2.innerHTML = "&nbsp;/&nbsp;";
            divi2.style.display = "inline-block";
            dir_id.appendChild(divi2);
        }
    }
}

// 根据表格内目录跳转
function gotoDir(td) {

    var path = PROJECT_ID + "";
    var dirs = dir_id.getElementsByClassName("dir_word");
    if (dirs.length > 1) {
        for (var i = 1; i < dirs.length; i++) {
            path += ("/" + dirs[i].innerHTML);
        }
    }

    var last = dir_id.getElementsByClassName("dir_word_last");
    if (last.length > 0) {
        path += ("/" + dir_id.getElementsByClassName("dir_word_last")[0].innerHTML);
    }
    path += ("/" + td.innerHTML);

    var type = td.fileType;
    if (type == "Dir") {

        getFile(path);

    } else if (type == "Code") {

        file_table_id.style.display = "none";

        // 项目已经结束,只能查看不能操作
        if (STATE == "Over") {

        } else if ((userId == PRO_USERID && ATTEND == "NO") ||
            (userId == PRO_USERID && ATTEND == "YES" && reviewState == "Done")) {
            // 发起者,自己不可以评审,只能查看\合并\评审;

            code_merge_id.style.display = "";
            getFileContent(path, "launcher_merge");
            getAllRecords();

        } else if (userId != PRO_USERID && reviewState == "Done") {
            // 评审者评审已结束,可以查看\合并, 不能审批

            code_merge_id.style.display = "";
            getFileContent(path, "launcher_merge");
            getAllRecords();

        } else {    // 发起者,自己可以评审; 评审者可以评审; 查看自己的评审记录

            code_table_id.style.display = "";
            getFileContent(path, "code_file");
            getSelfRecords();
        }

    } else if (type == "File") {
        file_table_id.style.display = "none";
        doc_id.style.display = "";
    }
}

function gotoWithDir(dirnode) {

    var path = PROJECT_ID;

    var dirs = dir_id.getElementsByClassName("dir_word");
    if (dirs.length > 1) {
        for (var i = 1; i < dirs.length; i++) {
            path += ("/" + dirs[i].innerHTML);

            if (dirnode == dirs[i].innerHTML) {
                break;
            }
        }
    }
    getFile(path);
}

// 返回上一级
function backLast() {

    var path = PROJECT_ID + "";
    var dirs = dir_id.getElementsByClassName("dir_word");
    if (dirs.length > 1) {
        for (var i = 1; i < dirs.length; i++) {
            path += ("/" + dirs[i].innerHTML);
        }
    }
    getFile(path);
}

// 提交评审
function finishCodeReview() {

    //  评审者结束代码评审
    if (code_table_id.style.display != "none") {
        var records = new Array();
        var count = 0;

        var pathName = PATH.substr(0, PATH.length - 1);

        var divs = code_table_id.getElementsByClassName("bug_div");

        if (divs.length > 0) {
            for (var i = 0; i < divs.length; i++) {
                var deftype = divs[i].getElementsByClassName("bug_type")[0].value;
                var defdesc = divs[i].getElementsByClassName("bug_desc")[0].value;
                var defline = divs[i].getElementsByClassName("pos_rec")[0].innerHTML;

                if (deftype == "缺陷类型" || defdesc == "") {
                    slidein(2, "您有缺陷未填写完整");
                    return;
                } else {
                    records[count] = [pathName, defline, deftype, "Code", defdesc].join("&");
                    count++;
                }
            }
        }

        if (count > 0) {
            $.ajax({
                type: "post",
                async: false,
                url: "/addReview",
                data: {
                    "userId": userId,
                    "projectId": PROJECT_ID,
                    "records": records
                },
                success: function (result) {
                    if (result == "SUCCESS") {
                        slidein(0, "提交成功");
                    }
                },
                error: function () {
                    slidein(1, "出故障了请稍候再试");
                }
            });
        }
    } else if (code_merge_id.style.display != "none") {
        //  合并代码提交

        $.ajax({
            type: "post",
            async: false,
            url: "/unionChoose",
            data: {
                "userId": userId,
                "recordId": recordID,
                "records": recordIDlist
            },
            success: function (result) {
                if (result > -1) {
                    slidein(0, "提交成功");
                }
            },
            error: function () {
                slidein(1, "出故障了请稍候再试");
            }
        });
    }
}

// 获取自己的记录
function getSelfRecords() {

    var pathName = PATH.substr(0, PATH.length - 1);
    $.ajax({
        type: "post",
        async: false,
        url: "/getOnlineUserRecordList",
        data: {
            "userID": userId,
            "projectID": PROJECT_ID,
            "path": pathName
        },
        success: function (result) {
            if (result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].state == "正常提交") {

                        addReDefects(result[i].lineNum, result[i], "code_file");

                    } else if (result[i].state == "合并项") {
                        $.ajax({
                            type: "post",
                            async: false,
                            url: "/getChildReview",
                            data: {"reviewId": result[i].id},
                            success: function (res) {

                                showMerges(result[i], res, "code_file");

                            },
                            error: function () {
                                slidein(1, "出故障了请稍候再试");
                            }
                        });

                    }
                }
            }
        },
        error: function () {
            slidein(1, "获取评审记录失败");
        }
    });
}

// 获取所有记录
function getAllRecords() {

    var pathName = PATH.substr(0, PATH.length - 1);
    $.ajax({
        type: "post",
        async: false,
        url: "/getOnlineProjectRecordList",
        data: {
            "projectID": PROJECT_ID,
            "path": pathName
        },
        success: function (result) {

            if (result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].state == "正常提交") {

                        addReDefects(result[i].lineNum, result[i], "launcher_merge");

                    } else if (result[i].state == "合并项") {

                        $.ajax({
                            type: "post",
                            async: false,
                            url: "/getChildReview",
                            data: {"reviewId": result[i].id},
                            success: function (res) {

                                showMerges(result[i], res, "launcher_merge");

                            },
                            error: function () {
                                slidein(1, "出故障了请稍候再试");
                            }
                        });

                    }
                }
            }
        },
        error: function () {
            slidein(1, "获取评审记录失败");
        }
    });

}

// 发起者结束项目
function finishPro() {
    $.ajax({
        type: "post",
        async: false,
        url: "/confirmReview",
        data: {
            "projectID": PROJECT_ID
        },
        success: function (result) {
            if (result == "SUCCESS") {
                slidein(0, "项目已结束");
            }
        },
        error: function () {
            slidein(1, "获取评审记录失败");
        }
    });
}

// 审批者结束项目
function completeReview() {
    $.ajax({
        type: "post",
        async: false,
        url: "/endReview",
        data: {
            "userId": userId,
            "projectID": PROJECT_ID
        },
        success: function (result) {
            if (result == "SUCCESS") {
                document.getElementById("feed_btn").style.display = "";
                slidein(0, "已结束该项目的评审");
            }
        },
        error: function () {
            slidein(1, "获取评审记录失败");
        }
    });
}

// 项目质量查看
function checkQuality() {
    window.location.href = "/pages/feedBack?projectId=" + PROJECT_ID;
}

//下载项目压缩文件
function downAllFile() {
    var form = $("<form>");//定义一个form表单
    form.attr("style", "display:none");
    form.attr("target", "");
    form.attr("method", "post");
    form.attr("action", "/download");
    var input1 = $("<input>");
    input1.attr("type", "hidden");
    input1.attr("name", "fileName");

    var path = PROJECT_ID;
    input1.attr("value", path);//路径名
    $("body").append(form);//将表单放置在web中
    form.append(input1);

    form.submit();//表单提交
}