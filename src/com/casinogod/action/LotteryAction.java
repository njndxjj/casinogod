package com.casinogod.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Lottery;
import com.casinogod.pojo.LotteryHistory;
import com.casinogod.pojo.User;
import com.casinogod.service.LotteryConfigService;
import com.casinogod.service.LotteryHistoryService;
import com.casinogod.service.UserProfileService;
import com.opensymphony.xwork2.ActionSupport;

public class LotteryAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int lotteryId;
	
	private long userId;

	private String lotteryValue;
	
	private int betAmount;
	
	private boolean result;
	
	private int num;
	
	private String betDateTime;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private LotteryConfigService lotteryConfigService;
	
	private LotteryHistoryService lotteryHistoryService;
	
	private UserProfileService userProfileService;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setLotteryValue(String lotteryValue) {
		this.lotteryValue = lotteryValue;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	public void setResult(boolean result) {
		this.result = result;
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
	
	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
	}

	public void setLotteryConfigService(LotteryConfigService lotteryConfigService) {
		this.lotteryConfigService = lotteryConfigService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	

	public void setLotteryHistoryService(LotteryHistoryService lotteryHistoryService) {
		this.lotteryHistoryService = lotteryHistoryService;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public String queryAll()
	{
		boolean flag=false;
		
		List <Lottery> list=lotteryConfigService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("lotteryInfos", list);
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addLotteryInfo()
	{
		boolean flag=false;
		
        List <LotteryHistory> list=lotteryHistoryService.queryById(this.lotteryId);
        
        
        if(list.size()>0)
        {
        	LotteryHistory lhistory=list.get(0);
        	
        	flag=lotteryConfigService.addLottery(this.userId, this.lotteryId,lhistory.getLotteryType(), 
        			this.lotteryValue, this.betAmount, this.result,0,this.num,this.betDateTime);	 	
        	
        	if(flag)
        	{
        		//update the userProfile
        		
        		User user=userProfileService.queryUserById(this.userId).get(0);
        		
        		if(this.result)
        		{
        			user.setGold(user.getGold()+this.betAmount);
        		}
        		else
        		{
        			user.setGold(user.getGold()-this.betAmount);
        		}
        		user.setDiamond(user.getDiamond());
        		userProfileService.updateGold(user);
        	       		
        		return queryAll();
    	    	
     	        
        	}
        }
	
		return ERROR;
		
	}
	
	public String queryByUserId()
	{
		boolean flag=false;
		
		List <Lottery> list=lotteryConfigService.queryByUserId(this.userId);
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("lotteryInfos", list);
		}
		
		return flag? SUCCESS:ERROR;
	}

	public void setNum(int num) {
		this.num = num;
	}	
	
	
	

}
