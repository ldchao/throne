/**
 * Created by L.H.S on 16/7/22.
 */

window.onload = function () {
    addSingle();
    addSingle();
    addMerge();
    addMerge();
}

function addSingle() {

    var div = document.createElement("div");
    div.innerHTML = document.getElementById("exist_copy").innerHTML;

    div.onmouseenter = function () {
        showCheck(this, 0);
    }

    div.onmouseleave = function () {
        hideCheck(this, 0);
    }

    document.getElementById("all-defect").appendChild(div);
}

function addMerge() {

    var headdiv = document.createElement("div");
    headdiv.innerHTML = document.getElementById("exist_copy_2").innerHTML;
    headdiv.getElementsByClassName("info-head_2")[0].style.backgroundColor = "#f3f3df";

    headdiv.onmouseenter = function () {
        showCheck(this, 1);
    }

    headdiv.onmouseleave = function () {
        hideCheck(this, 1);
    }

    document.getElementById("all-defect").appendChild(headdiv);

    var bodydiv = document.createElement("div");
    bodydiv.style.display = "none";
    for (var i = 0; i < 2; i++) {
        var div = document.createElement("div");
        div.innerHTML = document.getElementById("exist_copy_2").innerHTML;
        div.style.marginTop = "-15px";
        div.getElementsByClassName("merge_span")[0].style.display = "none";
        div.onmouseenter = function () {
            showCheck(this, 2);
        };
        div.onmouseleave = function () {
            hideCheck(this, 2);
        };

        bodydiv.appendChild(div);
    }

    document.getElementById("all-defect").appendChild(bodydiv);

    var spread = headdiv.getElementsByClassName("merge_span")[0];
    spread.onclick = function () {
        if (bodydiv.style.display == "none") {
            $(bodydiv).show();
        } else {
            $(bodydiv).hide();
        }
    }
}

function showCheck(parentDiv) {
    var slidediv = parentDiv.getElementsByClassName("left_slide")[0];
    $(slidediv).show(200);
}

function hideCheck(parentDiv) {
    var slidediv = parentDiv.getElementsByClassName("left_slide")[0];
    if (slidediv.getElementsByClassName("merge_box")[0].checked != true) {
        $(slidediv).hide(200);
    }
}

// 合并
 