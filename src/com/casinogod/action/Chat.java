package com.casinogod.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.casinogod.pojo.User;
import com.casinogod.utility.Utility;


public class Chat {
	
	private static Logger log = Logger.getLogger(Chat.class); 
	
	Map<String,ScriptSession> map=new HashMap<String, ScriptSession>();
	//向客户端推送消息(所有人)
	public void send(final String msg){
		if(msg==null||"".equals(msg)){
			return;
		}
		Browser.withCurrentPage(new Runnable() {
			public void run() {
				//获取当前页相关的ScriptSession
				Collection<ScriptSession> map=Browser.getTargetSessions();
				//获取发言用户User
				WebContext wc=WebContextFactory.get();
				Object obj= wc.getSession().getAttribute("user");
				//构建javascript语句
				ScriptBuffer sb=null;
				if(obj!=null){
					sb=new ScriptBuffer("addChatContent('"+((User)obj).getNickName()+"  "+Utility.getNowString()+"','"+msg+"');");
				}else{
					sb=new ScriptBuffer("addChatContent('匿名','"+msg+"');");
				}
				//发送消息
				for (ScriptSession scriptSession : map) {
					scriptSession.addScript(sb);
				}
				log.info("用户发言："+sb.toString());
			}
		});
		//更新用户列表
		updateUserList();
	}
	//向客户端推送消息（单人）
	public void sendTo(final long toId,final String msg){
		if(msg==null||"".equals(msg)){
			return;
		}		
		Browser.withCurrentPage(new Runnable() {
			public void run() {
				//获取存储在ServletContext中的ScriptSession的Map
				ServerContext sc= ServerContextFactory.get();
				ServletContext servletContext= sc.getServletContext();
				if(servletContext.getAttribute("ScriptSessionMap")==null){
					servletContext.setAttribute("ScriptSessionMap", new HashMap<String,ScriptSession>());
				}
				//取得Map
				Map<String,ScriptSession> sss=(Map<String,ScriptSession>) servletContext.getAttribute("ScriptSessionMap");
				
				//获取当前页相关的ScriptSession
				Collection<ScriptSession> collection=sss.values();
				
				//获取发言用户User
				WebContext wc=WebContextFactory.get();
				Object obj= wc.getSession().getAttribute("user");
				ScriptSession currentSS=wc.getScriptSession();
				//查找接受用户
				User toUser=null;
				ScriptSession toss=null;
				for (ScriptSession scriptSession : collection) {
					User user=(User) scriptSession.getAttribute("user");
					if(user.getUserId()==toId){
						toUser=user;
						toss=scriptSession;
					}
				}
				
				//构建javascript语句
				ScriptBuffer sb=null;
				if(obj!=null&&toUser!=null){
					
					sb=new ScriptBuffer("addChatContent('"+((User)obj).getNickName()+"向"+toUser.getNickName()+"说"+"  "+Utility.getNowString()+"','"+msg+"');");
					currentSS.addScript(sb);
					if(((User)obj).getUserId()!=toUser.getUserId()){
						toss.addScript(sb);
					}
				}else if(obj!=null&&toUser==null){
					sb=new ScriptBuffer("addChatContent('系统消息：','对方不在线，发送失败');");
					currentSS.addScript(sb);
				}
				
				log.info(sb.toString());
				
			
			}
		});
		//更新用户列表
		updateUserList();
	}
	//更新客户端列表
	public void updateUserList(){
		log.info("更新用户列表开始");
		Browser.withCurrentPage(new Runnable() {
			public void run() {
				//获取存储在ServletContext中的ScriptSession的Map
				ServerContext sc= ServerContextFactory.get();
				ServletContext servletContext= sc.getServletContext();
				if(servletContext.getAttribute("ScriptSessionMap")==null){
					servletContext.setAttribute("ScriptSessionMap", new HashMap<String,ScriptSession>());
				}
				//取得Map
				Map<String,ScriptSession> sss=(Map<String,ScriptSession>) servletContext.getAttribute("ScriptSessionMap");
				//获取当前页相关的ScriptSession
				Collection<ScriptSession> map=sss.values();
				//在线用户列表
				Map<Long,String> users=new HashMap<Long,String>();
				for (ScriptSession scriptSession : map) {
					//加入用户列表
					User us=(User) scriptSession.getAttribute("user");
					users.put(us.getUserId(),us.getNickName());
				}
				//构建javascript语句
				ScriptBuffer sb=new ScriptBuffer();
				sb.appendCall("userslist", users);
				//发送消息
				for (ScriptSession scriptSession : map) {
					scriptSession.addScript(sb);
				}
				log.info("更新用户列表："+users);
			}
		});
	}
	
	//用户退出
	public void userExit(int userid){
		log.info("退出用户的ID："+userid);
		//用户下线，更新用户列表
		//获取存储在ServletContext中的ScriptSession的Map
		ServerContext sc= ServerContextFactory.get();
		ServletContext servletContext= sc.getServletContext();
		if(servletContext.getAttribute("ScriptSessionMap")==null){
			servletContext.setAttribute("ScriptSessionMap", new HashMap<String,ScriptSession>());
		}
		//获取退出用户的SessionID
		WebContext wc=WebContextFactory.get();
		String sessionId= wc.getSession().getId();
		//取得Map
		Map<String,ScriptSession> map=(Map<String,ScriptSession>) servletContext.getAttribute("ScriptSessionMap");
		//移除SessionID
		if( map.get(sessionId)!=null){
			ScriptSession ss=map.get(sessionId);
			User user= (User) ss.getAttribute("user");
			if(user.getUserId()==userid){
				map.remove(sessionId);
			}
		}
		updateUserList();
		log.info("当前用户列表:"+map);
	}
}
