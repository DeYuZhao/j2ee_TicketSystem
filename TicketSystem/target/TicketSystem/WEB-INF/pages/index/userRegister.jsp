<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/29
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TicketSystem</title>
    <!-- CSS -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/public/button.css">
    <link rel="stylesheet" href="../../css/index/userAccount.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>Welcome</strong> to Tickets</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>注册</h3>
                            <p>Enter your information to register:
                                <a id="codebutton" href="#" onclick="sendCode()" class="button button-glow button-border button-rounded button-primary">发送验证码</a>
                            </p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Email</label>
                                <input type="text" name="form-username" placeholder="邮箱" class="form-username form-control" id="form-username" value="">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="form-password" placeholder="密码" class="form-password form-control" id="form-password" value="">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">ConfirmPassword</label>
                                <input type="password" name="form-password" placeholder="确认密码" class="form-password form-control" id="form-confirm-password" value="">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">VerificationCode</label>
                                <input type="text" name="form-password" placeholder="验证码" class="form-password form-control" id="form-code" value="">
                            </div>
                            <a id="registertojsp" href="" onclick="register()" class="button button-glow button-rounded button-raised button-primary">注册</a>
                            <a id="ulcancel" href="/" class="button button-glow button-rounded button-raised button-primary">取消</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- Javascript -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/index/index.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
