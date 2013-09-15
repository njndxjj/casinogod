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

import com.casinogod.pojo.RoomInfo;
import com.casinogod.service.RoomProfileService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigRoomInfo extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	
	private String gameType;
	
	private String roomLevel;
	
	private String levelScore;
	
	private String minHand;

	private String maxHand;
	
	private String otherNotes;
	
	private String beginTime;
	
	private String endTime;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private RoomProfileService roomProfileService;
	

	

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public void setRoomLevel(String roomLevel) {
		this.roomLevel = roomLevel;
	}

	public void setLevelScore(String levelScore) {
		this.levelScore = levelScore;
	}

	public void setMinHand(String minHand) {
		this.minHand = minHand;
	}

	public void setMaxHand(String maxHand) {
		this.maxHand = maxHand;
	}

	public void setOtherNotes(String otherNotes) {
		this.otherNotes = otherNotes;
	}
	
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

	public void setRoomProfileService(RoomProfileService roomProfileService) {
		this.roomProfileService = roomProfileService;
	}
	

	public void queryAll()
	{
		
		List <RoomInfo> list=roomProfileService.queryall();
		
		String responseJSON= "";    
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       HashMap< String, Object> map = new HashMap<String, Object>();
	    	
	       map.put("roomInfor", list);
	       
	       responseJSON +=JSONObject.fromObject(map).toString();
	      
		   response.setStatus(200);	
	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find the Room Infromation");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.room_Information);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			
			response.setStatus(401);
		}
			
		try {
			
			 try {
					responseJSON=CustomBase64.encode(responseJSON);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	               
	 }
		


	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		
		this.resquest=resquest;
		
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		this.response=response;
	}

}
