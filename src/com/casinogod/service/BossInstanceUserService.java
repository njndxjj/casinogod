package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casinogod.dao.BossInstanceUserInfoDAOImpl;
import com.casinogod.pojo.BossInstanceUserInfo;

public class BossInstanceUserService implements BossInstanceUser {
	
	private BossInstanceUserInfo bossInstanceUserInfo;
	private BossInstanceUserInfoDAOImpl bossInstanceUserDAO;
	

	public void setBossInstanceUserInfo(BossInstanceUserInfo bossInstanceUserInfo) {
		this.bossInstanceUserInfo = bossInstanceUserInfo;
	}


	public void setBossInstanceUserDAO(
			BossInstanceUserInfoDAOImpl bossInstanceUserDAO) {
		this.bossInstanceUserDAO = bossInstanceUserDAO;
	}


	public List<BossInstanceUserInfo> queryall() {
		// TODO Auto-generated method stub
		return bossInstanceUserDAO.querAll();
	}

	public boolean addBossInstanceUserInfo(int bossInstance, long userId,int result) {
		// TODO Auto-generated method stub
		
		bossInstanceUserInfo.setBossInstance(bossInstance);
		bossInstanceUserInfo.setUserId(userId);
		bossInstanceUserInfo.setResult(result);
		
		
		bossInstanceUserDAO.insertBossInstanceUserInfo(bossInstanceUserInfo);
		
		return true;
	}

	public List<BossInstanceUserInfo> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		return bossInstanceUserDAO.queryByUser(userId);
	}

	public List<BossInstanceUserInfo> queryByInstance(int bossInstance) {
		// TODO Auto-generated method stub
		return bossInstanceUserDAO.queryByInstance(bossInstance);
	}

	public List<BossInstanceUserInfo> queryByInstanceAndUser(long userId,
			int bossInstance) {
		// TODO Auto-generated method stub
		Map <Object,Object> ids=new HashMap<Object, Object>();
		ids.put("userId", userId);
		ids.put("bossInstance", bossInstance);
		return bossInstanceUserDAO.queryByInstanceAndId(ids);
	}

	public boolean updateResult(BossInstanceUserInfo bossInstanceUserInfo) {
		// TODO Auto-generated method stub
		return bossInstanceUserDAO.updateReuslt(bossInstanceUserInfo);
	}

}
