package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class Item {
	
	//define constructors for the User class
	public Item() {}
	
	private int id;
	
	private int gameType;
	
	private String itemName;
	
	private int itemPrice;
	
	private String comment;

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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	


	

	
	
}
