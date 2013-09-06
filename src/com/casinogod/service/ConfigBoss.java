package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BossConfig;
import com.casinogod.pojo.Item;


public interface ConfigBoss {
	
	public List<BossConfig> queryall();
	
	public boolean addBossConfig(int maxHP,int time,String otherNotes,int bossType,int userSize,
			String bossName,String bossImage);
	
	public List<BossConfig> queryById(int bossId);
	
	public List<BossConfig> queryByType(int bossType);
	
	public boolean  updateBossConfig(BossConfig bossConfig);
	
	public boolean deleteBossConfig(int bossId);
	
}
