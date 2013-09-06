package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossConfig;
import com.casinogod.pojo.BossUserInfo;


public interface BossUser {
	
	public List<BossUserInfo> queryall();
	
	public boolean addBossBossUserInfo(int bossType,int bossInstance,String battleId,
			int userId,int result);
	
	public List<BossUserInfo> queryByUserId(long userId);
	
	public List<BossUserInfo> queryByInstance(int bossInstance);
	
	public List<BossUserInfo> queryByBattle(String battleId);
	
	public List<BossUserInfo> queryByBattleAndUser(String battleId,int userId);
	
	public List<BossUserInfo> queryByInstanceAndBattle(String battleId,int bossInstance);
	
	public boolean updateResult(BossUserInfo bossUserInfo);
	
}
