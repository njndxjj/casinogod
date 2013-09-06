package com.casinogod.pojo;
/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class UserDevice {
	
	//define constructors for the User class
	public UserDevice() {}
		
	private long userId;
	
	private String deviceToken;
	
	private String registerTime;
	
	private String notes;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	


	

	
	
}
