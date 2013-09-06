package com.casinogod.pojo;

public class UserBattleHistory {
	
	private int id;
	
	private long userId;

	private String gameType;
	
	private int winTimes;
	
	private int lostTime;
	
	private int drawTimes;
	
	private long winMoney;
	
	private long lostMoney;

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

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public int getWinTimes() {
		return winTimes;
	}

	public void setWinTimes(int winTimes) {
		this.winTimes = winTimes;
	}

	public int getLostTime() {
		return lostTime;
	}

	public void setLostTime(int lostTime) {
		this.lostTime = lostTime;
	}

	public long getWinMoney() {
		return winMoney;
	}

	public void setWinMoney(long winMoney) {
		this.winMoney = winMoney;
	}

	public long getLostMoney() {
		return lostMoney;
	}

	public void setLostMoney(long lostMoney) {
		this.lostMoney = lostMoney;
	}

	public int getDrawTimes() {
		return drawTimes;
	}

	public void setDrawTimes(int drawTimes) {
		this.drawTimes = drawTimes;
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

}
