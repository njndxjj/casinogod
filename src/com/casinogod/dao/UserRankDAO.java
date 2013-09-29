package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.EventConfig;
import com.casinogod.pojo.RankUserInfo;

public interface UserRankDAO {
   
	
	public void insertUserRank(RankUserInfo rankUserInfo);
	
	public List <RankUserInfo> queryByType(int typeId,String column);
	
	public List <RankUserInfo> queryByTypeUser(int typeId,int userId);
		
	public boolean updateRank(RankUserInfo rankUserInfo);
	
	
}
