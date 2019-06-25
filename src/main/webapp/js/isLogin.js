/**
 * Created by Administrator on 2019/6/25 0025.
 */

$(function(){
    isLogin();
//                判断用户是否登录
    function isLogin(){
        $.ajax({
            type:"post",
            url:"user/islogin.do",
            success:function(msg){
                console.log(msg);

                if(msg == "false"){
                    console.log("用户未登录！")
                }else{
                    var json = $.parseJSON(msg);
                    var uname = json.uname;

                    $("#isLogin1").remove();
                    $("#isLogin2").remove();

                    $(".cor3").text("欢迎光临，"+uname);
                    $(".cor3").css({"color":"red"});

                    console.log(json);
                }

            },
            error:function(xhr){console.log("请求后台接口 user/islogin.do 失败！")}
        })
    }
})

