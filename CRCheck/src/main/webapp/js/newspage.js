/**
 * Created by L.H.S on 16/7/15.
 */
var newsRec;

function switchTab(index) {
    var ids = ["news_last", "news_history"];
    var divs = ["last_div", "history_div"];

    var currentid = document.getElementById(ids[index]);
    var clickid = document.getElementById(ids[(index + 1) % 2]);

    var currentdiv = document.getElementById(divs[index]);
    var clickdiv = document.getElementById(divs[(index + 1) % 2]);

    currentid.style.color = "#6b6f78";
    currentid.style.borderBottom = "none";
    clickid.style.color = "#7595e0";
    clickid.style.borderBottom = "2px solid #7595e0";

    currentdiv.style.display = "none";
    clickdiv.style.display = "block";
}

// 收到的消息
window.onload = function () {
    newsRec = document.getElementById("news").innerHTML;

    var userId = document.getElementById("storage").innerHTML;
    userId = userId.trim();
    if (userId != "") {
        $.ajax({
            type: "get",
            async: false,
            url: "/AllNewMessage",
            data: {"userId": userId},
            success: function (result) {
                if (result.length > 0){
                    for (var i = 0; i < result.length; i++) {
                        addNews(result[i].content);
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

function addNews(jsondata) {

    var data = jsondata.split("&");

    var div = document.createElement("div");
    div.setAttribute("class", "projects_div");
    div.innerHTML = newsRec;

    var proname = div.getElementsByClassName("title")[0];
    var invitor = div.getElementsByClassName("invitor")[0];
    var kind = div.getElementsByClassName("kind_div")[0].getElementsByTagName("span")[0];
    var content = div.getElementsByClassName("content_describe")[0];
    var dateinfo = div.getElementsByClassName("date_info")[0];

    proname.innerHTML = data[0];
    kind.innerHTML = data[1];
    content.innerHTML = data[2];
    dateinfo.innerHTML = "评审日期: " + data[4] + " - " + data[5];
    invitor.innerHTML = "邀请人: " + data[3];

    // var button = div.getElementsByClassName("continue_btn")[0];
    // if (jsondata.state == "Over") {
    //     button.innerHTML = "项目已结束";
    //     button.setAttribute("class", "finish_btn");
    // }
    // button.onclick = function () {
    //     goTo(proID);
    // }

    document.getElementById("last_div").appendChild(div);
}