package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.IAPlog;

public class IAPLogDAOImpl extends SqlMapClientDaoSupport implements IAPLogDAO {

	public List<IAPlog> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("iAPlog.findAll");
	}

	public void insertIAPlog(IAPlog iAPlog) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("iAPlog.insertIAP",iAPlog);
	}

	public List<IAPlog> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("iAPlog.findByUserId", userId);
	}

	public List<IAPlog> queryByDate(Map<String, String> dates) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("iAPlog.findByDate", dates);
	}

}
