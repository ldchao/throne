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
        form = document.getElementById("init-form").innerHTML;

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
            // form = document.getElementById("init-form").innerHTML;
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


        $.ajax({
            type: "post",
            async: false,
            url: "/checkReviewState",
            data: {"userID": userId, "projectID": projectId},
            success: function (result) {
                if (result == "Done") {
                    document.getElementById("endButtons").style.display = "block";
                    document.getElementById("begin").style.display = "none";
                    document.getElementById("end").style.display = "none";
                } else {
                    if (state != "Over") {
                        document.getElementById("end").style.display = "inline-block";
                    } else {
                        document.getElementById("end").style.display = "none";
                    }
                }
            },
            error: function () {
                slidein(1, "出错了")
            }
        });
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
    document.getElementById("end").style.display = "none";
    document.getElementById("init-form").appendChild(newForm);
    sequenceNum();
}

function sequenceNum() {
    var forms = document.getElementsByClassName("num");
    for (var i = 0; i < forms.length; i++) {
        forms[i].innerHTML = i;
    }
}

function deleteForm(node) {
    $(node).parent().parent().remove();
    sequenceNum();
}

function beginReview() {
    document.getElementById("review-block").style.display = "block";
    document.getElementById("begin").style.display = "none";
    document.getElementById("end").style.display = "none";

}

function publishForm() {
    var isEmpty = false;
    var formList = document.getElementsByClassName("form-empty");
    var resultList = new Array();
    for (var i = 0; i < formList.length; i++) {


        var textfields = formList[i].getElementsByClassName("textfield");
        var singleResult = "";

        for (var m = 0; m < 4; m++) {
            if (textfields[m].value == null || textfields[m].value.trim() == "") {
                isEmpty = true;
            }
        }

        for (var j = 0; j < 3; j++) {
            singleResult += textfields[j].value + "&";
        }
        singleResult += textfields[3].value;
        resultList[i] = singleResult;
    }

    if (!isEmpty) {
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

                    var type1;
                    if(document.getElementById("type-select").value == "代码评审"){
                        type1 = "Code";
                    }else if(document.getElementById("type-select").value == "文档评审"){
                        type1 = "File";
                    }

                    $.ajax({
                        type: "post",
                        async: false,
                        url: "/commit",
                        data: {
                            "userId": userId,
                            "projectId": projectId,
                            "codeLine": document.getElementById("line-num").value,
                            "reviewType":type1,
                            "time": document.getElementById("time-num").value
                        },
                        success: function (result) {
                            if (result == "SUCCESS") {
                            } else {
                                slidein(1, "提交失败请稍候再试");
                            }
                        },
                        error: function () {
                            slidein(1, "出故障了请稍候再试");
                        }
                    });




                } else {
                    slidein(1, "提交失败请稍候再试");
                }
            },
            error: function () {
                slidein(1, "出故障了请稍候再试");
            }
        });
    } else {
        slidein(1, "您的信息未填写完整");
    }




}

function endReview() {
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
                document.getElementById("endButtons").style.display = "";
                document.getElementById("finish_before").style.display = "none";
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

function mergeDefects() {
    window.location.href = "/pages/merge?projectID=" + projectId;
}

function checkQuality() {
    window.location.href = "/pages/feedBack?projectId=" + projectId;
}
