/**
 * Created by L.H.S on 16/7/12.
 */

// 调用该方法要用 setTimeout 延迟1800ms
function slidein(index, remindness) {

    var pics = ["green_pic", "red_pic", "yellow_pic"];
    var words = ["green_word", "red_word", "yellow_word"];

    document.getElementById("pic_div").setAttribute("class", pics[index]);
    document.getElementById("remind").setAttribute("class", words[index]);
    document.getElementById("remind").innerHTML = remindness;

    window.location.href = '#toaster';
    setTimeout("window.location.href='#toaster_close'", 1500);
}

// 每10秒遍历一次消息提醒
$(function () {
    setInterval("getNews()", 10000);
});

function getNews() {
    $.ajax({
        type: "get",
        async: false,
        url: "/MessageRemind",
        success: function (result) {
            if (result == "Changed") {
                slidein(2, '您有一条新消息');

            }
        },
        error: function () {
            alert("获取数据失败啦")
        }
    })
}