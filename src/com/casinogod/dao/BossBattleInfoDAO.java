package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import com.casinogod.pojo.BossBattleInfo;

public interface BossBattleInfoDAO {
   
	public List <BossBattleInfo> querAll();
	
	public void insertBossBattleInfo(BossBattleInfo bossBattleInfo);
	
	public List <BossBattleInfo> queryByBattle(Map<Object, Object> ids);
	
	public List <BossBattleInfo> queryByInstance(int bossInstance);
	
	public boolean updateBossBattleInfo(BossBattleInfo bossBattleInfo);
	
	
}
