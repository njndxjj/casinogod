package com.casinogod.pojo;

import java.sql.Date;
import java.util.List;

/**
 * 
 * @author xie.junjie
 *  userInfro class    
 */

public class User {
	
	//define constructors for the User class
	public User() {}
	
	private long userId;
	
	private String nickName;
	
	private String gender;
	
	private String emailAddress;
	
	private String telephone;
	
	private String userType;
	
	private String registerDate;
	
	private String image;
	
	private int level;
	
	private int exp;
	
	private int vipLevel;

	private int gold;
	
	private int diamond;
	
	private String otherNotes;
	
	private String friendList;
	
	private List <ItemUser> items;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getDiamond() {
		return diamond;
	}

	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}

	public String getOtherNotes() {
		return otherNotes;
	}

	public void setOtherNotes(String otherNotes) {
		this.otherNotes = otherNotes;
	}

	public List<ItemUser> getItems() {
		return items;
	}

	public void setItems(List<ItemUser> items) {
		this.items = items;
	}

	public String getFriendList() {
		return friendList;
	}

	public void setFriendList(String friendList) {
		this.friendList = friendList;
	}
	
	
	
}
