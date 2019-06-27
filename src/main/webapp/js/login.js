var userName;
var userPasswd;
var userAccount;


$(function () {
    $(".header").remove();
    $(".footer").remove();
})

//接收从注册页面传递的 用户名和密码
$(function () {

     userName= decodeURI(getUrlParam("userName"));
     userPasswd = decodeURI(getUrlParam("userPasswd"));
     userAccount = decodeURI(getUrlParam("userAccount"));

    if(userName != null){
        console.log(userName);
        console.log(userPasswd);
        console.log(userAccount);
    }
})


$(function () {

    console.log("接收数据: "+userName);

    $(".sing-in-button").click(function () {
        var user_account = $(".user-accent").val();
        var user_password = $(".user-password").val();

        // 0、登录成功
        if(user_account == userAccount && user_password == userPasswd){
            // $(location).attr("href", "index.html");
            window.location='index.html?userName='+encodeURI(encodeURI(userName))+'';
        }

        // 1、检查账户名是否为空
        else if(!user_account.trim()){
            promptInfo(0, "账户名不能为空！");
        }
        // 2、检查密码是否为空
        else if(!user_password.trim()){
            promptInfo(0, "密码不能为空！");
        }
        // 3、检查账户名是否正确
        else if(user_account != userAccount){
            promptInfo(0, "用户名错误！");
            console.log(userAccount)
        }
        // 4、检查密码是否正确
        else if(user_password != userPasswd){
            promptInfo(0, "密码错误！");
        }
        return;

    });

})