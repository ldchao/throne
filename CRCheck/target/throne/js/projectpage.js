/**
 * Created by L.H.S on 16/7/8.
 */
var idlist = new Array();  // 记录已经添加的用户
var idcount = 0;  // 下标
var currentids = new Array();  // 记录当前的8个随机用户
var pageNum = 0;  // 页码

function showIntroduce() {
    $("#introduce_child").slideToggle();
}

var iBase = {
    SetOpacity: function (ev, v) {
        ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')' : ev.style.opacity = v / 100;
    }
}

function showLaunch(elem_id) {

    var userId = document.getElementById("storage").innerHTML;
    userId = userId.trim();
    if (userId == "") {
        slidein("2", "您还没登录");
        return;
    }

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

    setIds();
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

    var userId = document.getElementById("storage").innerHTML;
    userId = userId.trim();
    if (userId == "") {
        slidein("2", "您还没登录");
        return;
    }

    $.ajax({
        type: "get",
        async: false,
        url: "/getUserList",
        data: {"userid": userId},
        success: function (result) {
            currentids = result;

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
        },
        error: function () {
            slidein(1, "获取评审者数据失败啦");
        }
    });
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

    // 隐藏最后一个
    if (idlist.length > 8) {
        hideId();
    }

    var spanum = document.getElementById("pages").getElementsByTagName("span").length;
    if (spanum * 8 < idlist.length) {
        var pagesdiv = document.getElementById("pages");
        var span = document.createElement("span");
        span.setAttribute("class", "dot");
        pagesdiv.appendChild(span);

        $(span).live('click', function () {
            gotoPage(this);
        });

        adjustDot();
    }

    var eachdiv = document.createElement("div");
    eachdiv.setAttribute("class", "div_each");
    $(eachdiv).live('click', function () {
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

    var selected_div = document.getElementById("selectedIds_div");
    var divs = selected_div.getElementsByClassName("div_each");
    var lastpos = hasInList(divs[divs.length - 1].getElementsByClassName("id_each")[0].innerHTML);

    var listpos = hasInList(id_remove);
    idlist.splice(listpos, 1);
    idcount--;

    for (var i = 0; i < divs.length; i++) {
        if (divs[i].getElementsByClassName("id_each")[0].innerHTML == id_remove) {
            selected_div.removeChild(divs[i]);
            break;
        }
    }

    var currentpos = isIncurrent(id_remove);
    if (currentpos > -1) {
        var imgs = document.getElementById("above_div").getElementsByClassName("div_each");
        imgs[currentpos].getElementsByClassName("img_each_after")[0].setAttribute("class", "img_each");
    }

    // 如果大于8个,需要补空位
    if (idlist.length > 7
        && selected_div.getElementsByClassName("div_each").length < 8) {

        if (pageNum * 8 + 7 < idlist.length && listpos > 0) {
            showId(lastpos - 1, true);
        } else if (selected_div.getElementsByClassName("div_each").length == 0) {
            var pagesdiv = document.getElementById("pages");
            var spans = pagesdiv.getElementsByTagName("span");
            pagesdiv.removeChild(spans[pageNum]);
            pageNum--;
            gotoPage(spans[pageNum]);
        }
    }
}

// 增加时,隐藏第九个元素
function hideId() {
    var selected_div = document.getElementById("selectedIds_div");
    var divs = selected_div.getElementsByClassName("div_each");
    selected_div.removeChild(divs[divs.length - 1]);
}

// 删除时,显示第九个元素, 传入被删除元素的前一个位置,即显示的元素的位置
// isDelete = true 删除时用, false 普通显示用
function showId(index, isDelete) {
    var eachdiv = document.createElement("div");
    eachdiv.setAttribute("class", "div_each");
    $(eachdiv).live('click', function () {
        removeIds(this.getElementsByClassName("id_each")[0].innerHTML);
    });

    var eachimg = document.createElement("div");
    eachimg.setAttribute("class", "img_each_selected");

    var eachid = document.createElement("div");
    eachid.setAttribute("class", "id_each");
    eachid.innerHTML = idlist[index];

    eachdiv.appendChild(eachimg);
    eachdiv.appendChild(eachid);

    document.getElementById("selectedIds_div").appendChild(eachdiv);

    // 去除一个分页节点
    var pagesdiv = document.getElementById("pages");
    var spans = pagesdiv.getElementsByTagName("span");
    if (isDelete == true && idlist.length % 8 == 0 && idlist.length >= 8 && spans.length * 8 > idlist.length) {
        pagesdiv.removeChild(spans[spans.length - 1]);
    }

    adjustDot();
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

// 翻页
function gotoPage(node) {

    var spans = document.getElementById("pages").getElementsByTagName("span");

    if (spans.length > 1) {
        var index = ($(node).parents("#pages").find("span").index($(node)));
        pageNum = index;
        var index = index * 8;
        document.getElementById("selectedIds_div").innerHTML = '';

        var max = idlist.length - index - 1;

        var min = max - 8;
        if (min < 0) {
            min = -1;
        }

        for (var i = max; i > min; i--) {
            showId(i, false);
        }
    } else {
        for (var i = idlist.length - 1; i > -1; i--) {
            showId(i, false);
        }
    }
}

function nextPage() {
    var spans = document.getElementById("pages").getElementsByTagName("span");
    if (pageNum < spans.length - 1) {
        pageNum++;
        gotoPage(spans[pageNum]);
    }
}

function prevPage() {
    var spans = document.getElementById("pages").getElementsByTagName("span");
    if (pageNum > 0) {
        pageNum--;
        gotoPage(spans[pageNum]);
    }
}

// 调整当前选定点的颜色
function adjustDot() {
    var spans = document.getElementById("pages").getElementsByTagName("span");
    for (var i = 0; i < spans.length; i++) {
        if (i == pageNum) {
            spans[i].style.backgroundColor = "#6093e2";
        } else {
            spans[i].style.backgroundColor = "#e7ecf5";
        }
    }
}

// 发布项目, 传入用户id
function publishPro() {

    var userId = document.getElementById("storage").innerHTML;
    userId = userId.trim();
    if (userId == "") {
        slidein("2", "您还没登录");
        return;
    }

    var proname = document.getElementById("pro_name").value;
    var prodescribe = document.getElementById("pro_describe").value;
    var codelang = document.getElementById("code_language").value;
    var startdate = document.getElementById("start_date").value;
    var enddate = document.getElementById("end_date").value;
    var publimit = document.getElementById("limit").checked;
    var selfin = document.getElementById("self_in").checked;

    var date = new Date();
    var today = date.getFullYear() + '-'
        + (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
        + date.getDate();

    if (proname == "" || prodescribe == "" || codelang == "编程语言"
        || startdate == "" || enddate == "") {
        slidein(1, "请填写完整信息");
        return;
    } else if (startdate >= enddate) {
        slidein(1, "结束时间应大于开始时间");
        return;
    } else if (enddate <= today) {
        slidein(1, "结束时间应大于今天");
        return;
    }

    var limit = "PRIVATE";
    if (publimit == true)
        limit = "PUBLIC";

    var self = "NO";
    if (selfin == true)
        self = "YES";

    var list_info = [userId, proname, prodescribe, codelang, startdate, enddate, limit].join("&");
    idlist.splice(0, 0, self);
    var id_info = idlist.join("&");

    $.ajax({
        type: "post",
        async: false,
        url: "/Launch",
        data: {
            "info1": list_info,
            "info2": id_info
        },
        success: function (result) {
            if (result == "SUCCESS") {
                slidein(0, "提交成功");
                setTimeout("window.location.href='ProjectDetailPage.jsp'", 1800);
            } else {
                slidein(1, "提交失败请稍候再试");
            }
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });

}
