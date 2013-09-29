package com.casinogod.webinterface;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.RankType;
import com.casinogod.pojo.RankUserInfo;
import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserBattleResult;
import com.casinogod.service.BattleProfileService;
import com.casinogod.service.RankTypeService;
import com.casinogod.service.RankUserService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.service.UserResultService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.DataStore;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.mysql.jdbc.Util;
import com.opensymphony.xwork2.ActionSupport;

public class ShowRank extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	

	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;

	private RankUserService rankUserService;

	private UserProfileService userProfileService;
	
	private RankTypeService rankTypeService;
	
	private UserResultService userResultService;
	
	private  UserLogIn userLogInService;
	
	
	
  
	

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.resquest=request;
	}
	

	public void setRankUserService(RankUserService rankUserService) {
		this.rankUserService = rankUserService;
	}


	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setRankTypeService(RankTypeService rankTypeService) {
		this.rankTypeService = rankTypeService;
	}
	

	public void setUserResultService(UserResultService userResultService) {
		this.userResultService = userResultService;
	}
	
	



	public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}

	private static Logger log = Logger.getLogger(ShowRank.class); 

	public void showBlackJack()
	
	{
		
		String responseJSON  = "";
		
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
		
		int rank=0;
		
		int i=1;
		
		if(Integer.valueOf(account)>0)
		{
			List <RankType> rankTypeList=rankTypeService.queryById(1);
			
			if(rankTypeList.size()>0)
			{
				
				List <RankUserInfo> rankUserInfoList=rankUserService.queryByType(rankTypeList.get(0).getTypeId());
				
				List <SimpleUser> userList=new ArrayList<SimpleUser>();
				List <String> snsIds=new ArrayList<String>();
				
				for(RankUserInfo rankUser: rankUserInfoList)
				{
					
					SimpleUser simpleUser=new SimpleUser();
					User user=new User();
					
					user=userProfileService.queryUserById(Long.valueOf(rankUser.getUserId())).get(0);
					String snsId=userLogInService.getAccount(Long.valueOf(rankUser.getUserId())).getSnsId();
					simpleUser.setGold(user.getGold());
					simpleUser.setExp(user.getExp());
					simpleUser.setGender(user.getGender());
					simpleUser.setImage(user.getImage());
					simpleUser.setLevel(user.getLevel());
					simpleUser.setNickName(user.getNickName());
					simpleUser.setUserId(user.getUserId());
					
					userList.add(simpleUser);
					if(snsId==null) snsId="";
					snsIds.add(snsId);
					
					if(rankUser.getUserId()==Integer.valueOf(account))
						rank=i;
					i++;
				}
				
				Map <Object,Object> rankInfor=new HashMap<Object, Object>();
				
				int rankSize=(Integer)DataStore.setting.get("rankSize");
				
				if(rankUserInfoList.size()>=rankSize)
				{
					if(rank<=rankSize)
					{
						rankInfor.put("rankUserInfoList", rankUserInfoList.subList(0, rankSize));
						rankInfor.put("simpleUser", userList.subList(0, rankSize));	
						rankInfor.put("snsId", snsIds.subList(0, rankSize));
					}
					else
					{
						List <RankUserInfo> list =new ArrayList<RankUserInfo>();
						list.addAll(rankUserInfoList.subList(0, rankSize));
						
						List <String> snsList=new ArrayList<String>();
						snsList.addAll(snsIds.subList(0, rankSize));
						
						list.add(rankUserInfoList.get(rank));
						snsList.add(snsIds.get(rank));
						
						List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
						simpleList.addAll(userList.subList(0, rankSize));
						
						
						rankInfor.put("rankUserInfoList", list);
						rankInfor.put("snsId", snsList);
						rankInfor.put("simpleUser", simpleList);
					}
				}
				else
				{
					rankInfor.put("rankUserInfoList", rankUserInfoList);
				    rankInfor.put("snsId", snsIds);
				    rankInfor.put("simpleUser", userList);
				}
				
				
				
				rankInfor.put("rank", rank);
				
				responseJSON+=JSONObject.fromObject(rankInfor).toString();
				response.setStatus(200);
					
			}
			
		}
		else
		{
			 ErrorResponse errorResponse = new ErrorResponse();
			 errorResponse.setErrorMessage("rank fail!");
			 errorResponse.setErrorAction("");
			 errorResponse.setErrorCode(ErrorCode.Battle_rankBlackJack);
				
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
	
	
	public void showTotalNum()
	
	{
		
		String responseJSON  = "";
		
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
	    String typeId=Utility.splitString(decode, "typeId");
		
		int rank=0;
		
		int i=1;
		
		if(Integer.valueOf(account)>0)
		{
			List <RankType> rankTypeList=rankTypeService.queryById(Integer.valueOf(typeId));
		
			
			if(rankTypeList.size()>0)
			{
				
				List <RankUserInfo> rankUserInfoList=rankUserService.queryByType(rankTypeList.get(0).getTypeId());
				
				List <SimpleUser> userList=new ArrayList<SimpleUser>();
				List <String> snsIds=new ArrayList<String>();
				
				
				for(RankUserInfo rankUser: rankUserInfoList)
				{
					
					SimpleUser simpleUser=new SimpleUser();
					User user=new User();
					
					user=userProfileService.queryUserById(Long.valueOf(rankUser.getUserId())).get(0);
					
					String snsId=userLogInService.getAccount(Long.valueOf(rankUser.getUserId())).getSnsId();
					simpleUser.setGold(user.getGold());
					simpleUser.setExp(user.getExp());
					simpleUser.setGender(user.getGender());
					simpleUser.setImage(user.getImage());
					simpleUser.setLevel(user.getLevel());
					simpleUser.setNickName(user.getNickName());
					simpleUser.setUserId(user.getUserId());
					
					userList.add(simpleUser);
					if(snsId==null) snsId="";
					snsIds.add(snsId);
					
					if(rankUser.getUserId()==Integer.valueOf(account))
						rank=i;
					i++;
				}
				
				Map <Object,Object> rankInfor=new HashMap<Object, Object>();
				
				if(rankUserInfoList.size()>=5)
				{
					rankInfor.put("rankUserInfoList", rankUserInfoList.subList(0, 5));
					rankInfor.put("simpleUser", userList.subList(0, 5));
					rankInfor.put("snsId", snsIds.subList(0, 5));
				}
				else
				{
					rankInfor.put("rankUserInfoList", rankUserInfoList);
					rankInfor.put("simpleUser", userList);
					rankInfor.put("snsId", snsIds);
				}
					
				rankInfor.put("rank", rank);
				
				responseJSON+=JSONObject.fromObject(rankInfor).toString();
				response.setStatus(200);
					
			}
			
		}
		else
		{
			 ErrorResponse errorResponse = new ErrorResponse();
			 errorResponse.setErrorMessage("rank fail!");
			 errorResponse.setErrorAction("");
			 errorResponse.setErrorCode(ErrorCode.Battle_rankBlackJack);
				
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
	
	
	public void showUserBattle()
	
	{
		
		String responseJSON  = "";
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
	    String battleType=Utility.splitString(decode, "battleType");
		
		if(Integer.valueOf(account)>0)
		{
			List <UserBattleResult> resultList=userResultService.userBattleResult(Integer.valueOf(account), 
					Integer.valueOf(battleType));
		
			
			SimpleUser simpleUser=new SimpleUser();
			User user=new User();
				
			user=userProfileService.queryUserById(Long.valueOf(account)).get(0);
			
			
			simpleUser.setGold(user.getGold());
			simpleUser.setExp(user.getExp());
			simpleUser.setGender(user.getGender());
			simpleUser.setImage(user.getImage());
			simpleUser.setLevel(user.getLevel());
			simpleUser.setNickName(user.getNickName());
			simpleUser.setUserId(user.getUserId());
			
			String snsId=userLogInService.getAccount(Long.valueOf(account)).getSnsId();
			 if(snsId==null) snsId="";
				
			Map <Object,Object> rankInfor=new HashMap<Object, Object>();
				
			rankInfor.put("userResult", resultList);
			rankInfor.put("simpleUser", simpleUser);	
			rankInfor.put("snsId", snsId);
			
				
			responseJSON+=JSONObject.fromObject(rankInfor).toString();
			response.setStatus(200);
					
			
			
		}
		else
		{
			 ErrorResponse errorResponse = new ErrorResponse();
			 errorResponse.setErrorMessage("rank fail!");
			 errorResponse.setErrorAction("");
			 errorResponse.setErrorCode(ErrorCode.Battle_rankBlackJack);
				
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
	
	
	public void showUserGameType()
	
	{
		
		String responseJSON  = "";
		
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
	    String gameType=Utility.splitString(decode, "gameType");
	    
		
		if(Integer.valueOf(account)>0)
		{
			List <UserBattleResult> resultList=userResultService.userBattleResult(Integer.valueOf(account), 
					Integer.valueOf(gameType));
			
			SimpleUser simpleUser=new SimpleUser();
			User user=new User();
				
			user=userProfileService.queryUserById(Long.valueOf(account)).get(0);
			String snsId=userLogInService.getAccount(Long.valueOf(account)).getSnsId();
			if(snsId==null) snsId="";
			simpleUser.setGold(user.getGold());
			simpleUser.setExp(user.getExp());
			simpleUser.setGender(user.getGender());
			simpleUser.setImage(user.getImage());
			simpleUser.setLevel(user.getLevel());
			simpleUser.setNickName(user.getNickName());
			simpleUser.setUserId(user.getUserId());
			
				
			Map <Object,Object> rankInfor=new HashMap<Object, Object>();
				
			rankInfor.put("userResult", resultList);
			rankInfor.put("simpleUser", simpleUser);
			rankInfor.put("snsId", snsId);
			
				
			responseJSON+=JSONObject.fromObject(rankInfor).toString();
			response.setStatus(200);
					
			
			
		}
		else
		{
			 ErrorResponse errorResponse = new ErrorResponse();
			 errorResponse.setErrorMessage("rank fail!");
			 errorResponse.setErrorAction("");
			 errorResponse.setErrorCode(ErrorCode.Battle_rankBlackJack);
				
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
	
	public void update()
	
	{
		
		String responseJSON  = "";
		
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
	    String battleType=Utility.splitString(decode, "battleType");
	    String gameType=Utility.splitString(decode, "gameType");
	    String totalResult=Utility.splitString(decode, "totalResult");
	    String winTotal=Utility.splitString(decode, "winTotal");
	    String loseTotal=Utility.splitString(decode, "loseTotal");
		
		if(Integer.valueOf(account)>0)
		{
			userResultService.updateResult(Integer.valueOf(account), Integer.valueOf(battleType), 
					Integer.valueOf(gameType),Integer.valueOf(totalResult)
					, Integer.valueOf(winTotal),Integer.valueOf(loseTotal) );
			
			SimpleUser simpleUser=new SimpleUser();
			User user=new User();
				
			user=userProfileService.queryUserById(Long.valueOf(account)).get(0);
			String snsId=userLogInService.getAccount(Long.valueOf(account)).getSnsId();
		    if(snsId==null) snsId="";
			simpleUser.setGold(user.getGold());
			simpleUser.setExp(user.getExp());
			simpleUser.setGender(user.getGender());
			simpleUser.setImage(user.getImage());
			simpleUser.setLevel(user.getLevel());
			simpleUser.setNickName(user.getNickName());
			simpleUser.setUserId(user.getUserId());
			
			
				
			Map <Object,Object> rankInfor=new HashMap<Object, Object>();
			
			List <UserBattleResult> lists=userResultService.userBattleResult(Integer.valueOf(account), Integer.valueOf(battleType));
				
			rankInfor.put("userResult", lists);
			rankInfor.put("simpleUser", simpleUser);	
			rankInfor.put("snsId", snsId);
			
				
			responseJSON+=JSONObject.fromObject(rankInfor).toString();
			response.setStatus(200);
					
			
			
		}
		else
		{
			 ErrorResponse errorResponse = new ErrorResponse();
			 errorResponse.setErrorMessage("rank fail!");
			 errorResponse.setErrorAction("");
			 errorResponse.setErrorCode(ErrorCode.Battle_rankBlackJack);
				
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
	
	
	
