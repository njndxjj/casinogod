<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.net.*" %>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>update reward</title>
   <script language="javascript" type="text/javascript" src="../My97DatePicker/WdatePicker.js">
   </script>
   <%@ taglib uri="/struts-tags" prefix="s"%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
    <link href="../bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

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
        <div class="span9">
          <div class="hero-unit">
           <form class="form-horizontal"  action="updateLottery.action" method="post">  
           <div class="control-group">
           <label class="control-label" for="lotteryId">编号</label>
           <div class="controls">
           <input type="text" id="lotteryId" placeholder="1000" name="lotteryId" value=<%=request.getParameter("lotteryId") %> readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="lotteryType">乐透类型</label>
           <div class="controls">
           <input type="text" name="lotteryType" placeholder="1" value=<%=request.getParameter("lotteryType") %> readonly="readonly">
           </div>
           </div>
            <div class="control-group">
           <label class="control-label" for="openDateTime">开奖时间</label>
           <%
             String openDateTime=URLEncoder.encode((String)request.getParameter("openDateTime"),"utf-8");
        	 openDateTime = openDateTime.replace("+", "&nbsp;");
        		   openDateTime = openDateTime.replace("%3A", ":");
        	 
           %>
           <div class="controls">
           <input type="text" id="beginTime" name="openDateTime" class="wdate"  class="Wdate" id="d412" 
          onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'9999-12-31 24:59:59'});" value=<%=openDateTime.substring(0, openDateTime.indexOf(".0")) %>>
           </div>
           </div>
            <div class="control-group">
           <label class="control-label" for="result">乐透结果</label>
           <div class="controls">
           <%
            String result=URLEncoder.encode((String)request.getParameter("result"),"utf-8");
           
           %>
           <input type="text" name="result" placeholder="1" value=<%=result %> >
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
    <script src="//cdnjs.bootcss.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>

  

</body>
</html>