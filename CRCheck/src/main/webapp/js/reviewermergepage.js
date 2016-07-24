/**
 * Created by L.H.S on 16/7/22.
 */

window.onload = function () {

    var defects = new Array();
    var reviewers = ["sure", "marioquer", "chao"];


    for (var i = 0; i < 5; i++) {
        defects[i] = ["CRC/src/java/" + ('a' + i) + ".java", (i + 1) * 10 + i + "行", "语法错误", reviewers[i % 3],
            "这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺" +
            "陷好傻啊啊这个缺陷好傻啊啊" +
            "这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷" +
            "好傻啊啊这个缺陷好傻啊", i];
    }

    for (var i = 0; i < defects.length; i++) {
        if (i % 3 == 0) {

            // 合并缺陷的ajax, 二维数组
            var merges = new Array();
            for (var j = 0; j < 2; j++) {
                merges[j] = ["CRC/src/java/" + ('a' + i) + ".java", (i + 1) * 10 + i + j + 2 + "行", "语法错误", reviewers[i % 3],
                    "这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺" +
                    "陷好傻啊啊这个缺陷好傻啊啊" +
                    "这个缺陷好傻啊啊这个缺陷好傻啊啊这个缺陷" +
                    "好傻啊啊这个缺陷好傻啊", -1];
            }

            addMerge(defects[i], merges);
        } else {
            addSingle(defects[i]);
        }
    }
}

function addSingle(defect) {

    var div = document.createElement("div");
    div.innerHTML = document.getElementById("exist_copy").innerHTML;

    var hedatexts = div.getElementsByClassName("head-text");
    hedatexts[0].innerHTML = defect[0];
    hedatexts[1].innerHTML = defect[1];
    hedatexts[2].innerHTML = defect[2];
    div.getElementsByClassName("who_name")[0].innerHTML = defect[3];
    div.getElementsByClassName("info-bottom")[0].innerHTML = defect[4];

    div.onmouseenter = function () {
        showCheck(this, 0);
    }

    div.onmouseleave = function () {
        hideCheck(this, 0);
    }

    document.getElementById("all-defect").appendChild(div);
}

function addMerge(defect, merges) {

    var headdiv = document.createElement("div");
    headdiv.innerHTML = document.getElementById("exist_copy_2").innerHTML;
    headdiv.getElementsByClassName("info-head_2")[0].style.backgroundColor = "#f3f3df";

    var hedatexts = headdiv.getElementsByClassName("head-text");
    hedatexts[0].innerHTML = defect[0];
    hedatexts[1].innerHTML = defect[1];
    hedatexts[2].innerHTML = defect[2];
    headdiv.getElementsByClassName("who_name")[0].innerHTML = defect[3];
    headdiv.getElementsByClassName("info-bottom_2")[0].innerHTML = defect[4];

    headdiv.onmouseenter = function () {
        showCheck(this, 1);
    }

    headdiv.onmouseleave = function () {
        hideCheck(this, 1);
    }

    document.getElementById("all-defect").appendChild(headdiv);

    var bodydiv = document.createElement("div");
    bodydiv.style.display = "none";
    for (var i = 0; i < merges.length; i++) {
        var div = document.createElement("div");
        div.innerHTML = document.getElementById("exist_copy_2").innerHTML;
        div.style.marginTop = "-15px";
        div.getElementsByClassName("merge_span")[0].style.display = "none";

        var innertexts = div.getElementsByClassName("head-text");
        innertexts[0].innerHTML = merges[i][0];
        innertexts[1].innerHTML = merges[i][1] + "行";
        innertexts[2].innerHTML = merges[i][2];
        div.getElementsByClassName("who_name")[0].innerHTML = merges[i][3];
        div.getElementsByClassName("info-bottom_2")[0].innerHTML = merges[i][4];

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
                addMerge(headdef, defects);
                closeLaunch("choose");
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
        // if (checkbox.checked == true && 是已经合并的) {
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
        addSingle(defects[i]);
    }
}