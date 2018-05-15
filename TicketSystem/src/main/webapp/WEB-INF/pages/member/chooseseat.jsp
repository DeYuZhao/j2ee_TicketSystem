<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/7
  Time: 11:17
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
    <link rel="stylesheet" href="../../css/member/jq22.css">
    <link rel="stylesheet" href="../../css/member/chooseseat.css">
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
                        <li><a href="/venue/venueregister">场馆申请</a></li>
                        <li><a href="/venue/venuelogin">场馆登录</a></li>
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
<div class="container">
    <div class="demo clearfix">
        <!---左边座位列表----->
        <div id="seat_area">
            <div class="front">舞台</div>
        </div>
        <!---右边选座信息----->
        <div class="booking_area">
            <p>演出：<span id="nameid">天将雄师</span></p>
            <p>时间：<span id="timeid">03月20日 22:15</span></p>
            <p>地点：<span id="addressid">天将雄师</span></p>
            <p>座位：</p>
            <ul id="seats_chose"></ul>
            <p>票数：<span id="tickects_num">0</span></p>
            <p>总价：<b>￥<span id="total_price">0</span></b></p>
            <div id="sure"><a id="sureid" href="" onclick="getSelectedSeat()">确认购买</a></div>
            <div id="cancel"><a id="cancelid" href="/display/showinfo">退出</a></div>
            <div id="legend"></div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/member/jquery.seat-charts.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js/member/chooseseat.js"></script>

</body>
</html>
