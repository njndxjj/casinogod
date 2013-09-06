package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.BossBattleInfo;

public class BossBattleInfoDAOImpl extends SqlMapClientDaoSupport implements BossBattleInfoDAO  {

	public List<BossBattleInfo> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossBattleInfo.findAll");
	}

	public void insertBossBattleInfo(BossBattleInfo bossBattleInfo) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("bossBattleInfo.insertBossBattleInfo", bossBattleInfo);

	}

	public List<BossBattleInfo> queryByBattle(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossBattleInfo.findByBattle", ids);
	}

	public List<BossBattleInfo> queryByInstance(int bossInstance) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossBattleInfo.findByBossInstance", bossInstance);
	}

	public boolean updateBossBattleInfo(BossBattleInfo bossBattleInfo) {
		// TODO Auto-generated method stub
		
		int updateCount;
		
		updateCount=this.getSqlMapClientTemplate().update("bossBattleInfo.updateStatue", bossBattleInfo);
		
		return updateCount>0?true:false;
	}

}
