/**
 * Created by L.H.S on 16/7/20.
 */



var isEdit = false;

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

}


function slideTo(id) {
    if (id == "friend") {
        $("html, body").animate({scrollTop: $("#friend").offset().top - 110}, 400);
    } else {
        $("html, body").animate({scrollTop: $("#achieve").offset().top - 110}, 400);
    }
}

function editInfo() {
    if (isEdit == false) {
        var allInfo = document.getElementsByClassName("info");
        var input = new Array(allInfo.length);
        for (var i = 0; i < 5; i++) {
            input[i] = document.createElement("input");
            input[i].className = "textfield";
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
        isEdit = true;
    } else {
        //还需要有一个保存的弹框
    }
}





