<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/5
  Time: 10:59
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
    <link rel="stylesheet" href="../../css/member/memberinfo.css">
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
                会员信息
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
                            <span class="member-attribute">账   户：</span>
                            <div id="form-username"></div>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">会员资格：</span>
                            <div id="membership-div">
                                <span id="membership"></span>
                                <div id="delete-membership" onclick="cancelMembership()">撤销</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">会员级别：</span>
                            <div id="memberlevel-div">

                            </div>

                        </div>
                        <div class="form-group">
                            <span class="member-attribute">当前积分：</span>
                            <div id="memberpoint-div"></div>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">可用优惠券：</span>
                            <div class="coupon">
                                <div class="coupon-div" id="coupon-div1">
                                    <img src="../../images/member/coupon1.png">
                                    <div class="coupon-num" id="coupon-div1-num"><strong id="coupon-div1-num-s1"></strong>张</div>
                                </div>
                                <div class="coupon-div" id="coupon-div2">
                                    <img src="../../images/member/coupon2.png">
                                    <div class="coupon-num" id="coupon-div2-num"><strong id="coupon-div1-num-s2"></strong>张</div>
                                </div>
                                <div class="coupon-div" id="coupon-div3">
                                    <img src="../../images/member/coupon3.png">
                                    <div class="coupon-num" id="coupon-div3-num"><strong id="coupon-div1-num-s3"></strong>张</div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">可兑换优惠券：</span>
                            <select class="form-control" id="form_coupon">
                                <option value="coupon-0" selected="selected">请选择</option>
                                <option value="coupon-1">2000积分换20元抵用券</option>
                                <option value="coupon-2">5000积分换60元抵用券</option>
                                <option value="coupon-3">8000积分换90元抵用券</option>
                            </select>
                        </div>
                        <a id="logintojsp" onclick="convertBenifit()" href="" class="button button-glow button-rounded button-raised button-primary">兑换</a>
                        <a id="ulcancel" href="/display/firstpage" class="button button-glow button-rounded button-raised button-primary">返回</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/member/memberinfo.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
