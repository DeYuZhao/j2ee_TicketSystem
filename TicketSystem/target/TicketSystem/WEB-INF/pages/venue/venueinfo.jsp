<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/6
  Time: 15:42
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
    <link rel="stylesheet" href="../../css/venue/venueinfo.css">
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
            <div class="row">
                <div id="venue-text">场馆信息</div>
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-bottom">
                        <form role="form" action="" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only">address</label>
                                <input type="text" name="form-password" placeholder="场馆地点" class="form-password form-control" id="form-address" value="">
                            </div>
                            <div class="form-group">
                                <label class="sr-only">VerificationCode</label>
                                <select class="form-control" id="form-seatinfo1">
                                    <option value="0" selected="">请选择</option>
                                    <option value="1" selected="">1</option>
                                    <option value="2" selected="">2</option>
                                    <option value="3" selected="">3</option>
                                    <option value="4" selected="">4</option>
                                    <option value="5" selected="">5</option>
                                </select>
                                <span class="form-seatinfo-text">区</span>
                                <select class="form-control" id="form-seatinfo2">
                                    <option value="0" selected="">请选择</option>
                                    <option value="3" selected="">3</option>
                                    <option value="4" selected="">4</option>
                                    <option value="5" selected="">5</option>
                                    <option value="6" selected="">6</option>
                                </select>
                                <span class="form-seatinfo-text">排</span>
                                <select class="form-control" id="form-seatinfo3">
                                    <option value="0" selected="">请选择</option>
                                    <option value="1" selected="">1</option>
                                    <option value="2" selected="">2</option>
                                    <option value="3" selected="">3</option>
                                    <option value="4" selected="">4</option>
                                    <option value="5" selected="">5</option>
                                    <option value="6" selected="">6</option>
                                    <option value="7" selected="">7</option>
                                    <option value="8" selected="">8</option>
                                    <option value="9" selected="">9</option>
                                    <option value="10" selected="">10</option>
                                    <option value="11" selected="">11</option>
                                    <option value="12" selected="">12</option>
                                    <option value="13" selected="">13</option>
                                    <option value="14" selected="">14</option>
                                    <option value="15" selected="">15</option>
                                    <option value="16" selected="">16</option>
                                    <option value="17" selected="">17</option>
                                    <option value="18" selected="">18</option>
                                    <option value="19" selected="">19</option>
                                    <option value="20" selected="">20</option>
                                    <option value="21" selected="">21</option>
                                    <option value="22" selected="">22</option>
                                    <option value="23" selected="">23</option>
                                    <option value="24" selected="">24</option>
                                    <option value="25" selected="">25</option>
                                </select>
                                <span class="form-seatinfo-text">座</span>
                            </div>
                            <a id="registertojsp" href="" onclick="askToChangeInfo()" class="button button-glow button-rounded button-raised button-primary">申请修改</a>
                            <a id="ulcancel" href="" class="button button-glow button-rounded button-raised button-primary">重置</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/venue/venueinfo.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>