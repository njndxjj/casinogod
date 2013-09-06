<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>用户登入</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/pic/glyphicons/png/glyphicons_037_coins.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/pic/glyphicons/png/glyphicons_037_coins.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/pic/glyphicons/png/glyphicons_037_coins.png">
    <link rel="apple-touch-icon-precomposed" href="/pic/glyphicons/png/glyphicons_037_coins.png">
    
    <link rel="shortcut icon" href="/pic/glyphicons/png/glyphicons_037_coins.png">
    <style type="text/css"></style></head>

  <body>

    <div class="container" >

      <form class="form-signin" action="userRegister.action" method="post">
        <h2 class="form-signin-heading">注册</h2>
         <div class="control-group">
         <label class="control-label" for="nickName">昵称</label>
         <div class="controls">
         <input type="text" name="nickName" placeholder="王小二">
         </div>
         </div>
         <div class="control-group">
         <label class="control-label" for="password">密码</label>
         <div class="controls">
         <input type="password" name="password" placeholder="123456">
         </div>
         </div>
         <div class="control-group">
         <label class="control-label" for="gender">性别</label>
         <label class="radio">
         <input type="radio" name="gender" id="gender1" value="m" checked>男
         </label>
         <label class="radio">
         <input type="radio" name="gender" id="gender2" value="f">女
         </label>
         </div>
         <div class="control-group">
         <label class="control-label" for="emailAddress">邮箱</label>
         <div class="controls">
         <input type="text" name="emailAddress" placeholder="test@gamil.com">
         </div>
         </div>
         <div class="control-group">
         <label class="control-label" for="telephone">手机号码</label>
         <div class="controls">
         <input type="text" name="telephone" placeholder="18004756987">
         </div>
         </div>
        <button class="btn btn-large btn-primary" type="submit">注册</button>
      </form>

    </div> 
    <script src="//cdnjs.bootcss.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
<html>
