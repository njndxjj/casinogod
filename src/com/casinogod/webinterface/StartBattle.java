package com.casinogod.webinterface;



import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;

import com.casinogod.battle.BattleFactory;
import com.casinogod.battle.BattleInfo;
import com.casinogod.battle.BattleType;
import com.casinogod.battle.GameType;
import com.casinogod.dao.ConfigurationDAOImpl;
import com.casinogod.pojo.BattleHistory;
import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossConfig;
import com.casinogod.pojo.BossInstanceUserInfo;
import com.casinogod.pojo.BossUserInfo;
import com.casinogod.pojo.Configuration;
import com.casinogod.pojo.EventConfig;
import com.casinogod.pojo.Item;
import com.casinogod.pojo.ItemUser;
import com.casinogod.pojo.RankType;
import com.casinogod.pojo.RankUserInfo;
import com.casinogod.pojo.StartBattleResponse;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserBattleHistory;
import com.casinogod.pojo.UserBattleResult;
import com.casinogod.service.BattleProfileService;
import com.casinogod.service.BossBattleService;
import com.casinogod.service.BossConfigService;
import com.casinogod.service.BossInstanceUserService;
import com.casinogod.service.BossUserService;
import com.casinogod.service.EventService;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.ItemHistoryConfigService;
import com.casinogod.service.ItemUserConfigService;
import com.casinogod.service.RankTypeService;
import com.casinogod.service.RankUserService;
import com.casinogod.service.UserBattleService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfile;
import com.casinogod.service.UserResultService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.DataStore;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class StartBattle extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	

	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private BattleProfileService battleProfileService;
	
	private UserBattleService userBattleService;
	
	private UserProfile userProfileService;
	
	private BossConfigService bossConfigService;
	
	private BossBattleService bossBattleService;
	
	private BossUserService bossUserService;
	
	private BossInstanceUserService bossInstanceService;
	
	private EventService eventService;
	
	private RankUserService rankUserService;
	
	private RankTypeService rankTypeService;
	
	private UserResultService userResultService;
	
	private  ItemConfigService    itemConfigService;
		
	private  ItemUserConfigService itemUserConfigService;
	
	private  ItemHistoryConfigService   itemHistoryConfigService;
	
	private  UserLogIn userLogInService;
	
	private ConfigurationDAOImpl configurationDAO;
	

	public void setUserBattleService(UserBattleService userBattleService) {
		this.userBattleService = userBattleService;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.resquest=request;
	}
	
	public void setBattleProfileService(BattleProfileService battleProfileService) {
		this.battleProfileService = battleProfileService;
	}
	

	
	public void setUserProfileService(UserProfile userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	public void setBossConfigService(BossConfigService bossConfigService) {
		this.bossConfigService = bossConfigService;
	}

	public void setBossBattleService(BossBattleService bossBattleService) {
		this.bossBattleService = bossBattleService;
	}

	public void setBossUserService(BossUserService bossUserService) {
		this.bossUserService = bossUserService;
	}
	
	public void setBossInstanceService(BossInstanceUserService bossInstanceService) {
		this.bossInstanceService = bossInstanceService;
	}
	

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	

	public void setRankUserService(RankUserService rankUserService) {
		this.rankUserService = rankUserService;
	}
	
	public void setRankTypeService(RankTypeService rankTypeService) {
		this.rankTypeService = rankTypeService;
	}
	

	public void setUserResultService(UserResultService userResultService) {
		this.userResultService = userResultService;
	}
	
	
	public void setItemConfigService(ItemConfigService itemConfigService) {
		this.itemConfigService = itemConfigService;
	}

	public void setItemUserConfigService(ItemUserConfigService itemUserConfigService) {
		this.itemUserConfigService = itemUserConfigService;
	}
	

	public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}

	public void setItemHistoryConfigService(
			ItemHistoryConfigService itemHistoryConfigService) {
		this.itemHistoryConfigService = itemHistoryConfigService;
	}
	
	

	public void setConfigurationDAO(ConfigurationDAOImpl configurationDAO) {
		this.configurationDAO = configurationDAO;
	}



	private static Logger log = Logger.getLogger(StartBattle.class); 

	public void startBattle()
	
	{
		String postdata="";
		String decode="";
	    
	    try {
		
	    	postdata=Utility.postdata(resquest);
	    	decode=CustomBase64.decode(postdata);

	    	System.out.println("descInfo--->"+CustomBase64.decode(postdata));
		
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    String account=Utility.splitString(decode, "userId");
	    String battleType=Utility.splitString(decode, "battleType");
	    String gameType=Utility.splitString(decode, "gameType");
	    String battlePrize=Utility.splitString(decode, "battlePrize");
	    String bossInstance=Utility.splitString(decode, "bossInstance");
	    String level=Utility.splitString(decode, "level");
			
		long userID=0;
		int battleTypePara=1;
		int gameTypePara=0;
		long battlePrizePara=200;
		
		int bInstance=0;
		
		if(account!=null)
			userID = Long.parseLong(account);
		if(battleType!=null)
		    battleTypePara = Integer.parseInt(battleType);
		if(gameType!=null)
		   gameTypePara = Integer.parseInt(gameType);
		if(battlePrize!=null)
			battlePrizePara = Long.parseLong(battlePrize);
		if(bossInstance!=null)
			bInstance =Integer.valueOf(bossInstance);
		//Should double check "battlePrize" is workable for the user
		BattleInfo batInfo = null;
		String responseJSON  = "";
		
		
		List <User> uList=userProfileService.queryUserById(Long.valueOf(account));
		
		User user =new User();
		
		int tempGold=0;
		
		if(uList.size()>0)
		{
			user=uList.get(0);
			
			user.setDiamond(user.getDiamond());
			tempGold=user.getGold()-Integer.valueOf(battlePrize);
			user.setGold(tempGold);
				
		}
		
		log.info("bInstance-->"+bossInstance);
		
		 
		if(userID>0&&gameTypePara==0&&battleTypePara>0&&bInstance==0&&tempGold>=0)
		{
			
			log.info("bInstance11-->"+bossInstance);
			
			//create raidboss 
			
			String createTime="1979-01-01 00:00:00";
			
			BossConfig bossConfig = new BossConfig();
			
			List <BossUserInfo> bInfoList=bossUserService.queryByUserId(Long.valueOf(account));
			
			if(bInfoList.size()>0)
			{
				BossUserInfo bUser=bInfoList.get(bInfoList.size()-1);
				
				List <BossBattleInfo> bbList=bossBattleService.queryByInstance(bUser.getBossInstance());
				
				if(bbList.size()>0)
					createTime=bbList.get(0).getStartTime();
			}
			
			log.info("createTime -->"+createTime);
			
			int winTimes=(Integer)DataStore.setting.get("winTimes");
			
			log.info("winTimes--->"+winTimes);
			
			if(battleProfileService.thressRate(Long.valueOf((account)), 2,1,createTime,
					winTimes,Integer.valueOf(gameType))&&Integer.valueOf(battleType)==1)
			{
				log.info("create new raidBoss");
			    
//				List <BossConfig> list=bossConfigService.queryall();
				
//				Random random=new Random();
//				
//				int randType=Math.abs(random.nextInt())%(list.size());
				
				int randType=Integer.valueOf(level);
				
				log.info("bossType is -->"+randType);
				
				List <EventConfig> listEvent=eventService.queryByType(2);
				
				if(listEvent.size()>0)
				{
					EventConfig eventConfig=listEvent.get(listEvent.size()-1);
					
					String startTime=eventConfig.getStartTime();
					
					String endTime=eventConfig.getEndTime();
					
			        String currentTime=Utility.getNowString();
			        
			        org.json.JSONObject data;
			        
			        int discount=0;
			        
			        try {
			        	
			        	data=new org.json.JSONObject(eventConfig.getDetailData());
			        	
			        	discount=data.getInt("BossType");
			        	
			        	log.info("discount-->"+discount);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
					if(eventConfig.getFrequency()==1)
					{
						if(currentTime.compareTo(startTime)>=0&&currentTime.compareTo(endTime)<=0)
						{
							//bossConfig=list.get(discount);
							
							List <BossConfig> listBoss=bossConfigService.queryByType(discount);
							
							log.info("bossType-->"+discount+"bossSize->>"+listBoss.size());
							
							bossConfig=listBoss.get(0);
						
						}
						else
						{
							List <BossConfig> listBoss=bossConfigService.queryByType(randType);
							bossConfig=listBoss.get(0);
						}
					}
					else
					{
						//compare hours
						String hours=Utility.getNowHours();
						
						String startHours=eventConfig.getStartTime().substring(11);
						
						String endHours=eventConfig.getEndTime().substring(11);
						
						SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); 
						
						try {
							
							Date beginDate = format.parse(startHours);
							
							Date endDate = format.parse(endHours);
							
							Date currentDate=format.parse(hours);
							
							if(currentDate.compareTo(beginDate)>=0&&currentDate.compareTo(endDate)<=0)
							{
								List <BossConfig> listBoss=bossConfigService.queryByType(discount);
								
								log.info("bossType-->"+discount+"bossSize->>"+listBoss.size());
								
								bossConfig=listBoss.get(0);
							}
							else
							{
								List <BossConfig> listBoss=bossConfigService.queryByType(randType);
								bossConfig=listBoss.get(0);
							}
							
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				}
				else
				{
					List <BossConfig> listBoss=bossConfigService.queryByType(randType);
					bossConfig=listBoss.get(0);
				}
					
				
				BossBattleInfo bossBattleInfo=new BossBattleInfo();
				
			    long battleRandId=Utility.randomLong();
				
				bossBattleInfo.setBattleTime(bossConfig.getTime());
				bossBattleInfo.setBossType(bossConfig.getBossType());
				bossBattleInfo.setCurrentHP(bossConfig.getMaxHP());
				bossBattleInfo.setMaxHP(bossConfig.getMaxHP());
				bossBattleInfo.setStartTime(Utility.getNowString());
				bossBattleInfo.setStatus(1);
				bossBattleInfo.setBossName(bossConfig.getBossName());
				bossBattleInfo.setBossImage(bossConfig.getBossImage());
				
				batInfo=BattleFactory.createBattle(userID, 3, gameTypePara,battleRandId, battlePrizePara,bossBattleInfo);
				
				bossBattleService.addBossBossBattleInfo(bossBattleInfo);
				
				//add bossUser 
				List <BossBattleInfo> bossInfo=bossBattleService.queryByBattle(bossBattleInfo.getBossType(),
						bossBattleInfo.getBossInstance());
				bossBattleInfo=bossInfo.get(0);
				
		
				 int bossInstance1=bossBattleInfo.getBossInstance(); 
				 int bossType=bossBattleInfo.getBossType();

				
				bossUserService.addBossBossUserInfo(bossType, bossInstance1, String.valueOf(battleRandId), Integer.valueOf(account)
						, 0);
				
				//add bossInstanceUser
				bossInstanceService.addBossInstanceUserInfo(bossInstance1, Long.valueOf(account), 0);	
				
				userProfileService.updateGold(user);
				
				DataStore.battePrize.put(String.valueOf(battleRandId), Integer.valueOf(battlePrize));
			}
			else
			{
				long battleId=Utility.randomLong();
				BossBattleInfo bossBattleInfo=new BossBattleInfo();
				batInfo=BattleFactory.createBattle(userID, battleTypePara, gameTypePara,battleId, battlePrizePara,bossBattleInfo);
				userProfileService.updateGold(user);
					
				DataStore.battePrize.put(String.valueOf(battleId), Integer.valueOf(battlePrize));
			}
			
		//	bInstance=0;
		
		}
		
		else if(bInstance>0&&Integer.valueOf(battleType)==3&&tempGold>=0)
		{
			
			//share Boss
			log.info("binstance-->"+bInstance);
			log.info("battleTypePara-->"+battleTypePara);
				
			List <BossBattleInfo> bossBattle=bossBattleService.queryByInstance(bInstance);
				
			if(bossBattle.size()>0)
			{
				BossBattleInfo bossBattleInfo=bossBattle.get(0);
							
				List <BossInstanceUserInfo> bossInstanceUser=bossInstanceService.
							queryByInstanceAndUser(Long.valueOf(account), bInstance);
					
				int bossInstance1=bossBattleInfo.getBossInstance();
				int bossType=bossBattleInfo.getBossType();
					
				if(bossInstanceUser.size()>0)
				{
						
					//hp && time  
					if(bossBattleInfo.getCurrentHP()>0&&
							!Utility.expiredTime(bossBattleInfo.getStartTime(), Utility.getNowString(), bossBattleInfo.getBattleTime()*60000))
					{
						//share the boss
						long battleRandId=Utility.randomLong();
						bossUserService.addBossBossUserInfo(bossType, bossInstance1, String.valueOf(battleRandId), 
								Integer.valueOf(account), 0);
						batInfo=BattleFactory.createBattle(userID, 3, gameTypePara,battleRandId, battlePrizePara,bossBattleInfo);
							
						userProfileService.updateGold(user);
							
						DataStore.battePrize.put(String.valueOf(battleRandId), Integer.valueOf(battlePrize));
					}
					else
					{
						batInfo=BattleFactory.createBattle(userID, 1, gameTypePara,0, battlePrizePara,bossBattleInfo);
							
					}
				}
				else
				{
					batInfo=BattleFactory.createBattle(userID, 1, gameTypePara,0, battlePrizePara,bossBattleInfo);
				}
			}
			else 
			{
				BossBattleInfo bbInfo=new BossBattleInfo();
				batInfo=BattleFactory.createBattle(userID, 1, gameTypePara,0, battlePrizePara,bbInfo);
			}
		}
		//other battle
		else if(tempGold>=0)
		{
			
			log.info("single-->"+"singleBattle");
			log.info("battleTypePara-->"+battleTypePara);
				
			long battleId=Utility.randomLong();
			BossBattleInfo bossBattleInfo=new BossBattleInfo();
			batInfo=BattleFactory.createBattle(userID, battleTypePara, gameTypePara,battleId, battlePrizePara,bossBattleInfo);
				
			userProfileService.updateGold(user);
				
			DataStore.battePrize.put(String.valueOf(battleId), Integer.valueOf(battlePrize));
			
			//bInstance=0;
		}
		else
		{
			
		}
	
			
		StartBattleResponse sbResponse = new StartBattleResponse();
			
		String userList="";
		
		if(batInfo != null && batInfo.getBattleStatus() == 1)
			{
				
				switch(batInfo.getGameType())
				{
				   //BlackJack
				    case 0:
				    	
				    //	PlayerInfo playerInfo = BattleFactory.getPlayerInfo(userID);

				    	sbResponse.setBattleInfo(batInfo);
				    	
				    	//insert into batInfo history
				   // 	BattleHistory battleHistory=new BattleHistory();
				   
				    	if(batInfo.getBattlePlayerUserIDList()!=null
				    			&&batInfo.getBattlePlayerUserIDList().length>0)
				    	{
				    		for(long i:batInfo.getBattlePlayerUserIDList())
				    		{
				    			userList+=String.valueOf(i);
				    		}
				    		
				    	}
				    	else
			    		  userList=null;
				    	
				    	if(batInfo.getBattleID()!=0)
				    		battleProfileService.addBattleProfile(batInfo.getBattleID(), batInfo.getBattleOwnerUserID(), userList, 
				    			1, Integer.parseInt(battleType),gameType.valueOf(batInfo.getGameType()).toString(),Utility.getNowString(),
				    			null, null,level==null?0:Integer.valueOf(level),0);
				    	
				    	break;
				    	
				    case 2:
				    	
				    	// playerInfo = BattleFactory.getPlayerInfo(userID);

				    	sbResponse.setBattleInfo(batInfo);

				    	if(batInfo.getBattlePlayerUserIDList()!=null
				    			&&batInfo.getBattlePlayerUserIDList().length>0)
				    	{
				    		for(long i:batInfo.getBattlePlayerUserIDList())
				    		{
				    			userList+=String.valueOf(i);
				    		}
				    		
				    	}
				    	else
			    		  userList=null;
				    	
				    	if(batInfo.getBattleID()!=0)
				    		battleProfileService.addBattleProfile(batInfo.getBattleID(), batInfo.getBattleOwnerUserID(), userList, 
				    			1, Integer.parseInt(battleType),gameType.valueOf(batInfo.getGameType()).toString(),Utility.getNowString(),
				    			null, null,level==null?0:Integer.valueOf(level),0);
				    	
				    	break;
				    	
				    
				    case 4:
				    	
				    	break;
				    		    	
				    default:
				    	
				    	sbResponse.setBattleInfo(batInfo);
				    	
				    	if(batInfo.getBattlePlayerUserIDList()!=null&&batInfo.getBattlePlayerUserIDList().length>0)
				    	{
				    		for(long i:batInfo.getBattlePlayerUserIDList())
				    		{
				    			userList+=String.valueOf(i);
				    		}
				    	 }
				    	else
				    		userList=null;
				    	if(batInfo.getBattleID()!=0)
				    			battleProfileService.addBattleProfile(batInfo.getBattleID(), batInfo.getBattleOwnerUserID(), userList, 
					    			1, Integer.parseInt(battleType),gameType.valueOf(batInfo.getGameType()).toString(),Utility.getNowString(),
					    			null, null,Integer.valueOf(level),0);
				    	break;
				 }
				responseJSON = JSONObject.fromObject(sbResponse).toString();
				response.setStatus(200);
			
	
		}
				
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Create battle fail!");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.Battle_StartBattleFail);
		
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
		
		try {
			responseJSON=CustomBase64.encode(responseJSON);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			log.info("response:"+responseJSON);
			out.println(responseJSON);
//			resquest.setAttribute("result", responseJSON);
			
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
	 }
	
  }
	
	public void endBattle()
	
	{
		log.info("end battle");
		
		String postdata="";
		String decode="";
	    
	    try {
		
	    	postdata=Utility.postdata(resquest);
	    	decode=CustomBase64.decode(postdata);

	    	System.out.println("descInfo--->"+CustomBase64.decode(postdata));
		
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    String account=Utility.splitString(decode, "userId");
	    String battleType=Utility.splitString(decode, "battleType");
	    String gameType=Utility.splitString(decode, "gameType");
	    String battlePrize=Utility.splitString(decode, "battlePrize");
	    String bossInstance=Utility.splitString(decode, "bossInstance");
	    String battleId=Utility.splitString(decode, "battleId");
	    String level=Utility.splitString(decode, "level");
	    String result=Utility.splitString(decode, "result");
	    String isSpecialWin=Utility.splitString(decode, "isSpecialWin");
	    
	//	int battlePrize
		
		int  prize=(Integer)DataStore.battePrize.get(String.valueOf(battleId));
		
	//	int prize=100;
		
		log.info(String.valueOf(battleId)+"-->"+prize);
		
		String responseJSON  = "";
	   
		//add battle history
	    switch(BattleType.values()[Integer.parseInt(battleType)])
		{
		  
		  case SingleBattle:
			  
					  log.info(" end battle singleBattle ");
					
					  boolean flag=false;
					  
					  flag = battleProfileService.addBattleProfile(Long.valueOf(battleId),Long.valueOf(account), null, 
				    			2, Integer.parseInt(battleType),GameType.values()[Integer.valueOf(gameType)].toString(),
				    			Utility.getNowString(), result, null,Integer.valueOf(level),Integer.valueOf(isSpecialWin));
					  
					  List <User> list=userProfileService.queryUserById(Long.valueOf(account));
					  
					  User user=null;
					  
					  if(list.size()>0) user=list.get(0);
					  
					  if(flag)
					  {
					   
						 //add or update userHistory
						 log.info(" end battle add battleHistory");
					     
						 List <UserBattleHistory >  userBattle=userBattleService.queryUser(Long.valueOf(account), 
							  GameType.values()[Integer.valueOf(gameType)].toString());
					 
					   if(userBattle.size()>0)
					   
					   {
						  //update 
						  if(Integer.valueOf(result)>0)
						  {
							  userBattle.get(0).setWinMoney(userBattle.get(0).getWinMoney()+Integer.valueOf(result));
							  userBattle.get(0).setWinTimes( userBattle.get(0).getWinTimes()+1);
							  int tempExp=user.getExp();
							  int updateExp=(int) (tempExp+Integer.valueOf(result)*0.02);
							  log.info("exp-->"+tempExp);
						
							  user.setExp(updateExp);
							  user.setLevel(Utility.getLevel(updateExp));
							  userProfileService.updateExp(user);
							  
						  }
						  else if(Integer.valueOf(result)<0)
						  {
							  userBattle.get(0).setLostMoney(userBattle.get(0).getLostMoney()+(0-Integer.valueOf(result)));
							  userBattle.get(0).setLostTime( userBattle.get(0).getLostTime()+1);
							  int tempExp=user.getExp();
							  int updateExp=(int) (tempExp+(0-Integer.valueOf(result))*0.005);
							  log.info("exp-->"+tempExp);
							  user.setExp(updateExp);
							  user.setLevel(Utility.getLevel(updateExp));
							  userProfileService.updateExp(user);
						  }
						  else
						  {
							  userBattle.get(0).setDrawTimes(userBattle.get(0).getDrawTimes()+1);
							  int tempExp=user.getExp();
							  int updateExp=(int) (tempExp+50);
							  log.info("exp-->"+tempExp);
							  user.setExp(updateExp);
							  user.setLevel(Utility.getLevel(updateExp));
							  userProfileService.updateExp(user);
						  }
						  
						 userBattleService.updateBattle(userBattle.get(0).getUserId(), userBattle.get(0).getGameType(),
								 userBattle.get(0).getWinTimes(),userBattle.get(0).getLostTime(), userBattle.get(0).getWinMoney(), 
								 userBattle.get(0).getLostMoney(),userBattle.get(0).getDrawTimes());
					  }
					  else
					  {
						  //add
						  if(Integer.valueOf(result)>0)
						  {
							  userBattleService.addUserBattle(Long.valueOf(account), 
									  GameType.values()[Integer.valueOf(gameType)].toString(), 
									  1, 0, Long.valueOf((result)), 0,0);
							  int tempExp=user.getExp();
							  int updateExp=(int) (tempExp+Integer.valueOf(result)*0.02);
							  user.setExp(updateExp);
							  user.setLevel(Utility.getLevel(updateExp));
							  userProfileService.updateExp(user);
						  }
						  else if(Integer.valueOf(result)<0)
						  {
							  userBattleService.addUserBattle(Long.valueOf(account), 
									  GameType.values()[Integer.valueOf(gameType)].toString(), 
									  0, 1, 0,Long.valueOf(result),0);
							  int tempExp=user.getExp();
							  int updateExp=(int) (tempExp+(0-Integer.valueOf(result))*0.005);
							  user.setExp(updateExp);
							  user.setLevel(Utility.getLevel(updateExp));
							  userProfileService.updateExp(user);
						  }
						  else
						  {
							  userBattleService.addUserBattle(Long.valueOf(account), 
									  GameType.values()[Integer.valueOf(gameType)].toString(), 
									  0, 0, 0,Long.valueOf(result),1);
							  int tempExp=user.getExp();
							  int updateExp=(int) (tempExp+50);
							  user.setExp(updateExp);
							  user.setLevel(Utility.getLevel(updateExp));
							  userProfileService.updateExp(user);
						  }
					  }
					  		  
					  //update userProfile		  
					  user.setGold(user.getGold()+Integer.valueOf(result)+prize);
					  user.setDiamond(user.getDiamond());
					  
					  int vidoPokerSum=battleProfileService.vidoPokerSum(2, 1, Utility.getDateString(), Utility.getNextDateString(), 2);
					  
					  userProfileService.updateGold(user);
					  
					  List <User> listUpdate=userProfileService.queryUserById(Long.valueOf(account));
					  
					  user=listUpdate.get(0);
					  
					  String snsId=userLogInService.getAccount(Long.valueOf(account)).getSnsId();
					  if(snsId==null) snsId="";
					  Map <String,Object> map=new HashMap<String, Object>();
					  
					  map.put("userInfo", user);
					  map.put("snsId", snsId);
					  
					  if("VideoPoker".equals(GameType.values()[Integer.valueOf(gameType)].toString()))
						  
						  map.put("videoPokerSum", vidoPokerSum);
					  
					 
					  responseJSON = JSONObject.fromObject(map).toString();
					  
					  response.setStatus(200);
					  
					 //update rank information
					  
					 if(Integer.valueOf(isSpecialWin)>0)
					 {
						 
					  //blackjack	 
					  List <RankType> rankTypeList=rankTypeService.queryById(1);
							
					  log.info("rankType-->"+rankTypeList.size());
							
					  if(rankTypeList.size()>0)
					  {
						RankType rankType=rankTypeList.get(0);
								
						String startTime=rankType.getStartTime();
						String endTime=rankType.getEndTime();
								
						List <RankUserInfo> rankUserInfo=rankUserService.queryByTypeUser(1, Integer.valueOf(account));
							
						if(rankUserInfo.size()>0)
						{
							//update
							log.info("updaet rankUser-->"+rankUserInfo.size());
							String updateTime=Utility.getNowString();
								 
							if(updateTime.compareTo(startTime)>=0&&updateTime.compareTo(endTime)<=0)
							{
								log.info("update updateTime-->"+updateTime);
								int total=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int winTime=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int loseTime=battleProfileService.isSpecialLoseNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								log.info("loseTimes-->"+loseTime);
								int drawTime=battleProfileService.isSpecialDrawNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								log.info("drawTimes-->"+loseTime);
								int totalMoney=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int winMoney=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int loseMoney=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								//rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
								RankUserInfo rankUser=new RankUserInfo();
								rankUser.setUserId(Integer.valueOf(account));
								rankUser.setRankValue(total);
							    rankUser.setTypeId(rankType.getTypeId());
								rankUser.setUpdateTime(updateTime);
								rankUser.setDrawTime(drawTime);
								rankUser.setWinTime(winTime);
								rankUser.setLoseTime(loseTime);
								rankUser.setTotalMoney(totalMoney);
								rankUser.setWinMoney(winMoney);
								rankUser.setLoseMoney(loseMoney);
									 
								rankUserService.updateRankType(rankUser);
							}
							else
							{
								log.info("updaet updateTime expriedTime-->"+updateTime);
								RankUserInfo rankUser=new RankUserInfo();
								rankUser.setUserId(Integer.valueOf(account));
								rankUser.setRankValue(0);
								rankUser.setTypeId(rankType.getTypeId());
								rankUser.setUpdateTime(updateTime);
								rankUser.setDrawTime(0);
								rankUser.setWinTime(0);
								rankUser.setLoseTime(0);
								rankUser.setTotalMoney(0);
								rankUser.setWinMoney(0);
								rankUser.setLoseMoney(0);
									 
								rankUserService.updateRankType(rankUser);
							}
								
					   }
					   else
						{
						   //add
						   log.info("add rankUser-->");
						   int total=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
						   int winTime=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
						   int loseTime=battleProfileService.isSpecialLoseNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
						   int drawTime=battleProfileService.isSpecialDrawNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
						   int totalMoney=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
						   int winMoney=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
						   int loseMoney=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
							//rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
								 
						    rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString(),winTime,loseTime,drawTime,totalMoney,winMoney,loseMoney);
									
					    }	
					  }
					 }
					else
					{
						 List <RankType> rankTypeList=rankTypeService.queryById(1);
							
						  log.info("rankTypeAdd-->"+rankTypeList.size());
								
						  if(rankTypeList.size()>0)
						  {
							RankType rankType=rankTypeList.get(0);
									
							String startTime=rankType.getStartTime();
							String endTime=rankType.getEndTime();
									
							List <RankUserInfo> rankUserInfo=rankUserService.queryByTypeUser(1, Integer.valueOf(account));
								
							if(rankUserInfo.size()<=0)
							{
								//add
								log.info("add rankUser-->");
								int total=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int winTime=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int loseTime=battleProfileService.isSpecialLoseNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int drawTime=battleProfileService.isSpecialDrawNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int totalMoney=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int winMoney=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int loseMoney=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString(),winTime,loseTime,drawTime,totalMoney,winMoney,loseMoney);
							}
							else
							{
								//update
								log.info("updaet rankUser-->"+rankUserInfo.size());
								String updateTime=Utility.getNowString();
								log.info("updaet updateTime-->"+updateTime);
								int total=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int winTime=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int loseTime=battleProfileService.isSpecialLoseNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								log.info("loseTimes-->"+loseTime);
								int drawTime=battleProfileService.isSpecialDrawNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								log.info("drawTimes-->"+loseTime);
								int totalMoney=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int winMoney=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int loseMoney=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								//rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
								RankUserInfo rankUser=new RankUserInfo();
								rankUser.setUserId(Integer.valueOf(account));
								rankUser.setRankValue(total);
							    rankUser.setTypeId(rankType.getTypeId());
								rankUser.setUpdateTime(updateTime);
								rankUser.setDrawTime(drawTime);
								rankUser.setWinTime(winTime);
								rankUser.setLoseTime(loseTime);
								rankUser.setTotalMoney(totalMoney);
								rankUser.setWinMoney(winMoney);
								rankUser.setLoseMoney(loseMoney);
									 
								rankUserService.updateRankType(rankUser);
							}
						  }
				    }  
					 
			       //VideoPoker
					 
					 if(("VideoPoker".equals(GameType.values()[Integer.valueOf(gameType)].toString())))
					 {
						 
					  //VideoPoker	 
					  List <RankType> rankTypeList=rankTypeService.queryById(4);
							
					  log.info("rankType-->"+rankTypeList.size());
							
					  if(rankTypeList.size()>0)
					  {
						RankType rankType=rankTypeList.get(0);
								
						String startTime=rankType.getStartTime();
						String endTime=rankType.getEndTime();
								
						List <RankUserInfo> rankUserInfo=rankUserService.queryByTypeUser(4, Integer.valueOf(account));
							
						if(rankUserInfo.size()>0)
						{
							//update
							log.info("updaet rankUser-->"+rankUserInfo.size());
							String updateTime=Utility.getNowString();
								 
							if(updateTime.compareTo(startTime)>=0&&updateTime.compareTo(endTime)<=0)
							{
								log.info("update updateTime-->"+updateTime);
								int total=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int winTime=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int loseTime=battleProfileService.isSpecialLoseNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								log.info("loseTimes-->"+loseTime);
								int drawTime=battleProfileService.isSpecialDrawNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								log.info("drawTimes-->"+loseTime);
								int totalMoney=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int winMoney=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int loseMoney=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								//rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
								RankUserInfo rankUser=new RankUserInfo();
								rankUser.setUserId(Integer.valueOf(account));
								rankUser.setRankValue(total);
							    rankUser.setTypeId(rankType.getTypeId());
								rankUser.setUpdateTime(updateTime);
								rankUser.setDrawTime(drawTime);
								rankUser.setWinTime(winTime);
								rankUser.setLoseTime(loseTime);
								rankUser.setTotalMoney(totalMoney);
								rankUser.setWinMoney(winMoney);
								rankUser.setLoseMoney(loseMoney);
									 
								rankUserService.updateRankType(rankUser);
							}
							else
							{
								log.info("updaet updateTime expriedTime-->"+updateTime);
								RankUserInfo rankUser=new RankUserInfo();
								rankUser.setUserId(Integer.valueOf(account));
								rankUser.setRankValue(0);
								rankUser.setTypeId(rankType.getTypeId());
								rankUser.setUpdateTime(updateTime);
								rankUser.setDrawTime(0);
								rankUser.setWinTime(0);
								rankUser.setLoseTime(0);
								rankUser.setTotalMoney(0);
								rankUser.setWinMoney(0);
								rankUser.setLoseMoney(0);
									 
								rankUserService.updateRankType(rankUser);
							}
								
					   }
					   else
						{
						   //add
						   log.info("add rankUser-->");
						   int total=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
						   int winTime=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
						   int loseTime=battleProfileService.isSpecialLoseNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
						   int drawTime=battleProfileService.isSpecialDrawNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
						   int totalMoney=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
						   int winMoney=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
						   int loseMoney=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
							//rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
								 
						    rankUserService.addRankUser(Integer.valueOf(account), 4, total, Utility.getNowString(),winTime,loseTime,drawTime,totalMoney,winMoney,loseMoney);
									
					    }	
					  }
					 }
					else
					{
						 List <RankType> rankTypeList=rankTypeService.queryById(4);
							
						  log.info("rankTypeAdd-->"+rankTypeList.size());
								
						  if(rankTypeList.size()>0)
						  {
							RankType rankType=rankTypeList.get(0);
									
							String startTime=rankType.getStartTime();
							String endTime=rankType.getEndTime();
									
							List <RankUserInfo> rankUserInfo=rankUserService.queryByTypeUser(4, Integer.valueOf(account));
								
							if(rankUserInfo.size()<=0)
							{
								//add
								log.info("add rankUser-->");
								int total=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int winTime=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int loseTime=battleProfileService.isSpecialLoseNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int drawTime=battleProfileService.isSpecialDrawNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int totalMoney=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int winMoney=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int loseMoney=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								rankUserService.addRankUser(Integer.valueOf(account), 4, total, Utility.getNowString(),winTime,loseTime,drawTime,totalMoney,winMoney,loseMoney);
							}
							else
							{
								//update
								log.info("updaet rankUser-->"+rankUserInfo.size());
								String updateTime=Utility.getNowString();
								log.info("updaet updateTime-->"+updateTime);
								int total=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int winTime=battleProfileService.isSpecialWin(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								int loseTime=battleProfileService.isSpecialLoseNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								log.info("loseTimes-->"+loseTime);
								int drawTime=battleProfileService.isSpecialDrawNum(Long.valueOf(account), 2, 1, startTime, endTime,Integer.valueOf(gameType));
								log.info("drawTimes-->"+loseTime);
								int totalMoney=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int winMoney=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								int loseMoney=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, 1, startTime, endTime);
								//rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
								RankUserInfo rankUser=new RankUserInfo();
								rankUser.setUserId(Integer.valueOf(account));
								rankUser.setRankValue(total);
							    rankUser.setTypeId(rankType.getTypeId());
								rankUser.setUpdateTime(updateTime);
								rankUser.setDrawTime(drawTime);
								rankUser.setWinTime(winTime);
								rankUser.setLoseTime(loseTime);
								rankUser.setTotalMoney(totalMoney);
								rankUser.setWinMoney(winMoney);
								rankUser.setLoseMoney(loseMoney);
									 
								rankUserService.updateRankType(rankUser);
							}
						  }
				    }  
				
					 
				// update/add userResult
					 
				List <UserBattleResult> resultList=userResultService.userBattleGameResult(Integer.valueOf(account), 
						Integer.valueOf(battleType),Integer.valueOf(gameType));
				
				int totalResult,winResult,loseResult;
					 
			    if(resultList.size()>0)
			    {
				   //update
				   totalResult=battleProfileService.totalReuslt(Long.valueOf(account), 2, Integer.valueOf(battleType));
				   winResult=battleProfileService.totalWinResult(Long.valueOf(account), 2, Integer.valueOf(battleType));
				   loseResult=battleProfileService.totalLoseResult(Long.valueOf(account), 2, Integer.valueOf(battleType));
					
				  userResultService.updateResult(Integer.valueOf(account),Integer.valueOf(battleType),
							 Integer.valueOf(gameType), totalResult, winResult, loseResult);
				}
			    else
				{
					 totalResult=battleProfileService.totalReuslt(Long.valueOf(account), 2, Integer.valueOf(battleType));
					 winResult=battleProfileService.totalWinResult(Long.valueOf(account), 2, Integer.valueOf(battleType));
				     loseResult=battleProfileService.totalLoseResult(Long.valueOf(account), 2, Integer.valueOf(battleType));
						 userResultService.addUserResult(Integer.valueOf(account),Integer.valueOf(battleType),
							 Integer.valueOf(gameType), totalResult, winResult, loseResult);
						 
				}
			    
			    List <RankType> rankTypeList=rankTypeService.queryById(2);
						
			    RankType rankType=rankTypeList.get(0);
					 
				// update/add userResult
			    
			    List <RankUserInfo> rankList=rankUserService.queryByTypeUser(2, Integer.valueOf(account));
					 
				if(rankList.size()>0)
				{
					int winTime=battleProfileService.totalWinNumt(Long.valueOf(account), 2, 1);
					int loseTime=battleProfileService.totalLoseNum(Long.valueOf(account), 2, 1);
				    int drawTime=battleProfileService.totalDrawNum(Long.valueOf(account), 2, 1);
							
				    //rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
					RankUserInfo rankUser=new RankUserInfo();
					rankUser.setUserId(Integer.valueOf(account));
					rankUser.setRankValue(winTime);
					rankUser.setTypeId(rankType.getTypeId());
					rankUser.setUpdateTime(Utility.getNowString());
					rankUser.setDrawTime(drawTime);
					rankUser.setWinTime(winTime);
					rankUser.setLoseTime(loseTime);
					rankUser.setTotalMoney(totalResult);
					rankUser.setWinMoney(winResult);
					rankUser.setLoseMoney(loseResult);
								 
					rankUserService.updateRankType(rankUser);
								 
				}
				else
				{
					int winTime=battleProfileService.totalWinNumt(Long.valueOf(account), 2, 1);
					int loseTime=battleProfileService.totalLoseNum(Long.valueOf(account), 2, 1);
					int drawTime=battleProfileService.totalDrawNum(Long.valueOf(account), 2, 1);
							
					//rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
					RankUserInfo rankUser=new RankUserInfo();
					rankUser.setUserId(Integer.valueOf(account));
					rankUser.setRankValue(winTime);
					rankUser.setTypeId(rankType.getTypeId());
					rankUser.setUpdateTime(Utility.getNowString());
				    rankUser.setDrawTime(drawTime);
					rankUser.setWinTime(winTime);
					rankUser.setLoseTime(loseTime);
						 
					rankUserService.addRankUser(Integer.valueOf(account), 2, winTime, 
								 Utility.getNowString(),winTime,loseTime,drawTime,totalResult,winResult,loseResult);
						 
				}
				
				// update/add daily userResult
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
				Calendar day=Calendar.getInstance();
			
			
				String today=format.format(day.getTime());
				
				String createTime=today;
				
				String endTime=Utility.getNowString();
				
				totalResult=battleProfileService.totalReusltWithDate(Long.valueOf(account), 2, Integer.valueOf(battleType), createTime, endTime);
				winResult=battleProfileService.totalWinResultWithDate(Long.valueOf(account), 2, Integer.valueOf(battleType), createTime, endTime);
				loseResult=battleProfileService.totalLoseResultWithDate(Long.valueOf(account), 2, Integer.valueOf(battleType), createTime, endTime);
							
				rankTypeList=rankTypeService.queryById(3);
								
				rankType=rankTypeList.get(0);
							 
				// update/add userResult
					    
				rankList=rankUserService.queryByTypeUser(3, Integer.valueOf(account));
							 
				if(rankList.size()>0)
				{
					int winTime=battleProfileService.totalWinNumtWithDate(Long.valueOf(account), 2, 1,createTime,endTime);
					int loseTime=battleProfileService.totalLoseNumWithDate(Long.valueOf(account), 2, 1,createTime,endTime);
					int drawTime=battleProfileService.totalDrawNumWithDate(Long.valueOf(account), 2, 1,createTime,endTime);
					
					RankUserInfo rankUser=new RankUserInfo();
					rankUser.setUserId(Integer.valueOf(account));
					rankUser.setRankValue(winTime);
					rankUser.setTypeId(rankType.getTypeId());
					rankUser.setUpdateTime(Utility.getDateString());
					rankUser.setDrawTime(drawTime);
					rankUser.setWinTime(winTime);
					rankUser.setLoseTime(loseTime);
					rankUser.setTotalMoney(totalResult);
					rankUser.setWinMoney(winResult);
					rankUser.setLoseMoney(loseResult);
										 
					rankUserService.updateRankType(rankUser);
										 
				}
				else
				{
					int winTime=battleProfileService.totalWinNumtWithDate(Long.valueOf(account), 2, 1,createTime,endTime);
					int loseTime=battleProfileService.totalLoseNumWithDate(Long.valueOf(account), 2, 1,createTime,endTime);
					int drawTime=battleProfileService.totalDrawNumWithDate(Long.valueOf(account), 2, 1,createTime,endTime);
									
							//rankUserService.addRankUser(Integer.valueOf(account), 1, total, Utility.getNowString());
					RankUserInfo rankUser=new RankUserInfo();
					rankUser.setUserId(Integer.valueOf(account));
					rankUser.setRankValue(winTime);
					rankUser.setTypeId(rankType.getTypeId());
					rankUser.setUpdateTime(Utility.getDateString());
					rankUser.setDrawTime(drawTime);
					rankUser.setWinTime(winTime);
					rankUser.setLoseTime(loseTime);
								 
					rankUserService.addRankUser(Integer.valueOf(account), 3, winTime, 
										 Utility.getNowString(),winTime,loseTime,drawTime,totalResult,winResult,loseResult);
								 
			    }
					 
			}
			else
			{
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setErrorMessage("add battle fail!");
				errorResponse.setErrorAction("");
				errorResponse.setErrorCode(ErrorCode.Battle_StartBattleFail);
							
				responseJSON = JSONObject.fromObject(errorResponse).toString();
				response.setStatus(401);
			}
							
			try {
				
				responseJSON=CustomBase64.encode(responseJSON);
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				System.out.println(responseJSON);
				out.println(responseJSON);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
					  
			break;

		  case BossBattle:
			  
			  log.info(" end battle bossBattle ");
			  
			  List <Configuration> listConfig=configurationDAO.querAll();
			    
			  if(listConfig.size()>0)
			  {
				  Configuration configuration=listConfig.get(0);
				  DataStore.setting.put("bossRewardNum", configuration.getBossRewardNum());
		
			  }
			  
			  log.info("bossRewardNum-->"+(Integer) DataStore.setting.get("bossRewardNum"));
				
			  flag=false;
			  
			  battleProfileService.addBattleProfile(Long.valueOf(battleId),Long.valueOf(account), null, 
		    			2, Integer.parseInt(battleType),GameType.values()[Integer.valueOf(gameType)].toString(),
		    			Utility.getNowString(), result, null,Integer.valueOf(level),Integer.valueOf(isSpecialWin));
			  
			  List <BossBattleInfo> listBoss=bossBattleService.queryByInstance(Integer.valueOf(bossInstance));
			  
			  BossBattleInfo bossBattleInfo=listBoss.get(0);
			 
			  bossBattleInfo.setBossInstance(Integer.valueOf(bossInstance));
			  
			  //  bossBattleInfo.setStatus(2);			  
			 
			  int tempHP=0;
			  
			  if(Integer.valueOf(result)>0)
			  {
				  tempHP=bossBattleInfo.getCurrentHP()-Integer.valueOf(result);
				  bossBattleInfo.setCurrentHP(tempHP);
			  }
			  else
			  {
				  tempHP=bossBattleInfo.getCurrentHP();
				  bossBattleInfo.setCurrentHP(tempHP);
			  }
			  
			  if(tempHP<=0)
				  bossBattleInfo.setStatus(2);
			  else
				  bossBattleInfo.setStatus(1);
			  
				 			  
			  flag=bossBattleService.updateBossInfo(bossBattleInfo);
			  
			  
			  if(flag)
			  {
				 List <BossUserInfo> bossUser=bossUserService.queryByInstanceAndBattle(String.valueOf(battleId), 
						 Integer.valueOf(bossInstance));
				 
				 List <BossInstanceUserInfo> instanceList=bossInstanceService.queryByInstanceAndUser
						 (Long.valueOf(account), Integer.valueOf(bossInstance));
				 
				 if(instanceList.size()>0)
				 {
					 
					 BossInstanceUserInfo userBoss= instanceList.get(0);
					 
					 BossUserInfo userBossInfo=bossUser.get(0);
					 
					 //it's the first user meet boss
					 
					 log.info("userBossInfo result--->"+userBoss.getResult());
					 
					 log.info("userBossInfo result--->"+bossConfigService.
							 queryByType(bossBattleInfo.getBossType()).get(0).getMaxHP());
					 
					 if(userBoss.getResult()==0||(userBoss.getResult()*2)==bossConfigService.
							 queryByType(bossBattleInfo.getBossType()).get(0).getMaxHP())
						 
					 {
						 String purchaseTime=Utility.getNowString();
						 
						 // boss reward
						 
						 List <Item> items=itemConfigService.queryall();
							
						 Item item=new Item();
						 
						 for(Item temp:items)
						 {
							 if(temp.getItemPrice()==-1)
								 item=temp;
						 }
						   
						 //add or update itemUser;
						 List <ItemUser> itemUsers=itemUserConfigService.getItem(Integer.valueOf(account),item.getItemName(),item.getGameType());
					  
							if(itemUsers.size()>0)
							{
								//update itemUser
								ItemUser itemUser=itemUsers.get(0);
								itemUser.setItemNum(itemUser.getItemNum()+(Integer) DataStore.setting.get("bossRewardNum"));
								itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
					    		itemUser.getItemNum());
					    	
							}else
							{
								itemUserConfigService.addItemUser(item.getId(),Integer.valueOf(account),item.getItemName(), (Integer) DataStore.setting.get("bossRewardNum"),
					    		item.getGameType(),item.getComment());
							}
							//add itemHistory
							itemHistoryConfigService.addItemUser(item.getGameType(),Integer.valueOf(account),0,item.getItemName(),
									(Integer)DataStore.setting.get("bossRewardNum"),
									0, purchaseTime, item.getComment());
					 }	 
					 
					 //update bossUserInfo
					 userBossInfo.setResult(userBossInfo.getResult()+Integer.valueOf(result));
					 			
					 //update bossInstance
					 	 
					 if(instanceList.size()>0)
					 {
						 BossInstanceUserInfo bossInstanceTemp=instanceList.get(0);
						 
						 bossInstanceTemp.setResult(bossInstanceTemp.getResult()+Integer.valueOf(result));
						 
						 bossInstanceService.updateResult(bossInstanceTemp);
					 }
					 
					 //update userInfo
					 
					 User userInfo=userProfileService.queryUserById(Long.valueOf(account)).get(0);
					 
					 userInfo.setGold(userInfo.getGold()+Integer.valueOf(result)+prize);
					 userInfo.setDiamond(userInfo.getDiamond());
					 
					 bossUserService.updateResult(userBossInfo);
					 
					 userProfileService.updateGold(userInfo);
					 
					 userInfo=userProfileService.queryUserById(Long.valueOf(account)).get(0);
					 
					 listBoss=bossBattleService.queryByInstance(Integer.valueOf(bossInstance));
					  
					 bossBattleInfo=listBoss.get(0);
					 
					 Map <Object,Object> map=new HashMap<Object,Object>();
					 
					 //update boss status 
					 
					 if(bossBattleInfo.getStatus()==1)
					 {
							
							String startTime=bossBattleInfo.getStartTime();
							
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
							
							int continueTime=bossBattleInfo.getBattleTime();
							
							try {
								Date curretTime=format.parse(startTime);
								
								long seconds=curretTime.getTime()+continueTime*60*1000;
								
								long currentSeconds=Utility.nowMilliSecondsLong();
								
								if(currentSeconds>seconds)
								{
									bossBattleInfo.setCurrentHP(bossBattleInfo.getCurrentHP());
									bossBattleInfo.setStatus(3);
									
									bossBattleService.updateBossInfo(bossBattleInfo);
								}
							
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
					 
					listBoss=bossBattleService.queryByInstance(Integer.valueOf(bossInstance));
					  
					 bossBattleInfo=listBoss.get(0);
					 
					 map.put("bossInstance", bossBattleInfo );
					 
					 map.put("userInfo",userInfo);
					 
					
					 // update/add userResult
					 
					 List <UserBattleResult> resultList=userResultService.userBattleGameResult(Integer.valueOf(account), Integer.valueOf(battleType),
							 Integer.valueOf(gameType));
					 
					 if(resultList.size()>0)
					 {
						 //update
						 int totalResult=battleProfileService.totalReuslt(Long.valueOf(account), 2, Integer.valueOf(battleType));
						 int winResult=battleProfileService.totalWinResult(Long.valueOf(account), 2, Integer.valueOf(battleType));
						 int loseResult=battleProfileService.totalLoseResult(Long.valueOf(account), 2, Integer.valueOf(battleType));
						 userResultService.updateResult(Integer.valueOf(account),Integer.valueOf(battleType),
							 Integer.valueOf(gameType), totalResult, winResult, loseResult);
					 }
					 else
					 {
						 int totalResult=battleProfileService.totalReuslt(Long.valueOf(account), 2, Integer.valueOf(battleType));
						 int winResult=battleProfileService.totalWinResult(Long.valueOf(account), 2, Integer.valueOf(battleType));
						 int loseResult=battleProfileService.totalLoseResult(Long.valueOf(account), 2, Integer.valueOf(battleType));
						 userResultService.addUserResult(Integer.valueOf(account),Integer.valueOf(battleType),
							 Integer.valueOf(gameType), totalResult, winResult, loseResult);
						 
					 }
					 
					 responseJSON=JSONObject.fromObject(map).toString();
					 response.setStatus(200);
					 
				 }
				 else
				 {
					 ErrorResponse errorResponse = new ErrorResponse();
					 errorResponse.setErrorMessage("no bossUser find!");
					 errorResponse.setErrorAction("");
					 errorResponse.setErrorCode(ErrorCode.Battle_StartBattleFail);
						
					 responseJSON = JSONObject.fromObject(errorResponse).toString();
					 response.setStatus(401);
				 }
			
			  }
			  else
			  {
				 ErrorResponse errorResponse = new ErrorResponse();
				 errorResponse.setErrorMessage("add bossbattle fail!");
				 errorResponse.setErrorAction("");
				 errorResponse.setErrorCode(ErrorCode.Battle_StartBattleFail);
					
				 responseJSON = JSONObject.fromObject(errorResponse).toString();
				 response.setStatus(401);
			 }
					
			 try {
				 	responseJSON=CustomBase64.encode(responseJSON);
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					System.out.println(responseJSON);
					out.println(responseJSON);
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		    }  
			  
			break;
			
		  default:
			
		    break;
			 
		}
 		  
	}
	 	
	public String queryAll()
	{
		boolean flag=false;
		List <BattleHistory> list=battleProfileService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("battleHistorys", list);
		}
		
		
		return flag? SUCCESS:ERROR;
	}	
	
	public void rankTimes()
	{
		
		String postdata="";
		String decode="";
	    
	    try {
		
	    	postdata=Utility.postdata(resquest);
	    	decode=CustomBase64.decode(postdata);

	    	System.out.println("descInfo--->"+CustomBase64.decode(postdata));
		
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	   
	    String gameType=Utility.splitString(decode, "gameType");

		List <UserBattleHistory> list=userBattleService.rankTimes(GameType.values()[Integer.valueOf(gameType)].toString());
		
		String responseJSON="";
		
		if(list.size()>0)
		{
			HashMap< String, Object> map = new HashMap<String, Object>();
			map.put("rankInfor", list);
			response.setStatus(200);
			responseJSON=JSONObject.fromObject(map).toString();
	    }
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("no rank in this gameType!");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.Battle_BattleRankFail);
		
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
		
		try {
			responseJSON=CustomBase64.encode(responseJSON);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			log.info(responseJSON);
			out.println(responseJSON);
//			resquest.setAttribute("result", responseJSON);
			
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
	 }
	
	}	
	
	
	
}
