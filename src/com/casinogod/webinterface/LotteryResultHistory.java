package com.casinogod.webinterface;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.LotteryHistory;
import com.casinogod.service.LotteryHistoryService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.opensymphony.xwork2.ActionSupport;

public class LotteryResultHistory extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private LotteryHistoryService lotteryHistoryService;
	
	public void setLotteryHistoryService(LotteryHistoryService lotteryHistoryService) {
		this.lotteryHistoryService = lotteryHistoryService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void queryAll()
	{
		
		List<LotteryHistory> list=lotteryHistoryService.queryall();
		
		String responseJSON= "";    
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       HashMap< String, Object> map = new HashMap<String, Object>();
	    	
	       map.put("LotteryInfor", list);
	       
	       responseJSON +=JSONObject.fromObject(map).toString();
	    	
			response.setStatus(200);	
	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find any Lottery Information");
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
