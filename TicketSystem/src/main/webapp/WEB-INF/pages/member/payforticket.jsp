<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/7
  Time: 11:20
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
    <link rel="stylesheet" href="../../css/member/payforticket.css">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body onload="countTime()">
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
                        <li><a href="#">会员信息</a></li>
                        <li><a href="#">资料设置</a></li>
                        <li><a href="#">修改密码</a></li>
                        <li><a href="#">我的订单</a></li>
                        <li><a href="#">统计信息</a></li>
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
<div class="whole">
    <div class="content-div">
        <div id="text">
            剩余支付时间：
        </div>
        <div class="timer">
            <div class="hours-wrapper">
                <div id="hours"></div>
                <div class="timer-text" id="timer-text-hours">hours</div>
            </div>
            <div class="minutes-wrapper">
                <div id="minutes"></div>
                <div class="timer-text" id="timer-text-minutes">minutes</div>
            </div>
            <div class="seconds-wrapper">
                <div id="seconds"></div>
                <div class="timer-text" id="timer-text-seconds">seconds</div>
            </div>
        </div>
        <div id="totalprice">
            总交易金额：<span id="price"><strong id="priceinfo"></strong></span><span id="yuan">元</span>
        </div>
        <div class="form-bottom">
            <form role="form" action="" method="post" class="login-form">
                <div class="form-group">
                    <span class="member-attribute">优惠券类型(一次仅可使用一张)：</span>
                    <input type="radio" value="option1" name="sex" id="twenty"><span class="radio-sex">20元代金券</span>
                    <input type="radio" value="option2" name="sex" id="sixty"><span class="radio-sex">60元代金券</span>
                    <input type="radio" value="option3" name="sex" id="ninety"><span class="radio-sex">90元代金券</span>
                </div>
                <div class="form-group">
                    <label class="sr-only" for="form-username">Email</label>
                    <input type="text" name="form-username" placeholder="账户" class="form-username form-control" id="form-username" value="">
                </div>
                <div class="form-group">
                    <label class="sr-only" for="form-password">Password</label>
                    <input type="password" name="form-password" placeholder="支付密码" class="form-password form-control" id="form-password" value="">
                </div>
                <a id="logintojsp" onclick="payForTickets()" href="/member/payforticket" class="button button-glow button-rounded button-raised button-primary">确认</a>
                <a id="ulcancel" href="/member/chooseseat" class="button button-glow button-rounded button-raised button-primary">取消</a>
            </form>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/member/payforticket.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
