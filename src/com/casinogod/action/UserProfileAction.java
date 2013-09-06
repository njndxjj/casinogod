package com.casinogod.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;



import com.casinogod.pojo.FriendRequest;
import com.casinogod.pojo.User;
import com.casinogod.service.RequestConfigService;
import com.casinogod.service.UserProfile;
import com.casinogod.utility.MD5Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserProfileAction  extends ActionSupport implements ServletRequestAware, ServletResponseAware{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nickName;
	
	private long account;
	
	private String password;
	
	private String gender;
	
	private String emailAddress;
	
	private String telephone;
	
	private int gold;
	
	private int diamond;
	
	private int exp;
	
	private int level;
    
	private UserProfile userProfileService;
	
	private RequestConfigService requestConfigService;

	private HttpServletRequest request;  
    private HttpServletResponse response;
    
    private static Logger log = Logger.getLogger(UserProfileAction.class); 

	public void setUserProfileService(UserProfile userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	

	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPassword() {
		return password;
	}

	public UserProfile getUserProfileService() {
		return userProfileService;
	}

	public UserProfileAction()
	{
		
	}
	
	
	
	public String queryall() throws IOException   
    {  
        
		List<User> list = userProfileService.queryallUser(); 
        
        request.setAttribute("users", list);  
        
       // JSONObject jsonObject=new J
        JSONArray json=JSONArray.fromObject(list); 
        
       // response.getWriter().write(json.toString());
        
        request.setAttribute("json", json.toString());
        
        return SUCCESS;  
    }  
	
	public String queryallunFriend() throws IOException   
    {  
        
		List<User> list = userProfileService.queryallUser(); 
         
		List <String> userList=new ArrayList<String>();
		
		for(int i=0;i<list.size()-1;i++)
		{
			userList.add(i, String.valueOf(list.get(i+1).getUserId()));
		}
		
		log.info("userId"+ActionContext.getContext().getSession().get("account"));
	
		List <User> users=userProfileService.queryUserById(Long.valueOf((String)ActionContext.getContext().getSession().get("account")));
		
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
		
		friendRequest.setOwenId(Long.valueOf((String)ActionContext.getContext().getSession().get("account")));
		
		friendRequest.setStatue(0);
		
		List <String> requestList=requestConfigService.queryStatue(friendRequest);
		
		if(requestList.size()>0)
		{
			userList.removeAll(requestList);
		}
		
		userList.remove(String.valueOf(users.get(0).getUserId()));
  
        request.setAttribute("friendList", userList);
        
        return SUCCESS;  
    }  
	
	public String acceptRequest() throws IOException   
    {  
		
		long owenId=Long.valueOf((String)ActionContext.getContext().getSession().get("account"));
		
		log.info("owenId -->"+owenId);
	
		List <User> users=userProfileService.queryUserById(owenId);

		try{
			
			//update request
			FriendRequest friendRequest=new FriendRequest();
			long userId=Long.valueOf((String)request.getParameter("userId"));
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
			
		}catch(Exception e)
		{
			System.out.println("no parameter :"+e.getMessage());
			return ERROR;
		}

        return SUCCESS;  
    }
	
	public String showFriends() throws IOException   
    {  
		boolean flag=false;
		long owenId=Long.valueOf((String)ActionContext.getContext().getSession().get("account"));
		
		System.out.println("owenId -->"+owenId);
	
		List <User> users=userProfileService.queryUserById(owenId);

		if(users.size()>0)
		{
			List <String> friends=new ArrayList<String>();
			
		    if(users.get(0).getFriendList()==null)  return ERROR;
			
			for(String account:users.get(0).getFriendList().split("#"))
			{
				if(account!=null)
					friends.add(account);
			}
			
			request.setAttribute("friends", friends);
			flag=true;
		}

        return flag?SUCCESS:ERROR;  
    }
	
	
	
	public String queryUserByName()
	{
		boolean flag = false;	
		
		List <User> list=userProfileService.queryUserByName(this.nickName);
		
		if(list.size()>0) 
		{
			
			flag=true;
			
			User user=list.get(0);
			
			JSONObject json=new JSONObject();
			
			String userInfo=json.toString();
			
			System.out.println("user json:"+userInfo);
			
			request.setAttribute("userInfo", userInfo);
		}
	    
		return flag==true?SUCCESS:ERROR;
	}
	
	public String queryUserById()
	{
		boolean flag = false;	
		
		List <User> list=userProfileService.queryUserById(this.account);
		
		if(list.size()>0) 
		{
			
			flag=true;
			
			User user=list.get(0);
			
			JSONObject json=new JSONObject(user);
			
			String userInfo=json.toString();
			
//			System.out.println("user json:"+userInfo);
			
			request.setAttribute("userInfo", userInfo);
		}
	    
		return flag==true?SUCCESS:ERROR;
	}
	
	public String register()  
    {  
        boolean flag = false; 
       
        System.out.println("username"+this.nickName);
        
        String password=MD5Util.string2MD5(this.password);
        
        flag = userProfileService.addUser(this.nickName, password, this.gender, this.emailAddress, this.telephone,"other"); 
  
        return flag==true?SUCCESS:ERROR;  
    }  
	
	public String updateUserGold()   
    {  
		List <User> list=userProfileService.queryUserById(this.account);
        boolean flag = false;  
       
        User user=list.get(0);
        
        user.setGold(this.gold);
        user.setDiamond(this.diamond);
        
        flag=userProfileService.updateGold(user);
  
        return flag==true?SUCCESS:ERROR;  
    } 
	
	public String updateUserExp()   
    {  
		List <User> list=userProfileService.queryUserById(this.account);
        boolean flag = false;  
       
        User user=list.get(0);
        
        user.setExp(this.exp);
        
        user.setLevel(this.level);
        
        flag=userProfileService.updateExp(user);
  
        return flag==true?SUCCESS:ERROR;  
    } 
	
	public String deleteUserInfo()   
    {  
        
        boolean flag = false;  
        
        flag = userProfileService.delete(this.account);  
  
        return flag==true?SUCCESS:ERROR;  
    }

	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public void setRequestConfigService(RequestConfigService requestConfigService) {
		this.requestConfigService = requestConfigService;
	}  
	
	
	
	
	
	
    
   
   
    
}
