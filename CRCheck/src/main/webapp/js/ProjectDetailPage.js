/**
 * Created by marioquer on 16/7/13.
 */
var form;
var defect;
var state = document.getElementById("p-state").innerHTML.trim();
var userId = document.getElementById("storage").innerHTML.trim();//当前用户
var owner = document.getElementById("owner").innerHTML.trim();//项目拥有者
var yesNo = document.getElementById("yesNo").innerHTML.trim();//是否自己参与评审
var projectId = document.getElementById("p-id").innerHTML.trim();
var detail = {


    init: function () {
        defect = document.getElementById("exist_copy").innerHTML;

        if (owner == userId) {
            $.ajax({
                type: "post",
                async: false,
                url: "/getSummaryReview",
                data: {"projectID": projectId},
                success: function (result) {
                    if (result.length > 0) {
                        var allDefect = document.getElementById("all-defect");
                        allDefect.style.display = "block";
                        for (var i = 0; i < result.length; i++) {
                            addDefect(result[i]);
                        }
                    }
                },
                error: function () {
                    slidein(1, "获取数据失败");
                }
            });
        } else {
            form = document.getElementById("init-form").innerHTML;
            $.ajax({
                type: "post",
                async: false,
                url: "/getPersonReviewList",
                data: {"userId": userId, "projectID": projectId},
                success: function (result) {
                    if (result.length > 0) {
                        var allDefect = document.getElementById("all-defect");
                        allDefect.style.display = "block";
                        for (var i = 0; i < result.length; i++) {
                            addDefect(result[i]);
                        }
                    }
                    var button = document.getElementById("begin");
                    switch (state) {
                        case "NotStart":
                            button.style.backgroundColor = "#D47859";
                            button.innerHTML = "尚未开始评审";
                            button.style.cursor = "default";
                            button.style.readOnly = true;
                            break;
                        case "Over":
                            button.style.backgroundColor = "#6B6F78";
                            button.innerHTML = "无法继续评审";
                            button.style.cursor = "default";
                            button.style.readOnly = true;
                            break;
                    }
                },
                error: function () {
                    slidein(1, "获取数据失败");
                }
            });
        }


    }
}

function addDefect(list) {
    var allDefect = document.getElementById("all-defect");

    var newDefect = document.createElement("div");
    newDefect.innerHTML = defect;
    newDefect.setAttribute("class", "exist-form");
    newDefect.style.marginTop = "20px";
    newDefect.getElementsByClassName("head-text")[0].innerHTML = list.path + ":";
    newDefect.getElementsByClassName("head-text")[1].innerHTML = list.lineNum + "行";
    newDefect.getElementsByClassName("head-text")[2].innerHTML = list.type;
    newDefect.getElementsByClassName("info-bottom")[0].innerHTML = list.description;
    allDefect.appendChild(newDefect);
}

function addForm() {
    var newForm = document.createElement("div");
    newForm.innerHTML = form;
    document.getElementById("init-form").appendChild(newForm);
}

function deleteForm(node) {
    $(node).parent().parent().remove();
}

function beginReview() {
    document.getElementById("review-block").style.display = "block";
    document.getElementById("begin").style.display = "none";
}

function publishForm() {
    var formList = document.getElementsByClassName("form-empty");
    var resultList = new Array();
    for (var i = 0; i < formList.length; i++) {


        var textfields = formList[i].getElementsByClassName("textfield");
        var singleResult = "";
        for (var j = 0; j < 3; j++) {
            singleResult += textfields[j].value + "&";
        }
        singleResult += textfields[3].value;
        resultList[i] = singleResult;
    }

    $.ajax({
        type: "post",
        async: false,
        url: "/addReview",
        data: {
            "userId": userId,
            "projectId": projectId,
            "records": resultList
        },
        success: function (result) {
            if (result == "SUCCESS") {
                slidein(0, "提交成功");
                setTimeout("window.location.reload()", 1800);
            } else {
                slidein(1, "提交失败请稍候再试");
            }
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });
}

function endReview(){
    publishForm();
    $.ajax({
        type: "post",
        async: false,
        url: "/endReview",
        data: {
            "userId": userId,
            "projectID": projectId,
        },
        success: function (result) {
            if (result == "SUCCESS") {
                slidein(0, "提交成功");
                setTimeout("window.location.reload()", 1800);
            } else {
                slidein(1, "提交失败请稍候再试");
            }
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });
}

$(document).ready(function () {
    detail.init();
});

