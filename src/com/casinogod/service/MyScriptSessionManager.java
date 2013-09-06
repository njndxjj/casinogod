package com.casinogod.service;


import java.util.Iterator;
import java.util.LinkedList;

import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.RealScriptSession;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.impl.DefaultScriptSessionManager;

public class MyScriptSessionManager extends DefaultScriptSessionManager implements ScriptSessionManager {
	LinkedList<ScriptSessionListener> ssl=new LinkedList<ScriptSessionListener>();
	@Override
	public void addScriptSessionListener(ScriptSessionListener arg0) {
		ssl.addLast(arg0);
	}

	@Override
	public RealScriptSession getScriptSession(String id, String url,
			String httpSessionId) {
		// TODO Auto-generated method stub
		RealScriptSession scriptSession= super.getScriptSession( id,url,httpSessionId);
		//µ÷ÓÃ¼àÌýÆ÷
		for (Iterator<ScriptSessionListener> it=ssl.iterator();it.hasNext();) {
			it.next().sessionCreated(new ScriptSessionEvent(scriptSession));
		}
		return scriptSession;
	}


	@Override
	public void removeScriptSessionListener(ScriptSessionListener arg0) {
			ssl.remove(arg0);
	}

	
}
