package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.AuthTokenDAOImpl;
import com.casinogod.dao.ItemUserDAOImpl;
import com.casinogod.pojo.AuthToken;
import com.casinogod.pojo.ItemUser;

public class AuthTokenService implements AuthTokenConfig {
	
	private AuthTokenDAOImpl authTokenDAO;
	
	private AuthToken authToken;
	
	public void setAuthTokenDAO(AuthTokenDAOImpl authTokenDAO) {
		this.authTokenDAO = authTokenDAO;
	}

	

	public void setAuthToken(AuthToken authToken) {
		this.authToken = authToken;
	}


	public List<AuthToken> queryall() {
		// TODO Auto-generated method stub
		return authTokenDAO.querAll();
	}

	public void addAuth(long userId, String authTokens) {
		// TODO Auto-generated method stub
		authToken.setAuthToken(authTokens);
		authToken.setUserId(userId);
		
		authTokenDAO.insertAuthToken(authToken);
	}

	public List<AuthToken> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		return authTokenDAO.queryByUserId(userId);
	}

	public boolean updateToken(AuthToken authToken) {
		// TODO Auto-generated method stub
		return authTokenDAO.updateToken(authToken);
	}
	
	
	

}
