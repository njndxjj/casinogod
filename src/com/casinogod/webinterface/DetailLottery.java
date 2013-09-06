package com.casinogod.webinterface;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Lottery;
import com.casinogod.pojo.LotteryHistory;
import com.casinogod.pojo.User;
import com.casinogod.service.LotteryConfigService;
import com.casinogod.service.LotteryHistoryService;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.GameConfig;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class DetailLottery extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String lotteryId;
	
	private String userId;
	
	private String lotteryValue;
	
	private String betAmount;
	
	private String num;
	
	private String betDateTime;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private LotteryConfigService lotteryConfigService;
	
	private LotteryHistoryService lotteryHistoryService; 

	private UserProfileService userProfileService;

	private final String configName = "lottery-config";
	private final  GameConfig config = new GameConfig(configName);

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setLotteryValue(String lotteryValue) {
		this.lotteryValue = lotteryValue;
	}

	public void setBetAmount(String betAmount) {
		this.betAmount = betAmount;
	}



	public void setBetDateTime(String betDateTime) {
		this.betDateTime = betDateTime;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
	
	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public void setLotteryConfigService(LotteryConfigService lotteryConfigService) {
		this.lotteryConfigService = lotteryConfigService;
	}
	
	
	public void setLotteryHistoryService(LotteryHistoryService lotteryHistoryService) {
		this.lotteryHistoryService = lotteryHistoryService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	
	public void setNum(String num) {
		this.num = num;
	}


	public void addLotteryInfo()
	{
		boolean flag=false;
		
        List <LotteryHistory> list=lotteryHistoryService.queryById(
        		Integer.valueOf(CustomBase64.decode(this.lotteryId)));
        
        String responseJSON="";
        
        if(list.size()>0)
        {
        	LotteryHistory lhistory=list.get(0);
        	
        	if(Utility.getNowString().compareTo(lhistory.getOpenDateTime())<0)
        		
        	{
        	//default level is 0
        	flag=lotteryConfigService.addLottery(
        			Long.valueOf(CustomBase64.decode(this.userId)),
        			Integer.valueOf(CustomBase64.decode(this.lotteryId)),
        			lhistory.getLotteryType(), 
        			CustomBase64.decode(this.lotteryValue), 
        			Integer.valueOf(CustomBase64.decode(this.betAmount)), 
        			false,
        			0,Integer.valueOf(CustomBase64.decode(this.num)),
        			Utility.getNowString());	 
        	
        	
       	
        	if(flag)
        	{
        		//update the userProfile
        		
        		User user=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.userId)))
        				.get(0);
        		
        		if(user.getGold()-Integer.valueOf(CustomBase64.
    					decode(this.betAmount))*Integer.valueOf(CustomBase64.decode(this.num))<0)
        		{
        			ErrorResponse errorResponse = new ErrorResponse();
        			errorResponse.setErrorMessage("gold is enough");
        			errorResponse.setErrorAction("");
        			errorResponse.setErrorCode(ErrorCode.user_UserProfile);
        				
        			responseJSON = JSONObject.fromObject(errorResponse).toString();
        			response.setStatus(401);
        		}
        		
        		else
        		{
        		
	        		user.setGold(user.getGold()-Integer.valueOf(CustomBase64.decode(this.betAmount)));
	        		
	        		
	        		user.setDiamond(user.getDiamond());
	        		
	        		userProfileService.updateGold(user);
	        		
	        		List <Lottery> lotteryList=lotteryConfigService.queryByUserId(
	        				Integer.valueOf(CustomBase64.decode(this.userId)));
	        		
	        		HashMap< String, Object> map = new HashMap<String, Object>();
	    	    	
	     	        map.put("userLottery", lotteryList);
	     	       
	     	        responseJSON +=JSONObject.fromObject(map).toString();
	     	    	
	     			response.setStatus(200);
        		}
        	}
        	else
        	{
        		ErrorResponse errorResponse = new ErrorResponse();
    			errorResponse.setErrorMessage("detail lottery add error");
    			errorResponse.setErrorAction("");
    			errorResponse.setErrorCode(ErrorCode.lottery_Information);
    				
    			responseJSON = JSONObject.fromObject(errorResponse).toString();
    			response.setStatus(401);
        		
        	}
        }
        	else
        	{
        		ErrorResponse errorResponse = new ErrorResponse();
    			errorResponse.setErrorMessage("expired time");
    			errorResponse.setErrorAction("");
    			errorResponse.setErrorCode(ErrorCode.lottery_Information);
    				
    			responseJSON = JSONObject.fromObject(errorResponse).toString();
    			response.setStatus(401);
        	}
        }
        else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find any Lottery Infromation");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.lottery_Information);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
			
		try {
			
			responseJSON=CustomBase64.encode(responseJSON);
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	public void getLotteryHistory()
	{
		
        List <LotteryHistory> list=lotteryHistoryService.queryById(
        		Integer.valueOf(CustomBase64.decode(this.lotteryId)));
        
        String responseJSON="";
        
        if(list.size()>0)
        {
        	LotteryHistory lhistory=list.get(0);
        	
        	responseJSON +=JSONObject.fromObject(lhistory).toString();
	     	    	
	     	response.setStatus(200);
        		
        }
              
        else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find any Lottery Infromation");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.lottery_Information);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
			
		try {
			
			responseJSON=CustomBase64.encode(responseJSON);
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	public void getUserLotteryHistory()
	{
		
        List <Lottery> list=lotteryConfigService.queryByUserId(
        		Integer.valueOf(CustomBase64.decode(this.userId)));
        
        String responseJSON="";
        
        if(list.size()>0)
        {
        	Map <String,Object> map=new HashMap<String, Object>();
        	
        	responseJSON +=JSONObject.fromObject(list).toString();
	     	    	
	     	response.setStatus(200);
        		
        }
              
        else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find any Lottery Infromation");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.lottery_Information);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
			
		try {
			
			responseJSON=CustomBase64.encode(responseJSON);
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	public void checkLottery()
	{
		List <Lottery> list=lotteryConfigService.queryByUserIdAndId(
				Integer.valueOf(CustomBase64.decode(this.userId)),Integer.valueOf(CustomBase64.decode(this.lotteryId)));
		
		String  responseJSON="";
		int rate=0;
		int gold=0;
		
		User user=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.userId)))
				.get(0);
		
		if(list.size()>0)
		{
			
			Lottery lottery=list.get(0);
			
			List <LotteryHistory> lhistory=lotteryHistoryService.queryById(
	        		Integer.valueOf(CustomBase64.decode(this.lotteryId)));
			
			LotteryHistory lotteryHistory=lhistory.get(0);
			
			if(null==lotteryHistory.getResult())
			{
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setErrorMessage("Cannot get the reward time ");
				errorResponse.setErrorAction("");
				errorResponse.setErrorCode(ErrorCode.lottery_Information);
					
				responseJSON = JSONObject.fromObject(errorResponse).toString();
				response.setStatus(401);
			}
			else
			{
				String []uResult=lottery.getLotteryValue().split("+");
				String []lResult=lotteryHistory.getResult().split("+");
				
				int before = 0;
				int back=0;
				
				if(Utility.isDiff(uResult))
				{
				
					for(int i=0;i<6;i++)
					{
						for(int j=0;j<6;j++)
						{
							if(uResult[i].equals(lResult[j]))
								before++;
						}
					}
					
					for(int i=6;i<7;i++)
					{
						for(int j=6;j<7;j++)
						{
							if(uResult[i].equals(lResult[j]))
								back++;
						}
					}
					
					//much the level
					
					if(before==6&&back==1)
					{
						lottery.setResult(true);
						lottery.setLevel(1);
						
						//update gold
						
						rate=Integer.valueOf(config.getConfigValue("g1","rate"));
						
						
						
						
					}
					else if(before==6&&back==0)
					{
						lottery.setResult(true);
						lottery.setLevel(2);
						
						//update gold
						rate=Integer.valueOf(config.getConfigValue("g2","rate"));
					}
					else if(before==5&&back==1)
					{
						lottery.setResult(true);
						lottery.setLevel(3);
						
						//update gold
						rate=Integer.valueOf(config.getConfigValue("g3","rate"));
					}
					else if((before==5&&back==0)||(before==4&&back==1))
					{
						lottery.setResult(true);
						lottery.setLevel(4);
						
						//update gold
						rate=Integer.valueOf(config.getConfigValue("g4","rate"));
					}
					else if((before==4&&back==0)||(before==3&&back==1))
					{
						lottery.setResult(true);
						lottery.setLevel(5);
						
						//update gold
						rate=Integer.valueOf(config.getConfigValue("g5","rate"));
					}
					else if(before<=2&&back==1)
					{
						lottery.setResult(true);
						lottery.setLevel(6);
						
						//update gold
						rate=Integer.valueOf(config.getConfigValue("g6","rate"));
					}					
					else
					{
						lottery.setResult(false);
						lottery.setLevel(0);
						
						rate=0;
					}
					
					//update lottery
					
					user.setGold(user.getGold()+rate*lottery.getNum());
					user.setDiamond(user.getDiamond());
					
					
					boolean flag=lotteryConfigService.updateResult(lottery.getLevel(), lottery.isResult(),
							lottery.getUserId(), lottery.getLotteryId());
					
					if(flag)
					{
						userProfileService.updateExp(user);
						
						Lottery listLottery=lotteryConfigService.queryByUserIdAndId(
								Integer.valueOf(CustomBase64.decode(this.userId)),
								Integer.valueOf(CustomBase64.decode(this.lotteryId))).get(0);
						
						User userUpdate=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.userId)))
								.get(0);
						Map <String,Object> map=new HashMap<String, Object>();
						
						map.put("user", userUpdate);
						map.put("levelReward", listLottery.getLevel());
						map.put("num", listLottery.getNum());
						map.put("rate", rate);
						
						responseJSON=JSONObject.fromObject(map).toString();
						
						
					}
					else
					{
						ErrorResponse errorResponse = new ErrorResponse();
						errorResponse.setErrorMessage("update error");
						errorResponse.setErrorAction("");
						errorResponse.setErrorCode(ErrorCode.db_update);
							
						responseJSON = JSONObject.fromObject(errorResponse).toString();
						response.setStatus(401);
						
					}
					
					
				}
				else
				{
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setErrorMessage("invild input");
					errorResponse.setErrorAction("");
					errorResponse.setErrorCode(ErrorCode.lottery_rewarderror);
						
					responseJSON = JSONObject.fromObject(errorResponse).toString();
					response.setStatus(401);
				}
			}
			
		}
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot get the user lottery result");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.lottery_rewarderror);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
		
		try {
			
			responseJSON=CustomBase64.encode(responseJSON);
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	

}
