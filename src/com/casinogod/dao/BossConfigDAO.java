package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.BossConfig;
import com.casinogod.pojo.Item;

public interface BossConfigDAO {
   
	public List <BossConfig> querAll();
	
	public void insertBossConfig(BossConfig bossConfig);
	
	public List <BossConfig> queryById(int id);
	
	public List <BossConfig> queryByType(int bossType);
	
	public boolean updateBossConfig(BossConfig bossConfig);
	
	public boolean deleteBossConfig(int id);
	
}
