/**
 * Created by L.H.S on 16/7/12.
 */

// 调用该方法要用 setTimeout 延迟1800ms
function slidein(index, remindness) {

    var pics = ["green_pic", "red_pic", "yellow_pic"];
    var words = ["green_word", "red_word", "yellow_word"];

    document.getElementById("pic_div").setAttribute("class", pics[index]);
    document.getElementById("remind").setAttribute("class", words[index]);
    document.getElementById("remind").innerHTML = remindness;

    window.location.href='#toaster';
    setTimeout("window.location.href='#toaster_close'", 1500);
}