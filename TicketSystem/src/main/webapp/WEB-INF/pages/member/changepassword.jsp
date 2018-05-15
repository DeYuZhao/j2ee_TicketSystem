<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/5
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>TicketSystem</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/public/button.css">
    <link rel="stylesheet" href="../../css/public/navigationbar.css">
    <link rel="stylesheet" href="../../css/member/basicinfo.css">
    <link rel="stylesheet" href="../../css/member/changepassword.css">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Tickets</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        我的Tickets
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" id="user-menu">
                        <li><a href="/member/memberinfo">会员信息</a></li>
                        <li><a href="/member/basicinfo">资料设置</a></li>
                        <li><a href="/member/changepassword">修改密码</a></li>
                        <li><a href="/member/orderinfo">我的订单</a></li>
                        <li><a href="/member/statistics">统计信息</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        我的场馆
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" id="venue-menu">
                        <li><a href="#">场馆申请</a></li>
                        <li><a href="#">场馆登录</a></li>
                    </ul>
                </li>
                <li id="logout">
                    <a href="/user_login">
                        <span class="glyphicon glyphicon-user"></span> 退出
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div id="whole">
    <div id="basicwhole">
        <div id="basicinfo-bar">
            <div id="basicinfo-bar-title">
                修改密码
            </div>
            <div id="basicinfo-bar-blank"></div>
        </div>
        <div id="basicinfo-content">
            <div id="basicinfo-content-left">
                <div id="basicinfo-content-left-img">
                    <img src="../../images/display/hot1.png">
                </div>
            </div>
            <div id="basicinfo-content-right">
                <div class="form-bottom">
                    <form role="form" action="" method="post" class="login-form">
                        <div class="form-group">
                            <span class="member-attribute">邮   箱：</span>
                            <input type="text" name="form-username" placeholder="邮箱" class="form-username form-control" id="form-username" value="">
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">旧 密 码：</span>
                            <input type="password" name="form-username" placeholder="旧密码" class="form-username form-control" id="form-oldpassword" value="">
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">新 密 码：</span>
                            <input type="password" name="form-username" placeholder="新密码" class="form-username form-control" id="form-newpassword" value="">
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">确认新密码：</span>
                            <input type="password" name="form-username" placeholder="确认新密码" class="form-username form-control" id="form-confirmnewpassword" value="">
                        </div>
                        <a id="logintojsp" onclick="changepassword()" href="" class="button button-glow button-rounded button-raised button-primary">保存</a>
                        <a id="ulcancel" href="/display/firstpage" class="button button-glow button-rounded button-raised button-primary">取消</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/member/changepassword.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
