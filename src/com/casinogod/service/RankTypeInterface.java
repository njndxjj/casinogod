package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossConfig;
import com.casinogod.pojo.BossUserInfo;
import com.casinogod.pojo.RankType;


public interface RankTypeInterface {
	
	
	public boolean addRankType(String rankName,String rankDescription,String startTime,
			String endTime);
	
	public List<RankType> queryById(int typeId);
	
	public List<RankType> queryAll();
	
	
	public boolean updateRankType(RankType rankType);
	
	public boolean deleteRankType(int typeId);
	
}
