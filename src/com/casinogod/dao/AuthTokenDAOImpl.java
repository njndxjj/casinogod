package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.AuthToken;
import com.casinogod.pojo.Item;

public class AuthTokenDAOImpl extends SqlMapClientDaoSupport implements  AuthTokenDAO {

	public List<AuthToken> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("authToken.findAllToken");
	}

	public void insertAuthToken(AuthToken authToken) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("authToken.insertToken", authToken);
	}

	public List<AuthToken> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("authToken.findByUserId", userId);
	}

	public boolean updateToken(AuthToken authToken) {
		// TODO Auto-generated method stub
		
		int updateAccount=this.getSqlMapClientTemplate().update("authToken.updateByUserId", 
				authToken);
		
		return updateAccount>0?true:false;
	}

	

}
