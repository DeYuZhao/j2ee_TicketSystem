<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/8
  Time: 10:16
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
    <script src="../../js/member/echarts.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/public/button.css">
    <link rel="stylesheet" href="../../css/public/navigationbar.css">
    <link rel="stylesheet" href="../../css/venue/venuestatistics.css">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Tickets——场馆管理</a>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="span2  col-xs-12 col-sm-3 col-md-2">
            <ul class="nav nav-pills nav-stacked">
                <li><a id="left-bar-1" href="/venue/venueinfo">修改信息</a></li>
                <li><a id="left-bar-2" href="/venue/venuepublish">发布计划</a></li>
                <li><a id="left-bar-3" href="/venue/venueticketlist">售票检票</a></li>
                <li><a id="left-bar-5" href="/venue/venuestatistics">统计信息</a></li>
                <li><a id="left-bar-6" href="/display/firstpage">退出</a></li>
            </ul>
        </div>
        <div class="navigation-right-whole">
            <div class="right-content">
                <div id="right-content-graph"></div>
                <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
                <script src="../../js/venue/venuestatistics.js"></script>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
