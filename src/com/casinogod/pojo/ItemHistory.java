package com.casinogod.pojo;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class ItemHistory {
	
	//define constructors for the User class
	
	public ItemHistory() {}
	
	private int id;
	
	private int gameType;
	
	private long owenId;
	
	private int purchaseType;
	
	private String itemName;
	
	private int itemNum;

	private long giftUserId;
	
	private String purchaseTime;
	
	private String otherNotes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public int getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(int purchaseType) {
		this.purchaseType = purchaseType;
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

	public long getGiftUserId() {
		return giftUserId;
	}

	public void setGiftUserId(long giftUserId) {
		this.giftUserId = giftUserId;
	}

	public String getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public String getOtherNotes() {
		return otherNotes;
	}

	public void setOtherNotes(String otherNotes) {
		this.otherNotes = otherNotes;
	}

	public long getOwenId() {
		return owenId;
	}

	public void setOwenId(long owenId) {
		this.owenId = owenId;
	}
	
	
	
	
	
	


	

	
	
}
