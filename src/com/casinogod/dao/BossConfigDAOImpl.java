package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.BossConfig;

public class BossConfigDAOImpl extends SqlMapClientDaoSupport implements BossConfigDAO {

	public List<BossConfig> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossConfig.findAll");
	}

	public void insertBossConfig(BossConfig bossConfig) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("bossConfig.insertBossConfig", bossConfig);
	}

	public List<BossConfig> queryById(int id) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossConfig.findBossConfigById",id);
	}

	public boolean updateBossConfig(BossConfig bossConfig) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("bossConfig.updateBossConfig", bossConfig);
		return updateCount>0?true:false;
	}

	public boolean deleteBossConfig(int id) {
		// TODO Auto-generated method stub
		int deleteCount;
		deleteCount=this.getSqlMapClientTemplate().delete("bossConfig.deleteBossConfig", id);
		return deleteCount>0?true:false;
	}

	public List<BossConfig> queryByType(int bossType) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossConfig.findBossConfigByType",bossType);
	}

}
