<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>update reward</title>
   <script language="javascript" type="text/javascript" src="/My97DatePicker/WdatePicker.js">
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
           <form class="form-horizontal"  action="updateBossInfo.action" method="post">  
           <div class="control-group">
           <label class="control-label" for="bossId">编号</label>
           <div class="controls">
           <input type="text" id="bossId" placeholder="1" name="bossId" value=<%=request.getParameter("bossId")%> readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="bossType">类型</label>
           <div class="controls">
           <input type="text" id="bossType" placeholder="1" name="bossType" value=<%=request.getParameter("bossType") %> readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="maxHP">体力</label>
           <div class="controls">
           <input type="text" name="maxHP" placeholder="1000" value=<%=request.getParameter("maxHP") %>>
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="time">周期</label>
           <div class="controls">
           <input type="text" name="time" placeholder="30" value=<%=request.getParameter("time") %>>
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="userSize">最大数</label>
           <div class="controls">
           <input type="text" name="userSize" placeholder="30" value=<%=request.getParameter("userSize") %>>
           </div>
           </div>
            <div class="control-group">
           <label class="control-label" for="bossName">名称</label>
           
              <%
           String bossName=new String(request.getParameter("bossName").getBytes("iso-8859-1"),"UTF-8");
           %>
           <div class="controls">
           <input type="text" name="bossName" placeholder="30" value=<%=bossName %>>
           </div>
           </div>
            <div class="control-group">
           <label class="control-label" for="bossImage">头像</label>
           <div class="controls">
           <input type="text" name="bossImage" placeholder="30" value=<%=request.getParameter("bossImage") %>>
           </div>
           </div>
           <div class="control-group">
            <%
           String otherNotes=new String(request.getParameter("otherNotes").getBytes("iso-8859-1"),"UTF-8");
           %>
           <label class="control-label" for="otherNotes">说明</label>
           <div class="controls">
           <input type="text" name="otherNotes" placeholder="21点boss" value=<%=otherNotes %>>
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