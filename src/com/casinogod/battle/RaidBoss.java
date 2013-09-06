package com.casinogod.battle;


import com.casinogod.utility.Utility;

public class RaidBoss {

	private long battleID = 0;
	private int bossInstance = 0;
	private int battleStatus = 1;
	private int battleType=3;
	private String battleCreateTime ;
	private int bossHP = 1000;
	private int gameType = 0;
	private int battleTime=30;
	private int owenId=0;
	private int bossType;


	

	public RaidBoss(int owenId,int bossInstance, int gType,int battleTime,int bossHP,int bossType)
	{
		owenId=owenId;
		battleID = Utility.randomLong();
		bossInstance = bossInstance;
		battleType = battleType;
		gameType =gType ;
		battleCreateTime = Utility.getNowString();	
		bossType=bossType;
	}
	
	public long getBattleID() {
		return battleID;
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

	public int getBossInstance() {
		return bossInstance;
	}

	public void setBossInstance(int bossInstance) {
		this.bossInstance = bossInstance;
	}

	public int getBossHP() {
		return bossHP;
	}

	public void setBossHP(int bossHP) {
		this.bossHP = bossHP;
	}

	public int getBattleTime() {
		return battleTime;
	}

	public void setBattleTime(int battleTime) {
		this.battleTime = battleTime;
	}

	public int getOwenId() {
		return owenId;
	}

	public void setOwenId(int owenId) {
		this.owenId = owenId;
	}

	public int getBossType() {
		return bossType;
	}

	public void setBossType(int bossType) {
		this.bossType = bossType;
	}

	
	
	
}
