package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;

import com.casinogod.dao.ConfigurationDAOImpl;
import com.casinogod.pojo.Configuration;
import com.casinogod.utility.DataStore;





public class MySessionListener implements HttpSessionListener {
	
	private static Logger log = Logger.getLogger(MySessionListener.class); 
	
	public void sessionCreated(HttpSessionEvent se) {
		ServerContext sc=ServerContextFactory.get();
		Container container= sc.getContainer();
		ScriptSessionManager ssm = (ScriptSessionManager) container.getBean(ScriptSessionManager.class.getName());
		ssm.addScriptSessionListener(new ScriptSessionListener() {
			
			public void sessionDestroyed(ScriptSessionEvent sse) {
				log.info("scriptSession����");
			}
			
			
			public void sessionCreated(ScriptSessionEvent sse) {
				/*
				 * ȱ�ݣ�ÿ��Sessionֻ����һ���û���½���������û���½������ÿˢ��һ�Σ��������һ���µ�ScriptSession
				 * 		ͬʱÿ��Session�ж���û�����ô���޷��ж����ĸ��û����е�ˢ��
				 * 
				 */
				//��ȡ�洢��ServletContext�е�ScriptSessionMap
				ServerContext sc= ServerContextFactory.get();
				ServletContext servletContext= sc.getServletContext();
				if(servletContext.getAttribute("ScriptSessionMap")==null){
					servletContext.setAttribute("ScriptSessionMap", new HashMap<String,ScriptSession>());
				}
				//ȡ��Map
				Map<String,ScriptSession> map=(Map<String,ScriptSession>) servletContext.getAttribute("ScriptSessionMap");
				//��ȡSessionId
				String httpSessionId= WebContextFactory.get().getSession().getId();
				Object user =WebContextFactory.get().getSession().getAttribute("user");
				sse.getSession().setAttribute("user", user);
				map.put(httpSessionId, sse.getSession());
				
				log.info(httpSessionId+" -- scriptSession����");
				
			
			}
		});
	}

	
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}
	



}
