package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossConfig;
import com.casinogod.pojo.BossUserInfo;
import com.casinogod.pojo.RankType;
import com.casinogod.pojo.RankUserInfo;


public interface RankUserInterface {
	
	
	public boolean addRankUser(int userId,int  typeId,int rankValue,
			String updateTime,int winTime,int loseTime,int drawTime,int totalMoney,int winMoney,int loseMoney);
	
	public List<RankUserInfo> queryByType(int typeId);
	
	public List<RankUserInfo> queryByTypeUser(int typeId,int userId);
		
	public boolean updateRankType(RankUserInfo rankUserInfo);
	
	
}
