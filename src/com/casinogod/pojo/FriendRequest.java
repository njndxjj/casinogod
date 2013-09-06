package com.casinogod.pojo;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class FriendRequest {
	
	//define constructors for the User class
	public FriendRequest() {}
	
	private int id;
	
	private long owenId;
	
	private long userId;
	
	private int statue;
	
	private String requestDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getOwenId() {
		return owenId;
	}

	public void setOwenId(long owenId) {
		this.owenId = owenId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public int getStatue() {
		return statue;
	}

	public void setStatue(int statue) {
		this.statue = statue;
	}
	
	

	
}
