package com.casinogod.pojo;
/**
 * 
 * @author xie.junjie
 *  
 */

public class LogInReward {
	
	//define constructors for the User class
	public LogInReward() {}
	
	private int id;
	
	private long userId;
	
	private String logInTime;
	
	private int lastTimes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLogInTime() {
		return logInTime;
	}

	public void setLogInTime(String logInTime) {
		this.logInTime = logInTime;
	}

	public int getLastTimes() {
		return lastTimes;
	}

	public void setLastTimes(int lastTimes) {
		this.lastTimes = lastTimes;
	}
	

}
