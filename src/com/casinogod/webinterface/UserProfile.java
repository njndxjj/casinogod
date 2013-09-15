package com.casinogod.webinterface;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserAccount;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class UserProfile extends ActionSupport implements ServletResponseAware,ServletRequestAware{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  
    	
	private UserProfileService userProfileService;
	
	private UserLogIn userLogInService;
	
	private HttpServletResponse response;
	
	private HttpServletRequest request;
	
	private static Logger log = Logger.getLogger(UserProfile.class); 
    
  //  private HttpServletRequest request=ServletActionContext.getRequest();
	
	
	
    public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}
    
    public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub		
		this.request=request;		
	}
    
    



	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public void updateUserProfile()  {
        
    User user=null ;  
    
    boolean flag=false;
    String snsId="";
    

    String postdata="";
    String decode="";

    
    try {
		postdata=Utility.postdata(this.request);
		decode=CustomBase64.decode(postdata);
    	System.out.println("postdata-->"+postdata);
    	System.out.println("decode--->"+CustomBase64.decode(postdata));
	
    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    String account=Utility.splitString(decode, "account");
    String nickName=Utility.splitString(decode, "nickName");
    String gender=Utility.splitString(decode, "gender");
    String emailAddress=Utility.splitString(decode, "emailAddress");
    String image=Utility.splitString(decode, "image");
    String telephone=Utility.splitString(decode, "telephone");
    
    if(Integer.valueOf(account)>0)
    {
        user=userProfileService.queryUserById(Integer.valueOf(account)).get(0);  
        
    	flag = userProfileService.updateProfile(Integer.valueOf(account), nickName, 
    			gender, emailAddress,telephone, image);
    	snsId=userLogInService.getAccount(user.getUserId()).getSnsId();
    
    }
    
    String responseJSON= "";    
   
    if(flag)  
    {  
    	log.info("user update success: "+user.getUserId());  
    	Map <String ,Object > map=new HashMap<String, Object>();
    	map.put("userInfo", user);
    	map.put("snsId", snsId);
		responseJSON = JSONObject.fromObject(map).toString();
		response.setStatus(200);	
    }
    
	else
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("update the User:  error");
		errorResponse.setErrorAction("");
		errorResponse.setErrorCode(ErrorCode.update_UserProfile);
			
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
    
    public void userProfile()  {
        
        User user=null ;  
        
        String postdata="";
        String decode="";

        
        try {
    		postdata=Utility.postdata(this.request);
    		decode=CustomBase64.decode(postdata);
        	System.out.println("postdata-->"+postdata);
        	System.out.println("decode--->"+CustomBase64.decode(postdata));
    	
        } catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
        
        String account=Utility.splitString(decode, "account");
        
        if(Integer.valueOf(account)>0)
       
            user=userProfileService.queryUserById(Integer.valueOf(account)).get(0);  
           
        String responseJSON= "";    
       
        if(user!=null)  
        {  
        	log.info("user success: "+user.getUserId());   		
    		responseJSON = JSONObject.fromObject(user).toString();
    		response.setStatus(200);	
        }
        
    	else
    	{
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setErrorMessage("get the User:  error");
    		errorResponse.setErrorAction("");
    		errorResponse.setErrorCode(ErrorCode.user_UserProfile);
    			
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
    
    
    public void gameUserInfo()  {
        
        User user=null ;  
        
        String postdata="";
        String decode="";

        
        try {
    		postdata=Utility.postdata(this.request);
    		decode=CustomBase64.decode(postdata);
        	System.out.println("postdata-->"+postdata);
        	System.out.println("decode--->"+CustomBase64.decode(postdata));
    	
        } catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
        
        String userId=Utility.splitString(decode, "userId");
        
        if(Long.valueOf(userId)>0)
       
            user=userProfileService.queryUserById(Long.valueOf(userId)).get(0);  
        
        UserAccount userAccount=userLogInService.getAccount(Long.valueOf(userId));
           
        String responseJSON= "";    
       
        if(user!=null)  
        {  
        	log.info("user success: "+user.getUserId());   
        	SimpleUser simpleUser=new SimpleUser();
        	
        	simpleUser.setGold(user.getGold());
			simpleUser.setExp(user.getExp());
			simpleUser.setGender(user.getGender());
			simpleUser.setImage(user.getImage());
			simpleUser.setLevel(user.getLevel());
			simpleUser.setNickName(user.getNickName());
			simpleUser.setUserId(user.getUserId());
			
			Map<String,Object> map=new HashMap<String, Object>();
			
			map.put("simpleUser", simpleUser);
			map.put("snsID", userAccount.getSnsId());
    		
			responseJSON = JSONObject.fromObject(map).toString();
    		response.setStatus(200);	
        }
        
    	else
    	{
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setErrorMessage("get the User:  error");
    		errorResponse.setErrorAction("");
    		errorResponse.setErrorCode(ErrorCode.user_UserProfile);
    			
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
    
    
    public void updateGold()  {
        
        User user=null ;  
        
        String postdata="";
        String decode="";

        
        try {
    		postdata=Utility.postdata(this.request);
    		decode=CustomBase64.decode(postdata);
        	System.out.println("postdata-->"+postdata);
        	System.out.println("decode--->"+CustomBase64.decode(postdata));
    	
        } catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
        
        String account=Utility.splitString(decode, "account");
        String gold=Utility.splitString(decode, "gold");
        
        boolean flag=false;
        
        if(Integer.valueOf(account)>0&&Integer.valueOf(gold)>0)
        {
            user=userProfileService.queryUserById(Integer.valueOf(account)).get(0); 
            user.setGold(user.getGold()+Integer.valueOf(gold));
            user.setDiamond(user.getDiamond());
        	flag = userProfileService.updateGold(user);
        
        }
        
        String responseJSON= "";    
       
        if(flag)  
        {  
        	log.info("user update success: "+user.getUserId());   		
    		responseJSON = JSONObject.fromObject(user).toString();
    		response.setStatus(200);	
        }
        
    	else
    	{
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setErrorMessage("update the User diamond:  error");
    		errorResponse.setErrorAction("");
    		errorResponse.setErrorCode(ErrorCode.update_UserProfile);
    			
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
    
    
    
   
	

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}


	
	
	
	
	

	
	
	
	
	
	
	
	
	

}
