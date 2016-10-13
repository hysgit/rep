/**
 * Created by hy on 10/13/16.
 */


$(function(){
    $("#logout").click(function () {
        $.ajax({
            type: "POST",//请求方式，get或post
            url: "/user/logout",
            data: "",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            timeout: 15000,  //超时时间设置，单位毫秒
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                alert('系统繁忙,请稍后再试');
            },
            success: function (data) {
                window.location = data.data;
            }
        });
    });
});