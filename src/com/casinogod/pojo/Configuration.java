package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class Configuration {
	
	//define constructors for the User class
	public Configuration() {}
	
	private int id;
	
	private int winTimes;
	
	private int rankSize;
	
	private int bossRewardNum;
	
	private int invitedReward;
	
	private int invitedItem; 

	public int getWinTimes() {
		return winTimes;
	}

	public void setWinTimes(int winTimes) {
		this.winTimes = winTimes;
	}

	public int getRankSize() {
		return rankSize;
	}

	public void setRankSize(int rankSize) {
		this.rankSize = rankSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBossRewardNum() {
		return bossRewardNum;
	}

	public void setBossRewardNum(int bossRewardNum) {
		this.bossRewardNum = bossRewardNum;
	}

	public int getInvitedReward() {
		return invitedReward;
	}

	public void setInvitedReward(int invitedReward) {
		this.invitedReward = invitedReward;
	}

	public int getInvitedItem() {
		return invitedItem;
	}

	public void setInvitedItem(int invitedItem) {
		this.invitedItem = invitedItem;
	}
	
	

	
}
	

	
	
	
	
	
	


	

	
	

