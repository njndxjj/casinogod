<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>gift item</title>
   <script language="javascript" type="text/javascript" src="/My97DatePicker/WdatePicker.js">
   </script>
   <%@ taglib uri="/struts-tags" prefix="s"%>
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
        <div class="span9">
          <div class="hero-unit">
           <form class="form-horizontal"  action="giftItems.action" method="post">  
           <div class="control-group">
           <label class="control-label" for="userId">账号</label>
           <div class="controls">
           <input type="text" id="userId" placeholder="" name="userId" value=<%=account%> readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="itemId">道具编号</label>
           <div class="controls">
           <input type="text" name="itemId" placeholder="1" value="<%=request.getParameter("itemId")%>" readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="itemName">道具名称</label>
           <div class="controls">
           <input type="text" name="itemName" placeholder="itemtest" value="<%=request.getParameter("itemName")%>" readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="gameType">游戏类型</label>
           <div class="controls">
           <input type="text" name="gameType" placeholder="1" value="<%=request.getParameter("gameType")%>" readonly="readonly">
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="itemNum">数量</label>
           <div class="controls">
           <input type="text" name="itemNum" placeholder="1" >
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="giftUserId">赠送给</label>
           <div class="controls">
           <input type="text" name="giftUserId" placeholder="10002" >
           </div>
           </div>
           <div class="control-group">
           <label class="control-label" for="comment">说明</label>
           <div class="controls">
           <input type="text" name="comment" placeholder="" >
           </div>
           </div>
            <div class="controls">
         <button class="btn btn-primary" type="submit">赠送</button>
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
    <script src="bootstrap/js/bootstrap.min.js"></script>

  

</body>
</html>