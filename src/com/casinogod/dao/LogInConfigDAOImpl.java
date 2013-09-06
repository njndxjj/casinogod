package com.casinogod.dao;

import java.util.List;

import org.apache.catalina.deploy.LoginConfig;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.LogInRewardConfig;

public class LogInConfigDAOImpl extends SqlMapClientDaoSupport implements LogInConfigDAO {

	public List<LogInRewardConfig> querAll() {
		// TODO Auto-generated method stub
		List <LogInRewardConfig> list=null;
		list=this.getSqlMapClientTemplate().queryForList("logInRewardConfig.querryAll");
		return list;
	}

	public void insertItem(LogInRewardConfig loginConfig) {
		// TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert("logInRewardConfig.insertConfig", loginConfig);
	}

	public List<LogInRewardConfig> queryById(int id) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("logInRewardConfig.querryById",id);
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
       this.getSqlMapClientTemplate().delete("logInRewardConfig.deleteConfig", id);
	}

	public void updateById(LogInRewardConfig loginConfig) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("logInRewardConfig.updateConfig", loginConfig);
	}

	public List<LogInRewardConfig> queryByDay(int day) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("logInRewardConfig.querryByDay",day);
	}

}
