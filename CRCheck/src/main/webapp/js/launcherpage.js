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

            for(var i=0; i<result.length; i++) {
                if(result[i].state == "正常提交") {
                    addSingle(result[i]);
                } else if(result[i].state == "合并项") {

                    $.ajax({
                        type: "post",
                        async: false,
                        url: "/getChildReview",
                        data: {"reviewId": result[i].id},
                        success: function (res) {
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
    headtexts[1].innerHTML = singleDef.lineNum + "行";
    headtexts[2].innerHTML = singleDef.type;
    div.getElementsByClassName("who_name")[0].innerHTML = singleDef.userId;
    div.getElementsByClassName("info-bottom")[0].innerHTML = singleDef.description;

    div.onmouseenter = function () {
        showCheck(this, 0);
    }

    div.onmouseleave = function () {
        hideCheck(this, 0);
    }

    document.getElementById("all-defect").appendChild(div);
}

function addMerge(singleDef, mergeDef) {

    var headdiv = document.createElement("div");
    headdiv.innerHTML = document.getElementById("exist_copy_2").innerHTML;
    headdiv.getElementsByClassName("info-head_2")[0].style.backgroundColor = "#f3f3df";

    var headtexts = headdiv.getElementsByClassName("head-text");
    headtexts[0].innerHTML = singleDef.path;
    headtexts[1].innerHTML = singleDef.lineNum + "行";
    headtexts[2].innerHTML = singleDef.type;
    headdiv.getElementsByClassName("who_name")[0].innerHTML = singleDef.userId;
    headdiv.getElementsByClassName("info-bottom_2")[0].innerHTML = singleDef.description;
    headdiv.getElementsByClassName("merge_span")[0].innerHTML = "共合并" + mergeDef.length + "缺陷";
    var elemi = document.createElement("i");
    elemi.setAttribute("class", "fa fa-angle-double-down");
    headdiv.getElementsByClassName("merge_span")[0].appendChild(elemi);

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
        texts[0].innerHTML = singleDef.path;
        texts[1].innerHTML = singleDef.lineNum + "行";
        texts[2].innerHTML = singleDef.type;
        div.getElementsByClassName("who_name")[0].innerHTML = singleDef.userId;
        div.getElementsByClassName("info-bottom_2")[0].innerHTML = singleDef.description;

        
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

function showCheck(parentDiv, isSin) {

    var slidediv = parentDiv.getElementsByClassName("left_slide")[0];
    slidediv.style.height = parentDiv.offsetHeight;
    $(slidediv).show(200);

    var correct = slidediv.getElementsByClassName("check_btn")[0];
    correct.onclick = function () {
        Correct(parentDiv, 0, isSin);
    }

    var error = slidediv.getElementsByClassName("times_btn")[0];
    error.onclick = function () {
        Correct(parentDiv, 1, isSin);
    }
}

function hideCheck(parentDiv) {
    var slidediv = parentDiv.getElementsByClassName("left_slide")[0];
    $(slidediv).hide(200);
}

// isCor 0正确, 1错误; isSin 0单个, 1合并, 2合并不要顶栏颜色
function Correct(parentDiv, isCor, isSin) {

    var classname = ["class-intent", "class-intent_2", "class-intent_2"];
    var bgcolor = ["#f3f9f5", "f9f3f3"];
    var fontcolor = ["#376b48", "#8f5757"];

    var div = parentDiv.getElementsByClassName(classname[isSin])[0];
    div.style.backgroundColor = bgcolor[isCor];

    var ps = div.getElementsByTagName("p");
    for (var i = 0; i < ps.length; i++) {
        ps[i].style.color = fontcolor[isCor];
    }

    var headcolor = ["#E2F6E9", "#FAE8E8"];
    if (isSin == 1) {
        div.getElementsByClassName("info-head_2")[0].style.backgroundColor = headcolor[isCor];
    }
}

function mergeDefects() {
    var proId = document.getElementById("storage_proId").innerHTML;
    proId = proId.trim();
    window.location.href = "/pages/merge?projectID=" + proId;
}