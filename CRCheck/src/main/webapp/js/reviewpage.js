/**
 * Created by L.H.S on 16/7/20.
 */

function mouseOver(tr){
    tr.style.backgroundColor = "#f3faff";
    tr.getElementsByTagName("i")[0].style.display = "block";

    tr.getElementsByTagName("i")[0].onclick = function () {
        showDiv(this);
    }
}

function mouseOut(tr) {
    tr.style.backgroundColor = "transparent";
    tr.getElementsByTagName("i")[0].style.display = "none";
}

function showDiv(tr) {
    var div = document.createElement("div");
    div.setAttribute("class", "bug_div");
    tr.appendChild(div);
}