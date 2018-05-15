<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/7
  Time: 10:56
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
    <link rel="stylesheet" href="../../css/admin/adminchangelist.css">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Tickets——后台管理</a>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="span2  col-xs-12 col-sm-3 col-md-2">
            <ul class="nav nav-pills nav-stacked">
                <li><a id="left-bar-1" href="/admin/adminchangelist">审查申请</a></li>
                <li><a id="left-bar-2" href="/admin/adminsettleaccount">结算利润</a></li>
                <li><a id="left-bar-3" href="/admin/ticketstatistics">统计信息</a></li>
                <li><a id="left-bar-4" href="/">退出系统</a></li>
            </ul>
        </div>
        <div class="navigation-right-whole">
            <div id="right-top-bar">
                <div id="right-top-1"><a class="top-a" href="/admin/adminregisterlist">场馆注册申请</a></div>
                <div id="right-top-2"><a class="top-b" href="/admin/adminchangelist">场馆修改申请</a></div>
            </div>
            <%--这里是分页list，还未完成--%>
            <div>
                <div class="list-content">
                    <table class="table" id="list-table">
                        <thead>
                        <tr>
                            <th class="top-address">地点</th>
                            <th>区数</th>
                            <th>排数</th>
                            <th>座位数</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="text-center" id="pagination-on">
                    <ul id="pagination-demo-v1_1" class="pagination-sm"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="../../js/public/jquery.twbsPagination.js"></script>
<script src="../../js/admin/adminchange.js"></script>
</body>
</html>
