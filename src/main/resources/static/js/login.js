//登录按钮
function Login() {
    //验证
    var name = $("#username").val();
    var password = $("#password").val();
    //用户名为空
    if (name == "" || name == null) {
        $("#message").html("用户名为空");
        return false;
    }
    //密码为空
    if (password == "" || password == null) {
        $("#message").html("密码为空");
        return false;
    }
    $.ajax({
        url: "login",    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true,//请求是否异步，默认为异步，这也是ajax重要特性
        data: {
            'userName': name,
            'password': password,
        },    //参数值
        type: "POST",   //请求方式
        beforeSend: function (data) {
            //请求前的处理
            //alert("前的处理");
            console.log(data);
        },
        success: function (data) {
            //请求成功时处理
            let msg=data.msg;
            alert(data.msg);
            window.location.href="index";
            console.log(data);
        },
        complete: function (data) {
            //请求完成的处理
            //alert("完成的处理");
            console.log(data);
        },
        error: function (data) {
            //请求出错处理
            //alert("出错处理");
            console.log(data);
        }
    });

}
//失去焦点
function cleanNerror() {
    $("#message").html("");
}
function cleanPerror() {
    $("#message").html("");
}
//页面重置
function onReset() {
    $("input").attr("value", "");
}
//回车登录
$(document).keydown(function (event) {
    $("#login").click();
});