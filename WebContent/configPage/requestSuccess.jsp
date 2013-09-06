<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>friend request</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%@ taglib uri="/struts-tags" prefix="s"%>
    <!-- Le styles -->
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <link href="./bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="assets/ico/favicon.png">
  <style type="text/css"></style></head>

  <body>
  
   <%   
    String sa = (String)session.getAttribute("username");   
    String account=(String)session.getAttribute("account");	
   %>   

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" >CasinoGod</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as <a href="#" class="navbar-link"><%=sa %></a>
            </p>
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">用户信息</li>
              <li class="active"><a href="userInfo.action?account=<%=account %>">用户信息</a></li>
              <li class="nav-header">房间信息</li>
              <li ><a href="configBattle.action">进入房间</a></li>
              <li class="nav-header">游戏信息</li>
              <li><a href="itemAll.action">购买道具</a></li>
              <li><a href="itemUser.action?userId=<%=account%>">道具信息</a></li>
              <li><a href="itemHistoryUser.action?userId=<%=account%>">消费记录</a></li>
              <li><a href="querryUserLottery.action?userId=<%=account%>">乐透记录</a></li>
              <li class="nav-header">好友信息</li>
              <li><a href="friendRequest.action">申请好友</a></li>
              <li><a href="querryRequest.action">好友请求</a></li>
              <li><a href="showFriend.action">查看好友</a></li>
              <li class="nav-header">注销</li>
              <li><a href="logout.action">注销</a></li>
            </ul>
          </div>
        </div>
       
        <div class="span9">
          <div class="hero-unit">
           <h2> 发送申请成功，请等待</h2>
     </div>
      </div>

    </div>
      <hr>
      <footer>
        <p>© CasionGod 2013</p>
      </footer>
     </div>
    <script src="//cdnjs.bootcss.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="./bootstrap/js/bootstrap.min.js"></script>

  

</body>
</html>