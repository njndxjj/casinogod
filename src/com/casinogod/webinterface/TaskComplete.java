package com.casinogod.webinterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.core.task.TaskTimeoutException;

import com.casinogod.pojo.BattleHistory;
import com.casinogod.pojo.InviteTable;
import com.casinogod.pojo.Item;
import com.casinogod.pojo.ItemUser;
import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.Task;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserTask;
import com.casinogod.service.BattleProfileService;
import com.casinogod.service.InvitedTableService;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.ItemUserConfigService;
import com.casinogod.service.TaskService;
import com.casinogod.service.UserProfileService;
import com.casinogod.service.UserTaskService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class TaskComplete extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
		
	private BattleProfileService battleProfileService;
	
	
	private UserProfileService userProfileService;
	
	private ItemConfigService itemConfigService;
	
	private  ItemUserConfigService itemUserConfigService;
	
	private InvitedTableService invitedTableService;
	
	private TaskService taskService;
	
	private UserTaskService userTaskService;
	
	private static Logger log = Logger.getLogger(TaskComplete.class);

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
	
	
	public void setBattleProfileService(BattleProfileService battleProfileService) {
		this.battleProfileService = battleProfileService;
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
	
	public void setInvitedTableService(InvitedTableService invitedTableService) {
		this.invitedTableService = invitedTableService;
	}
	
	
	public void setUserTaskService(UserTaskService userTaskService) {
		this.userTaskService = userTaskService;
	}
	

	public void taskComplete()
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
		    String taskId=Utility.splitString(decode,"taskId");
		
			List <User> users=userProfileService.queryUserById(Long.valueOf(account));
			
			List <Task> taskList=taskService.queryById(Integer.valueOf(taskId));
			
			Task task=taskList.get(0);
			
			List <UserTask>  userTask=userTaskService.queryByStatus
					(Integer.valueOf(account), Integer.valueOf(taskId), 
							Utility.getDateString(),1);
			
			List <UserTask>  userTaskFriend=userTaskService.queryByIdAndNoTime(Integer.valueOf(account), 
					Integer.valueOf(taskId), 1);
		
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			Calendar day=Calendar.getInstance();
		
			//day.add(Calendar.DATE, -1);
		
			String yesToday=format.format(day.getTime());
			
			String startTime=yesToday+" 00:00:00";
			
			String endTime=yesToday+" 23:59:59";
		
			String responseJSON= "";  
			
			User user;
		
			if(task.getTaskType()==1)
			{
		    if(userTask.size()<=0)	    
		    {  	 
		    	       
		        user=users.get(0);
		       
		       List <BattleHistory> list=battleProfileService.winBattle(user.getUserId(), 2, 
		    		   task.getBattleType(), startTime,endTime,task.getGameType());
		       
		       int temp=0;
		       
		       int index=0;

		       if(taskList.size()>0)
		       {
		    	  if(list.size()>0)
		    	  {
		    		  List <UserTask>  userTaskStatus=userTaskService.queryByStatus
	    						(Integer.valueOf(account), Integer.valueOf(taskId), Utility.getDateString(),0);
		    		  if(list.size()>=task.getTaskRate())
		    		  {
		    			  if(userTaskStatus.size()<=0)
		    			  {
		    				  //add 
		    				  userTaskService.insertUserTask(Integer.valueOf(account), Integer.valueOf(taskId), 1, Utility.getNowString());
		    			  }
		    			  else
		    			  {
		    				  userTaskService.updateType(Integer.valueOf(account), Integer.valueOf(taskId), 1, Utility.getNowString());
		    			  }
		    			  temp=1;
		    		  }
		    		  else
		    		  {
		    			  if(userTaskStatus.size()<=0)
		    				  userTaskService.insertUserTask(Integer.valueOf(account), Integer.valueOf(taskId), 0, Utility.getNowString());
		    		  }
		    		  
		    	  }
		    	
		    	  if(temp!=0)
		    	  {
		    		  
		    		  List <Item> itemList=itemConfigService.queryById(task.getItemId());
					
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
							  List <ItemUser> itemUsers=itemUserConfigService.getItem(Integer.valueOf(account),item.getId(),item.getGameType());
									  
							  //update itemUser
							  ItemUser itemUser=itemUsers.get(0);
							  itemUser.setItemNum(itemUser.getItemNum()+taskList.get(index).getItemNum());
							  itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
											itemUser.getItemNum());
							    	
						  }
		    		 }
		    	   }
		       }
		    	   
		       users=userProfileService.queryUserById(Long.valueOf(account));
		    	   
		       user=users.get(0);
		       
		       List <UserTask>  userTaskFinish=userTaskService.queryByIdAndType(Integer.valueOf(account), Integer.valueOf(taskId), Utility.getDateString());
		    	  
		       Map<Object, Object> map=new HashMap<Object, Object>();	
		       
		       UserTask tempTask=new UserTask();
		       
		       if(userTaskFinish.size()==0)
		       {
		    	   
		    	   tempTask.setStatus(0);
		    	   tempTask.setTaskId(Integer.valueOf(taskId));
		    	   tempTask.setUpdateTime(Utility.getNowString());
		    	   tempTask.setUserId(Integer.valueOf(account));
		       }
		       
		       
		       map.put("userInfo", user);
		      
		       if(userTaskFinish.size()==0)
		       {
		    	   map.put("taskInfo", tempTask);
		       }
		       else
		       {
		    	   map.put("taskInfo", userTaskFinish.get(0));
		       }
		       
		       responseJSON +=JSONObject.fromObject(map).toString();
		    	       
		       response.setStatus(200);	
		    
		    }
			
		   else
		   {
				
		    	ErrorResponse errorResponse = new ErrorResponse();
				
		    	errorResponse.setErrorMessage("the task has complete");
				
		    	errorResponse.setErrorAction("");
				
		    	errorResponse.setErrorCode(ErrorCode.invitecode_Exits);
					
				
		    	responseJSON = JSONObject.fromObject(errorResponse).toString();
				
		    	response.setStatus(200);
		    }
	}
	else if(task.getTaskType()==2)
	{
		 if(userTask.size()<=0)  
		 {  	 
			 user=users.get(0);       
		       
		       int total=battleProfileService.isSpecialWin(user.getUserId(), 2, 
		    		   task.getBattleType(), startTime,endTime,task.getGameType());
		       
		       int temp=0;
		       int index=0;

		       taskList=taskService.queryByType(2);
		   
		       if(taskList.size()>0)
		       {
		    	   if(total>0)
			    	  {
			    		  List <UserTask>  userTaskStatus=userTaskService.queryByStatus
		    						(Integer.valueOf(account), Integer.valueOf(taskId), Utility.getDateString(),0);
			    		  if(total>=task.getTaskRate())
			    		  {
			    			  if(userTaskStatus.size()<=0)
			    			  {
			    				  //add 
			    				  userTaskService.insertUserTask(Integer.valueOf(account), Integer.valueOf(taskId), 1, Utility.getNowString());
			    			  }
			    			  else
			    			  {
			    				  userTaskService.updateType(Integer.valueOf(account), Integer.valueOf(taskId), 1, Utility.getNowString());
			    			  }
			    			  temp=1;
			    		  }
			    		  else
			    		  {
			    			  if(userTaskStatus.size()<=0)
			    				  userTaskService.insertUserTask(Integer.valueOf(account), Integer.valueOf(taskId), 0, Utility.getNowString());
			    		  }
			    		 
		    				   
		               }
		 
		    		  
		   if(temp!=0)
		    {
			   List <Item> itemList=itemConfigService.queryById(task.getItemId());
					
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
							  List <ItemUser> itemUsers=itemUserConfigService.getItem(Integer.valueOf(account),item.getId(),item.getGameType());
									  
							  //update itemUser
							  ItemUser itemUser=itemUsers.get(0);
							  itemUser.setItemNum(itemUser.getItemNum()+taskList.get(index).getItemNum());
							  itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
											itemUser.getItemNum());
							    	
						  }
		    		 }
		    }
		       
		   
		    	   
		       
		       
		    List <UserTask>  userTaskFinish=userTaskService.queryByIdAndType(Integer.valueOf(account), Integer.valueOf(taskId), Utility.getDateString());
		    	  
		    Map<Object, Object> map=new HashMap<Object, Object>();		    		  
		       
		    UserTask tempTask=new UserTask();
		       
		    if(userTaskFinish.size()==0)
		     {
		    	   
		    	   tempTask.setStatus(0);
		    	   tempTask.setTaskId(Integer.valueOf(taskId));
		    	   tempTask.setUpdateTime(Utility.getNowString());
		    	   tempTask.setUserId(Integer.valueOf(account));
		       }
		       
		       
		       map.put("userInfo", user);
		       if(userTaskFinish.size()==0)
		       {
		    	   map.put("taskInfo", tempTask);
		       }
		       else
		       {
		    	   map.put("taskInfo", userTaskFinish.get(0));
		       }
		       
		       responseJSON +=JSONObject.fromObject(map).toString();
		    	       
		       response.setStatus(200);	
		    
		    }
			
		   else
		   {
				
		    	ErrorResponse errorResponse = new ErrorResponse();
				
		    	errorResponse.setErrorMessage("the task has complete");
				
		    	errorResponse.setErrorAction("");
				
		    	errorResponse.setErrorCode(ErrorCode.invitecode_Exits);
					
				
		    	responseJSON = JSONObject.fromObject(errorResponse).toString();
				
		    	response.setStatus(200);
		    }
				
			
	}
			
	else if(task.getTaskType()==3)
	{
		
		List <InviteTable> list=invitedTableService.queryByUserId(Integer.valueOf(account));	
		
		user=users.get(0);
		   
	    if(userTaskFriend.size()<=0)  
	    {  	 
	    	
	    	int temp=0;
 		   
 		   int index=0;
	    	         
	       if(list.size()>0)
	       { 
	    	  
	    	   taskList=taskService.queryByType(3);
	    	      
	    	   if(taskList.size()>0)
	    	   {
	    		   
	    		   
			    	List <UserTask>  userTaskStatus=userTaskService.queryByStatus
		    						(Integer.valueOf(account), Integer.valueOf(taskId), Utility.getDateString(),0);
			    	
			    	if(list.size()>=task.getTaskRate())
			    	{
			    		if(userTaskStatus.size()<=0)
			    		{
			    			//add 
			    			userTaskService.insertUserTask(Integer.valueOf(account), Integer.valueOf(taskId), 1, Utility.getNowString());
			    		}
			    		else
			    		{
			    			userTaskService.updateType(Integer.valueOf(account), Integer.valueOf(taskId), 1, Utility.getNowString());
			    		}
			    		temp=1;
			    	}
			    	else
			    	{
			    		if(userTaskStatus.size()<=0)
			    			userTaskService.insertUserTask(Integer.valueOf(account), Integer.valueOf(taskId), 0, Utility.getNowString());
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
								List <ItemUser> itemUsers=itemUserConfigService.getItem(Integer.valueOf(account),item.getId(),item.getGameType());
								  
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
	    	   
	       List <UserTask>  userTaskFinish=userTaskService.queryByIdAndType(Integer.valueOf(account), Integer.valueOf(taskId), Utility.getDateString());
	    	  
		    Map<Object, Object> map=new HashMap<Object, Object>();		    		  
		       
		    UserTask tempTask=new UserTask();
		       
		    if(userTaskFinish.size()==0)
		    {
		    	   
		    	   tempTask.setStatus(0);
		    	   tempTask.setTaskId(Integer.valueOf(taskId));
		    	   tempTask.setUpdateTime(Utility.getNowString());
		    	   tempTask.setUserId(Integer.valueOf(account));
		     }
		    
		    map.put("userInfo", user);
		    if(userTaskFinish.size()==0)
		    {
		    	map.put("taskInfo", tempTask);
		     }
		    else
		    {
		    	map.put("taskInfo", userTaskFinish.get(0));
		     }
		       
		       responseJSON +=JSONObject.fromObject(map).toString();
		    	       
		       response.setStatus(200);	
		    
		    }
			
		   else
		   {
				
		    	ErrorResponse errorResponse = new ErrorResponse();
				
		    	errorResponse.setErrorMessage("the task has complete");
				
		    	errorResponse.setErrorAction("");
				
		    	errorResponse.setErrorCode(ErrorCode.invitecode_Exits);
					
				
		    	responseJSON = JSONObject.fromObject(errorResponse).toString();
				
		    	response.setStatus(200);
		    }
	    }
	else
	{
		//
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
				
			
		
	

	
	public void taskList()
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
		
		List <Task> tasklist= taskService.queryallStatus();
		
		List <UserTask> userTaskList=userTaskService.queryById(Integer.valueOf(account));
		
		String responseJSON="";
		   
	    if(tasklist.size()>0)  
	    {  	 
	    	
	    	Map <String,List> map= new HashedMap();
	    	
	    	if(userTaskList.size()>0)
	    	{
	    		for(int i=0;i<userTaskList.size();i++)
	    		{
	    			if(userTaskList.get(i).getStatus()==1)
	    			{
	    				for(int j=0;j<tasklist.size();j++)
	    				{
	    					if(tasklist.get(j).getTaskId()==userTaskList.get(i).getTaskId())
	    						tasklist.get(j).setStatus(userTaskList.get(i).getStatus());
	    				}
	    			}
	    		}
	    	}
	    	
	    	
	    	map.put("taskInfo", tasklist);
	    	       
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
