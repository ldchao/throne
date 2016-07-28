/**
 * Created by marioquer on 16/7/28.
 */

var form;

var projectId = document.getElementById("p-id").innerHTML.trim();

function loadRecord() {

    form = document.getElementById("commit_item").innerHTML;

    $.ajax({
        type: "post",
        async: false,
        url: "../lineChart",
        data: {
            "projectId": projectId,
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                var newForm = document.createElement("div");
                newForm.setAttribute("class", "commit-item");
                newForm.innerHTML = form;
                newForm.getElementsByClassName("userName-small")[0].innerHTML = result[i].userId;
                newForm.getElementsByClassName("commit-time")[0].innerHTML = result[i].endTime;
                newForm.getElementsByClassName("discription")[0].innerHTML = result[i].description;
                newForm.getElementsByClassName("num1-content")[0].innerHTML = result[i].method1;
                newForm.getElementsByClassName("num2-content")[0].innerHTML = result[i].method2;
                newForm.style.display = "";
                document.getElementsByClassName("history_div")[0].appendChild(newForm);
            }
        },
        error: function () {
            slidein(1, "出故障了请稍候再试");
        }
    });
}

