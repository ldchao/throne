/**
 * Created by L.H.S on 16/7/22.
 */

function showCheck() {

    var parentDiv = document.getElementById("exist_copy");

    var slidediv = parentDiv.getElementsByClassName("left_slide")[0];
    slidediv.style.height = parentDiv.getElementsByClassName("exist-form")[0].offsetHeight;
    $(slidediv).toggle("slide");

    var correct = slidediv.getElementsByClassName("check_btn")[0];
    correct.onclick = function () {
        Correct(parentDiv, 0);
    }

    var error = slidediv.getElementsByClassName("times_btn")[0];
    error.onclick = function () {
        Correct(parentDiv, 1);
    }
}

function hideCheck() {
    var parentDiv = document.getElementById("exist_copy");
    var slidediv = parentDiv.getElementsByClassName("left_slide")[0];
    $(slidediv).toggle("slide");
}

function Correct(parentDiv, isCor) {

    var bgcolor = ["#f3f9f5", "f9f3f3"];
    var fontcolor = ["#376b48", "#8f5757"];

    var div = parentDiv.getElementsByClassName("class-intent")[0];
    div.style.backgroundColor = bgcolor[isCor];

    var ps = div.getElementsByTagName("p");
    for(var i=0; i<ps.length; i++) {
        ps[i].style.color = fontcolor[isCor];
    }


    // div.getElementsByClassName("info-head")[0].style.color = fontcolor[isCor];
}