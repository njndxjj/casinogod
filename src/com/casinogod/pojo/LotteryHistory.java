package com.casinogod.pojo;


/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class LotteryHistory {
	
	//define constructors for the User class
	
	public LotteryHistory() {}
	
	private int lotteryId;
	
	private int lotteryType;
	
    private String openDateTime;
	
	private String result;

	public int getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
	}

	public int getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(int lotteryType) {
		this.lotteryType = lotteryType;
	}

	public String getOpenDateTime() {
		return openDateTime;
	}

	public void setOpenDateTime(String openDateTime) {
		this.openDateTime = openDateTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


}
