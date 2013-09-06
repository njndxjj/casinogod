package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.LogInReward;

public class LogInRewardDAOImpl extends SqlMapClientDaoSupport implements LogInRewardDAO {

	public List<LogInReward> querAll() {
		// TODO Auto-generated method stub
		List <LogInReward> list=null;
		list=this.getSqlMapClientTemplate().queryForList("logInReward.queryAll");
		return list;
	}

	public void insertLogIn(LogInReward logInReward) {
		// TODO Auto-generated method stub
       this.getSqlMapClientTemplate().insert("logInReward.insertLogIn", logInReward);
	}

	public List<LogInReward> queryById(long userId) {
		// TODO Auto-generated method stub
		List <LogInReward> list=null;
		list=this.getSqlMapClientTemplate().queryForList("logInReward.queryById",userId);
		return list;
	}

	public void updateById(LogInReward logInReward) {
		// TODO Auto-generated method stub
       this.getSqlMapClientTemplate().update("logInReward.updateLogIn", logInReward);
	}

	public List<Long> queryByDate(Map<Object, Object> dates) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("logInReward.queryByDate", dates);
	}

	public List<Long> queryUser() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("logInReward.queryForUser");
	}

}
