<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.net.*" %>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>update reward</title>
   <script language="javascript" type="text/javascript" src="./My97DatePicker/WdatePicker.js">
   </script>
   <%@ taglib uri="/struts-tags" prefix="s"%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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
           <form class="form-horizontal"  action="updateEvent.action" method="post">  
           <div class="control-group">
           <label class="control-label" for="eventId">编号</label>
           <div class="controls">
           <input type="text" id="eventId" placeholder="1" name="eventId" value=<%=request.getAttribute("eventId") %> readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="eventType">类型</label>
           <div class="controls">
           <input type="text" name="eventType" placeholder="1" value=<%=request.getAttribute("eventType") %> readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="startTime">开始时间</label>
        
           <div class="controls">
           <input type="text" id="startTime" name="startTime" class="wdate"  class="Wdate" id="d412" 
        onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'9999-12-31 24:59:59'});" value=<%=request.getAttribute("startTime") %>>
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="endTime">结束时间</label>
       
           <div class="controls">
           <input type="text" id="endTime" name="endTime" class="wdate"  class="Wdate" id="d412" 
        onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'9999-12-31 24:59:59'});" value=<%=request.getAttribute("endTime") %>>
           </div>
           </div>
            <div class="control-group">
            
           <label class="control-label" for="description">描述</label>
           <div class="controls">
           <input type="text" name="description" placeholder="" value=<%=request.getAttribute("description") %>>
           </div>
           </div>
           <div class="control-group">
         
           <label class="control-label" for="title">标题</label>
           <div class="controls">
           <input type="text" name="title" placeholder="" value=<%=request.getAttribute("title") %>>
           </div>
           </div>
           <div class="control-group">
           
           <label class="control-label" for="imageUrl">头像</label>
           <div class="controls">
           <input type="text" name="imageUrl" placeholder="" value=<%=request.getAttribute("imageUrl") %>>
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="frequency">方式</label>
           <div class="controls">
           <input type="text" name="frequency" placeholder="" value=<%=request.getAttribute("frequency") %>>
           </div>
           </div>
            <div class="control-group">
            
           <label class="control-label" for="detailData">明细</label>
           <div class="controls">
           <input type="text" name="detailData" placeholder="" value=<%=request.getAttribute("detailData") %>>
           </div>
           </div>
            <div class="control-group">
           <label class="control-label" for="enable">激活</label>
           <div class="controls">
           <input type="text" name="enable" placeholder="" value=<%=request.getAttribute("enable") %>>
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
    <script src="./bootstrap/js/bootstrap.min.js"></script>

  

</body>
</html>