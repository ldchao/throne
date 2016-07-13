/**
 * Created by L.H.S on 16/7/8.
 */

function showIntroduce() {
    $("#introduce_child").slideToggle();
}

var iBase = {
    SetOpacity: function (ev, v) {
        ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')' : ev.style.opacity = v / 100;
    }
}

function showLaunch(elem_id) {
    var elem = document.getElementById(elem_id);
    var speed = 12;
    var opacity = 100;

    elem.style.display = "block";
    iBase.SetOpacity(elem, 0);
    var val = 0;
    (function () {
        iBase.SetOpacity(elem, val);
        val += 5;
        if (val <= opacity) {
            setTimeout(arguments.callee, speed)
        }
    })();

}

function closeLaunch(elem_id) {
    var elem = document.getElementById(elem_id);
    var speed = 15;
    var opacity = 0;

    var val = 100;
    (function () {
        iBase.SetOpacity(elem, val);
        val -= 5;
        if (val >= opacity) {
            setTimeout(arguments.callee, speed);
        } else if (val < 0) {
            elem.style.display = 'none';
        }
    })();
}

// 随机八个用户
function setIds() {

    for (var i = 0; i < 8; i++) {
        var ids = "id" + (i + "");
        document.getElementById(ids).innerHTML = ids;
    }
}

// 添加选择的用户
function addIds(index) {

    var selectId = "id" + (index + "");
    var selectImg = "img" + (index + "");

    var elem_img = document.getElementById(selectImg)
    if(elem_img.getAttribute("class") == "img_each_after"){
        removeIds(index);
        return;
    }

    elem_img.setAttribute("class", "img_each_after");

    var eachdiv = document.createElement("div");
    var eachdiv_id = "div" + (index + "");
    eachdiv.setAttribute("id", eachdiv_id);
    eachdiv.setAttribute("class", "div_each");
    eachdiv.setAttribute("onclick", "removeIds(" + index + ")");

    var eachimg = document.createElement("div");
    eachimg.setAttribute("class", "img_each_selected");

    var eachid = document.createElement("div");
    eachid.setAttribute("class", "id_each");
    eachid.innerHTML = selectId;

    eachdiv.appendChild(eachimg);
    eachdiv.appendChild(eachid);

    var refnode = document.getElementById("selectedIds_div").getElementsByTagName("div");
    document.getElementById("selectedIds_div").insertBefore(eachdiv, refnode[0]);

}

// 删除已选用户
function removeIds(index) {
    var divid = "div" + (index + "");
    var elem = document.getElementById(divid);
    document.getElementById("selectedIds_div").removeChild(elem);
    var selectImg = "img" + (index + "");
    document.getElementById(selectImg).setAttribute("class", "img_each");
}

