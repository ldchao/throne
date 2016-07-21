/**
 * Created by L.H.S on 16/7/20.
 */

function mouseOver(tr) {
    tr.style.backgroundColor = "#f3faff";
    tr.getElementsByTagName("i")[0].style.display = "block";

    tr.getElementsByTagName("i")[0].onclick = function () {
        showDiv(tr);
    }
}

function mouseOut(tr) {
    tr.style.backgroundColor = "transparent";
    tr.getElementsByTagName("i")[0].style.display = "none";
}

function showDiv(tr) {
    var index = $(tr).parents(".file_table").find("tr").index($(tr));

    var row = document.getElementById("code_file").insertRow(index + 1);
    row.style.height = "97px";
    row.style.width = "100%";

    var td = document.createElement("td");
    td.setAttribute("colspan", "3");

    var div = document.createElement("div");
    div.setAttribute("class", "bug_div");
    div.innerHTML = document.getElementById("bugdiv_id").innerHTML;
    td.appendChild(div);

    var addbtn = div.getElementsByClassName("bug_add")[0];
    addbtn.onclick = function () {
        showDiv(row);
    };

    var checkbox = div.getElementsByTagName("input")[0];
    checkbox.onclick = function () {
        if(checkbox.checked == false)
            document.getElementById("selectAll").checked = false;
    }

    var delbtn = div.getElementsByClassName("del_btn")[0];
    delbtn.onclick = function () {
        var t = $(this).parents("tr");
        var pos = $(t).parents(".file_table").find("tr").index($(t));
        document.getElementById("code_file").deleteRow(pos);
    };

    row.appendChild(td);
    document.getElementById("selectAll").checked = false;
}

function selectAll() {

    var divs = document.getElementById("code_file").getElementsByClassName("bug_div");
    for (var i = 0; i < divs.length; i++) {
        var box = divs[i].getElementsByTagName("input")[0];
        box.checked = document.getElementById("selectAll").checked;
    }
}

function delAll() {

    var divs = document.getElementById("code_file").getElementsByClassName("bug_div");
    var n = divs.length - 1;
    for (var i = n; i > -1; i--) {
        var box = divs[i].getElementsByTagName("input")[0];
        if (box.checked == true) {
            var t = $(divs[i]).parents("tr");
            var pos = $(t).parents(".file_table").find("tr").index($(t));
            document.getElementById("code_file").deleteRow(pos);
        }
    }
    document.getElementById("selectAll").checked = false;
}