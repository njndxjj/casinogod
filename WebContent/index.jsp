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

      <form class="form-signin" action="login.action" method="post">
        <h2 class="form-signin-heading">登入</h2>
        <input type="text" name="account" class="input-block-level" placeholder="账号">
        <input type="password" name="password" class="input-block-level" placeholder="密码">
        <button class="btn btn-large btn-primary" type="submit">登入</button>
        <a class="btn btn-large btn-primary" href="register.action">注册</a>
      </form>

    </div> 
    <script src="//cdnjs.bootcss.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
<html>
