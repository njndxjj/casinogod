package com.casinogod.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.casinogod.pojo.AuthToken;
import com.casinogod.service.AuthTokenService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;



public class AuthorityInterceptor extends MethodFilterInterceptor implements ServletRequestAware
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -178936577238291639L;
	private static Logger log = Logger.getLogger(AuthorityInterceptor.class); 
	private HttpServletRequest request;
	private AuthTokenService authTokenService;

	

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub		
		this.request=request;		
	}
	
	

	public void setAuthTokenService(AuthTokenService authTokenService) {
		this.authTokenService = authTokenService;
	}


	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("authrity useraccount");
		
		log.info("==>"+actionInvocation.getInvocationContext().getName());  
		log.info("====>"+actionInvocation.getInvocationContext().getLocale());  
		log.info("====>"+actionInvocation.getInvocationContext().getParameters());  
		
		String useraccount=(String)ServletActionContext.getRequest().getSession().getAttribute("account");
		
		if(useraccount==null)
			
		{
			//Map paramMap = actionInvocation.getInvocationContext().getParameters();
			
			
		
	//		String[] userTokens = (String[]) paramMap.get("authToken");
	//		String userToken=CustomBase64.decode(userTokens[0]);
		
	//		String[] accounts = (String[]) paramMap.get("account");
	//		String account=CustomBase64.decode(accounts[0]);
			
			 String postdata="";
			 String decode="";
			    
			 try {
					postdata=Utility.postdata(this.request);
					decode=CustomBase64.decode(postdata);
			    	System.out.println("postdata-->"+postdata);
			    	System.out.println("decode--->"+CustomBase64.decode(postdata));
				
			    } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			String account=Utility.splitString(decode, "account");
			String userToken=Utility.splitString(decode, "authToken");
			
			log.info("userToken "+userToken);
			log.info("userAccount "+account);
			
			List <AuthToken>list=authTokenService.queryByUserId(Long.valueOf(account));
		
			if(list.size()>0)
				
			{
			
				if(userToken.equals(list.get(0).getAuthToken()))
				{
					log.info("authrity userToken:"+userToken);
					return actionInvocation.invoke();
				}
				else
					return Action.LOGIN;
			}
			else
			{
				return Action.LOGIN;
			}
		}
		else
		{
			return actionInvocation.invoke();				
		}
	}




}