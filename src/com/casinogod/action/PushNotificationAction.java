package com.casinogod.action;

import java.util.ArrayList;
import java.util.List;

import javapns.communication.exceptions.CommunicationException;
import javapns.notification.PushedNotification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.battle.EnvType;
import com.casinogod.pojo.UserDevice;
import com.casinogod.service.UserDeviceService;
import com.casinogod.utility.PushNotificationThread;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class PushNotificationAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long userId;
	
	private String sendList;
	
	private String deviceToken;
	
	private String notes;
	
	private String env;
	
	private String text;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private UserDeviceService userDeviceService;
	
//	private final static String root=new File("WebContent").getAbsolutePath();
	
	private final static String keystore="D:\\workspace\\CasinoGod\\WebContent\\pnkey\\PokerKingAPNCertificates.p12";
	
//	private final static String keystore="/usr/local/apache-tomcat-6.0.37/webapps/CasinoGod/pnkey/PokerKingAPNCertificates.p12";
	
	private final static String password="GodCasino201#";
	

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public void setUserDeviceService(UserDeviceService userDeviceService) {
		this.userDeviceService = userDeviceService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
   
	public void setEnv(String env) {
		this.env = env;
	}

	public void setSendList(String sendList) {
		this.sendList = sendList;
	}
	

	public void setText(String text) {
		this.text = text;
	}

	public String addDeviceInfo()
	{
		boolean flag=false;
		
		flag=userDeviceService.addDevice(this.userId, this.deviceToken, Utility.getNowString(), this.notes);
		
		return flag?SUCCESS:ERROR;
	
	}
	
	public String updateDeviceInfo()
	{
		boolean flag=false;
		
		flag=userDeviceService.updateDevice(this.deviceToken, this.userId);
		
		return flag?SUCCESS:ERROR;
	
	}
	
	public String deleteDeviceInfo()
	{
		boolean flag=false;
		
		flag=userDeviceService.deleteDevice(this.userId);
		
		return flag?SUCCESS:ERROR;
	
	}
	
	public String findAllDevice()
	{
		boolean flag=false;
		
		List<UserDevice> list=userDeviceService.queryallDevice();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("userDevice", list);
		}
		
		return flag?SUCCESS:ERROR;
	}
	
	public String pushNotification() 
	{
		//invoke push method
	    PushNotificationThread pn=new PushNotificationThread();
	     
	    boolean production=EnvType.values()[Integer.valueOf(this.env)].toString().equalsIgnoreCase(("Production"));
	    
	    List <String> tokens=new ArrayList<String>();
	    
	    //get token list;	    
	    if(this.sendList.equals("@all"))  
	    {
	    	tokens=userDeviceService.queryall();
	    }
	    else
	    {
	    	for(String userId:this.sendList.split("@"))
	    	{
	    		if(userId.equals("")||userId==null) 
	    			continue;
	    		else
	    		{
	    			//add tokens
	    			List <String> tokenByUser=userDeviceService.queryById(Long.parseLong(userId));
	    			if(tokenByUser.size()>0)
	    				tokens.addAll(tokenByUser);
	    		}
	    	}
	    }
	    
	    System.out.println("keystore-->"+keystore);
	   
	    try {
			pn.sendPush(keystore, password, production, tokens, true, 2,this.text);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    		
	    return SUCCESS;
	}
}
