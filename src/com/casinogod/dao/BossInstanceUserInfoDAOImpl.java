package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.BossInstanceUserInfo;
import com.casinogod.pojo.BossUserInfo;

public class BossInstanceUserInfoDAOImpl extends SqlMapClientDaoSupport  implements BossInstanceUserInfoDAO {

	public List<BossInstanceUserInfo> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossInstanceUserInfo.findAll");
	}

	public void insertBossInstanceUserInfo(
			BossInstanceUserInfo bossInstanceUserInfo) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("bossInstanceUserInfo.insertBossInstanceUserInfo",bossInstanceUserInfo);

	}

	public List<BossInstanceUserInfo> queryByUser(long userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossInstanceUserInfo.findByUserId",userId);
	}

	public List<BossInstanceUserInfo> queryByInstance(int bossInstance) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossInstanceUserInfo.findByInstance",bossInstance);
	}

	public List<BossInstanceUserInfo> queryByInstanceAndId(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bossInstanceUserInfo.findByUserIdInstance",ids);
	}

	public boolean updateReuslt(BossInstanceUserInfo bossInstanceUserInfo) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("bossInstanceUserInfo.updateResult", bossInstanceUserInfo);
		return updateCount>0?true:false;
	}

}
