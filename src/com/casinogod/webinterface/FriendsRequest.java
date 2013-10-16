package com.casinogod.webinterface;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.FriendRequest;
import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.User;
import com.casinogod.service.RequestConfigService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class FriendsRequest extends ActionSupport implements ServletResponseAware,ServletRequestAware{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

        	
	private UserProfileService userProfileService;
	
	private UserLogIn userLogInService;
	
	private RequestConfigService requestConfigService;
	
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private static Logger log = Logger.getLogger(FriendsRequest.class); 
 

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setRequestConfigService(RequestConfigService requestConfigService) {
		this.requestConfigService = requestConfigService;
	}
	
	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
	
	

	public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void queryallunFriend()  {
		
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
    	
    	String responseJSON="";
        
    	List<User> list = userProfileService.queryallUser(); 
        
		List <String> userList=new ArrayList<String>();
		
		for(int i=0;i<list.size()-1;i++)
		{
			userList.add(i, String.valueOf(list.get(i+1).getUserId()));
		}
		
		List <User> users=userProfileService.queryUserById(Long.valueOf(account));
		
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
		
		friendRequest.setOwenId(Long.valueOf(account));
		
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
		
		if(userList.size()>0&&userList.size()<5)
			 
		{       
			for(String userId:userList)
			{
				User user=userProfileService.queryUserById(Long.valueOf(userId)).get(0);
				
				String snsId=userLogInService.getAccount(Long.valueOf(userId)).getSnsId();
				
				if(snsId==null) snsId="";
				
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
		
		
		if(userList.size()>5)
			 
		{
			//int k=0;
			
			Collection< String> randUser=new HashSet<String>();
			
			Random random = new Random();
			
			for(int i=0;i<5;i++)
			{
				int j=random.nextInt(userList.size());
				
				while(randUser.contains(userList.get(j)))
				{
					j=random.nextInt(userList.size());
				}
				
				randUser.add(userList.get(j));
			}
			
			for(String userId:randUser)
			{
				User user=userProfileService.queryUserById(Long.valueOf(userId)).get(0);
				
				String snsId=userLogInService.getAccount(Long.valueOf(userId)).getSnsId();
				
				if(snsId==null) snsId="";
				
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
	    String userId=Utility.splitString(decode, "userId");
    	
    	boolean flag=false;

    	long owenId=Long.valueOf(account);
		
		String dateTime=Utility.getNowString();
		
		flag=requestConfigService.addFriendRequest(owenId, Long.valueOf(userId), dateTime,0);
		
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
			    	for(String account1:users.get(0).getFriendList().split("#"))
			    	{
			    		if(account1!=null)
			    			friends.add(account1);
			    	}
				}

			}
			
			List <SimpleUser> simpleList=new ArrayList<SimpleUser>();
			
			if(friends.size()>0)
			{

				for(String userid:friends)
				{
					User user=userProfileService.queryUserById(Long.valueOf(userid)).get(0);
					SimpleUser simpleUser=new SimpleUser();
					
					String snsId=userLogInService.getAccount(Long.valueOf(userid)).getSnsId();
					
					if(snsId==null) snsId="";
					
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
        
    	boolean flag=false;
		
    	long owenId=Long.valueOf(account);
		
		log.info("owenId -->"+owenId);
	
		List <User> users=userProfileService.queryUserById(owenId);
		
		List <String> friends=new ArrayList<String>();

		if(users.size()>0)
		{
		    if(users.get(0).getFriendList()!=null)  
		    {
		    	for(String account1:users.get(0).getFriendList().split("#"))
		    	{
		    		if(account1!=null)
		    			friends.add(account1);
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
			if(snsId==null) snsId="";
			
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
	    String userid=Utility.splitString(decode, "userId");

    	long owenId=Long.valueOf(account);
	
    	log.info("owenId -->"+owenId);
	
    	boolean flag=false;

    	List <User> users=userProfileService.queryUserById(owenId);
    	
    	//update request
    
		FriendRequest friendRequest=new FriendRequest();
		long userId=Long.valueOf(userid);
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
		    	for(String account1:users.get(0).getFriendList().split("#"))
		    	{
		    		if(account1!=null)
		    			friends.add(account1);
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
				if(snsId==null) snsId="";
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
	    String userid=Utility.splitString(decode, "userId");

    	long userId=Long.valueOf(account);
	
    	log.info("owenId -->"+userId);
    	
    	long owenId=Long.valueOf(userid);
	
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

    	long userId=Long.valueOf(account);
		
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
				if(snsId==null) snsId="";
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
	    String userid=Utility.splitString(decode, "userId");

    	long owenId=Long.valueOf(account);
    	
		long userId=Long.valueOf(userid);
	
    	log.info("owenId -->"+owenId);
	
    	boolean flag=false;

    	List <User> users=userProfileService.queryUserById(owenId);
    	
    	String friendsOwen="";
    	
   	
    	if(users.get(0).getFriendList().contains(userid))
    	{	
    		if(users.size()>0)
    		{
    		    if(users.get(0).getFriendList()!=null)  
    		    {
    		    	if(users.get(0).getFriendList().split("#").length>1)
    		    	{
    		    		for(String account1:users.get(0).getFriendList().split("#"))
    		    		{
        		    		if(!"".equals(account1)&&!account1.equals(userid)&&null!=account1) {
    							 friendsOwen+=(account1+"#");
    						}
        		    	}	
    		    	}
    		    	else
    		    	{
    		    	    String account1=users.get(0).getFriendList().split("#")[0];
    		    	    if(account1.equals(userid))
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
    	
       	
    	if(userNews.get(0).getFriendList().contains(String.valueOf(account)))
    	{	
    		if(userNews.size()>0)
    		{
    		    if(userNews.get(0).getFriendList()!=null)  
    		    {
    		    	if(userNews.get(0).getFriendList().split("#").length>1)
    		    	{
    		    		for(String account1:userNews.get(0).getFriendList().split("#"))
    		    		{
        		    		if(!"".equals(account1)&&!account1.equals(String.valueOf(account))&&
        		    				null!=account1) {
        		    			friendsUser+=(account1+"#");
    						}
        		    	}	
    		    	}
    		    	else
    		    	{
    		    		String account1=userNews.get(0).getFriendList().split("#")[0];
    		    	    if(account1.equals(String.valueOf(account)))
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
		    	for(String account1:users.get(0).getFriendList().split("#"))
		    	{
		    		if(account1!=null&&"".equals(account1))
		    			friendList.add(account1);
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
				if(snsId==null) snsId="";
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
	    	

			map.put("friends", "");
			map.put("snsId", "");      

			responseJSON +=JSONObject.fromObject(map).toString();
			    	
		    response.setStatus(200);	
		}
		
		try {
		System.out.println("response mess-->"+responseJSON);
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
	  	
	  	boolean flag=false;
	
	  	long owenId=Long.valueOf(account);
	  	
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
					if(snsId==null) snsId="";
					
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
  
  public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.resquest=request;
  }


    
    
    
}
    
   
    
    

