package com.casinogod.webinterface;



import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossInstanceUserInfo;
import com.casinogod.service.BossBattleService;
import com.casinogod.service.BossInstanceUserService;
import com.casinogod.service.BossUserService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class ShowBoss extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	
	private String account;
	
	private String status;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
		
	private BossInstanceUserService bossInstanceService;
	
	private BossUserService bossUserService;
	
	private BossBattleService bossBattleService;
	
	

	public void setAccount(String account) {
		this.account = account;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.resquest=request;
	}
	
	public void setBossInstanceService(BossInstanceUserService bossInstanceService) {
		this.bossInstanceService = bossInstanceService;
	}


	public void setBossUserService(BossUserService bossUserService) {
		this.bossUserService = bossUserService;
	}

	public void setBossBattleService(BossBattleService bossBattleService) {
		this.bossBattleService = bossBattleService;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}

	private static Logger log = Logger.getLogger(ShowBoss.class); 

	public void showBoss()
	
	{
		
		String responseJSON  = "";
		if(this.account!=null)
		{
			//share raidboss 
		
		//response
		
		List <BossInstanceUserInfo> allInstanceUser=bossInstanceService.queryByUserId(Long.valueOf(CustomBase64.decode(this.account)));
		
		
		
		if(allInstanceUser.size()>0)
		{
			List <BossBattleInfo> list=new ArrayList<BossBattleInfo>();
			
			for(BossInstanceUserInfo bossUserInfo:allInstanceUser)
			{
			
				//	ShowBossUser showBossUser=new ShowBossUser();
				
				BossBattleInfo bossBattleInfo=bossBattleService.queryByInstance(bossUserInfo.getBossInstance()).get(0);
				
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
				
				if(Integer.valueOf(CustomBase64.decode(this.status))==0)
				
				{
//					showBossUser.setBossInstance(bossUserInfo.getBossInstance());
//					
//					showBossUser.setResult(bossUserInfo.getResult());
//					
//					showBossUser.setStatus(bossBattleInfo.getStatus());
//						
//					showBossUser.setUserId(bossUserInfo.getUserId());
					
					list.add(bossBattleInfo);
				}
				else if(Integer.valueOf(CustomBase64.decode(this.status))==bossBattleInfo.getStatus())
				{
//					showBossUser.setBossInstance(bossUserInfo.getBossInstance());
//					
//					showBossUser.setResult(bossUserInfo.getResult());
//					
//					showBossUser.setStatus(bossBattleInfo.getStatus());
//						
//					showBossUser.setUserId(bossUserInfo.getUserId());
					
					list.add(bossBattleInfo);
				}
				
			}
			
			Collections.reverse(list);
			
			Map <String,Object> map=new HashMap<String, Object>();
			
			map.put("bossInstance", list);
						
			responseJSON += JSONObject.fromObject(map).toString();
			  
			response.setStatus(200);
		}
		else
		{
			 ErrorResponse errorResponse = new ErrorResponse();
			 errorResponse.setErrorMessage("share battle fail!");
			 errorResponse.setErrorAction("");
			 errorResponse.setErrorCode(ErrorCode.Battle_shareRaidBoss);
				
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
