package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class ChatLimit {
	
	//define constructors for the User class
	public ChatLimit() {}
	
	private int userId;
	
	
	private String startTime;
	
	
	
	private String endTime;



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getStartTime() {
		return startTime;
	}



	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	


	

	
	
}
