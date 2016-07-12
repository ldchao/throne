/**
 * Created by L.H.S on 16/7/11.
 */

function Login() {

    var userId = document.getElementById("userId_log").value;
    var password = document.getElementById("password_log").value;

    var valid = true;

    if (userId == "") {
        $("#userId_log").parent().append("<label class='lblerr_1'>" + "请输入用户名" + "</label>");
        valid = false;
    }

    if (password == "") {
        $("#password_log").parent().append("<label class='lblerr_2'>" + "请输入密码" + "</label>");
        valid = false;
    }

    if (valid == true) {
        $.ajax({
            type: "post",
            async: false, // 同步执行
            url: "/Login",
            data: {
                "userId": userId,
                "password": password
            },
            success: function (result) {
                if (result == "SUCCESS") {
                    window.location.href = "../pages/HomePage.jsp";
                    slidein(0, '登录成功');
                } else if (result == "FAIL") {
                    $("#password_log").parent().append("<label class='lblerr_2'>" + "用户名不存在或密码错误" + "</label>");
                } else {
                    alert("数据库连接失败啦");
                }
            },
            error: function () {
                alert("获取数据失败啦")
            }
        })
    } else {
        return;
    }
}

function Register() {

    var userId = document.getElementById("userId_reg").value;
    var password = document.getElementById("password_reg").value;
    var power = "PRIVATE";
    if (document.getElementById("power_reg").checked == true) {
        power = "PUBLIC";
    }

    var valid = true;

    if (userId == "") {
        $("#userId_reg").parent().append("<label class='lblerr_1'>" + "请输入用户名" + "</label>");
        valid = false;
    }

    if (password == "") {
        $("#password_reg").parent().append("<label class='lblerr_2'>" + "请输入密码" + "</label>");
        valid = false;
    } else if (password.length < 6) {
        $("#userId_reg").parent().append("<label class='lblerr_2'>" + "密码太短啦" + "</label>");
        valid = false;
    } else if (password.length > 20) {
        $("#userId_reg").parent().append("<label class='lblerr_2'>" + "密码超过20位啦" + "</label>");
        valid = false;
    }

    if (valid == true) {
        $.ajax({
            type: "post",
            async: false, // 同步执行
            url: "/Register",
            data: {
                "userId": userId,
                "password": password,
                "power": power
            },
            success: function (result) {
                if (result == "SUCCESS") {
                    window.location.href = "../pages/HomePage.jsp";
                    slidein(0, '注册成功并已登录');
                } else if (result == "FAIL") {
                    $("#password_reg").parent().append("<label class='lblerr_1'>" + "用户名已存在" + "</label>");
                } else {
                    alert("数据库连接失败啦");
                }
            },
            error: function () {
                alert("获取数据失败啦")
            }
        })
    } else {
        return;
    }
}

$(function () {

    $("#userId_log").focus(resetLoginErr);
    $("#password_log").focus(resetLoginErr);
    $("#userId_reg").focus(resetLoginErr);
    $("#password_reg").focus(resetLoginErr);

    $("#Login").on("hide.bs.modal", function (a) {
        $("#Login .input_field_div .lblerr_1").remove();
        $("#Login .input_field_div .lblerr_2").remove();
        $("#Login .input_field_div input").val("");
    });

    $("#Register").on("hide.bs.modal", function (a) {
        $("#Register .input_field_div .lblerr_1").remove();
        $("#Register .input_field_div .lblerr_2").remove();
        $("#Register .input_field_div input").val("");
    });

    $("#userId_log").keypress(function (a) {
        if (a.which == "13" || a.which == "9") {
            $("#password_log").focus().select();
            a.stopPropagation()
        }
    });
    $("#password_log").keypress(function (a) {
        if (a.which == "13") {
            Login();
        }
    });

    $("#userId_reg").keypress(function (a) {
        if (a.which == "13" || a.which == "9") {
            $("#password_reg").focus().select();
            a.stopPropagation();
        }
    });
    $("#password_reg").keypress(function (a) {
        if (a.which == "13") {
            Register();
        }
    });
});

function resetLoginErr() {
    $(this).siblings().each(function () {
        if ($(this).hasClass("lblerr_1")) {
            $(this).remove()
        }
    });

    $(this).siblings().each(function () {
        if ($(this).hasClass("lblerr_2")) {
            $(this).remove()
        }
    });
}


function backToTop() {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 150) {
            $("#back-to-top").fadeIn(200);
        } else {
            $("#back-to-top").fadeOut(200);
        }
    });
    $("#back-to-top").click(function () {
        $("html, body").animate({scrollTop: 0}, 500);
    });
}
$(document).ready(function () {
    backToTop();
})