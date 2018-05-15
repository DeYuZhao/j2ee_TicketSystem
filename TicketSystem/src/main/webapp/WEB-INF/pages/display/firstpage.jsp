<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/1
  Time: 16:47
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
    <link rel="stylesheet" href="../../css/display/firstpage.css">
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
<div id="firstpage">
    <div id="whole">
        <div id="first-div">
            <div id="first-first">
                <div id="first-first-img">
                    <img src="../../images/display/logo.png">
                </div>
            </div>
            <div id="first-second">
                <input type="text" name="form-username" placeholder="请输入演唱会信息" class="form-username form-control" id="form-username" value="">
                <input type="submit" value="搜索" id="first-second-searchbutton">
            </div>
        </div>
        <div id="second-div">
            <div id="second-first">
                <div id="second-first-bar">
                    <span>商品分类</span>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>演唱会</span></a>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>音乐会</span></a>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>话剧歌剧</span></a>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>舞蹈芭蕾</span></a>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>展会展览</span></a>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>体育比赛</span></a>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>曲苑杂坛</span></a>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>儿童亲子</span></a>
                </div>
                <div class="second-first-catagory">
                    <a href="#" class="second-first-catagory-a"><span>度假休闲</span></a>
                </div>
            </div>
            <div id="second-second">
                <img id="second-second-img" src="../../images/display/mainimg.png">
            </div>
            <div id="second-third">
                <div id="second-third-bar">
                    <span>人气演出</span>
                </div>
                <div class="second-third-toptext">Top 1</div>
                <div class="second-third-catagory">
                    <a href="#" class="second-third-catagory-a"><img src="../../images/display/top1.png"></a>
                </div>
                <div class="second-third-toptext">Top 2</div>
                <div class="second-third-catagory">
                    <a href="#" class="second-third-catagory-a"><img src="../../images/display/top2.png"></a>
                </div>
                <div class="second-third-toptext">Top 3</div>
                <div class="second-third-catagory">
                    <a href="#" class="second-third-catagory-a"><img src="../../images/display/top3.png"></a>
                </div>
            </div>
        </div>
        <div id="third-div">
            <div id="third-first">
                <div id="third-first-title">热门推荐</div>
                <div id="third-first-blank"></div>
            </div>
            <div id="third-second">
                <div class="third-second-ticket" id="third-second-ticket1">
                    <div class="third-second-ticket-img" id="third-second-ticket-img1">
                        <img src="../../images/display/hot1.png">
                    </div>
                    <div class="third-second-ticket-info" id="third-second-ticket-info1">
                        <a href="/display/showinfo" onclick="deliverTitle(this.title)" id="ainfo1" title=""></a>
                    </div>
                    <div class="third-second-ticket-price" id="third-second-ticket-price1">
                        票价：<strong id="p1text"></strong>起
                    </div>
                </div>
                <div class="third-second-ticket" id="third-second-ticket2">
                    <div class="third-second-ticket-img" id="third-second-ticket-img2">
                        <img src="../../images/display/hot2.png">
                    </div>
                    <div class="third-second-ticket-info" id="third-second-ticket-info2">
                        <a href="/display/showinfo" id="ainfo2" onclick="deliverTitle(this.title)" title=""></a>
                    </div>
                    <div class="third-second-ticket-price" id="third-second-ticket-price2">
                        票价：<strong id="p2text"></strong>起
                    </div>
                </div>
                <div class="third-second-ticket" id="third-second-ticket3">
                    <div class="third-second-ticket-img" id="third-second-ticket-img3">
                        <img src="../../images/display/hot3.png">
                    </div>
                    <div class="third-second-ticket-info" id="third-second-ticket-info3">
                        <a href="/display/showinfo" id="ainfo3" onclick="deliverTitle(this.title)" title=""></a>
                    </div>
                    <div class="third-second-ticket-price" id="third-second-ticket-price3">
                        票价：<strong id="p3text"></strong>起
                    </div>
                </div>
                <div class="third-second-ticket" id="third-second-ticket4">
                    <div class="third-second-ticket-img" id="third-second-ticket-img4">
                        <img src="../../images/display/hot4.png">
                    </div>
                    <div class="third-second-ticket-info" id="third-second-ticket-info4">
                        <a href="/display/showinfo" id="ainfo4" onclick="deliverTitle(this.title)" title=""></a>
                    </div>
                    <div class="third-second-ticket-price" id="third-second-ticket-price4">
                        票价：<strong id="p4text"></strong>起
                    </div>
                </div>
                <div class="third-second-ticket" id="third-second-ticket5">
                    <div class="third-second-ticket-img" id="third-second-ticket-img5">
                        <img src="../../images/display/hot5.png">
                    </div>
                    <div class="third-second-ticket-info" id="third-second-ticket-info5">
                        <a href="/display/showinfo" id="ainfo5" onclick="deliverTitle(this.title)" title=""></a>
                    </div>
                    <div class="third-second-ticket-price" id="third-second-ticket-price5">
                        票价：<strong id="p5text"></strong>起
                    </div>
                </div>
                <div class="third-second-ticket" id="third-second-ticket6">
                    <div class="third-second-ticket-img" id="third-second-ticket-img6">
                        <img src="../../images/display/hot6.png">
                    </div>
                    <div class="third-second-ticket-info" id="third-second-ticket-info6">
                        <a href="/display/showinfo" id="ainfo6" onclick="deliverTitle(this.title)" title=""></a>
                    </div>
                    <div class="third-second-ticket-price" id="third-second-ticket-price6">
                        票价：<strong id="p6text"></strong>起
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/display/firstpage.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
