package com.casinogod.webinterface;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.BoardCastInfo;
import com.casinogod.pojo.Item;
import com.casinogod.service.BoardCastService;
import com.casinogod.service.ItemConfigService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BoardCastPro extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private BoardCastService boardCastService;

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

	
	public void setBoardCastService(BoardCastService boardCastService) {
		this.boardCastService = boardCastService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void queryAll() throws UnsupportedEncodingException
	{

		List <BoardCastInfo> list=boardCastService.queryall();
		
		String responseJSON= "";    
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       HashMap< String, Object> map = new HashMap<String, Object>();
	    	
	       map.put("broadCastProfile", list);
	       
	       responseJSON +=JSONObject.fromObject(map).toString();
	       
	       responseJSON=CustomBase64.encode(responseJSON);
	    	
		   response.setStatus(200);	
	    
	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find any BoardCast Infromation");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.boardCast_Information);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			
			responseJSON=CustomBase64.encode(responseJSON);
			 
			
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
	
}
