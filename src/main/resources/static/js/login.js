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
    // 登陆提交请求
    /* $.ajax({
        url: 'account/login.do',
        type: 'POST',
        data: {
            'username': name,
            'password': md5pwd,
            'Verification': Verification
        },
        dataType: 'JSON',
        success: function (result) {
            // result 就是服务器发送回来 的JsonResult对象
            // state 和 data属性是在sonResult中定义的Bean属性
            if (result.state == 0) {
                window.location.href = 'index.html';// 登陆成功，跳转用户主页
                return;
            }
            var field = result.state;
            if (field == 1) {
                // 显示用户名错误
                $('#message').empty().append(result.message);
            }
            if (field == 2) {
                // 显示密码错误
                $('#message').empty().append(result.message);
            }
        }
    }); */
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