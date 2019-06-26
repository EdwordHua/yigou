/**
 * Created by Administrator on 2019/6/26 0026.
 */
var $timer;
$(function(){
    $pro = $("<div class='prompt'></div>");
    $body = $("body");
    $body.append($pro);
    $(".prompt").css({
        "width": "180px",
        "height": "80px",
        "background-color": "#777",
        "text-align": "center",
        "color": "#ffffff",
        "font-size": "24px",
        //"font-weight":"bold",
        "line-height": "80px",
        "border-radius": "10px",
        "position": "absolute",
        "left": "50%",
        "top": "50%",
        "margin-left": "-90px",
        "margin-top": "-40px",
        "box-sizing": "border-box",
        "display": "none",
        "opacity": "0.9",
        "z-index": "10000"
    })
})

function prompt(content){
    $(".prompt").text(content);
    clearInterval($timer);

    $(".prompt").stop().fadeIn("fast");

    $timer = setInterval(function(){
        $(".prompt").stop().fadeOut("fast");
    }, 3000);
}