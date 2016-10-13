<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="statics/js/jQuery.md5.js"></script>

</head>
<body style="margin:20%">
    <form id="formid" action="/user/login" method="post" enctype="text/plain">
        <label>用户名:<input type="text" id="username"></label>
        <label>密码:<input type="password" id="password"></label>
        <input type="button" id="btnsubmit" value="登陆">
    </form>

<script>
    $(function(){
        $("#btnsubmit").click(function () {

            var username = $("#username").val();
            var password = $("#password").val();
            console.log(username + " " + password);
            if(username==null || username=="" || username == undefined)
            {
                return;
            }
            if(password==null || password=="" || password == undefined)
            {
                return;
            }

            var md5str = $.md5(password);

            $("#password").val(md5str);
            document.getElementById("formid").submit();
        });
    });
</script>
</body>
</html>
