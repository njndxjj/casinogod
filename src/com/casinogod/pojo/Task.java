package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class Task {
	
	//define constructors for the User class
	public Task() {}
	
	private int taskId;
	private int taskType;
		
	private int  taskRate;
	
	private int  itemId;
	
	private int itemNum;
	
	private int status;
	
	private int isDaily;
	
	private String description;
	
	private int battleType;
	
	private int gameType;
	


	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public int getTaskRate() {
		return taskRate;
	}

	public void setTaskRate(int taskRate) {
		this.taskRate = taskRate;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsDaily() {
		return isDaily;
	}

	public void setIsDaily(int isDaily) {
		this.isDaily = isDaily;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
	
	
	
	

	
	
	
	
	
	


	

	

	


	

	
	
}
