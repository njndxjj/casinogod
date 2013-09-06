package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.BossConfigDAOImpl;
import com.casinogod.pojo.BossConfig;

public class BossConfigService implements ConfigBoss {
	
	BossConfig bossConfig;
	BossConfigDAOImpl bossConfigDAO;
	
	
	public void setBossConfig(BossConfig bossConfig) {
		this.bossConfig = bossConfig;
	}

	public void setBossConfigDAO(BossConfigDAOImpl bossConfigDAO) {
		this.bossConfigDAO = bossConfigDAO;
	}

	public List<BossConfig> queryall() {
		// TODO Auto-generated method stub
		return bossConfigDAO.querAll();
	}

	public boolean addBossConfig(int maxHP,int time,String otherNotes,int bossType,int userSize
			,String bossName,String bossImage) {
		// TODO Auto-generated method stub
		bossConfig.setMaxHP(maxHP);
		bossConfig.setTime(time);
		bossConfig.setBossType(bossType);
		bossConfig.setOtherNotes(otherNotes);
		bossConfig.setUserSize(userSize);
		bossConfig.setBossName(bossName);
		bossConfig.setBossImage(bossImage);
		bossConfigDAO.insertBossConfig(bossConfig);
		return true;
	}

	public List<BossConfig> queryById(int bossId) {
		// TODO Auto-generated method stub
		return bossConfigDAO.queryById(bossId);
	}

	public boolean updateBossConfig(BossConfig bossConfig) {
		// TODO Auto-generated method stub
		return bossConfigDAO.updateBossConfig(bossConfig);
	}

	public boolean deleteBossConfig(int bossId) {
		// TODO Auto-generated method stub
		return bossConfigDAO.deleteBossConfig(bossId);
	}

	public List<BossConfig> queryByType(int bossType) {
		// TODO Auto-generated method stub
		return bossConfigDAO.queryByType(bossType);
	}



}
