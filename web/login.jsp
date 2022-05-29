<%--
  Created by IntelliJ IDEA.
  User: liuyu
  Date: 2022/5/29
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="static/cascader/cascader.css"/>
    <!-- 引入 layui.js -->
    <script src="static/layui/layui.js"></script>
    <script src="static/cascader/cascader.js"></script>
    <script src="static/js/index.js"></script>
    <script>
        //校验准备z
        // $.validator.setDefaults({
        // 	submitHandler: function() {
        // 		alert("提交事件!");
        // 	}


        layui.use(['jquery'], function () {
            var $ = layui.jquery;
            $().ready(function() {
                $("#myForm").validate();
                //验证完成后提交
                $("#myForm").submit;
            });
        });

        // });
        // $().ready(function() {
        //     $("#myForm").validate();
        //     //验证完成后提交
        //     $("#myForm").submit;
        // });
        // //method. 1
        function getImage(){
            /*
              1. 参阅JQuery对标签属性的操作，https://www.runoob.com/jquery/jquery-dom-set.html
              2. 不加flag="+Math.random()是实现不了局部刷新的功能的。因为src中如果每次访问的地址一样的话就会发生不更新的情况
             */
            $("#vcodeImg").attr("src","http://localhost:8080/ysj/captcha?t="+Math.random());
        };

    </script>
    <title>登陆界面</title>
    <style>
        body{
            background-image: url(static/img/R-C.jpg);
            background-size: 100%;
        }
    </style>
</head>
<body >
<div style="text-align: center"><b><font size="6">欢迎登录本系统</font></b></div><br><BR>
<div align="center">
    <form class="layui-form layui-form-pane" id = "myForm" action="http://localhost:8080/ysj/login" method="post" >
        <!--jquery表单验证，参考 https://www.runoob.com/jquery/jquery-plugin-validate.html-->
        账号:<input type="text" name="username" minlength="3" required /><BR>
        密码: <input type="password" name="password" /><BR>
        验证码:<input type="text" name="captcha" size="3">
        <!-- 将验证码当成图片处理 -->
        <img title="点击图片切换验证码" id="vcodeImg" onClick="getImage()" src="http://localhost:8080/ysj/captcha" >
        <a href="#" onClick="getImage()">刷新</a>
        <br>
        <input type="submit" value="登录" >
    </form>
</div>
</body>
</html>


