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
}

function showMerge() {
    
    var bigdiv = document.createElement("div");
    bigdiv.style.display = "none";
    for(var i=0; i<2; i++) {
        var div = document.createElement("div");
        div.innerHTML = document.getElementById("exist_copy_2").innerHTML;
        div.style.marginTop = "-20px";
        div.getElementsByClassName("merge_span")[0].style.display = "none";
        bigdiv.appendChild(div);
    }
    document.getElementById("exist_copy_2").appendChild(bigdiv);

    $(bigdiv).slideToggle();
}