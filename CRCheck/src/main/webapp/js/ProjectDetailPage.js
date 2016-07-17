/**
 * Created by marioquer on 16/7/13.
 */
var form;
var state = document.getElementById("p-state").innerHTML;
var userId = document.getElementById("storage").innerHTML;//当前用户
var owner = document.getElementById("owner").innerHTML;//项目拥有者
var yesNo = document.getElementById("yesNo").innerHTML;//是否自己参与评审
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

        //$.ajax({
        //    type: "get",
        //    async: true,
        //    url: "/AllAttendProjects",
        //    data: {"userId": userId},
        //    success: function (result) {
        //        for (var i = 0; i < result.length; i++) {
        //            addCRCpro(result[i]);
        //        }
        //    },
        //    error: function () {
        //        slidein(1, "获取数据失败");
        //    }
        //});

        detail.judge();
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
        var firstLine = formList[i].childNodes[1].childNodes;
        var secondLine = firstLine.nextSibling;
        var singleResult = new Array(4);
        for (var j = 0;j<3;j++){
            singleResult[i] = firstLine[i].value;
        }
        singleResult[3] = secondLine.value;
        resultList[i] = singleResult;
    }

    $.ajax({
        type: "post",
        async: false,
        url: "/Launch",
        data: {
            "resultList": resultList,
        },
        success: function (result) {
            if (result > -1) {
                slidein(0, "提交成功");
                setTimeout("goTo(" + result + ")", 1800);
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

