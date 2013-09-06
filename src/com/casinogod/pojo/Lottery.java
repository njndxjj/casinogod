package com.casinogod.pojo;


/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class Lottery {
	
	//define constructors for the User class
	public Lottery() {}
	
	private long userId;
	
	private int lotteryId;
	
	private int lotteryType;
	
	private String lotteryValue;
	
	private int level;
	
	private int num;
	
	private int betAmount;
	
	private boolean result;
	
	private String betDateTime;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(int lotteryType) {
		this.lotteryType = lotteryType;
	}

	public String getLotteryValue() {
		return lotteryValue;
	}

	public void setLotteryValue(String lotteryValue) {
		this.lotteryValue = lotteryValue;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getBetDateTime() {
		return betDateTime;
	}

	public void setBetDateTime(String betDateTime) {
		this.betDateTime = betDateTime;
	}

	public int getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	

	
	
	

	
	
	


	

	
	
}
