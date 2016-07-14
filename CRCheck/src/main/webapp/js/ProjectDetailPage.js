/**
 * Created by marioquer on 16/7/13.
 */
var form;
var detail = {


    judge: function(){
        var state;
        switch(state){
            case NotStart:
            case Starting:
            case Over:
        }
    },

    init: function(){
        form = document.getElementById("init-form").innerHTML;
        detail.judge();
    }
}

function addForm() {
    var newForm = document.createElement("div");
    newForm.innerHTML = form;
    document.getElementById("init-form").appendChild(newForm);
}


$(document).ready(function(){
    detail.init();
});