<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>config item</title>
   <script language="javascript" type="text/javascript" src="./My97DatePicker/WdatePicker.js">
   </script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="../assets/ico/favicon.png">
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
              <li><a href="queryall.action">用户管理</a></li>
              <li><a href="accountInfo.action">账号管理</a></li>
              <li class="nav-header">配置信息</li>
              <li><a href="configRoom.action">房间配置</a></li>
              <li><a href="configItemInfo.action">道具配置</a></li>
              <li><a href="configReward.action">登陆奖励配置</a></li>
              <li><a href="configLottery.action">乐透个人配置</a></li>
               <li><a href="configProduct.action">IAP道具配置</a></li>
               <li><a href="configLotteryHistory.action">乐透记录配置</a></li>
              <li class="nav-header">记录信息</li>
              <li><a href="queryBattleHistory.action">比赛记录</a></li>
              <li><a href="roomAll.action">房间配置查询</a></li>
              <li><a href="itemAll.action">道具查询</a></li>
              <li><a href="querryReward.action">奖励配置信息</a></li>
              <li><a href="lotteryHistory.action">乐透配置信息</a></li>
              <li><a href="rewardInfo.action">登录奖励信息</a></li>
               <li><a href="querryProduct.action">产品信息</a></li>
              <li class="nav-header">广播系统</li>
              <li><a href="configBoardCast.action">配置广播信息</a></li>
              <li><a href="configDevice.action">设备注册</a></li>
              <li><a href="querryBoardCast.action">查看广播信息</a></li>
              <li><a href="searchBoardCast.action">查看某时间内广播信息</a></li>
              <li><a href="findDevice.action">查看设备注册信息</a></li>
              <li><a href="configPush.action">发送push消息</a></li>
              <li class="nav-header">注销</li>
              <li><a href="logout.action">注销</a></li>
            </ul>
          </div>
        </div>
       
        <div class="span9">
          <div class="hero-unit">
           <form class="form-horizontal"  action="addEvent.action" method="post">  
           <div class="control-group">
           <label class="control-label" for="eventType">活动类型</label>
           <div class="controls">
           <input type="text" id="eventType" placeholder="活动类型" name="eventType">
           </div>
           </div>
           <div class="control-group info">
           <label class="control-label" for="startTime">开始时间</label>
           <div class="controls">
           <input type="text" id="startTime" name="startTime" class="wdate"  class="Wdate" id="d412" 
        onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'9999-12-31 24:59:59'});">
           </div>
            </div>
           <div class="control-group info">
           <label class="control-label" for="endTime">结束时间</label>
           <div class="controls">
           <input type="text" id="endTime" name="endTime" class="wdate"  class="Wdate" id="d412" 
        onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'9999-12-31 24:59:59'});">
           </div>
            </div>
            <div class="control-group info">
           <label class="control-label" for="description">说明</label>
           <div class="controls">
           <input type="text" id="description" name="description" placeholder="说明" >
           </div>
           </div>
            <div class="control-group info">
           <label class="control-label" for="title">标题</label>
           <div class="controls">
           <input type="text" id="title" name="title" placeholder="标题" >
           </div>
           </div>
            <div class="control-group info">
           <label class="control-label" for="imageUrl">图标</label>
           <div class="controls">
           <input type="text" id="imageUrl" name="imageUrl" placeholder="图标" >
           </div>
           </div>
            <div class="control-group info">
           <label class="control-label" for="frequency">方式</label>
           <div class="controls">
           <input type="text" id="frequency" name="frequency" placeholder="方式" >
           </div>
           </div>
            <div class="control-group info">
           <label class="control-label" for="detailData">详细说明</label>
           <div class="controls">
           <input type="text" id="detailData" name="detailData" placeholder="详细说明" >
           </div>
           </div>
            <div class="control-group info">
           <label class="control-label" for="enable">激活</label>
           <div class="controls">
           <input type="text" id="enable" name="enable" placeholder="1" >
           </div>
           </div>
            <div class="controls">
         <button class="btn btn-primary" type="submit">提交</button>
         </div>
         </form>

          </div>
      </div>
      <hr>    
    </div>
    <footer>
        <p>© CasionGod 2013</p>
      </footer> 
     </div>
    <script src="//cdnjs.bootcss.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

  

</body>
</html>