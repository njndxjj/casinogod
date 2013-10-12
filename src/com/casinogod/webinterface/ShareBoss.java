package com.casinogod.webinterface;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javapns.communication.exceptions.CommunicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.battle.EnvType;
import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossInstanceUserInfo;
import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.User;
import com.casinogod.service.BossBattleService;
import com.casinogod.service.BossInstanceUserService;
import com.casinogod.service.LogInRewardService;
import com.casinogod.service.UserDeviceService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.PushNotificationThread;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class ShareBoss extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
			
	private BossInstanceUserService bossInstanceService;
	
	private LogInRewardService logInRewardService;
	
	private UserDeviceService userDeviceService;
	
	private UserProfileService userProfileService;
	
	private BossBattleService bossBattleService;
	
	private  UserLogIn userLogInService;
	
//  private final static String keystore="D:\\workspace\\CasinoGod\\WebContent\\pnkey\\PokerKingAPNCertificates.p12";
    
    private final static String keystore="/usr/local/apache-tomcat-6.0.37/webapps/CasinoGod/WebContent/pnkey/PokerKingAPNCertificates.p12";
    
	private final static String password="GodCasino201#";


	

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.resquest=request;
	}
	
	
	

	public void setBossInstanceService(BossInstanceUserService bossInstanceService) {
		this.bossInstanceService = bossInstanceService;
	}

   
	public void setLogInRewardService(LogInRewardService logInRewardService) {
		this.logInRewardService = logInRewardService;
	}
	
	

	public void setUserDeviceService(UserDeviceService userDeviceService) {
		this.userDeviceService = userDeviceService;
	}
	
	

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	
	public void setBossBattleService(BossBattleService bossBattleService) {
		this.bossBattleService = bossBattleService;
	}
	

	public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}


	private static Logger log = Logger.getLogger(ShareBoss.class); 

	public void shareBoss()
	
	{
		
		String postdata="";
		String decode="";
	    
	    try {
		
	    	postdata=Utility.postdata(resquest);
	    	decode=CustomBase64.decode(postdata);
	    	System.out.println("addLotteryInfo-->"+postdata);
	    	System.out.println("addLotteryInfo--->"+CustomBase64.decode(postdata));
		
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    String account=Utility.splitString(decode, "account");
	    String bossInstance=Utility.splitString(decode, "bossInstance");
	    String env=Utility.splitString(decode, "env");
			
		long userID=0;
		int battleTypePara=0;
		
		if(account!=null)
			userID = Long.parseLong(account);
		String responseJSON  = "";
		if(account!=null&&Integer.valueOf(bossInstance)>0)
		{
			//share raidboss 
			
			String currentTime =Utility.getNowString();
			
			String beforeTime =Utility.getBeforeHourAgoString(1);
			
		    List <Long> userIdList= logInRewardService.queryDate(beforeTime, currentTime);
		   
	    	List <Long> randUser=new ArrayList<Long>();
	    	
	    		    
	    	if(userIdList.size()>0&&userIdList.size()>=5)
		    {		    			    		    	
		    	for(int i=0;i<3;i++)
		    	{
		    		Random random=new Random();
					
					int randType=Math.abs(random.nextInt())%(userIdList.size());
					
					if(!randUser.contains(userIdList.get(randType))&&100!=userIdList.get(randType))
					{
						randUser.add(userIdList.get(randType));
						
					}
					else
					{
						i--;
					}
		    	}
		    	
		    }
		    else
		    {
		    	List <Long> userAll=logInRewardService.queryUser();
		    	
		    	for(int i=0;i<3;i++)
		    	{
		    		Random random=new Random();
					
					int randType=Math.abs(random.nextInt())%(userAll.size());
					
	//				List <BossInstanceUserInfo> allInstanceUser= bossInstanceService.queryByUserId(randType);
					
					if(!randUser.contains(userAll.get(randType))&&100!=userAll.get(randType))
					{
						List <BossInstanceUserInfo> allInstanceUser= bossInstanceService.queryByUserId(randType);
						
						int mun=0;
						
						int sum=0;
						
						while(allInstanceUser.size()>0&&sum<=5)
						{
							for(BossInstanceUserInfo bs:allInstanceUser)
							{
								if(bossBattleService.queryByInstance(bs.getBossInstance()).get(0).getStatus()==1)
									mun++;
							}	
							
							if(mun>=5)
							{
								randType=Math.abs(random.nextInt())%(userAll.size());
								sum++;
							}
							else
								break;
						}
						
							
					   randUser.add(userAll.get(randType));
						
					}
					else
					{
						i--;
					}
		    	}
		    	
		    }
	    	
	    List <String> tokens=new ArrayList<String>();
	    
	    //invoke push method
	    PushNotificationThread pn=new PushNotificationThread();
	     
	    boolean production=EnvType.values()[Integer.valueOf(env)].toString().equalsIgnoreCase(("Production"));
	    
	    List <SimpleUser> simpleUserList=new ArrayList<SimpleUser>();
	    
	    List <String> snsIds=new ArrayList<String>();
	    			
		for(long id:randUser)
		{
			log.info("userId-->"+id);
			
			List <BossInstanceUserInfo> allInstanceUser= bossInstanceService.queryByUserId(id);
			
			List <User> userList=userProfileService.queryUserById(id);
			
			String snsId=userLogInService.getAccount(id).getSnsId();
			if(snsId==null) snsId="";

			
			if(allInstanceUser.size()>0)
			{
				//no do
			}
			else
				bossInstanceService.addBossInstanceUserInfo(Integer.valueOf(CustomBase64.decode(bossInstance)), Long.valueOf(id), 0);	
			
			SimpleUser simpleUser=new SimpleUser();
		    
		    User user=new User();
			
			if(userList.size()>0)
			{
				user=userList.get(0);
				simpleUser.setGold(user.getGold());
				simpleUser.setExp(user.getExp());
				simpleUser.setGender(user.getGender());
				simpleUser.setImage(user.getImage());
				simpleUser.setLevel(user.getLevel());
				simpleUser.setNickName(user.getNickName());
				simpleUser.setUserId(user.getUserId());
				
				simpleUserList.add(simpleUser);
				
				snsIds.add(snsId);
			}
				
			
			//add token
			

		    List <String> tokenByUser=userDeviceService.queryById(id);
		    
		    if(tokenByUser.size()>0)
		    	tokens.addAll(tokenByUser);
		    
		}
		

		//response
		
//		List <BossInstanceUserInfo> allInstanceUser= bossInstanceService.queryByInstance(this.bossInstance);
		
		if(simpleUserList.size()>0)
		{
			
			Map <String,Object> map=new HashMap<String, Object>();
			
			map.put("simpleUser", simpleUserList);
			map.put("snsId", snsIds);
			
			responseJSON += JSONObject.fromObject(map).toString();
			  
			response.setStatus(200);
		}
		else
		{
			 ErrorResponse errorResponse = new ErrorResponse();
			 errorResponse.setErrorMessage("share battle fail!");
			 errorResponse.setErrorAction("");
			 errorResponse.setErrorCode(ErrorCode.Battle_shareRaidBoss);
				
			 responseJSON = JSONObject.fromObject(errorResponse).toString();
			 response.setStatus(401);
		}
		
		
		try {
			responseJSON=CustomBase64.encode(responseJSON);	
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			log.info("response-->"+responseJSON);
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		finally
		{
			try {
				pn.sendPush(keystore, password, production, tokens, true, 2,"快来加入raidBoss战斗吧!!");
			} catch (CommunicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
  }
	
	
	
}	
	
	
	
}
