package com.casinogod.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.dao.ConfigurationDAOImpl;
import com.casinogod.pojo.Configuration;
import com.casinogod.pojo.LogInReward;
import com.casinogod.pojo.LogInRewardConfig;
import com.casinogod.pojo.PageModel;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserAccount;
import com.casinogod.service.LogInRewardConfigService;
import com.casinogod.service.LogInRewardService;
import com.casinogod.service.PageService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfile;
import com.casinogod.utility.DataStore;
import com.casinogod.utility.MD5Util;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Login  extends ActionSupport implements ServletResponseAware,ServletRequestAware{
    
	private int account;
	
	private String password;
    
	private UserLogIn userLogInService;
	
	private LogInRewardService logInRewardService;
	
	private LogInRewardConfigService logInRewardConfigService;
		
	private UserProfile userProfileService;
	
	private PageService pageService;
	
	private ConfigurationDAOImpl configurationDAO;
	
	private HttpServletResponse response;
	
	private HttpServletRequest request;
	
	private static Logger log = Logger.getLogger(Login.class);
	
  // private HttpServletRequest request=ServletActionContext.getRequest();
   
    public String logInSuccess()  {
        
    User user=null ;  
    
    boolean flag=false;
    
    String password=MD5Util.string2MD5(this.password);
   
    user = userLogInService.login(Integer.valueOf(this.account), password); 
    
    flag = userLogInService.isFreeze(Integer.valueOf(this.account));
    
    List <Configuration> listConfig=configurationDAO.querAll();
    
    if(listConfig.size()>0)
    {
    	Configuration configuration=listConfig.get(0);
    	DataStore.setting.put("winTimes", configuration.getWinTimes());
    	DataStore.setting.put("rankSize", configuration.getRankSize());
    	DataStore.setting.put("bossRewardNum", configuration.getBossRewardNum());
    	DataStore.setting.put("invitedReward", configuration.getInvitedReward());
    	DataStore.setting.put("invitedItem", configuration.getInvitedItem());
    }
    log.info("winTimes--->"+(Integer) DataStore.setting.get("winTimes"));
  
   
    if(user!=null)  
    {  
    	

    	
    	Map<String, Object> attibutes = ActionContext.getContext().getSession();//��¼�û���¼��Ϣ   
        
    	attibutes.put("username", user.getNickName());  
        attibutes.put("account", String.valueOf(user.getUserId()));//��¼session
        //log in success and no freeze
        if(flag)
        {
        	List <LogInReward> list=null;
        	list=logInRewardService.queryById(user.getUserId());
        	if(list.size()<=0)
        	{
        		logInRewardService.add(user.getUserId(), Utility.getNowString(), 1);	
        		
        		List <LogInRewardConfig> logConfig=logInRewardConfigService.queryByDay(1);
    			
    			if(logConfig.size()>0)
    			{
    				int reward=logConfig.get(0).getReward();
    				
    				int exp=user.getExp()+reward;
    				
    				user.setExp(exp);
    				user.setLevel(user.getLevel());
    				
    				userProfileService.updateExp(user);
    			}
        	}
        	else 
        	{
        	 
        		List <LogInReward> rewardList=logInRewardService.queryById(user.getUserId());
        		
        		String currentTime=Utility.getNowString();
        		
        		String currentDate=currentTime.split(" ")[0];
        		
        		String lastDate=rewardList.get(0).getLogInTime().split(" ")[0];
        		
        		if(currentDate.compareToIgnoreCase(lastDate)>0)
        		{
        			//get the yesterday day
        			Calendar calendar=Calendar.getInstance();
        			calendar.add(Calendar.DATE,-1);
        			
        			String yseterday=new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        			
        			log.info("ysetesday day: "+yseterday);
        			
        			if(yseterday.compareToIgnoreCase(lastDate)==0)
        			{	
        				//update 
        				logInRewardService.update(user.getUserId(), currentTime, rewardList.get(0).getLastTimes()+1);
        			
        				rewardList=logInRewardService.queryById(user.getUserId());
        			
        				int day=rewardList.get(0).getLastTimes();
        			
        				List <LogInRewardConfig> logConfig=logInRewardConfigService.queryByDay(day);
        			
        				if(logConfig.size()>0)
        				{
        					int reward=logConfig.get(0).getReward();
        				
        					int exp=user.getExp()+reward;
        				
        					user.setExp(exp);
        					user.setLevel(user.getLevel());
        				
        					userProfileService.updateExp(user);
        				}
        			}
        			else
        			{
        				logInRewardService.update(user.getUserId(), currentTime, 0);
        			}
        			
        		}
        		else 
        		{
        	      //update 
               	  logInRewardService.update(user.getUserId(), currentTime, rewardList.get(0).getLastTimes());
        		}
        		
        	}
        }
        
 //     user = userLogInService.login(Integer.valueOf(this.account), this.password);
        
        if(user.getNickName().equals("admin"))
        {
        	return "admin";
        }
        return flag?SUCCESS:"freeze";
    }
    
    return user!=null?SUCCESS:ERROR;
                   
    }
    
    public String logAgain()  {
        
        boolean flag=false;
        
        Map<String, Object> attibutes = ActionContext.getContext().getSession();
        
        if(attibutes.get("username")!=null&&attibutes.get("account")!=null)
         
        {
        	flag=true;
        	
        	if(attibutes.get("username").equals("admin"))
            {
            	return "admin";
            }
        }
        
        
        return flag?SUCCESS:ERROR;
                       
        }
    
    public String logOut()  
    {   
    	Map<String, Object> attibutes = ActionContext.getContext().getSession();  
    	attibutes.remove("username");
    	attibutes.remove("account");
    	return SUCCESS; 
    }
    
    public String queryReward()
    {
    	List <LogInReward> list=logInRewardService.queryall();
    	request.setAttribute("logInRewards", list);
    	return SUCCESS; 
    }
    
    public String queryAllAccount()
    {
    	List <UserAccount> list=userLogInService.findAll();
    	int pageSize = 0;
    	try
    	{
    		pageSize=Integer.valueOf((String)request.getParameter("pageSize"));
    		request.setAttribute("pageSize", pageSize);
    	}catch(Exception e)
    	{
    		pageSize=10;
    		request.setAttribute("pageSize", 10);
    	}
    	    	
    	int offset=0;
   	  
   	    try {  
       		
   	    	offset = Integer.parseInt(request.getParameter("pager.offset"));  
       		 
   		  }catch (Exception e) {
   			  
   			  
   		  }
   		  
   	    PageModel pm = pageService.findAllByList(list, offset, pageSize); 
   	       
   	    request.setAttribute("pm", pm);
    	
    	
    
   	    return SUCCESS; 
    }
    
    public String updatePassword()
    {
    	userLogInService.updatePassword(this.account, this.password);
    	return SUCCESS;
    }


	
	public void setAccount(int account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserLogInService(UserLogIn userLogInService) {
	
		this.userLogInService = userLogInService;
	}



	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void setLogInRewardService(LogInRewardService logInRewardService) {
		this.logInRewardService = logInRewardService;
	}

	public void setLogInRewardConfigService(
			LogInRewardConfigService logInRewardConfigService) {
		this.logInRewardConfigService = logInRewardConfigService;
	}

	public void setUserProfileService(UserProfile userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public void setConfigurationDAO(ConfigurationDAOImpl configurationDAO) {
		this.configurationDAO = configurationDAO;
	}
	
	
	
	

	
	
	
	
	
	
    
	
	
   
   
    
}
