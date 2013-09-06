package com.casinogod.battle;


import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.utility.Utility;

public class BattleInfo {

	private long battleID = 0;
	private long battleOwnerUserID = 0;
	private long[] battlePlayerUserIDList;
	private int battleStatus = 1;
	private String battleCreateTime ;
	private int battleType = 1;
	private long battlePrize = 1000;
	private int gameType = 0;
	private int[] dealerCards;
	
	private BossBattleInfo bossBattleInfo=new BossBattleInfo();

	public int[] getDealerCards() {
		return dealerCards;
	}

	public void setDealerCards(int[] dealerCards) {
		this.dealerCards = dealerCards;
	}

	public BattleInfo(long userID, int bType, int gType, long bPrize,long battleId)
	{
		battleID = battleId;
		battleOwnerUserID = userID;
		battleType = bType;
		battlePrize = bPrize;
		battleCreateTime = Utility.getNowString();
		gameType = gType;
		
	}
	
	
	public long getBattleID() {
		return battleID;
	}

	public long getBattleOwnerUserID() {
		return battleOwnerUserID;
	}

	public void setBattleOwnerUserID(long battleOwnerUserID) {
		this.battleOwnerUserID = battleOwnerUserID;
	}

	public long[] getBattlePlayerUserIDList() {
		return battlePlayerUserIDList;
	}

	public void setBattlePlayerUserIDList(long[] battlePlayerUserIDList) {
		this.battlePlayerUserIDList = battlePlayerUserIDList;
	}

	
	public void setBattleID(long battleID) {
		this.battleID = battleID;
	}

	

	
	public String getBattleCreateTime() {
		return battleCreateTime;
	}

	public void setBattleCreateTime(String battleCreateTime) {
		this.battleCreateTime = battleCreateTime;
	}

	public long getBattlePrize() {
		return battlePrize;
	}

	public void setBattlePrize(long battlePrize) {
		this.battlePrize = battlePrize;
	}

	public int getBattleStatus() {
		return battleStatus;
	}

	public void setBattleStatus(int battleStatus) {
		this.battleStatus = battleStatus;
	}

	public int getBattleType() {
		return battleType;
	}

	public void setBattleType(int battleType) {
		this.battleType = battleType;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public BossBattleInfo getBossBattleInfo() {
		return bossBattleInfo;
	}

	public void setBossBattleInfo(BossBattleInfo bossBattleInfo) {
		this.bossBattleInfo = bossBattleInfo;
	}
	
	

	
	
	
}
