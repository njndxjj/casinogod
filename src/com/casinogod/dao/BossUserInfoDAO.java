package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossUserInfo;

public interface BossUserInfoDAO {
   
	public List <BossUserInfo> querAll();
	
	public void insertBossUserInfo(BossUserInfo bossUserInfo);
	
	public List <BossUserInfo> queryByBattle(String battleId);
	
	public List <BossUserInfo> queryByUser(long userId);
	
	public List <BossUserInfo> queryByInstance(int bossInstance);
	
	public List <BossUserInfo> queryByInstanceAndId(Map<Object, Object> ids);
	
	public List <BossUserInfo> queryByBattleAndId(Map<Object, Object> ids);
	
	public boolean updateReuslt(BossUserInfo bossUserInfo);
	
	
}
