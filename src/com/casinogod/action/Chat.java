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
	//��ͻ���������Ϣ(������)
	public void send(final String msg){
		if(msg==null||"".equals(msg)){
			return;
		}
		Browser.withCurrentPage(new Runnable() {
			public void run() {
				//��ȡ��ǰҳ��ص�ScriptSession
				Collection<ScriptSession> map=Browser.getTargetSessions();
				//��ȡ�����û�User
				WebContext wc=WebContextFactory.get();
				Object obj= wc.getSession().getAttribute("user");
				//����javascript���
				ScriptBuffer sb=null;
				if(obj!=null){
					sb=new ScriptBuffer("addChatContent('"+((User)obj).getNickName()+"  "+Utility.getNowString()+"','"+msg+"');");
				}else{
					sb=new ScriptBuffer("addChatContent('����','"+msg+"');");
				}
				//������Ϣ
				for (ScriptSession scriptSession : map) {
					scriptSession.addScript(sb);
				}
				log.info("�û����ԣ�"+sb.toString());
			}
		});
		//�����û��б�
		updateUserList();
	}
	//��ͻ���������Ϣ�����ˣ�
	public void sendTo(final long toId,final String msg){
		if(msg==null||"".equals(msg)){
			return;
		}		
		Browser.withCurrentPage(new Runnable() {
			public void run() {
				//��ȡ�洢��ServletContext�е�ScriptSession��Map
				ServerContext sc= ServerContextFactory.get();
				ServletContext servletContext= sc.getServletContext();
				if(servletContext.getAttribute("ScriptSessionMap")==null){
					servletContext.setAttribute("ScriptSessionMap", new HashMap<String,ScriptSession>());
				}
				//ȡ��Map
				Map<String,ScriptSession> sss=(Map<String,ScriptSession>) servletContext.getAttribute("ScriptSessionMap");
				
				//��ȡ��ǰҳ��ص�ScriptSession
				Collection<ScriptSession> collection=sss.values();
				
				//��ȡ�����û�User
				WebContext wc=WebContextFactory.get();
				Object obj= wc.getSession().getAttribute("user");
				ScriptSession currentSS=wc.getScriptSession();
				//���ҽ����û�
				User toUser=null;
				ScriptSession toss=null;
				for (ScriptSession scriptSession : collection) {
					User user=(User) scriptSession.getAttribute("user");
					if(user.getUserId()==toId){
						toUser=user;
						toss=scriptSession;
					}
				}
				
				//����javascript���
				ScriptBuffer sb=null;
				if(obj!=null&&toUser!=null){
					
					sb=new ScriptBuffer("addChatContent('"+((User)obj).getNickName()+"��"+toUser.getNickName()+"˵"+"  "+Utility.getNowString()+"','"+msg+"');");
					currentSS.addScript(sb);
					if(((User)obj).getUserId()!=toUser.getUserId()){
						toss.addScript(sb);
					}
				}else if(obj!=null&&toUser==null){
					sb=new ScriptBuffer("addChatContent('ϵͳ��Ϣ��','�Է������ߣ�����ʧ��');");
					currentSS.addScript(sb);
				}
				
				log.info(sb.toString());
				
			
			}
		});
		//�����û��б�
		updateUserList();
	}
	//���¿ͻ����б�
	public void updateUserList(){
		log.info("�����û��б�ʼ");
		Browser.withCurrentPage(new Runnable() {
			public void run() {
				//��ȡ�洢��ServletContext�е�ScriptSession��Map
				ServerContext sc= ServerContextFactory.get();
				ServletContext servletContext= sc.getServletContext();
				if(servletContext.getAttribute("ScriptSessionMap")==null){
					servletContext.setAttribute("ScriptSessionMap", new HashMap<String,ScriptSession>());
				}
				//ȡ��Map
				Map<String,ScriptSession> sss=(Map<String,ScriptSession>) servletContext.getAttribute("ScriptSessionMap");
				//��ȡ��ǰҳ��ص�ScriptSession
				Collection<ScriptSession> map=sss.values();
				//�����û��б�
				Map<Long,String> users=new HashMap<Long,String>();
				for (ScriptSession scriptSession : map) {
					//�����û��б�
					User us=(User) scriptSession.getAttribute("user");
					users.put(us.getUserId(),us.getNickName());
				}
				//����javascript���
				ScriptBuffer sb=new ScriptBuffer();
				sb.appendCall("userslist", users);
				//������Ϣ
				for (ScriptSession scriptSession : map) {
					scriptSession.addScript(sb);
				}
				log.info("�����û��б�"+users);
			}
		});
	}
	
	//�û��˳�
	public void userExit(int userid){
		log.info("�˳��û���ID��"+userid);
		//�û����ߣ������û��б�
		//��ȡ�洢��ServletContext�е�ScriptSession��Map
		ServerContext sc= ServerContextFactory.get();
		ServletContext servletContext= sc.getServletContext();
		if(servletContext.getAttribute("ScriptSessionMap")==null){
			servletContext.setAttribute("ScriptSessionMap", new HashMap<String,ScriptSession>());
		}
		//��ȡ�˳��û���SessionID
		WebContext wc=WebContextFactory.get();
		String sessionId= wc.getSession().getId();
		//ȡ��Map
		Map<String,ScriptSession> map=(Map<String,ScriptSession>) servletContext.getAttribute("ScriptSessionMap");
		//�Ƴ�SessionID
		if( map.get(sessionId)!=null){
			ScriptSession ss=map.get(sessionId);
			User user= (User) ss.getAttribute("user");
			if(user.getUserId()==userid){
				map.remove(sessionId);
			}
		}
		updateUserList();
		log.info("��ǰ�û��б�:"+map);
	}
}
