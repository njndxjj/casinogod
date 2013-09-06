package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class UserBattleResult {
	
	//define constructors for the User class
	public UserBattleResult() {}
	
	private int userId;
	
	private int gameType;
	
	private int  battleType;
	
	private int winTotal;
	
	private int loseTotal;
	
	private int totalResult;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public int getBattleType() {
		return battleType;
	}

	public void setBattleType(int battleType) {
		this.battleType = battleType;
	}

	public int getWinTotal() {
		return winTotal;
	}

	public void setWinTotal(int winTotal) {
		this.winTotal = winTotal;
	}

	public int getLoseTotal() {
		return loseTotal;
	}

	public void setLoseTotal(int loseTotal) {
		this.loseTotal = loseTotal;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	

	

	
	


	

	
	
}
