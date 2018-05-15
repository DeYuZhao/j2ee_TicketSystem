<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/6
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TicketSystem</title>
    <!-- CSS -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/public/button.css">
    <link rel="stylesheet" href="../../css/venue/venueregister.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>Welcome</strong> to Tickets</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>场馆注册</h3>
                            <p>Enter your information to register:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Email</label>
                                <div id="form-username"></div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="form-password" placeholder="场馆登录密码" class="form-password form-control" id="form-password" value="">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">ConfirmPassword</label>
                                <input type="password" name="form-password" placeholder="确认密码" class="form-password form-control" id="form-confirm-password" value="">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">VerificationCode</label>
                                <input type="text" name="form-password" placeholder="场馆地点" class="form-password form-control" id="form-address" value="">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">VerificationCode</label>
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
                            <a id="registertojsp" href="" onclick="venueRegister()" class="button button-glow button-rounded button-raised button-primary">注册</a>
                            <a id="ulcancel" href="/display/firstpage" class="button button-glow button-rounded button-raised button-primary">取消</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- Javascript -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../js/venue/venueregister.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
