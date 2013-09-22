package com.casinogod.webinterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.battle.UserType;
import com.casinogod.dao.ConfigurationDAOImpl;
import com.casinogod.pojo.AuthToken;
import com.casinogod.pojo.Configuration;
import com.casinogod.pojo.LogInReward;
import com.casinogod.pojo.LogInRewardConfig;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserAuthResponse;
import com.casinogod.service.AuthTokenService;
import com.casinogod.service.FriendInvitedService;
import com.casinogod.service.LogInRewardConfigService;
import com.casinogod.service.LogInRewardService;
import com.casinogod.service.UserDeviceService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfile;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.DataStore;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.MD5Util;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAuth extends ActionSupport implements ServletResponseAware,ServletRequestAware {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   
    
	private UserLogIn userLogInService;
	
	private UserProfile userProfileService;
	
	
	private LogInRewardService logInRewardService;
	
	private LogInRewardConfigService logInRewardConfigService;
	
	private UserDeviceService userDeviceService;
	
	private AuthTokenService authTokenService;
	
	private FriendInvitedService friendInvitedService;
	

	
	private ConfigurationDAOImpl configurationDAO;
	
	private HttpServletResponse response;
	
	private HttpServletRequest request;
	

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub		
		this.request=request;		
	}
	

	public void setFriendInvitedService(FriendInvitedService friendInvitedService) {
		this.friendInvitedService = friendInvitedService;
	}

	private static Logger log = Logger.getLogger(UserAuth.class); 
    
  //private HttpServletRequest request=ServletActionContext.getRequest();
   
    public void userAuth() throws UnsupportedEncodingException  {
        
    User user=null ;  
    
    boolean flag=false;
    
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
    String pw=Utility.splitString(decode, "password");
    String userType=Utility.splitString(decode, "userType");
    String snsId=Utility.splitString(decode, "snsId");
    String sToken=Utility.splitString(decode, "snsToken");
    String deviceToken=Utility.splitString(decode, "deviceToken");
    

    List <Configuration> listConfig=configurationDAO.querAll();
    
    if(listConfig.size()>0)
    {
    	Configuration configuration=listConfig.get(0);
    	DataStore.setting.put("winTimes", configuration.getWinTimes());
    	DataStore.setting.put("rankSize", configuration.getRankSize());
    	DataStore.setting.put("bossRewardNum", configuration.getBossRewardNum());
    	DataStore.setting.put("invitedReward", configuration.getInvitedReward());
    	DataStore.setting.put("invitedItem", configuration.getInvitedItem());
    }
    log.info("winTimes--->"+(Integer) DataStore.setting.get("winTimes"));
    
    if(pw!=null)
    {
    
    	String password=MD5Util.string2MD5(pw);
   
    	user = userLogInService.login(Integer.valueOf(account), password); 
    
    	flag = userLogInService.isFreeze(Integer.valueOf(account));
    }
    
    if(Integer.valueOf(userType)>=0&&snsId!=null)
    {
    	String snsToken=MD5Util.string2MD5(sToken);
    	
    	user=userLogInService.logInSNS(UserType.values()[Integer.valueOf(userType)].toString(), snsId); 
    	
    	if(user==null)
    	{
    		//register the new account
    		boolean gender;
    		if(!DataStore.registerSex.containsKey("gender"))
    		{
    			gender=false;
    		}
    		else
    		{
    			gender=DataStore.registerSex.containsKey("gender");
    		}
    		String nickName=Utility.randomString();
    		String password=MD5Util.string2MD5("111111");
    		gender=userProfileService.addUserSNA(nickName, password, gender?"f":"m", "test@test.com", "123456", UserType.values()[Integer.valueOf(userType)].toString(), snsId, snsToken);
    		DataStore.registerSex.put("gender", gender);
    		user=userLogInService.logInSNS(UserType.values()[Integer.valueOf(userType)].toString(), snsId); 
    		
        }
    	
    	flag = userLogInService.isFreeze(user.getUserId());
    	
    	
    }
    
    String responseJSON= "";    
   
    if(user!=null)  
    {  
    
      log.info("user find success: "+user.getUserId());
    	
      Map<String, Object> attibutes = ActionContext.getContext().getSession();// user session 
      
      attibutes.put("username", user.getNickName());  
      attibutes.put("account", user.getUserId());
    	
       if(flag)
        {
        	List <LogInReward> list=null;
        	
        	list=logInRewardService.queryById(user.getUserId());
        	
        	if(list.size()<=0)
        	{
        		logInRewardService.add(user.getUserId(), Utility.getNowString(), 1);
        		
        		//create friend code
        		
        		log.info("create friend invite code");
        		
        		String code="";
        			
        	    code =String.valueOf(user.getUserId());
        		       
        		friendInvitedService.addFriendInvite((int)user.getUserId(), code, Utility.getNowString(),0);
        		
        		List <LogInRewardConfig> logConfig=logInRewardConfigService.queryByDay(1);
    			
    			if(logConfig.size()>0)
    			{
    				int reward=logConfig.get(0).getReward();
    				
    				int exp=user.getExp()+reward;
    				
    				user.setExp(exp);
    				
    				userProfileService.updateExp(user);
    			}
        	}
        	else 
        	{
        	 
        		List <LogInReward> rewardList=logInRewardService.queryById(user.getUserId());
        		
        		String currentTime=Utility.getNowString();
        		
        		String currentDate=currentTime.split(" ")[0];
        		
        		String lastDate=rewardList.get(0).getLogInTime().split(" ")[0];
        		
        		if(currentDate.compareToIgnoreCase(lastDate)>0)
        		{
        			//get the yesterday day
        			Calendar calendar=Calendar.getInstance();
        			calendar.add(Calendar.DATE,-1);
        			
        			String yseterday=new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        			
        			log.info("ysetesday day: "+yseterday);
        			
        			if(yseterday.compareToIgnoreCase(lastDate)==0)
        			{	
        				//update 
        				logInRewardService.update(user.getUserId(), currentTime, rewardList.get(0).getLastTimes()+1);
        			
        				rewardList=logInRewardService.queryById(user.getUserId());
        			
        				int day=rewardList.get(0).getLastTimes();
        			
        				List <LogInRewardConfig> logConfig=logInRewardConfigService.queryByDay(day);
        			
        				if(logConfig.size()>0)
        				{
        					int reward=logConfig.get(0).getReward();
        				
        					int exp=user.getExp()+reward;
        				
        					user.setExp(exp);
        				
        					userProfileService.updateExp(user);
        				}
        			}
        			else
        			{
        				logInRewardService.update(user.getUserId(), currentTime, 0);
        			}
        			
        		}
        		else 
        		{
        	      //update 
               	  logInRewardService.update(user.getUserId(), currentTime, rewardList.get(0).getLastTimes());
        		}
        		
        		//update device token
        		if(deviceToken!=null)
        		{
        			List <String> listDevice=userDeviceService.queryById(user.getUserId());
        			if(listDevice.size()<=0)
        			{
        				String []token=deviceToken.substring(1, deviceToken.length()-1).split(" ");
        				StringBuffer device =new StringBuffer();
        				for(String test:token)
        					device.append(test);
        				
        			    flag=userDeviceService.addDevice(user.getUserId(), device.toString(), Utility.getNowString(),null);
        			}
        		}
        		
        	}
        }
       String snsid=userLogInService.getAccount(user.getUserId()).getSnsId();
        UserAuthResponse uaResponse = new UserAuthResponse();
	    uaResponse.setUserinfo(user);
	    uaResponse.setSnsId(snsid);
	    String tokenString=Utility.randomString(16);
	    uaResponse.setToken(tokenString);//Need real code	    
	    uaResponse.setGameServerUrl(GameServerFactory.getGameServer(user.getUserId()));	    
//	    String tokenValue=CustomBase64.encode(URLEncoder.encode(uaResponse.getToken(),"utf-8"));
		responseJSON = JSONObject.fromObject(uaResponse).toString();
//	    DataStore.registerToken.put(String.valueOf(user.getUserId()), uaResponse.getToken());
		List <AuthToken>list=authTokenService.queryByUserId(user.getUserId());
		if(list.size()>0)
		{
			AuthToken auth=new AuthToken();
			auth.setUserId(user.getUserId());
			auth.setAuthToken(tokenString);
			
			authTokenService.updateToken(auth);
			
			log.info("update suceess");
		}
		else
		{
			authTokenService.addAuth(user.getUserId(), uaResponse.getToken());
		}

		response.setStatus(200);	
    }
    else if(user!=null&&!flag)
    {
    	ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("the user account is freezed");
		errorResponse.setErrorAction("");
		errorResponse.setErrorCode(ErrorCode.UserAuth_LoginFail);
			
		responseJSON = JSONObject.fromObject(errorResponse).toString();
		response.setStatus(402);
    }
	else
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("Cannot find the User: " + account + ", " + pw);
		errorResponse.setErrorAction("");
		errorResponse.setErrorCode(ErrorCode.UserAuth_AccountFreeze);
			
		responseJSON = JSONObject.fromObject(errorResponse).toString();
		response.setStatus(401);
	}
		
	try {
		responseJSON=CustomBase64.encode(responseJSON);	
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(responseJSON);
		
        log.info("response message :"+responseJSON);
			
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
               
    }
    
   

	public void setUserLogInService(UserLogIn userLogInService) {
		
		System.out.println("");
		this.userLogInService = userLogInService;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void setUserProfileService(UserProfile userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setLogInRewardService(LogInRewardService logInRewardService) {
		this.logInRewardService = logInRewardService;
	}

	public void setLogInRewardConfigService(
			LogInRewardConfigService logInRewardConfigService) {
		this.logInRewardConfigService = logInRewardConfigService;
	}

	
	public void setUserDeviceService(UserDeviceService userDeviceService) {
		this.userDeviceService = userDeviceService;
	}

	public void setAuthTokenService(AuthTokenService authTokenService) {
		this.authTokenService = authTokenService;
	}

	public void setConfigurationDAO(ConfigurationDAOImpl configurationDAO) {
		this.configurationDAO = configurationDAO;
	}

	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
