<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/6
  Time: 10:21
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
    <link rel="stylesheet" href="../../css/member/orderinfo.css">
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
    <div id="whole-content">
        <div class="order_statusBar">
            <div class="order_statusBar_tag">
                <div class="taginfo" id="whole-order" onclick="state0click()">等待检票</div>
                <div class="taginfo" id="wait-match" onclick="state1click()">等待配票</div>
                <div class="taginfo" id="prepare-ticket" onclick="state2click()">已经检票</div>
                <div class="taginfo" id="outdate-ticket" onclick="state3click()">失效订单</div>
                <div class="taginfo" id="return-ticket" onclick="state4click()">已退订</div>
            </div>
        </div>
        <div class="orderinfo">
            <div class="orderinfo-left">
                <img class="imgclass" src="">
            </div>
            <div class="orderinfo-middle">
                <div class="order-time"></div>
                <div class="order-address"></div>
                <div class="order-num"></div>
                <div class="order-total"></div>
            </div>
            <div class="orderinfo-right">
                <div class="order-state"></div>
                <div class="order-return"></div>
            </div>
        </div>
        <div class="orderinfo">
            <div class="orderinfo-left">
                <img class="imgclass" src="">
            </div>
            <div class="orderinfo-middle">
                <div class="order-time"></div>
                <div class="order-address"></div>
                <div class="order-num"></div>
                <div class="order-total"></div>
            </div>
            <div class="orderinfo-right">
                <div class="order-state"></div>
                <div class="order-return"></div>
            </div>
        </div>
        <div class="orderinfo">
            <div class="orderinfo-left">
                <img class="imgclass" src="">
            </div>
            <div class="orderinfo-middle">
                <div class="order-time"></div>
                <div class="order-address"></div>
                <div class="order-num"></div>
                <div class="order-total"></div>
            </div>
            <div class="orderinfo-right">
                <div class="order-state"></div>
                <div class="order-return"></div>
            </div>
        </div>
        <div class="orderinfo">
            <div class="orderinfo-left">
                <img class="imgclass" src="">
            </div>
            <div class="orderinfo-middle">
                <div class="order-time"></div>
                <div class="order-address"></div>
                <div class="order-num"></div>
                <div class="order-total"></div>
            </div>
            <div class="orderinfo-right">
                <div class="order-state"></div>
                <div class="order-return"></div>
            </div>
        </div>
        <div class="text-center" id="pagination-on">
            <ul id="pagination-demo-v1_1" class="pagination-sm"></ul>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="../../js/public/jquery.twbsPagination.js"></script>
<script src="../../js/member/orderinfo.js"></script>
</body>
</html>

