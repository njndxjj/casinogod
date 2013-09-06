package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class InviteTable {
	
	//define constructors for the User class
	public InviteTable() {}
	
	private int userId;
	
	private int invitedId;
		
	private String inviteTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getInvitedId() {
		return invitedId;
	}

	public void setInvitedId(int invitedId) {
		this.invitedId = invitedId;
	}

	public String getInviteTime() {
		return inviteTime;
	}

	public void setInviteTime(String inviteTime) {
		this.inviteTime = inviteTime;
	}


	
}
