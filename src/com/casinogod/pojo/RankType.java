package com.casinogod.pojo;

import java.sql.Date;

/**
 * 
 * @author xie.junjie
 *  userInfro class
 */

public class RankType {
	
	//define constructors for the User class
	public RankType() {}
	
	private int typeId;
	
	private String rankName;
	
	private String rankDescription;
	
	private String startTime;
	
	private String endTime;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public String getRankDescription() {
		return rankDescription;
	}

	public void setRankDescription(String rankDescription) {
		this.rankDescription = rankDescription;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	


	

	
	
}
