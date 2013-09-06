package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class FriendInvite {
	
	//define constructors for the User class
	public FriendInvite() {}
	
	private int userId;
	
	private String inviteCode;
	
	private String createTime;
	
	private int friendCount;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getFriendCount() {
		return friendCount;
	}

	public void setFriendCount(int friendCount) {
		this.friendCount = friendCount;
	}

	
	


	

	
	
}
