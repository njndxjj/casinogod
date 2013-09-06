package com.casinogod.webinterface;



import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.org.rapid_framework.util.CalendarUtils;

import com.casinogod.action.AuthorityInterceptor;
import com.casinogod.pojo.FriendRequest;
import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.User;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.RequestConfigService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FriendsRequest extends ActionSupport implements ServletResponseAware {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String account;
    
    private String userId;
        	
	private UserProfileService userProfileService;
	
	private UserLogIn userLogInService;
	
	private RequestConfigService requestConfigService;
	
	
	private HttpServletResponse response;
	
	private static Logger log = Logger.getLogger(FriendsRequest.class); 
    
  //  private HttpServletRequest request=ServletActionContext.getRequest();
	
	public void setAccount(String account) {
		this.account = account;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setRequestConfigService(RequestConfigService requestConfigService) {
		this.requestConfigService = requestConfigService;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void queryallunFriend()  {
    	
    	String responseJSON="";
        
    	List<User> list = userProfileService.queryallUser(); 
        
		List <String> userList=new ArrayList<String>();
		
		for(int i=0;i<list.size()-1;i++)
		{
			userList.add(i, String.valueOf(list.get(i+1).getUserId()));
		}
		
		List <User> users=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.account)));
		
		if(users.get(0).getFriendList()!=null)
		{
			List <String> friends=new ArrayList<String>();
			for(String userId:users.get(0).getFriendList().split("#"))
			{
				friends.add(userId);
			}
			
			userList.removeAll(friends);
		}
				
		FriendRequest friendRequest=new FriendRequest();
		
		friendRequest.setOwenId(Long.valueOf(CustomBase64.decode(this.account)));
		
		friendRequest.setStatue(0);
		
		List <String> requestList=requestConfigService.queryStatue(friendRequest);
		
		if(requestList.size()>0)
		{
			userList.removeAll(requestList);
		}
		
		userList.remove(String.valueOf(users.get(0).getUserId()));
		
		List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
		
		List <String> snsIds=new ArrayList<String>();
		
		if(users.size()>0)
		{
		
		if(userList.size()>0)
			 
		{       
			for(String userId:userList)
			{
				User user=userProfileService.queryUserById(Long.valueOf(userId)).get(0);
				
				String snsId=userLogInService.getAccount(Long.valueOf(userId)).getSnsId();
				
				snsIds.add(snsId);
				
				SimpleUser simpleUser=new SimpleUser();
				
				simpleUser.setGold(user.getGold());
				simpleUser.setExp(user.getExp());
				simpleUser.setGender(user.getGender());
				simpleUser.setImage(user.getImage());
				simpleUser.setLevel(user.getLevel());
				simpleUser.setNickName(user.getNickName());
				simpleUser.setUserId(user.getUserId());
				
				simpleList.add(simpleUser);
			}
			
		}
			
		HashMap< String, Object> map = new HashMap<String, Object>();
			    	
		map.put("simpleUser", simpleList);
		map.put("snsId", snsIds);
			       
		responseJSON +=JSONObject.fromObject(map).toString();
			    	
		response.setStatus(200);	
		
	  }
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("get user:  error");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.update_UserProfile);
			
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(501);
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
    
    public void sendRequest()  {
    	
    	String responseJSON="";
    	
    	boolean flag=false;

    	long owenId=Long.valueOf(CustomBase64.decode(this.account));
		
		String dateTime=Utility.getNowString();
		
		flag=requestConfigService.addFriendRequest(owenId, Long.valueOf(CustomBase64.
				decode(this.userId)), dateTime,0);
		
		List <String> snsIds=new ArrayList<String>();
		
		if(flag)
			 
		{       
			// friends information
			
			List <User> users=userProfileService.queryUserById(owenId);
			
			List <String> friends=new ArrayList<String>();

			if(users.size()>0)
			{
			    if(users.get(0).getFriendList()!=null)  
			    {
			    	for(String account:users.get(0).getFriendList().split("#"))
			    	{
			    		if(account!=null)
			    			friends.add(account);
			    	}
				}

			}
			
			List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
			
			if(friends.size()>0)
			{

				for(String userId:friends)
				{
					User user=userProfileService.queryUserById(Long.valueOf(userId)).get(0);
					SimpleUser simpleUser=new SimpleUser();
					
					String snsId=userLogInService.getAccount(Long.valueOf(userId)).getSnsId();
					
					snsIds.add(snsId);
					
					simpleUser.setGold(user.getGold());
					simpleUser.setExp(user.getExp());
					simpleUser.setGender(user.getGender());
					simpleUser.setImage(user.getImage());
					simpleUser.setLevel(user.getLevel());
					simpleUser.setNickName(user.getNickName());
					simpleUser.setUserId(user.getUserId());
					
					simpleList.add(simpleUser);
			   }
			}
				
			HashMap< String, Object> map = new HashMap<String, Object>();
				    	
			map.put("friends", simpleList);
			map.put("snsId", snsIds);
				       
			responseJSON +=JSONObject.fromObject(map).toString();
				    	
			response.setStatus(200);	
		}
		
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("get user:  error");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.update_UserProfile);
			
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(501);
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
    
    
    public void showFriends()  {
    	
    	String responseJSON="";
        
    	boolean flag=false;
		
    	long owenId=Long.valueOf(CustomBase64.decode(this.account));
		
		log.info("owenId -->"+owenId);
	
		List <User> users=userProfileService.queryUserById(owenId);
		
		List <String> friends=new ArrayList<String>();

		if(users.size()>0)
		{
		    if(users.get(0).getFriendList()!=null)  
		    {
		    	for(String account:users.get(0).getFriendList().split("#"))
		    	{
		    		if(account!=null)
		    			friends.add(account);
		    	}
		    	
		    	flag=true;
			}

		
		
		List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
		List <String> snsIds=new ArrayList<String>();
		
		if(friends.size()>0)
		{
			for(String userId:friends)
		
			{
				
			User user=userProfileService.queryUserById(Long.valueOf(userId)).get(0);
				
			SimpleUser simpleUser=new SimpleUser();
			
			String snsId=userLogInService.getAccount(Long.valueOf(userId)).getSnsId();
			
			snsIds.add(snsId);
				
			simpleUser.setGold(user.getGold());
			simpleUser.setExp(user.getExp());
			simpleUser.setGender(user.getGender());
			simpleUser.setImage(user.getImage());
			simpleUser.setLevel(user.getLevel());
			simpleUser.setNickName(user.getNickName());
			simpleUser.setUserId(user.getUserId());
				
			simpleList.add(simpleUser);
			
			}
		}
			
			HashMap< String, Object> map = new HashMap<String, Object>();
			    	
			map.put("friends", simpleList);
			map.put("snsId", snsIds);
			       
			responseJSON +=JSONObject.fromObject(map).toString();
			    	
		    response.setStatus(200);	
		}
		
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("get user:  error");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.update_UserProfile);
			
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(501);
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
    
    
    public void acceptRequest()  {
    	
    	
    	String responseJSON="";

    	long owenId=Long.valueOf(CustomBase64.decode(this.account));
	
    	log.info("owenId -->"+owenId);
	
    	boolean flag=false;

    	List <User> users=userProfileService.queryUserById(owenId);
    	
    	//update request
    
		FriendRequest friendRequest=new FriendRequest();
		long userId=Long.valueOf(CustomBase64.decode(this.userId));
		log.info("userId-->"+userId);
		friendRequest.setOwenId(userId);
		friendRequest.setUserId(owenId);
		friendRequest.setStatue(1);
		requestConfigService.updateStatue(friendRequest);
		
		//update userFriends
		if(users.get(0).getFriendList()==null) 
			users.get(0).setFriendList(String.valueOf(userId)+"#");
		else
		{
			if(users.get(0).getFriendList().contains(String.valueOf(userId)))
				users.get(0).setFriendList(users.get(0).getFriendList());
			else
				users.get(0).setFriendList(users.get(0).getFriendList()+String.valueOf(userId)+"#");
		}
		
		userProfileService.updateFriend(users.get(0));
		List <User> userNews=userProfileService.queryUserById(userId);
		if(userNews.get(0).getFriendList()==null) 
			userNews.get(0).setFriendList(String.valueOf(owenId)+"#");
		else
		{
			if(userNews.get(0).getFriendList().contains(String.valueOf(owenId)))
				userNews.get(0).setFriendList(userNews.get(0).getFriendList());
			else
				userNews.get(0).setFriendList(userNews.get(0).getFriendList()+String.valueOf(owenId)+"#");
		}
		
		userProfileService.updateFriend(userNews.get(0));
		
		
		List <String> friends=new ArrayList<String>();
		
		users=userProfileService.queryUserById(owenId);

		if(users.size()>0)
		{
		    if(users.get(0).getFriendList()!=null)  
		    {
		    	for(String account:users.get(0).getFriendList().split("#"))
		    	{
		    		if(account!=null)
		    			friends.add(account);
		    	}
		    	
		    	flag=true;
			}

		}
		
		List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
		List <String> snsIds=new ArrayList<String>();
		
		if(flag)			 
		{       
			for(String userAccount:friends)
			{
				User user=userProfileService.queryUserById(Long.valueOf(userAccount)).get(0);
				
				SimpleUser simpleUser=new SimpleUser();
				
				String snsId=userLogInService.getAccount(Long.valueOf(userAccount)).getSnsId();
				
				snsIds.add(snsId);
				
				simpleUser.setGold(user.getGold());
				simpleUser.setExp(user.getExp());
				simpleUser.setGender(user.getGender());
				simpleUser.setImage(user.getImage());
				simpleUser.setLevel(user.getLevel());
				simpleUser.setNickName(user.getNickName());
				simpleUser.setUserId(user.getUserId());
				
				simpleList.add(simpleUser);
			}
			
			HashMap< String, Object> map = new HashMap<String, Object>();
			    	
			map.put("friends", simpleList);
			map.put("snsId", snsIds);
			       
			responseJSON +=JSONObject.fromObject(map).toString();
			    	
		    response.setStatus(200);	
		}
		
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("get user:  error");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.update_UserProfile);
			
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(501);
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
    
    public void cancelRequest()  {
    	
    	
    	String responseJSON="";

    	long userId=Long.valueOf(CustomBase64.decode(this.account));
	
    	log.info("owenId -->"+userId);
    	
    	long owenId=Long.valueOf(CustomBase64.decode(this.userId));
	
    	boolean flag=false;
    	
		flag=requestConfigService.deleteRequest(owenId, userId);
		
		//update userFriends
		
		HashMap< String, Object> map = new HashMap<String, Object>();
		
		if(flag)			 
		{       
			
			User user=userProfileService.queryUserById(Long.valueOf(userId)).get(0);
				
			SimpleUser simpleUser=new SimpleUser();
			
			String snsId=userLogInService.getAccount(Long.valueOf(userId)).getSnsId();
				
			simpleUser.setGold(user.getGold());
			simpleUser.setExp(user.getExp());
			simpleUser.setGender(user.getGender());
			simpleUser.setImage(user.getImage());
			simpleUser.setLevel(user.getLevel());
			simpleUser.setNickName(user.getNickName());
			simpleUser.setUserId(user.getUserId());
			
			map.put("simpleUser", simpleUser);
			map.put("snsId", snsId);
							       
			responseJSON +=JSONObject.fromObject(simpleUser).toString();
			    	
		    response.setStatus(200);	
		}
		
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("get user:  error");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.update_UserProfile);
			
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(501);
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
    
    public void sendRequstMail()  {
    	
    	
    	String responseJSON="";

    	long userId=Long.valueOf(CustomBase64.decode(this.account));
		
		List <FriendRequest> requests=requestConfigService.queryUserId(userId);
		
		List <String> owenIds=new ArrayList<String>();
		
		if(requests.size()>0)
		{
			for(FriendRequest request :requests)
			{
				owenIds.add(String.valueOf(request.getOwenId()));
			}
			
		}
		
		List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
		List <String> snsIds=new ArrayList<String>();
	
		
		
		if(owenIds.size()>0)			 
		{       
			for(String userAccount:owenIds)
			{
				User user=userProfileService.queryUserById(Long.valueOf(userAccount)).get(0);
				
				SimpleUser simpleUser=new SimpleUser();
				
				String snsId=userLogInService.getAccount(Long.valueOf(userAccount)).getSnsId();
				snsIds.add(snsId);
				
				simpleUser.setGold(user.getGold());
				simpleUser.setExp(user.getExp());
				simpleUser.setGender(user.getGender());
				simpleUser.setImage(user.getImage());
				simpleUser.setLevel(user.getLevel());
				simpleUser.setNickName(user.getNickName());
				simpleUser.setUserId(user.getUserId());
				
				simpleList.add(simpleUser);
			}
			
			HashMap< String, Object> map = new HashMap<String, Object>();
			    	
			map.put("requestFriends", simpleList);
			map.put("snsId", snsIds);
			       
			responseJSON +=JSONObject.fromObject(map).toString();
			    	
		    response.setStatus(200);	
		}
		
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("get user:  error");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.update_UserProfile);
			
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(501);
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
    
  public void removeFriends()  {
    	
    	
    	String responseJSON="";

    	long owenId=Long.valueOf(CustomBase64.decode(this.account));
    	
		long userId=Long.valueOf(CustomBase64.decode(this.userId));
	
    	log.info("owenId -->"+owenId);
	
    	boolean flag=false;

    	List <User> users=userProfileService.queryUserById(owenId);
    	
    	String friendsOwen="";
    	
   	
    	if(users.get(0).getFriendList().contains(String.valueOf(CustomBase64.decode(this.userId))))
    	{	
    		if(users.size()>0)
    		{
    		    if(users.get(0).getFriendList()!=null)  
    		    {
    		    	if(users.get(0).getFriendList().split("#").length>1)
    		    	{
    		    		for(String account:users.get(0).getFriendList().split("#"))
    		    		{
        		    		if(!"".equals(account)&&!account.equals(String.valueOf(CustomBase64.decode(this.userId)))&&null!=account) {
    							 friendsOwen+=(account+"#");
    						}
        		    	}	
    		    	}
    		    	else
    		    	{
    		    	    String account=users.get(0).getFriendList().split("#")[0];
    		    	    if(account.equals(String.valueOf(this.userId)))
    		    	    	friendsOwen=null;
    		    	    else
    		    	    	friendsOwen=users.get(0).getFriendList();
    		    	}
    		    	    	
    			}

    		}
    	}
    	
    	users.get(0).setFriendList(friendsOwen);
			
    	userProfileService.updateFriend(users.get(0));
		
    	
    	List <User> userNews=userProfileService.queryUserById(userId);
    	
    	String friendsUser="";
    	
       	
    	if(userNews.get(0).getFriendList().contains(String.valueOf(CustomBase64.decode(this.account))))
    	{	
    		if(userNews.size()>0)
    		{
    		    if(userNews.get(0).getFriendList()!=null)  
    		    {
    		    	if(userNews.get(0).getFriendList().split("#").length>1)
    		    	{
    		    		for(String account:userNews.get(0).getFriendList().split("#"))
    		    		{
        		    		if(!"".equals(account)&&!account.equals(String.valueOf(CustomBase64.decode(this.account)))&&
        		    				null!=account) {
        		    			friendsUser+=(account+"#");
    						}
        		    	}	
    		    	}
    		    	else
    		    	{
    		    		String account=userNews.get(0).getFriendList().split("#")[0];
    		    	    if(account.equals(String.valueOf(CustomBase64.decode(this.account))))
    		    	    	friendsUser=null;
    		    	    else
    		    	    	friendsUser=userNews.get(0).getFriendList();	
    		    	}
    			}

    		}
    	}
    	
    	userNews.get(0).setFriendList(friendsUser);
		
		userProfileService.updateFriend(userNews.get(0));
		
	
		users=userProfileService.queryUserById(owenId);
		
		
		List <String > friendList=new ArrayList<String>();

		if(users.size()>0)
		{
		    if(users.get(0).getFriendList()!=null)  
		    {
		    	for(String account:users.get(0).getFriendList().split("#"))
		    	{
		    		if(account!=null)
		    			friendList.add(account);
		    	}
		    	
		    	flag=true;
			}

		}
		
		List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
		List <String> snsIds=new ArrayList<String>();
		
		if(flag)			 
		{    
		  if(friendList.size()>0)	
		  {
			for(String userAccount:friendList)
			{
				User user=userProfileService.queryUserById(Long.valueOf(userAccount)).get(0);
				
				SimpleUser simpleUser=new SimpleUser();
				
				String snsId=userLogInService.getAccount(Long.valueOf(userAccount)).getSnsId();
				snsIds.add(snsId);
				
				simpleUser.setGold(user.getGold());
				simpleUser.setExp(user.getExp());
				simpleUser.setGender(user.getGender());
				simpleUser.setImage(user.getImage());
				simpleUser.setLevel(user.getLevel());
				simpleUser.setNickName(user.getNickName());
				simpleUser.setUserId(user.getUserId());
				
				simpleList.add(simpleUser);
			}
		  }
			
			HashMap< String, Object> map = new HashMap<String, Object>();
			    	
			map.put("friends", simpleList);
			map.put("snsId", snsIds);
			       
			responseJSON +=JSONObject.fromObject(map).toString();
			    	
		    response.setStatus(200);	
		}
		
		else
		{
			HashMap< String, Object> map = new HashMap<String, Object>();
	    	
			map.put("friends", simpleList);
			       
			responseJSON +=JSONObject.fromObject(map).toString();
			    	
		    response.setStatus(501);	
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
  
  public void querryRequest()  {
  	
	  	String responseJSON="";
	  	
	  	boolean flag=false;
	
	  	long owenId=Long.valueOf(CustomBase64.decode(this.account));
	  	
	  	List <User> users=userProfileService.queryUserById(owenId);
			
	  	List <FriendRequest> requests=requestConfigService.queryUserId(owenId);
		
		List <String> owenIds=new ArrayList<String>();
		
		if(users.size()>0)
		{
			List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
			List <String> snsIds=new ArrayList<String>();
			
			if(requests.size()>0)
			
			{
			
				for(FriendRequest request :requests)
				{
					owenIds.add(String.valueOf(request.getOwenId()));
				}
			}
			
			
			
			if(owenIds.size()>0)
			{
	
				for(String userId:owenIds)
				{
					User user=userProfileService.queryUserById(Long.valueOf(userId)).get(0);
					SimpleUser simpleUser=new SimpleUser();
					String snsId=userLogInService.getAccount(Long.valueOf(userId)).getSnsId();
					snsIds.add(snsId);
					
					simpleUser.setGold(user.getGold());
					simpleUser.setExp(user.getExp());
					simpleUser.setGender(user.getGender());
					simpleUser.setImage(user.getImage());
					simpleUser.setLevel(user.getLevel());
					simpleUser.setNickName(user.getNickName());
					simpleUser.setUserId(user.getUserId());
					
					simpleList.add(simpleUser);
			   }
			}
				
			HashMap< String, Object> map = new HashMap<String, Object>();
				    	
			map.put("requestFriends", simpleList);
			map.put("snsId", snsIds);
				       
			responseJSON +=JSONObject.fromObject(map).toString();
				    	
			response.setStatus(200);
		
		}
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("get request:  error");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.update_UserProfile);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(501);
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
    
   
    
    

