package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.BossUserInfo;

public  class BossUserInfoDAOImpl extends SqlMapClientDaoSupport implements BossUserInfoDAO {

	public List<BossUserInfo> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossUserInfo.findAll");
	}

	public void insertBossUserInfo(BossUserInfo bossUserInfo) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("bossUserInfo.insertBossUserInfo",bossUserInfo);
	}

	public List<BossUserInfo> queryByBattle(String battleId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossUserInfo.findByBattle",battleId);
	}

	public List<BossUserInfo> queryByInstance(int bossInstance) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossUserInfo.findByInstance",bossInstance);
	}

	public List<BossUserInfo> queryByInstanceAndId(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossUserInfo.findByInstanceIdBattleId", ids);
	}

	public List<BossUserInfo> queryByBattleAndId(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossUserInfo.findByUserIdBattleId", ids);
	}

	public List<BossUserInfo> queryByUser(long userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossUserInfo.findByUserId", userId);
	}

	public boolean updateReuslt(BossUserInfo bossUserInfo) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("bossUserInfo.updateResult", bossUserInfo);
		return false;
	}

}
