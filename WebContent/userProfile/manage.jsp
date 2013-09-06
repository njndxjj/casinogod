<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
   <%@ taglib uri="/struts-tags" prefix="s"%>
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
        <table border=1>  
        <tr>  
        <th width="39%">账号</th>  
        <th width="39%">昵称</th> 
        <th width="39%">性别</th>
        <th width="39%">邮箱</th>
        <th width="39%">手机</th>
        <th width="39%">类型</th>
        <th width="39%">注册时间</th>
        <th width="39%">头像</th>
        <th width="39%">等级</th>
        <th width="39%">经验</th>
        <th width="39%">VIP等级</th>
        <th width="39%">金币</th>
        <th width="39%">钻石</th>
        <th width="39%">说明</th>
        <th width="45%">操作</th>    
    </tr> 
    <s:iterator id="user" value="#request.users"> 
    <tr>  
         <td>${user.userId}</td>
         <td>${user.nickName}</td>  
          <s:if test='%{#user.gender=="m"}'>
            <td>男</td>
           </s:if>
           <s:else>
            <td>女</td>
           </s:else>
         <td>${user.emailAddress}</td>
         <td>${user.telephone}</td>
         <td>${user.userType}</td>
         <td>${user.registerDate}</td>
         <td>${user.image}</td>
         <td>${user.level}</td>
         <td>${user.exp}</td>
         <td>${user.vipLevel}</td>
         <td>${user.gold}</td>
         <td>${user.diamond}</td>
         <td>${user.otherNotes}</td>
         
         <td>
         <a href="configGold.action?account=${user.userId}&gold=${user.gold}&diamond=${user.diamond}">更新金币</a>
         <a href="configExp.action?account=${user.userId}&exp=${user.exp}&level=${user.level}">更新经验</a>
          <a href="deleteUserInfo.action?account=${user.userId}">删除</a></td>   
        </tr>  
        </s:iterator> 
         </table>
         </div>
      </div>
      <hr>
      <footer>
        <p>© CasionGod 2013</p>
      </footer>
    </div>
     </div>
    <script src="//cdnjs.bootcss.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

</body>
</html>