/**
 * Created by L.H.S on 16/7/20.
 */


var userList;

var state = document.getElementById("p-state").innerHTML.trim();
var userId = document.getElementById("storage").innerHTML.trim();//当前用户
var owner = document.getElementById("owner").innerHTML.trim();//项目拥有者
var yesNo = document.getElementById("yesNo").innerHTML.trim();//是否自己参与评审
var projectId = document.getElementById("p-id").innerHTML.trim();


//使用IE条件注释来判断是否IE6，通过判断userAgent不一定准确
if (document.all) document.write('<!--[if lte IE 6]><script type="text/javascript">window.ie6= true<\/script><![endif]-->');
// var ie6 = /msie 6/i.test(navigator.userAgent);//不推荐，有些系统的ie6 userAgent会是IE7或者IE8
function change(picId, fileId) {
    var pic = document.getElementById(picId);
    var file = document.getElementById(fileId);
    if (window.FileReader) {//chrome,firefox7+,opera,IE10,IE9，IE9也可以用滤镜来实现
        oFReader = new FileReader();
        oFReader.readAsDataURL(file.files[0]);
        oFReader.onload = function (oFREvent) {
            pic.src = oFREvent.target.result;
        };
    }
    else if (document.all) {//IE8-
        file.select();
        var reallocalpath = document.selection.createRange().text//IE下获取实际的本地文件路径
        if (window.ie6) pic.src = reallocalpath; //IE6浏览器设置img的src为本地路径可以直接显示图片
        else { //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
            pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
            pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
        }
    }
    else if (file.files) {//firefox6-
        if (file.files.item(0)) {
            url = file.files.item(0).getAsDataURL();
            pic.src = url;
        }
    }


    var imageFile = $('input[name=imageFile]').val();

    $('form').ajaxSubmit({
        type: 'post', // 提交方式 get/post
        url: '/headPortraitsUpload', // 需要提交的 url
        data: {
            'imageFile': imageFile
        },
        success: function (result) { // data 保存提交后返回的数据，一般为 json 数据
            if (result == "SUCCESS") {
                slidein(0, "更改头像成功");
            } else {
                slidein(1, "更改头像失败");
            }
        },
        error: function () {
            slidein(1, "获取数据失败");
        }
    })

}


function slideTo(id) {
    if (id == "friend") {
        $("html, body").animate({scrollTop: $("#friend").offset().top - 110}, 400);
    } else {
        $("html, body").animate({scrollTop: $("#achieve").offset().top - 110}, 400);
    }
}

var isEdit = 0;

function editInfo() {
    if (isEdit == 0) {
        var allInfo = document.getElementsByClassName("info");
        var input = new Array(allInfo.length);
        for (var i = 0; i < 5; i++) {
            input[i] = document.createElement("input");
            input[i].className = "textfield edit";
            input[i].type = "text";
            input[i].style.width = "auto";
            input[i].style.height = "26px";
            input[i].style.fontSize = "16px";
            input[i].style.padding = "8px 6px";
            input[i].style.display = "inline-block";
            input[i].style.textAlign = "center";
            input[i].style.color = "#737474";
            input[i].value = allInfo[0].innerHTML;
            allInfo[0].parentNode.replaceChild(input[i], allInfo[0]);
        }
        document.getElementById("edit-button").innerHTML = "确认保存";
        isEdit = 1;
    } else {
        //还需要有一个保存的弹框
        var allEdit = document.getElementsByClassName("edit");
        $.ajax({
            type: "post",
            async: false,
            url: "/updateUser",
            data: {
                "id": allEdit[0].value,
                "blog": allEdit[1].value,
                "email": allEdit[2].value,
                "phone": allEdit[3].value,
                "address": allEdit[4].value,
                "sex": "男"
            },
            success: function (result) {
                if (result == "SUCCESS") {
                    var input = new Array(allEdit.length);
                    for (var i = 0; i < 5; i++) {
                        input[i] = document.createElement("div");
                        if (i == 0) {
                            input[i].className = "info info-username";
                        } else {
                            input[i].className = "info";
                        }
                        input[i].innerHTML = allEdit[0].value;
                        allEdit[0].parentNode.replaceChild(input[i], allEdit[0]);
                    }
                    document.getElementById("edit-button").innerHTML = "修改资料";
                    isEdit = 0;
                    slidein(0, "修改信息成功");
                } else {
                    slidein(1, "提交失败请稍候再试");
                }
            },
            error: function () {
                slidein(1, "获取数据失败");
            }
        });
    }
}

function imageEdit(isOk) {
    if (isOk == "1") {
        $("#portrait-filter").fadeIn(100);
    } else {
        $("#portrait-filter").fadeOut(100);
    }
}


function loadAchieve() {

    userList = document.getElementById("user-list").innerHTML;


    //加载个人累计的信息
    $.ajax({
        type: "post",
        async: false,
        url: "/checkContributionSum",
        data: {
            "userID": userId
        },
        success: function (result) {
            var allAchieve = document.getElementsByClassName("history");
            allAchieve[0].innerHTML = result.row + "行";
            allAchieve[1].innerHTML = result.time + "分钟";
            allAchieve[2].innerHTML = result.amount + "个";
            allAchieve[3].innerHTML = result.accuracy + "%";
            allAchieve[4].innerHTML = result.coverage + "%";
        },
        error: function () {
            slidein(1, "获取数据失败");
        }
    });

    //加载成就列表
    $.ajax({
        type: "post",
        async: false,
        url: "/checkAchievement",
        data: {
            "userID": userId
        },
        success: function (result) {
            var lines = document.getElementsByClassName("lines");
            var times = document.getElementsByClassName("times");
            var defects = document.getElementsByClassName("defects");
            var rights = document.getElementsByClassName("rights");
            var covers = document.getElementsByClassName("covers");

            for (var i = 0; i < result[0]; i++) {
                lines[i].style.backgroundImage = "url('../image/line/line" + (5 - i) + ".svg')";
            }
            for (var i = 0; i < result[1]; i++) {
                times[i].style.backgroundImage = "url('../image/line/time" + (5 - i) + ".svg')";
            }
            for (var i = 0; i < result[2]; i++) {
                defects[i].style.backgroundImage = "url('../image/line/defect" + (5 - i) + ".svg')";
            }
            for (var i = 0; i < result[3]; i++) {
                rights[i].style.backgroundImage = "url('../image/line/right" + (5 - i) + ".svg')";
            }
            for (var i = 0; i < result[4]; i++) {
                covers[i].style.backgroundImage = "url('../image/line/cover" + (5 - i) + ".svg')";
            }

        },
        error: function () {
            slidein(1, "获取数据失败");
        }
    });

    //加载好友列表
    $.ajax({
        type: "post",
        async: false,
        url: "/findAllFriend",
        data: {
            "uid": userId
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                var friendblock = document.createElement("div");
                friendblock.className = "result-item";
                friendblock.innerHTML = userList;
                friendblock.style.display = "inline-block";
                friendblock.getElementsByClassName("result-image")[0].src = result[i].headPortrait;
                friendblock.getElementsByClassName("result-name")[0].innerHTML = result[i].id;
                friendblock.getElementsByClassName("result-plus")[0].style.display = "none";
                document.getElementsByClassName("myfriend")[0].appendChild(friendblock);
            }
        },
        error: function () {
            slidein(1, "获取数据失败");
        }
    });

}


function searchUser() {
    var input = document.getElementById("searchField").value;
    $.ajax({
        type: "post",
        async: false,
        url: "/searchUserList",
        data: {
            "key": input
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                var friendblock = document.createElement("div");
                friendblock.className = "result-item";
                friendblock.innerHTML = userList;
                friendblock.style.display = "inline-block";
                friendblock.getElementsByClassName("result-image")[0].src = result[i].headPortrait;
                friendblock.getElementsByClassName("result-name")[0].innerHTML = result[i].id;
                document.getElementsByClassName("search-row")[0].appendChild(friendblock);
            }
        },
        error: function () {
            slidein(1, "获取数据失败");
        }
    });
}

function keySearch(){
    if(event.keyCode == "13"){
        searchUser();
    }
}

function addFriend(plus){
    var fid = plus.parentNode.getElementsByClassName("result-name")[0].innerHTML;

    $.ajax({
        type: "post",
        async: false,
        url: "/addFriend",
        data: {
            "uid": userId,
            "fid": fid
        },
        success: function (result) {
           if(result == "SUCCESS"){
               alert(0);
               var node = document.createElement("div");
               node.innerHTML = plus.parentNode.innerHTML;
               node.className = "result-item";
               node.style.display = "inline-block";
               node.getElementsByClassName("result-plus")[0].style.display = "none";
               document.getElementsByClassName("myfriend")[0].appendChild(node);
           }else{
               alert(1);
           }
        },
        error: function () {
            slidein(1, "获取数据失败");
        }
    });
}


