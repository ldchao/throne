/**
 * Created by marioquer on 16/7/13.
 */
var form;
var defect;
var state = document.getElementById("p-state").innerHTML;
var userId = document.getElementById("storage").innerHTML;//当前用户
var owner = document.getElementById("owner").innerHTML;//项目拥有者
var yesNo = document.getElementById("yesNo").innerHTML;//是否自己参与评审
var projectId = document.getElementById("p-id").innerHTML;
var detail = {

    judge: function () {

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

    init: function () {
        form = document.getElementById("init-form").innerHTML;
        defect = document.getElementById("all-defect").innerHTML;

        $.ajax({
            type: "post",
            async: true,
            url: "/getPersonReviewList",
            data: {"userId": userId, "projectID": projectId},
            success: function (result) {
                if (result.length > 0) {
                    document.getElementById("all-defect").style.display = "block";
                    for (var i = 0; i < result.length; i++) {
                        addDefect(result[i]);
                    }
                }
            },
            error: function () {
                slidein(1, "获取数据失败");
            }
        });

        detail.judge();
    }
}

function addDefect(list) {
    var newDefect = document.createElement("div");
    newDefect.innerHTML = defect;
    var allDefect = document.getElementById("all-defect")
    for (var i = 0; i < list.length - 1; i++) {
        allDefect.appendChild(newDefect);
    }
    var singleDefect = allDefect.getElementsByClassName("exist-form");
    for (var i = 0; i < list.length; i++) {
        for (var j = 0; j < list[i].length - 1; j++) {
            singleDefect[i].getElementsByClassName("head-text")[j] = list[i][j];
        }
        singleDefect[i].getElementsByClassName("info-bottom")[0] = list[i][3];
    }
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
        //alert(secondLine.innerHTML+"Inner")
        //alert(secondLine.value + "value")
        resultList[i] = singleResult;
        alert(resultList);
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

$(document).ready(function () {
    detail.init();
});

