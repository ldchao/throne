/**
 * Created by L.H.S on 16/7/22.
 */

window.onload = function () {

    var proId = document.getElementById("storage_proId").innerHTML;
    proId = proId.trim();
    $.ajax({
        type: "post",
        async: false,
        url: "/getSummaryReview",
        data: {"projectID": proId},
        success: function (result) {

            for (var i = 0; i < result.length; i++) {
                if (result[i].state == "正常提交") {
                    result[i].lineNum += "行";
                    addSingle(result[i]);
                } else if (result[i].state == "合并项") {

                    $.ajax({
                        type: "post",
                        async: false,
                        url: "/getChildReview",
                        data: {"reviewId": result[i].id},
                        success: function (res) {
                            for (var j = 0; j < res.length; j++) {
                                res[j].lineNum += "行";
                            }
                            addMerge(result[i], res);
                        },
                        error: function () {
                            slidein(1, "出故障了请稍候再试");
                        }
                    });

                }
            }
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });
};

function addSingle(singleDef) {

    var div = document.createElement("div");
    div.innerHTML = document.getElementById("exist_copy").innerHTML;

    var headtexts = div.getElementsByClassName("head-text");
    headtexts[0].innerHTML = singleDef.path;
    headtexts[1].innerHTML = singleDef.lineNum;
    headtexts[2].innerHTML = singleDef.type;
    div.getElementsByClassName("who_name")[0].innerHTML = singleDef.userId;
    div.getElementsByClassName("info-bottom")[0].innerHTML = singleDef.description;
    div.getElementsByClassName("recordId")[0].innerHTML = singleDef.id;

    div.onmouseenter = function () {
        showCheck(this, 0);
    };

    div.onmouseleave = function () {
        hideCheck(this, 0);
    };

    document.getElementById("all-defect").appendChild(div);
}

function addMerge(singleDef, mergeDef) {

    var headdiv = document.createElement("div");
    headdiv.innerHTML = document.getElementById("exist_copy_2").innerHTML;
    headdiv.getElementsByClassName("info-head_2")[0].style.backgroundColor = "#f3f3df";

    var headtexts = headdiv.getElementsByClassName("head-text");
    headtexts[0].innerHTML = singleDef.path;
    headtexts[1].innerHTML = singleDef.lineNum;
    headtexts[2].innerHTML = singleDef.type;
    headdiv.getElementsByClassName("who_name")[0].innerHTML = singleDef.userId;
    headdiv.getElementsByClassName("info-bottom_2")[0].innerHTML = singleDef.description;
    headdiv.getElementsByClassName("merge_span")[0].innerHTML = "共合并" + mergeDef.length + "缺陷";
    headdiv.getElementsByClassName("recordId")[0].innerHTML = singleDef.id;

    headdiv.onmouseenter = function () {
        showCheck(this, 1);
    }

    headdiv.onmouseleave = function () {
        hideCheck(this, 1);
    }

    document.getElementById("all-defect").appendChild(headdiv);

    var bodydiv = document.createElement("div");
    bodydiv.style.display = "none";

    for (var i = 0; i < mergeDef.length; i++) {
        var div = document.createElement("div");
        div.innerHTML = document.getElementById("exist_copy_2").innerHTML;
        div.style.marginTop = "-15px";
        div.getElementsByClassName("merge_span")[0].style.display = "none";

        var texts = div.getElementsByClassName("head-text");
        texts[0].innerHTML = mergeDef[i].path;
        texts[1].innerHTML = mergeDef[i].lineNum + "行";
        texts[2].innerHTML = mergeDef[i].type;
        div.getElementsByClassName("who_name")[0].innerHTML = mergeDef[i].userId;
        div.getElementsByClassName("info-bottom_2")[0].innerHTML = mergeDef[i].description;
        div.getElementsByClassName("recordId")[0].innerHTML = mergeDef[i].id;

        div.onmouseenter = function () {
            showCheck(this, 2);
        };
        div.onmouseleave = function () {
            hideCheck(this, 2);
        };

        bodydiv.appendChild(div);
    }

    document.getElementById("all-defect").appendChild(bodydiv);

    var spread = headdiv.getElementsByClassName("merge_span")[0];
    spread.onclick = function () {
        if (bodydiv.style.display == "none") {
            $(bodydiv).show();
        } else {
            $(bodydiv).hide();
        }
    }
}

function showCheck(parentDiv) {
    var slidediv = parentDiv.getElementsByClassName("left_slide")[0];
    $(slidediv).show(200);
}

function hideCheck(parentDiv) {
    var slidediv = parentDiv.getElementsByClassName("left_slide")[0];
    if (slidediv.getElementsByClassName("merge_box")[0].checked != true) {
        $(slidediv).hide(200);
    }
}

// 合并
function Merge() {

    var recId = new Array();
    var defects = new Array();
    var pos = new Array();
    var count = 0;

    var divs = document.getElementById("all-defect").getElementsByClassName("exist-form");

    for (var i = 0; i < divs.length; i++) {
        var checkbox = divs[i].getElementsByClassName("merge_box")[0];
        if (checkbox.checked == true) {
            pos[count] = i;
            var texts = divs[i].getElementsByClassName("head-text");
            defects[count] = new Array();
            defects[count][0] = texts[0].innerHTML;
            defects[count][1] = texts[1].innerHTML;
            defects[count][2] = texts[2].innerHTML;
            defects[count][3] = divs[i].getElementsByClassName("who_name")[0].innerHTML;
            if (divs[i].getElementsByClassName("info-bottom").length != 0) {
                defects[count][4] = divs[i].getElementsByClassName("info-bottom")[0].innerHTML;
            } else {
                defects[count][4] = divs[i].getElementsByClassName("info-bottom_2")[0].innerHTML;
            }
            defects[count][5] = divs[i].getElementsByClassName("recordId")[0].innerHTML;
            count++;
        }
    }

    if (defects.length > 1) {
        for (var i = 0; i < count; i++) {
            var eachdiv = document.createElement("div");
            eachdiv.innerHTML = document.getElementById("defect_copy").innerHTML;
            eachdiv.setAttribute("class", "def_div");
            eachdiv.getElementsByClassName("line_def")[0].innerHTML = defects[i][1];
            eachdiv.getElementsByClassName("type_def")[0].innerHTML = defects[i][2];
            eachdiv.getElementsByClassName("describe_def")[0].innerHTML = defects[i][4];

            document.getElementById("defects_parent").appendChild(eachdiv);
            showLaunch("choose");

            eachdiv.onclick = function () {

                for (var j = count - 1; j > -1; j--) {
                    divs[pos[j]].parentNode.removeChild(divs[pos[j]]);
                }

                var index = $(this).parent().find(".def_div").index($(this)) - 1;
                var headdef = defects[index];
                defects.splice(index, 1);

                var singledef = {
                    "path": headdef[0], "lineNum": headdef[1],
                    "type": headdef[2], "userId": headdef[3], "description": headdef[4],
                    "id": headdef[5]
                };
                recId[0] = singledef.id;

                var mergedef = new Array();
                for (var k = 0; k < defects.length; k++) {
                    mergedef[k] = {
                        "path": defects[k][0], "lineNum": defects[k][1],
                        "type": defects[k][2], "userId": defects[k][3], "description": defects[k][4],
                        "id": defects[k][5]
                    };
                    recId[k + 1] = defects[k][5];
                }

                var proId = document.getElementById("storage_proId").innerHTML;
                proId = proId.trim();
                var userId = document.getElementById("storage").innerHTML;
                userId = userId.trim();
                $.ajax({
                    type: "post",
                    async: true,
                    url: "/unionChoose",
                    data: {
                        "userId": userId,
                        "recordId": proId,
                        "records": recId
                    },
                    success: function (result) {
                        if(result == "SUCCESS") {
                            slidein(0, "提交成功");
                            addMerge(singledef, mergedef);
                            closeLaunch("choose");
                        }
                    },
                    error: function () {
                        slidein(1, "出故障了请稍候再试");
                    }
                });

            }
        }
    }

}

function UndoMerge() {

    var defects = new Array();
    var pos = new Array();
    var count = 0;

    var divs = document.getElementById("all-defect").getElementsByClassName("exist-form");

    for (var i = 0; i < divs.length; i++) {
        var checkbox = divs[i].getElementsByClassName("merge_box")[0];

        if (checkbox.checked == true) {
            pos[count] = i;
            var texts = divs[i].getElementsByClassName("head-text");
            defects[count] = new Array();
            defects[count][0] = texts[0].innerHTML;
            defects[count][1] = texts[1].innerHTML;
            defects[count][2] = texts[2].innerHTML;
            defects[count][3] = divs[i].getElementsByClassName("who_name")[0].innerHTML;
            if (divs[i].getElementsByClassName("info-bottom").length != 0) {
                defects[count][4] = divs[i].getElementsByClassName("info-bottom")[0].innerHTML;
            } else {
                defects[count][4] = divs[i].getElementsByClassName("info-bottom_2")[0].innerHTML;
            }
            count++;
        }
    }

    for (var i = count - 1; i > -1; i--) {
        divs[pos[i]].parentNode.removeChild(divs[pos[i]]);
    }

    for (var i = 0; i < count; i++) {
        var singledef = {
            "path": defects[i][0], "lineNum": defects[i][1],
            "type": defects[i][2], "userId": defects[i][3], "description": defects[i][4]
        };
        addSingle(singledef);
    }
}

function finishMerge() {

}