<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/4
  Time: 15:57
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
    <link rel="stylesheet" href="../../css/member/basicinfo.css">
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
                基本资料
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
                            <span class="member-attribute">昵   称：</span>
                            <input type="text" name="form-username" placeholder="邮箱" class="form-username form-control" id="form-username" value="">
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">性   别：</span>
                            <input type="radio" value="option1" name="sex" id="man"><span class="radio-sex">男性</span>
                            <input type="radio" value="option2" name="sex" id="woman"><span class="radio-sex">女性</span>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">出生日期：</span>
                            <div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" value="" id="form_birthday">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">婚姻状况：</span>
                            <select class="form-control" id="form_marriage">
                                <option value="marriage-0" selected="">请选择</option>
                                <option value="marriage-1" selected="">已婚</option>
                                <option value="marriage-2" selected="">未婚</option>
                                <option value="marriage-3" selected="">恋爱</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">教育程度：</span>
                            <select class="form-control" id="form_education">
                                <option value="education-0" selected="">请选择</option>
                                <option value="education-1" selected="">高中及以下</option>
                                <option value="education-2" selected="">大学专科</option>
                                <option value="education-3" selected="">大学本科</option>
                                <option value="education-4" selected="">硕士</option>
                                <option value="education-5" selected="">博士及以上</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">从事行业：</span>
                            <select class="form-control" id="form_bussiness">
                                <option value="bussiness-0" selected="">请选择</option>
                                <option value="bussiness-1" selected="">政府机关/社会团体</option>
                                <option value="bussiness-2" selected="">邮电通讯</option>
                                <option value="bussiness-3" selected="">IT行业/互联网</option>
                                <option value="bussiness-4" selected="">商业/贸易</option>
                                <option value="bussiness-5" selected="">旅游/餐饮/酒店</option>
                                <option value="bussiness-6" selected="">银行/金融/证券/保险/投资</option>
                                <option value="bussiness-7" selected="">健康/医疗服务</option>
                                <option value="bussiness-8" selected="">建筑/房地产</option>
                                <option value="bussiness-9" selected="">交通运输/物流仓储</option>
                                <option value="bussiness-10" selected="">法律/司法</option>
                                <option value="bussiness-11" selected="">文化/娱乐/体育</option>
                                <option value="bussiness-12" selected="">媒介/广告</option>
                                <option value="bussiness-13" selected="">教育/科研</option>
                                <option value="bussiness-14" selected="">农/林/渔/牧</option>
                                <option value="bussiness-15" selected="">制造业</option>
                                <option value="bussiness-16" selected="">其他</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">月 收 入：</span>
                            <select class="form-control" id="form_income">
                                <option value="income-0" selected="">请选择</option>
                                <option value="income-1" selected="">2000元以下</option>
                                <option value="income-2" selected="">2000~4999元</option>
                                <option value="income-3" selected="">5000~9999元</option>
                                <option value="income-4" selected="">10000~20000元</option>
                                <option value="income-5" selected="">20000元以上</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">当前余额：</span>
                            <div id="form_balance">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="member-attribute">现 居 地：</span>
                            <input type="text" name="form-address" placeholder="家庭住址" class="form-username form-control" id="form_address" value="">
                        </div>
                        <a id="logintojsp" onclick="saveInfo()" href="" class="button button-glow button-rounded button-raised button-primary">保存</a>
                        <a id="ulcancel" href="/display/firstpage" class="button button-glow button-rounded button-raised button-primary">取消</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../js/public/jquery-3.2.1.min.js"></script>
<script src="../../js/member/basicinfo.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="../../bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
</body>
</html>