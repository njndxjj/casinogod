package com.casinogod.webinterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.casinogod.pojo.EventConfig;
import com.casinogod.pojo.Item;
import com.casinogod.pojo.ItemUser;
import com.casinogod.pojo.User;
import com.casinogod.service.EventService;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.ItemHistoryConfigService;
import com.casinogod.service.ItemUserConfigService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class ItemPurchase extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	

	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private  UserProfileService   userProfileService;
	
	private  ItemConfigService    itemConfigService;
	
	private  ItemHistoryConfigService   itemHistoryConfigService;
	
	private  ItemUserConfigService itemUserConfigService;
	
	private EventService eventService;
	
	private  UserLogIn userLogInService;
	
	private static Logger log = Logger.getLogger(ItemPurchase.class); 
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	




	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.resquest=request;
	}
	
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setItemConfigService(ItemConfigService itemConfigService) {
		this.itemConfigService = itemConfigService;
	}

	public void setItemHistoryConfigService(
			ItemHistoryConfigService itemHistoryConfigService) {
		this.itemHistoryConfigService = itemHistoryConfigService;
	}
	
	
	public void setItemUserConfigService(ItemUserConfigService itemUserConfigService) {
		this.itemUserConfigService = itemUserConfigService;
	}
	
	

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	
	public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}


	public void buyitems() 
	
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
	    String itemId=Utility.splitString(decode, "itemId");
	    String itemNum=Utility.splitString(decode, "itemNum");
	
		
		long itemAccount=Long.valueOf(account);
		
		List <User> list=userProfileService.queryUserById(itemAccount);
		
		Map<Object,Object> map=new HashMap<Object, Object>();
		
		String responseJSON= "";  
		
		if(list.size()>0) 
		{
			User user=list.get(0);
			
			List <Item> items=itemConfigService.queryById(Integer.valueOf(itemId));
			
			Item item=items.get(0);
			
			if(item.getItemName().equals("Gold")||item.getGameType()==100)
			{
				
				//buy gold
				
				int diamond = 0,gold = 0;
				
				if(user.getDiamond()-Integer.valueOf(itemNum)>0&&user.getDiamond()>0)
			
				{
					List <EventConfig> listEvent=eventService.queryByType(1);
									
					if(listEvent.size()>0)
					{
						EventConfig eventConfig=listEvent.get(0);
						
						String startTime=eventConfig.getStartTime();
						
						String endTime=eventConfig.getEndTime();
						
				        String currentTime=Utility.getNowString();
				        
				        org.json.JSONObject data;
				        
				        int discount=0;
				        
				        try {
				        	data=new org.json.JSONObject(eventConfig.getDetailData());
				        	discount=data.getInt("GoldDiscount");
				        	log.info("discount-->"+discount);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
						if(eventConfig.getFrequency()==1)
						{
							if(currentTime.compareTo(startTime)>=0&&currentTime.compareTo(endTime)<=0)
							{
								diamond=user.getDiamond()-Integer.valueOf(itemNum);
								gold=user.getGold()+(Integer.valueOf(itemNum))*(item.getItemPrice()*(1+discount));
							}
							else
							{
								diamond=user.getDiamond()-Integer.valueOf(itemNum);
								gold=user.getGold()+(Integer.valueOf(itemNum)*item.getItemPrice());
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
									diamond=user.getDiamond()-Integer.valueOf(itemNum);
									gold=user.getGold()+(Integer.valueOf(itemNum)*(item.getItemPrice()*(1+discount)));
								}
								else
								{
									diamond=user.getDiamond()-Integer.valueOf(itemNum);
									gold=user.getGold()+(Integer.valueOf(itemNum)*item.getItemPrice());
								}
								
								
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
					else
					{
						diamond=user.getDiamond()-Integer.valueOf(itemNum);
						gold=user.getGold()+(Integer.valueOf(itemNum)*item.getItemPrice());
					}
					user.setGold(gold);
					user.setDiamond(diamond);
				  
					//update userProfile
					userProfileService.updateGold(user);
				    
				    String purchaseTime=Utility.getNowString();
				   
				    //add itemHistory
				    itemHistoryConfigService.addItemUser(item.getGameType(),Integer.valueOf(account),0,item.getItemName(),
				    		Integer.valueOf(itemNum),
				    0, purchaseTime, item.getComment());
				    
				    List <User> listUser=userProfileService.queryUserById(Integer.valueOf(account));
				    
				    String snsId=userLogInService.getAccount(Long.valueOf(account)).getSnsId();
				    if(snsId==null) snsId="";
				    map.put("userInfo", listUser.get(0));
				    map.put("snsId", snsId);
				    				       
				    responseJSON +=JSONObject.fromObject(map).toString();
				    	
					response.setStatus(200);	

				}
			
				else
				{
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setErrorMessage("Cannot find any user Item Infromation");
					errorResponse.setErrorAction("");
					errorResponse.setErrorCode(ErrorCode.item_Information);
						
					responseJSON = JSONObject.fromObject(errorResponse).toString();
					response.setStatus(401);
				}	
			}
			
			else
			
			{
				if(user.getDiamond()-Integer.valueOf(itemNum)>0&&user.getDiamond()>0)
				
				{
					int diamond=user.getDiamond()-Integer.valueOf(itemNum);
					user.setGold(user.getGold());
					user.setDiamond(diamond);
			  
					//update userProfile
					userProfileService.updateGold(user);
			    
					String purchaseTime=Utility.getNowString();
			   
					//add or update itemUser;
					List <ItemUser> itemUsers=itemUserConfigService.getItem(Integer.valueOf(account),item.getId(),item.getGameType());
			  
					if(itemUsers.size()>0)
					{
						//update itemUser
						ItemUser itemUser=itemUsers.get(0);
						itemUser.setItemNum(itemUser.getItemNum()+(item.getItemPrice()*Integer.valueOf(itemNum)));
						itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
			    		itemUser.getItemNum());
			    	
					}else
					{
						itemUserConfigService.addItemUser(Integer.valueOf(itemId),Integer.valueOf(account),
								item.getItemName(), Integer.valueOf(itemNum),
			    		item.getGameType(),item.getComment());
					}
					//add itemHistory
					itemHistoryConfigService.addItemUser(item.getGameType(),Integer.valueOf(account),
							0,item.getItemName(),Integer.valueOf(itemNum),0, purchaseTime, item.getComment());
			    
					List <User> listUser=userProfileService.queryUserById(Integer.valueOf(account));
					
					String snsId=userLogInService.getAccount(Long.valueOf(account)).getSnsId();
					if(snsId==null) snsId="";   
					map.put("userInfo", listUser.get(0));
					map.put("snsId", snsId);
			    
				    responseJSON +=JSONObject.fromObject(map).toString();
				    	
					response.setStatus(200);	

				}
			
				else
				{
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setErrorMessage("Cannot find any user Item Infromation");
					errorResponse.setErrorAction("");
					errorResponse.setErrorCode(ErrorCode.item_Information);
						
					responseJSON = JSONObject.fromObject(errorResponse).toString();
					response.setStatus(401);
				}
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

	public void giftItems()
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
	    String itemId=Utility.splitString(decode, "itemId");
	    String itemNum=Utility.splitString(decode, "itemNum");
	    String giftUserId=Utility.splitString(decode, "giftUserId");
	    
	    
	    List <User> list=userProfileService.queryUserById(Integer.valueOf(account));
	    
	    List <User> giftList=userProfileService.queryUserById(Integer.valueOf(giftUserId));
	    
	    boolean flag=false;
	    
	    String responseJSON="";
		
		if(list.size()>0&&giftList.size()>0) 
		
		{
			User user=list.get(0);

			List <ItemUser> itemUsers=user.getItems();
			
			ItemUser itemUserTemp=new ItemUser();
			
			for(ItemUser itemUser :itemUsers)
			{
				if(itemUser.getItemId()==Integer.valueOf(itemId))
				{
					itemUserTemp=itemUser;
					 break;
				}
			}
			
			if(itemUserTemp.getItemNum()-Integer.valueOf(itemNum)>0)
			{
			
			  //update itemUser;
			  List <ItemUser> itemUsersBuy=itemUserConfigService.getItem(Integer.valueOf(account),
					  itemUserTemp.getItemId(),itemUserTemp.getGameType());
			  
			  //update itemUser
			  ItemUser itemUser=itemUsersBuy.get(0);
			 
			  itemUser.setItemNum(itemUser.getItemNum()-Integer.valueOf(itemNum));
			  
			  itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
			    			itemUser.getItemNum());
			  
			   //add itemHistory
			  itemHistoryConfigService.addItemUser(itemUserTemp.getGameType(),Integer.valueOf(account),2,
					  itemUserTemp.getItemName(),Integer.valueOf(itemNum),
					  Integer.valueOf(giftUserId), Utility.getNowString(), itemUserTemp.getComment());
			  
			  flag=true;
			
			}
			else if(itemUserTemp.getItemNum()-Integer.valueOf(itemNum)==0)
			{
				
				// update itemUser;
				List <ItemUser> itemUsersBuy=itemUserConfigService.getItem(Integer.valueOf(account),
						itemUserTemp.getItemId(),itemUserTemp.getGameType());
				  
				//update itemUser
				ItemUser itemUser=itemUsersBuy.get(0);
				
				itemUserConfigService.deleteItemUser(itemUser.getId());			
				  //add itemHistory
				 itemHistoryConfigService.addItemUser(itemUserTemp.getGameType(),Integer.valueOf(account),
						 2,itemUserTemp.getItemName(),Integer.valueOf(itemNum),
						 Integer.valueOf(giftUserId), Utility.getNowString(), itemUserTemp.getComment());
				 
				 flag=true;
				
			}
			else
			{
				flag=false;
			}
			
	if(flag)
	
	{
		//update or add gift item
		
		List <ItemUser> itemUsersGift=itemUserConfigService.getItem(Integer.valueOf(giftUserId),
				itemUserTemp.getItemId(),itemUserTemp.getGameType());		
	
		if(itemUsersGift.size()>0)
		
		{
			//update itemUser
			ItemUser itemUser=itemUsersGift.get(0);
		 
			itemUser.setItemNum(itemUser.getItemNum()+Integer.valueOf(itemNum));
		  
			itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
		    			itemUser.getItemNum()); 
		}
		else
		{
			itemUserConfigService.addItemUser(Integer.valueOf(itemId),Integer.valueOf(giftUserId),
					itemUserTemp.getItemName(), Integer.valueOf(itemNum),
				itemUserTemp.getGameType(),itemUserTemp.getComment());
		}
	 
	}
	 
	if(flag)
		
	{
	 
	 
		Map<String,Object> map=new HashMap<String, Object>();
		List <User> listUser=userProfileService.queryUserById(Integer.valueOf(account));
			 
	       
		String snsId=userLogInService.getAccount(Long.valueOf(account)).getSnsId();
		if(snsId==null) snsId="";
		map.put("userInfo", listUser.get(0));
		map.put("snsId", snsId);
    
	    responseJSON +=JSONObject.fromObject(map).toString();
	    	
		response.setStatus(200);	
	}
	else
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("Cannot find any user Item Infromation");
		errorResponse.setErrorAction("");
		errorResponse.setErrorCode(ErrorCode.item_Information);
			
		responseJSON = JSONObject.fromObject(errorResponse).toString();
		response.setStatus(401);
	}

	}

	else
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("user no find");
		errorResponse.setErrorAction("");
		errorResponse.setErrorCode(ErrorCode.UserAuth_UserNotExist);
			
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
		
		
	public void consumeItem()
	
	{
		  boolean flag=false;
		  
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
		    String itemId=Utility.splitString(decode, "itemId");
		    String itemNum=Utility.splitString(decode, "itemNum");
		    String giftUserId=Utility.splitString(decode, "giftUserId");
		    
		  List <User> list=userProfileService.queryUserById(Long.valueOf(account));
		  
		  String responseJSON="";
			
	      if(list.size()>0) 
			
		   {
				User user=list.get(0);

				List <ItemUser> itemUsers=user.getItems();
				
				ItemUser itemUserTemp=new ItemUser();
				

				for(ItemUser itemUser :itemUsers)
				{
					if(itemUser.getItemId()==Integer.valueOf(itemId))
					{
						itemUserTemp=itemUser;
						 break;
					}
				}
				
				if(itemUserTemp.getItemNum()-Integer.valueOf(itemNum)>0)
				{
				
				  //update itemUser;
				  List <ItemUser> itemUsersBuy=itemUserConfigService.getItem(Integer.valueOf(account),
						  itemUserTemp.getItemId(),itemUserTemp.getGameType());
				  
				  //update itemUser
				  ItemUser itemUser=itemUsersBuy.get(0);
				 
				  itemUser.setItemNum(itemUser.getItemNum()-Integer.valueOf(itemNum));
				  
				  itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
				    			itemUser.getItemNum());
				  //add itemHistory
				 itemHistoryConfigService.addItemUser(itemUserTemp.getGameType(),
						 Integer.valueOf(account),1,itemUserTemp.getItemName(),Integer.valueOf(itemNum),
				    0, Utility.getNowString(), itemUserTemp.getComment());
				
				 
				 flag=true;
				
				}
				else if(itemUserTemp.getItemNum()-Integer.valueOf(itemNum)==0)
				{
					
					// update itemUser;
					List <ItemUser> itemUsersBuy=itemUserConfigService.getItem(Integer.valueOf(account),itemUserTemp.getItemId(),
							itemUserTemp.getGameType());
					  
					//update itemUser
					ItemUser itemUser=itemUsersBuy.get(0);
					
					itemUserConfigService.deleteItemUser(itemUser.getId());			
					  //add itemHistory
					 itemHistoryConfigService.addItemUser(itemUserTemp.getGameType(),Integer.valueOf(account),1,
							 itemUserTemp.getItemName(),Integer.valueOf(itemNum),
					 0, Utility.getNowString(), itemUserTemp.getComment());
					 
					 
					 flag=true;
				}
				else
				{
					flag=false;
				}
				
				
				if(flag)
				{
					
					Map<String,Object> map=new HashMap<String, Object>();
					List <User> listUser=userProfileService.queryUserById(Integer.valueOf(account));
						 
				       
					String snsId=userLogInService.getAccount(Long.valueOf(account)).getSnsId();
					if(snsId==null) snsId="";
					map.put("userInfo", listUser.get(0));
					map.put("snsId", snsId);
			    
				    responseJSON +=JSONObject.fromObject(map).toString();
					    	
				     response.setStatus(200);
				}
				else
				{
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setErrorMessage("purash item fail");
					errorResponse.setErrorAction("");
					errorResponse.setErrorCode(ErrorCode.item_Information);
						
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
	
}
