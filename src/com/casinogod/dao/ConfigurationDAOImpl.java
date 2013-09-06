package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.Configuration;

public class ConfigurationDAOImpl extends SqlMapClientDaoSupport implements ConfigurationDAO {

	public List<Configuration> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("configuration.findAllConfig");
	}

	public void insertConfiguration(Configuration configuration) {
		// TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert("configuration.insertConfiguration", configuration);
	}

	public List<Configuration> queryById(int id) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("configuration.findConfigById",id);
	}

	public boolean updateConfiguration(Configuration configuration) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("configuration.updateConfig", configuration);
		return updateCount>0?true:false;
	}

}
