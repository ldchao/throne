/**
 * Created by marioquer on 16/7/13.
 */
var form;
var detail = {


    judge: function () {
        var state;
        var button = document.getElementById("begin");
        switch (state) {
            case NotStart:
                button.style.backgroundColor("#D47859");
                button.innerHTML = "尚未开始评审";
                button.style.cursor("none");
                button.style.readOnly = true;
            case Over:
                button.style.backgroundColor("#6B6F78");
                button.innerHTML = "评审已经结束";
                button.style.cursor("none");
                button.style.readOnly = true;
                break;
        }
    },

    init: function () {
        form = document.getElementById("init-form").innerHTML;
        detail.judge();
    }
}

function addForm() {
    var newForm = document.createElement("div");
    newForm.innerHTML = form;
    document.getElementById("init-form").appendChild(newForm);
}

function deleteForm(node) {
    $(node).parent().parent().remove();
}


$(document).ready(function () {
    detail.init();
});