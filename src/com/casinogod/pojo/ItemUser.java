package com.casinogod.pojo;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class ItemUser {
		
	private int id;
	
	private int itemId;
	
	private long userId;
	
	private String itemName;
	
	private int itemNum;
	
	private int gameType;
	
	private String comment;
	
	
	public ItemUser() {};

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	
	

	
	
	


	

	
	
}
