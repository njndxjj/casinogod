package com.casinogod.webinterface;



import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserAccount;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.opensymphony.xwork2.ActionSupport;

public class UserProfile extends ActionSupport implements ServletResponseAware {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String account;
    
    private String userId;
    
	private String nickName;
    
    private String gender;
    
    private String emailAddress;
    
    private String telephone;
    
    private String image;
    
    private String gold;
    	
	private UserProfileService userProfileService;
	
	private UserLogIn userLogInService;
	
	private HttpServletResponse response;
	
	private static Logger log = Logger.getLogger(UserProfile.class); 
    
  //  private HttpServletRequest request=ServletActionContext.getRequest();
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
    public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}



	public void updateUserProfile()  {
        
    User user=null ;  
    
    boolean flag=false;
    
    String snsId="";
    
    if(Integer.valueOf(CustomBase64.decode(this.account))>0)
    {
        user=userProfileService.queryUserById(Integer.valueOf(CustomBase64.decode(this.account))).get(0);  
        
    	flag = userProfileService.updateProfile(Integer.valueOf(CustomBase64.decode(this.account)), CustomBase64.decode(this.nickName), 
    			CustomBase64.decode(this.gender), CustomBase64.decode(this.emailAddress),CustomBase64.decode( this.telephone), CustomBase64.decode(this.image));
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
        
        if(Integer.valueOf(CustomBase64.decode(this.account))>0)
       
            user=userProfileService.queryUserById(Integer.valueOf(CustomBase64.decode(this.account))).get(0);  
           
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
        
        if(Long.valueOf(CustomBase64.decode(this.userId))>0)
       
            user=userProfileService.queryUserById(Long.valueOf(CustomBase64.decode(this.userId))).get(0);  
        
        UserAccount userAccount=userLogInService.getAccount(Long.valueOf(CustomBase64.decode(this.userId)));
           
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
        
        boolean flag=false;
        
        if(Integer.valueOf(CustomBase64.decode(this.account))>0&&Integer.valueOf(CustomBase64.decode(this.gold))>0)
        {
            user=userProfileService.queryUserById(Integer.valueOf(CustomBase64.decode(this.account))).get(0); 
            user.setGold(user.getGold()+Integer.valueOf(CustomBase64.decode(this.gold)));
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
    
    
    
    public void setAccount(String account) {
		this.account = account;
	}
	

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	
	
	
	
	

	
	
	
	
	
	
	
	
	

}
