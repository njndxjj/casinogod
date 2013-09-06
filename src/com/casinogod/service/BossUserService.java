package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casinogod.dao.BossUserInfoDAOImpl;
import com.casinogod.pojo.BossUserInfo;

public class BossUserService implements BossUser {
	
	private BossUserInfo bossUserInfo;
	
	private BossUserInfoDAOImpl bossInfoDAO;
		

	public BossUserInfo getBossUserInfo() {
		return bossUserInfo;
	}

	public void setBossUserInfo(BossUserInfo bossUserInfo) {
		this.bossUserInfo = bossUserInfo;
	}

	public BossUserInfoDAOImpl getBossInfoDAO() {
		return bossInfoDAO;
	}

	public void setBossInfoDAO(BossUserInfoDAOImpl bossInfoDAO) {
		this.bossInfoDAO = bossInfoDAO;
	}

	public List<BossUserInfo> queryall() {
		// TODO Auto-generated method stub
		return bossInfoDAO.querAll();
	}

	public boolean addBossBossUserInfo(int bossType, int bossInstance,
			String battleId, int userId, int result) {
		// TODO Auto-generated method stub
		bossUserInfo.setBattleId(battleId);
		bossUserInfo.setBossInstance(bossInstance);
		bossUserInfo.setBossType(bossType);
		bossUserInfo.setResult(result);
		bossUserInfo.setUserId(userId);
		bossInfoDAO.insertBossUserInfo(bossUserInfo);
		
		return true;
	}

	public List<BossUserInfo> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		return bossInfoDAO.queryByUser(userId);
	}

	public List<BossUserInfo> queryByInstance(int bossInstance) {
		// TODO Auto-generated method stub
		return bossInfoDAO.queryByInstance(bossInstance);
	}

	public List<BossUserInfo> queryByBattle(String battleId) {
		// TODO Auto-generated method stub
		return bossInfoDAO.queryByBattle(battleId);
	}

	public List<BossUserInfo> queryByBattleAndUser(String battleId, int userId) {
		// TODO Auto-generated method stub
		Map<Object,Object> ids = new HashMap<Object, Object>();
		ids.put("battleId", battleId);
		ids.put("userId", userId);
		return bossInfoDAO.queryByBattleAndId(ids);
	}

	public List<BossUserInfo> queryByInstanceAndBattle(String battleId,
			int bossInstance) {
		// TODO Auto-generated method stub
		Map<Object,Object> ids = new HashMap<Object, Object>();
		ids.put("battleId", battleId);
		ids.put("bossInstance", bossInstance);
		return bossInfoDAO.queryByInstanceAndId(ids);
	}

	public boolean updateResult(BossUserInfo bossUserInfo) {
		// TODO Auto-generated method stub
		return bossInfoDAO.updateReuslt(bossUserInfo);
	}

}
