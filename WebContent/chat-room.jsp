<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("path",basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CasinoGod - chatRoom</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<style type="text/css">
dd{
	margin:0;
	margin-left:5px;
}
dt{
	margin:0;
	font-weight: bold;
	font-size:14px;
	color:#6389c4;
	font-style: italic;
}
</style>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/interface/Chat.js"></script>
<script type="text/javascript">
window.onload=function(){
	dwr.engine.setActiveReverseAjax(true); 
	Chat.updateUserList();
};
//设置接收人
function setReceiver(obj){ 
	document.getElementById("reply-to").innerHTML = "接收人：" + obj.innerHTML;
	$('#replyTo').val($(obj).attr('id'));
}

//接受消息
function addChatContent(username,msg){
	$('#chatContent').append($('<dt></dt>').append(username+':'));
	$('#chatContent').append($('<dd></dd>').append(msg));
}
//发送消息

function sendMsg(){
	if($('#content').val()){
		if(parseInt($.trim($('#replyTo').val()), 0)==0){
		 	Chat.send($('#content').val());
		 	$('#content').val('');
		 }else{
		 	Chat.sendTo(parseInt($.trim($('#replyTo').val(), 0)),$('#content').val());
		 }
	 }
}

//用户退出
window.onunload=function(){
		//怎样监听ScriptSession失效
		//IE此处有问题，它可能采用事件冒泡形式，关闭之前不会执行该方法
		Chat.userExit($('#userid').val());
		dwr.engine.setNotifyServerOnPageUnload(true);
	    alert("退出聊天室");
};
//接受更新用户列表 
function userslist(list){
	$('#userlist li:not(:first)').remove();
	for ( var i in list)  {
		var em=$('<li><a href="javascript:void(0);" id="'+i+'" onclick="setReceiver(this);" >'+list[i]+'</a></li>');
		$('#userlist').append(em);
	}
}
</script>
</head>
<body>
<div id="chat-room">
	<div class="lefter" >
		<h2>在线用户列表</h2>
		<ul id="userlist">
			<li><a href="javascript:void(0)" id="0" onclick="setReceiver(this);" >所有人</a></li>
		</ul>
	</div>
	<div class="righter">
		<div id="history">
			<dl id="chatContent">
			</dl>
		</div>
		<div class="spacer"></div>
		<div id="reply-to">接收人：所有人</div><input type="hidden" id="replyTo" value="0" />
		<div class="reply">
			<input type="hidden" id="userid" value="${user.userId}"/>
			<input type="text" class="text" id="content"  style="width:350px;"/><label class="ui-blue">
			<input id="send" onclick="sendMsg()" type="button" value="发送" /></label>
		</div>
	</div>
</div>
</body>
</html>
