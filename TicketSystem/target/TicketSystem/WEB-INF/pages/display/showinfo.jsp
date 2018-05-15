<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/5
  Time: 16:31
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
    <link rel="stylesheet" href="../../css/display/showinfo.css">
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
<div id="whole">
    <div id="whole-content">
        <div id="left">
            <div id="left-img-div">
                <img id="left-img" src="../../images/display/test.png">
            </div>
        </div>
        <div id="right">
            <div id="right-first">
                <div id="right-first-showtitle">
                    <span id="right-first-showname"></span>
                </div>
            </div>
            <div id="right-second">
                <div id="right-second-showtime">
                    <div class="show-info">时间</div>
                    <div id="right-second-timeinfo"></div>
                </div>
            </div>
            <div id="right-third">
                <div id="right-third-showaddress">
                    <div class="show-info">地点</div>
                    <div id="right-third-addressinfo"></div>
                </div>
            </div>
            <div id="right-fourth">
                <div id="right-fourth-selectticket">
                    <div class="show-info">选择票面</div>
                    <div id="right-fourth-ticket">
                        <%--<div class="right-fourth-ticketinfo">380票面</div>--%>
                        <%--<div class="right-fourth-ticketinfo">580票面</div>--%>
                        <%--<div class="right-fourth-ticketinfo">880票面</div>--%>
                        <%--<div class="right-fourth-ticketinfo">1080票面</div>--%>
                        <%--<div class="right-fourth-ticketinfo">1280票面</div>--%>
                    </div>
                </div>
            </div>
            <div id="right-fifth">
                <div id="right-fifth-shownumber">
                    <div class="show-info">选择数量</div>
                    <div id="right-fifth-numberinfo">
                        <div id="min" onclick="decrease()">-</div>
                        <div id="num_box">1</div>
                        <div id="add" onclick="add()">+</div>
                    </div>
                </div>
            </div>
            <div id="right-sixth">
                <div id="right-sixth-showtotal">
                    <div class="show-info">合计</div>
                    <div id="right-sixth-totalprice">
                        <div id="totalprice-number"></div>
                        <div id="totalprice-yuan">元</div>
                    </div>
                </div>
            </div>
            <div id="right-seventh">
                <div id="right-seventh-nowbuy"><a href="" onclick="postPrice()" id="randomchooseid">立即购买</a></div>
                <div id="right-seventh-choosebuy"><a href="/member/chooseseat">选座购买</a></div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/display/showinfo.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
