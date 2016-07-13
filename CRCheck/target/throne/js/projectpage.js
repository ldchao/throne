/**
 * Created by L.H.S on 16/7/8.
 */
var idlist = new Array();  // 记录已经添加的用户
var idcount = 0;  // 下标
var currentids = new Array();  // 记录当前的8个随机用户

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

    $.ajax({
        type: "get",
        async: false,
        url: "/getUserList",
        success: function (result) {
            currentids = result.userList;
        },
        error: function () {
            alert("失败")
        }
    });

    for (var i = 0; i < 8; i++) {

        var ids = "id" + (i + "");
        document.getElementById(ids).innerHTML = currentids[i];

        var pos = hasInList(currentids[i]);

        var selectImg = "img" + (i + "");
        var elem_img = document.getElementById(selectImg);

        if (pos > -1) {
            elem_img.setAttribute("class", "img_each_after");
        } else {
            elem_img.setAttribute("class", "img_each");
        }
    }
}

// 添加选择的用户
function addIds(index) {
    var selectId = "id" + (index + "");
    var selectImg = "img" + (index + "");
    var elem_id = document.getElementById(selectId);
    var elem_img = document.getElementById(selectImg);

    if (elem_img.getAttribute("class") == "img_each_after") {
        removeIds(elem_id.innerHTML);
        return;
    }

    elem_img.setAttribute("class", "img_each_after");

    // 添加至已添加用户的列表
    idlist[idcount] = elem_id.innerHTML;
    idcount++;

    var eachdiv = document.createElement("div");
    eachdiv.setAttribute("class", "div_each");
    $(eachdiv).live('click',function(){
        removeIds(this.getElementsByClassName("id_each")[0].innerHTML);
    });

    var eachimg = document.createElement("div");
    eachimg.setAttribute("class", "img_each_selected");

    var eachid = document.createElement("div");
    eachid.setAttribute("class", "id_each");
    eachid.innerHTML = elem_id.innerHTML;

    eachdiv.appendChild(eachimg);
    eachdiv.appendChild(eachid);

    var refnode = document.getElementById("selectedIds_div").getElementsByTagName("div");
    document.getElementById("selectedIds_div").insertBefore(eachdiv, refnode[0]);

}

// 删除已选用户
function removeIds(id_remove) {

    var listpos = hasInList(id_remove);
    idlist.splice(listpos, 1);
    idcount--;

    var selected_div = document.getElementById("selectedIds_div");
    var divs = selected_div.getElementsByClassName("div_each");

    selected_div.removeChild(divs[divs.length - 1 - listpos]);

    var currentpos = isIncurrent(id_remove);
    if (currentpos > -1) {
        var imgs = document.getElementById("above_div").getElementsByClassName("div_each");
        imgs[currentpos].getElementsByClassName("img_each_after")[0].setAttribute("class", "img_each");
    }
}

// 返回已添加的id列表中的位置
function hasInList(str) {
    var pos = -1;
    for (var i = 0; i < idlist.length; i++) {
        if (idlist[i] == str) {
            pos = i;
            break;
        }
    }
    return pos;
}

// 返回当前8个随机用户列表中的位置
function isIncurrent(str) {
    var pos = -1;
    for (var i = 0; i < currentids.length; i++) {
        if (currentids[i] == str) {
            pos = i;
            break;
        }
    }

    return pos;
}

