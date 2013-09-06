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

import com.casinogod.pojo.FriendInvite;
import com.casinogod.pojo.InviteTable;
import com.casinogod.pojo.InvitedInfo;
import com.casinogod.pojo.Item;
import com.casinogod.pojo.ItemUser;
import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.Task;
import com.casinogod.pojo.User;
import com.casinogod.service.FriendInvitedService;
import com.casinogod.service.InvitedTableService;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.ItemUserConfigService;
import com.casinogod.service.TaskService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.DataStore;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class InviteFriend extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private String account;
	
	private String inviteCode;
	
	private FriendInvitedService friendInvitedService;
	
	private InvitedTableService invitedTableService;
	
	private UserProfileService userProfileService;
	
	private ItemConfigService itemConfigService;
	
	private  ItemUserConfigService itemUserConfigService;
	
	private TaskService taskService;
	
	private  UserLogIn userLogInService;

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}


	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	

	public void setAccount(String account) {
		this.account = account;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public void setFriendInvitedService(FriendInvitedService friendInvitedService) {
		this.friendInvitedService = friendInvitedService;
	}

	public void setInvitedTableService(InvitedTableService invitedTableService) {
		this.invitedTableService = invitedTableService;
	}
	
	
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	public void setItemConfigService(ItemConfigService itemConfigService) {
		this.itemConfigService = itemConfigService;
	}
	

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	
	public void setItemUserConfigService(ItemUserConfigService itemUserConfigService) {
		this.itemUserConfigService = itemUserConfigService;
	}
	
	
	public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}

	public void createCode()
	{

		List <FriendInvite> list=friendInvitedService.queryByUserId(Integer.valueOf(CustomBase64.decode(this.account)));		
	
		String responseJSON= "";  
		
		boolean flag=false;
		   
	    if(list.size()<=0)  
	    {  	    	
	    
	       FriendInvite friendInvite;
	       
	       String code="";
		//			code = CustomBase64.encode(this.account);
		   code = CustomBase64.decode(this.account);
	       
	       flag=friendInvitedService.addFriendInvite(Integer.valueOf(CustomBase64.decode(this.account)), code, Utility.getNowString(),0);
	       
	       if(flag)
	       { 
	    	   friendInvite=friendInvitedService.queryByUserId(Integer.valueOf(CustomBase64.decode(this.account))).get(0);
	    	   responseJSON +=JSONObject.fromObject(friendInvite).toString();
	    	   response.setStatus(200);	
	       }
	    	
		  
		   
		   else
		   {
			   ErrorResponse errorResponse = new ErrorResponse();
			   errorResponse.setErrorMessage("add error of db");
			   errorResponse.setErrorAction("");
			   errorResponse.setErrorCode(ErrorCode.db_insert);
					
				responseJSON = JSONObject.fromObject(errorResponse).toString();
				response.setStatus(401);
		   }
	    
	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("the invitedCode is exist");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.invitecode_Exits);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
			
		try {
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}	
	
	public void showCode()
	{

		List <FriendInvite> list=friendInvitedService.queryByUserId(Integer.valueOf(CustomBase64.decode(this.account)));		
	
		String responseJSON= "";  
		
		
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       FriendInvite friendInvite;
	       
	       friendInvite=list.get(0);
	    	  
	       responseJSON +=JSONObject.fromObject(friendInvite).toString();
	       response.setStatus(200);	

	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("the invitedCode is not exist");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.invitecode_Exits);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
			
		try {
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}	
	
	public void accessCode()
	{

		List <FriendInvite> userCodeList=friendInvitedService.queryByUserId(Integer.valueOf(CustomBase64.decode(this.account)));
		
		String userCode=userCodeList.get(0).getInviteCode();
	
		String responseJSON= "";  
		
		boolean flag=false;
	
	    FriendInvite friendInvite;
	     
	     List <FriendInvite> listFriend=friendInvitedService.queryByCode(CustomBase64.decode(this.inviteCode));
	       
	     if(listFriend.size()>0)
	     { 
	    	 friendInvite=listFriend.get(0);
	    	   
	    	   if(friendInvite.getInviteCode().equals(userCode))
	    	   {
	    		   InvitedInfo info=new InvitedInfo();
	    		   SimpleUser simpleUser=new SimpleUser();
	    		   
	    		   String snsId=userLogInService.getAccount(Long.valueOf(CustomBase64.decode(this.account))).getSnsId();
	    		   
	    		   
	    		   info.setStatus(1);
	    		   info.setRepDesc("不能输入自己的邀请码");
	    		   
	    		   Map<Object,Object> map=new HashMap<Object, Object>();
	    		   
	    		   map.put("simpleUser", simpleUser);
	    		   map.put("invitedInfo", info);
	    		   map.put("snsId", snsId);
	    		   
	    		   responseJSON +=JSONObject.fromObject(map).toString();
	    	       
    			   response.setStatus(200);
	    		   
	    	   }
	    	   
	    	   else
	    	
	    	   {
	    		   flag=invitedTableService.addInviteTable(Integer.valueOf(friendInvite.getInviteCode()),
	    				   Integer.valueOf(CustomBase64.decode(this.account)),Utility.getNowString());
	    	   
	    		   if(flag)
	    		   {
	    			   friendInvite.setFriendCount(friendInvite.getFriendCount()+1);
	    			   
	    			   friendInvitedService.updateCount(friendInvite);
	    			   
	    			   User user=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.account))).get(0);
	    			   
	    			   String snsId=userLogInService.getAccount(Long.valueOf(CustomBase64.decode(this.account))).getSnsId();
				  
	    			   SimpleUser simpleUser=new SimpleUser();
					
	    			   simpleUser.setGold(user.getGold());
	    			   simpleUser.setExp(user.getExp());
	    			   simpleUser.setGender(user.getGender());
	    			   simpleUser.setImage(user.getImage());
	    			   simpleUser.setLevel(user.getLevel());
	    			   simpleUser.setNickName(user.getNickName());
	    			   simpleUser.setUserId(user.getUserId());
	    			   
	    			   InvitedInfo info=new InvitedInfo();
	    		  
	    			   info.setStatus(0);
		    		   info.setRepDesc("输入成功,谢谢使用邀请码");
		    		   
		    		   //gift reward
		    		   
		    		   int invitedReward=(Integer)DataStore.setting.get("invitedReward");
		    	       int invitedItem=(Integer)DataStore.setting.get("invitedItem");
		    	       int num=(Integer)DataStore.setting.get("bossRewardNum");
		    	       
		    	       List <User> Invitedusers=userProfileService.queryUserById(
		    	    		   Long.valueOf(friendInvite.getInviteCode()));
		    	       
		    	       User invitedUser=Invitedusers.get(0);
		    	      
		    	       List <Item> itemList=itemConfigService.queryById(invitedItem);
						   
		    	       if(itemList.size()>0)
						{
		    	    	   Item item=itemList.get(0);
								   
		    	    	   int diamond,gold = 0;
											
							diamond=user.getDiamond();
						    gold=user.getGold()+invitedReward;
							user.setGold(gold);
							user.setDiamond(diamond);
									   
							userProfileService.updateGold(user);
							
							diamond=invitedUser.getDiamond();
						    gold=invitedUser.getGold()+invitedReward;
						    invitedUser.setGold(gold);
						    invitedUser.setDiamond(diamond);
						    userProfileService.updateGold(invitedUser);
									
							List <ItemUser> itemUsers=itemUserConfigService.getItem(Integer.valueOf(CustomBase64.decode(this.account)),
									item.getItemName(),item.getGameType());
							
							List <ItemUser> itemInviteUsers=itemUserConfigService.getItem(Integer.valueOf(friendInvite.getInviteCode()),item.getItemName(),item.getGameType());
							
							if(itemUsers.size()>0)
							{
								//update itemUser
								ItemUser itemUser=itemUsers.get(0);
								itemUser.setItemNum(itemUser.getItemNum()+num);
								itemUserConfigService.updateItemUser(itemUser.getUserId(), 
										itemUser.getItemName(),itemUser.getGameType(),itemUser.getItemNum());
						    }
							else
							{
								itemUserConfigService.addItemUser(item.getId(), Integer.valueOf(CustomBase64.decode(this.account)), 
										item.getItemName(), num, item.getGameType(), item.getComment());
							}
							
							if(itemInviteUsers.size()>0)
							{
								//update itemUser
								ItemUser itemUser=itemInviteUsers.get(0);
								itemUser.setItemNum(itemUser.getItemNum()+num);
								itemUserConfigService.updateItemUser(itemUser.getUserId(), 
										itemUser.getItemName(),itemUser.getGameType(),itemUser.getItemNum());	
							}
							else
							{
								itemUserConfigService.addItemUser(item.getId(), invitedUser.getUserId(), 
										item.getItemName(), num, item.getGameType(), item.getComment());
							}
						
			    		  }
			    		 
		    		   Map<Object,Object> map=new HashMap<Object, Object>();
		    		   
		    		   map.put("simpleUser", simpleUser);
		    		   map.put("invitedInfo", info);
		    		   map.put("snsId", snsId);
		    		   
		    		   responseJSON +=JSONObject.fromObject(map).toString();
		    	       
	    			   response.setStatus(200);
	    		   }
	    		   else
	    		   {
	    			   InvitedInfo info=new InvitedInfo();
	    			   
	    			   User user=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.account))).get(0);
	 				  
	    			   SimpleUser simpleUser=new SimpleUser();
					
	    			   simpleUser.setGold(user.getGold());
	    			   simpleUser.setExp(user.getExp());
	    			   simpleUser.setGender(user.getGender());
	    			   simpleUser.setImage(user.getImage());
	    			   simpleUser.setLevel(user.getLevel());
	    			   simpleUser.setNickName(user.getNickName());
	    			   simpleUser.setUserId(user.getUserId());
	    			   
	    			   String snsId=userLogInService.getAccount(Long.valueOf(CustomBase64.decode(this.account))).getSnsId();
		    		   
		    		   
		    		   info.setStatus(2);
		    		   info.setRepDesc("已经使用过了邀请码");
		    		   
		    		   Map<Object,Object> map=new HashMap<Object, Object>();
		    		   
		    		   map.put("simpleUser", simpleUser);
		    		   map.put("invitedInfo", info);
		    		   map.put("snsId", snsId);
		    		   
		    		   responseJSON +=JSONObject.fromObject(map).toString();
		    	       
	    			   response.setStatus(200);
	    	   }
	    	 } 
	       }
		   else
		   {
			   InvitedInfo info=new InvitedInfo();
			   
			   User user=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.account))).get(0);
				  
			   SimpleUser simpleUser=new SimpleUser();
			
			   simpleUser.setGold(user.getGold());
			   simpleUser.setExp(user.getExp());
			   simpleUser.setGender(user.getGender());
			   simpleUser.setImage(user.getImage());
			   simpleUser.setLevel(user.getLevel());
			   simpleUser.setNickName(user.getNickName());
			   simpleUser.setUserId(user.getUserId());
    		   
			   String snsId=userLogInService.getAccount(Long.valueOf(CustomBase64.decode(this.account))).getSnsId();
    		   
    		   info.setStatus(3);
    		   info.setRepDesc("请输入正确邀请码");
    		   
    		   Map<Object,Object> map=new HashMap<Object, Object>();
    		   
    		   map.put("simpleUser", simpleUser);
    		   map.put("invitedInfo", info);
    		   map.put("snsId", snsId);
    		   
    		   responseJSON +=JSONObject.fromObject(map).toString();
    	       
			   response.setStatus(200);
		   }
		
		try {
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}	
	
	public void totalInvite()
	{

		List <InviteTable> list=invitedTableService.queryByUserId(Integer.valueOf(CustomBase64.decode(this.account)));	
		
		List <User> users=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.account)));
		
		//int invitedItem=(Integer)DataStore.setting.get("invitedItem");
	
		String responseJSON= "";  
		
		Map<Object,Object> map=new HashMap<Object, Object>();
		   
	    if(users.size()>0)  
	    {  	 
	    	       
	       User user=users.get(0);
	    
	       if(list.size()>0)
	       { 
	    	  
	    	   List <Task> taskList=taskService.queryByType(3);
	    	   
	    	   int [] mun=new int[taskList.size()];
	    	   
	    	   if(taskList.size()>0)
	    	   {
	    		   int temp=0;
	    		   
	    		   int index=0;
	    		   
	    		   for(int i=0;i<taskList.size()-1;i++)
	    		   {
	    			   if(list.size()<taskList.get(0).getTaskRate())
	    				   break;
	    			   else if(list.size()>taskList.get(taskList.size()-1).getTaskRate())
	    				   break;
	    			   else
	    			   {
	    				   if(list.size()>=taskList.get(i).getTaskRate()
	    						   &&list.size()<taskList.get(i+1).getTaskRate())
	    				   {
	    					   temp=taskList.get(i).getTaskRate();
	    					   index=i;
	    				   }
	    				}
	    				   
	    		   }
	    		   
	    		   if(temp!=0)
	    		   {
	    			   
	    			   List <Item> itemList=itemConfigService.queryById(taskList.get(index).getItemId());
//					   
					   if(itemList.size()>0)
					   {
						   Item item=itemList.get(0);
						   
						   if(item.getGameType()==100)
						   {
							   int diamond = 0,gold = 0;
									
							   diamond=user.getDiamond();
							   gold=user.getGold()+taskList.get(index).getItemNum();
							   user.setGold(gold);
							   user.setDiamond(diamond);
							   
							   userProfileService.updateGold(user);
							}
							else
							{
								List <ItemUser> itemUsers=itemUserConfigService.getItem(Integer.valueOf(CustomBase64.decode(this.account)),
										item.getItemName(),item.getGameType());
								  
								//update itemUser
								ItemUser itemUser=itemUsers.get(0);
								itemUser.setItemNum(itemUser.getItemNum()+taskList.get(index).getItemNum());
								itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
						    	      itemUser.getItemNum());
						    	
							}
	    		         }
	    		 }
	    	  }
	       }
	    	   
	       users=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.account)));
	       
	       String snsId=userLogInService.getAccount(Long.valueOf(CustomBase64.decode(this.account))).getSnsId();
	    	   
	       user=users.get(0);
	    	   
	       SimpleUser simpleUser=new SimpleUser();
					
		   simpleUser.setGold(user.getGold());
		   simpleUser.setExp(user.getExp());
		   simpleUser.setGender(user.getGender());
		   simpleUser.setImage(user.getImage());
		   simpleUser.setLevel(user.getLevel());
		   simpleUser.setNickName(user.getNickName());
		   simpleUser.setUserId(user.getUserId());
		   
		   map.put("simpleUser", simpleUser);
		   map.put("snsId", snsId);
	    		  
	       responseJSON +=JSONObject.fromObject(map).toString();
	    	       
	       response.setStatus(200);	
	    
	    }
	   else
	   {
			
	    	ErrorResponse errorResponse = new ErrorResponse();
			
	    	errorResponse.setErrorMessage("the invitedCode is exist");
			
	    	errorResponse.setErrorAction("");
			
	    	errorResponse.setErrorCode(ErrorCode.invitecode_Exits);
				
			
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
