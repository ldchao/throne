/**
 * Created by L.H.S on 16/7/20.
 */

var file_table_id = document.getElementById("file_table_id");
var PROJECT_ID = document.getElementById("storage_proId").innerHTML.trim();

// 代码评审合并
window.onload = function () {

    // addReDefects(2);
    // addReDefects(6);
    // addReDefects(9);

    getFile(PROJECT_ID + "");
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
    td.appendChild(div);

    var addbtn = div.getElementsByClassName("bug_add")[0];
    addbtn.onclick = function () {
        addDiv(row, parentId);
    };

    var checkbox = div.getElementsByTagName("input")[0];
    checkbox.onclick = function () {
        if (checkbox.checked == false)
            document.getElementById("selectAll").checked = false;
    }

    var delbtn = div.getElementsByClassName("del_btn")[0];
    delbtn.onclick = function () {
        var t = $(this).parents("tr");
        var pos = $(t).parents(".file_table").find("tr").index($(t));
        document.getElementById(parentId).deleteRow(pos);
    };

    row.appendChild(td);
    document.getElementById("selectAll").checked = false;

    return td;
}

function selectAll(parentId) {

    var divs = document.getElementById(parentId).getElementsByClassName("bug_div");
    for (var i = 0; i < divs.length; i++) {
        var box = divs[i].getElementsByTagName("input")[0];
        if (parentId == "code_file") {
            box.checked = document.getElementById("selectAll").checked;
        } else {
            box.checked = document.getElementById("selectAll_merge").checked;
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

// 合并缺陷
function addReDefects(pos) {

    var table = document.getElementById("launcher_merge");
    var trs = table.getElementsByTagName("tr");
    for (var i = 0; i < trs.length; i++) {
        var tds = trs[i].getElementsByTagName("td");
        if (tds.length > 1 && tds[1].innerHTML == pos) {
            var retd = addDiv(trs[i], "launcher_merge");
            retd.getElementsByClassName("del_btn")[0].style.display = "none";
            retd.getElementsByTagName("select")[0].value = "空指针";
            retd.getElementsByTagName("select")[0].disabled = true;
            retd.getElementsByClassName("bug_desc")[0].value = "这是一个空指针缺陷" + tds[1].innerHTML;
            retd.getElementsByClassName("bug_desc")[0].readOnly = true;
            retd.getElementsByClassName("pos_rec")[0].innerHTML = tds[1].innerHTML;
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
            defects[count][0] = divs[i].getElementsByClassName("userId_div")[0].innerHTML; // 评审者
            defects[count][1] = divs[i].getElementsByTagName("select")[0].value;      // 缺陷类型
            defects[count][2] = divs[i].getElementsByClassName("bug_desc")[0].value;  // 描述
            defects[count][3] = divs[i].getElementsByClassName("pos_rec")[0].innerHTML; // 行数位置
            count++;
        }
    }

    if (defects.length > 1) {
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
                var retd = addReDefects(defects[index][3]);
                retd.getElementsByClassName("merge_span")[0].style.display = "";

                closeLaunch("choose");
            }
        }
    }

    delAll("launcher_merge");
}

// 读取文件夹
function getFile(path) {

    $.ajax({
        type: "post",
        async: false,
        url: "/dir/" + path,
        success: function (result) {
            createFileTable(result);
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });
}

// 读取文件内容
function getFileContent(path) {
    $.ajax({
        type: "post",
        async: false,
        url: "/dir/" + path,
        success: function (result) {
            createFileTable(result);
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });
}

function createFileTable(result) {
    for (var i = 0; i < result.length; i++) {

        var tr = document.createElement("tr");
        tr.setAttribute("class", "table_body");

        var td1 = document.createElement("td");
        td1.setAttribute("class", "td1_style");
        alert( result[i].path);
        var pathname = result[i].path.split("/");
        td1.innerHTML = pathname[pathname.length - 1];
        tr.appendChild(td1);

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