package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class BossConfig {
	
	//define constructors for the User class
	public BossConfig() {}
	
	private int bossId;
	
	private int bossType;
	
	private int maxHP;
	
	private int time;
	
	private String otherNotes;
	
	private int userSize;
	
	private String bossName;
	
	private String bossImage;

	public int getBossId() {
		return bossId;
	}

	public void setBossId(int bossId) {
		this.bossId = bossId;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getOtherNotes() {
		return otherNotes;
	}

	public void setOtherNotes(String otherNotes) {
		this.otherNotes = otherNotes;
	}

	public int getBossType() {
		return bossType;
	}

	public void setBossType(int bossType) {
		this.bossType = bossType;
	}

	public int getUserSize() {
		return userSize;
	}

	public void setUserSize(int userSize) {
		this.userSize = userSize;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public String getBossImage() {
		return bossImage;
	}

	public void setBossImage(String bossImage) {
		this.bossImage = bossImage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	

	
	
}
