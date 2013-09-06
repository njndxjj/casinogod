<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>userAuth</title>
</head>
<body>
<form  action="userAuth.action" method="post">  
  <table align="center">
     <tr>
     <td>
      <h2><a href="./userProfile/register.jsp"> 用户注册</a> </h2>
      <h2><a href="./queryall.action"> 用户管理</a></h2>
     </td>
     </tr>
     <tr>
     <td> 
         账号:<input type="text" name="account" />  
    </td>
    </tr>
    <tr>
    <td>
           密码:<input type="password" name="password" />  
    </td>
    </tr>
    <tr>
    <td>
    <input type="submit" name="sub" value="登录" />      
    <input type="reset" name="reset" value="重置" /> 
    </td>
    </tr>  
</table>
 </form>
</body>
</html>