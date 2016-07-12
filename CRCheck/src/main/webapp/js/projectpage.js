/**
 * Created by L.H.S on 16/7/8.
 */

function showIntroduce() {
    $("#introduce_child").slideToggle();
}

var iBase = {
    SetOpacity: function (ev, v) {
        ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')' : ev.style.opacity = v / 100;
    }
}

function showLaunch() {
    var elem = document.getElementById("launch");
    var speed = 12;
    var opacity = 100;

    elem.style.display = "block";
    iBase.SetOpacity(elem, 0);
    var val = 0;
    (function () {
        iBase.SetOpacity(elem, val);
        val += 5;
        if (val <= opacity) {
            setTimeout(arguments.callee, speed)
        }
    })();

}

function closeLaunch() {
    var elem = document.getElementById("launch");
    var speed = 15;
    var opacity = 0;

    var val = 100;
    (function () {
        iBase.SetOpacity(elem, val);
        val -= 5;
        if (val >= opacity) {
            setTimeout(arguments.callee, speed);
        } else if (val < 0) {
            elem.style.display = 'none';
        }
    })();
}
