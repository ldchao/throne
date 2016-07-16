/**
 * Created by L.H.S on 16/7/15.
 */
var newsRec;
var newsHis;

function switchTab(index) {
    var ids = ["news_last", "news_history"];
    var divs = ["last_div", "history_div"];

    var currentid = document.getElementById(ids[index]);
    var clickid = document.getElementById(ids[(index + 1) % 2]);

    var currentdiv = document.getElementById(divs[index]);
    var clickdiv = document.getElementById(divs[(index + 1) % 2]);

    currentdiv.innerHTML = "";
    clickdiv.innerHTML = "";

    currentid.style.color = "#6b6f78";
    currentid.style.borderBottom = "none";
    clickid.style.color = "#7595e0";
    clickid.style.borderBottom = "2px solid #7595e0";

    currentdiv.style.display = "none";
    clickdiv.style.display = "block";

    if (index == 1) {
        getLastNews();
    } else {
        getHistory();
    }
}

// 收到的消息
window.onload = function () {
    newsRec = document.getElementById("news").innerHTML;
    newsHis = document.getElementById("history").innerHTML;
    getLastNews();
}

function getLastNews() {

    var userId = document.getElementById("storage").innerHTML;
    userId = userId.trim();
    if (userId != "") {
        $.ajax({
            type: "get",
            async: false,
            url: "/AllNewMessage",
            data: {"userId": userId},
            success: function (result) {
                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        addLastNews(result[i]);
                    }
                } else {
                    var div = document.createElement("div");
                    div.innerHTML = "您暂时没有新消息";
                    div.setAttribute("class", "noMessage");
                    document.getElementById("last_div").appendChild(div);
                }
            },
            error: function () {
                slidein(1, "新的消息加载失败");
            }
        });
    }
}

function addLastNews(jsondata) {

    var data = jsondata.content.split("&");

    var div = document.createElement("div");
    div.setAttribute("class", "projects_div");
    div.innerHTML = newsRec;

    var proname = div.getElementsByClassName("title")[0];
    var invitor = div.getElementsByClassName("invitor")[0];
    var kind = div.getElementsByClassName("kind_div")[0].getElementsByTagName("span")[0];
    var content = div.getElementsByClassName("content_describe")[0];
    var dateinfo = div.getElementsByClassName("date_info")[0];

    var messId = jsondata.messageID;
    proname.innerHTML = data[0];
    kind.innerHTML = data[1];
    content.innerHTML = data[2];
    dateinfo.innerHTML = "评审日期: " + data[4] + " - " + data[5];
    invitor.innerHTML = "邀请人: " + data[3];

    var acceptbtn = div.getElementsByClassName("continue_btn")[0];
    acceptbtn.onclick = function () {
        newsDeal("/ApproveMessage", messId);
    };

    var refusebtn = div.getElementsByClassName("refuse_btn")[0];
    refusebtn.onclick = function () {
        newsDeal("/RefuseMessage", messId);
    }

    var ignorebtn = div.getElementsByClassName("ignore_btn")[0];
    ignorebtn.onclick = function () {
        newsDeal("/IgnoreMessage", messId);
    }

    var deletebtn = div.getElementsByClassName("delete_btn")[0];
    deletebtn.onclick = function () {
        newsDeal("/DeleteMessage", messId);
    }

    document.getElementById("last_div").appendChild(div);
}

function newsDeal(link, messId) {

    $.ajax({
        type: "get",
        async: false,
        url: link,
        data: {"messageId": messId},
        success: function (result) {
            if (result == "SUCCESS") {
                slidein(0, "操作成功");
            } else {
                slidein(1, "操作失败请稍候再试")
            }
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });

}

function getHistory() {

    var userId = document.getElementById("storage").innerHTML;
    userId = userId.trim();
    if (userId != "") {
        $.ajax({
            type: "get",
            async: false,
            url: "/AllOldMessage",
            data: {"userId": userId},
            success: function (result) {
                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        addHistory(result[i]);
                    }
                } else {
                    var div = document.createElement("div");
                    div.innerHTML = "您暂时没有历史消息";
                    div.setAttribute("class", "noMessage");
                    document.getElementById("history_div").appendChild(div);
                }
            },
            error: function () {
                slidein(1, "新的消息加载失败");
            }
        });
    }
}

function addHistory(jsondata) {


    var data = jsondata.content.split("&");
    var state = jsondata.accepting_state;
    var statedescribe = "已忽略";
    if(state == "Agree") {
        statedescribe = "已接受";
    } else if (state == "Refuse") {
        statedescribe = "已拒绝";
    }

    var div = document.createElement("div");
    div.setAttribute("class", "projects_div");
    div.innerHTML = newsHis;

    var proname = div.getElementsByClassName("title")[0];
    var invitor = div.getElementsByClassName("invitor")[0];
    var kind = div.getElementsByClassName("kind_div")[0].getElementsByTagName("span")[0];
    var content = div.getElementsByClassName("content_describe")[0];
    var dateinfo = div.getElementsByClassName("date_info")[0];
    var stateinfo = div.getElementsByClassName("statestyle")[0];

    var messId = jsondata.messageID;
    proname.innerHTML = data[0];
    kind.innerHTML = data[1];
    content.innerHTML = data[2];
    dateinfo.innerHTML = "评审日期: " + data[4] + " - " + data[5];
    invitor.innerHTML = "邀请人: " + data[3];
    stateinfo.innerHTML = "处理状态: " + statedescribe;

    document.getElementById("history_div").appendChild(div);
}