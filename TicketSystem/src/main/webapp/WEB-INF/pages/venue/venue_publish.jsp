<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/6
  Time: 16:43
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
    <link rel="stylesheet" href="../../bootstrap/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../../css/public/button.css">
    <link rel="stylesheet" href="../../css/public/navigationbar.css">
    <link rel="stylesheet" href="../../css/venue/venue_publish.css">
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
                <div class="form-top">
                    演出计划
                </div>
                <div class="form-bottom">
                    <form role="form" action="" method="post" class="login-form">
                        <div class="form-group">
                            <span class="member-attribute">名   称：</span>
                            <input type="text" name="form-username" placeholder="演出标题" class="form-username form-control" id="form-name" value="">
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">演出日期：</span>
                            <div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii:ss">
                                <input id="plan-date" class="form-control" size="16" type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">价   格：</span>
                            <input type="text" name="form-username" placeholder="从低到高价格以，隔开" class="form-username form-control" id="form-username" value="">
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">演出类型：</span>
                            <select class="form-control" id="form_type">
                                <option value="type-0" selected="selected">请选择</option>
                                <option value="type-1">音乐会</option>
                                <option value="type-2">演唱会</option>
                                <option value="type-3">话剧歌剧</option>
                                <option value="type-4">舞蹈芭蕾</option>
                                <option value="type-5">展会展览</option>
                                <option value="type-6">体育比赛</option>
                                <option value="type-7">曲苑杂坛</option>
                                <option value="type-8">儿童亲子</option>
                                <option value="type-9">度假休闲</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">简单描述：</span>
                            <input type="text" name="form-description" placeholder="简单描述" class="form-username form-control" id="form-description" value="">
                        </div>
                        <a id="logintojsp" onclick="publish()" href="" class="button button-glow button-rounded button-raised button-primary">发布</a>
                        <a id="ulcancel" href="" class="button button-glow button-rounded button-raised button-primary">清空</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="../../bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="../../js/venue/venuepublish.js"></script>
</body>
</html>
