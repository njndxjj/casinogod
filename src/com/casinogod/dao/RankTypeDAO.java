package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.RankType;

public interface RankTypeDAO {
   
	public List <RankType> querAll();
	
	public void insertRankType(RankType rankType);
	
	public List <RankType> queryById(int typeId);
	
	public boolean updateRankType(RankType rankType);
	
	public boolean deleteRankType(int typeId);
	
}
