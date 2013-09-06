package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.UserBattleResult;

public class UserBattleResultDAOImpl extends SqlMapClientDaoSupport implements UserBattleResultDAO {

	public List<UserBattleResult> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userBattleResult.findAll");
	}

	public List<UserBattleResult> queryUserAndBattle(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userBattleResult.findUserAndBattleType",ids);
	}

	public List<UserBattleResult> queryUserAndGame(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userBattleResult.findUserAndGameType",ids);
	}

	public List<UserBattleResult> queryUserAndGameAndBattle(
			Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userBattleResult.findUserAndGameTypeAndBattle",ids);
		
	}

	public void insertBattleResult(UserBattleResult userBattleResult) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("userBattleResult.insertUserBattle", userBattleResult);
	}

	public boolean updateBattleResult(UserBattleResult userBattleResult) {
		// TODO Auto-generated method stub
		int updateCount=0;
		updateCount=this.getSqlMapClientTemplate().update("userBattleResult.updateResult", userBattleResult);
		return updateCount>0?true:false;
	}

}
