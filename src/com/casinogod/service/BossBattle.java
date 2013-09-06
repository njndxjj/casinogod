package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossConfig;


public interface BossBattle {
	
	public List<BossBattleInfo> queryall();
	
	public boolean addBossBossBattleInfo(BossBattleInfo bossBattleInfo);
	
	public List<BossBattleInfo> queryByBattle(int bossType,int bossInstance);
	
	public List<BossBattleInfo> queryByInstance(int bossInstance);
	
	public boolean  updateBossInfo(BossBattleInfo bossBattleInfo);

	
	
}
