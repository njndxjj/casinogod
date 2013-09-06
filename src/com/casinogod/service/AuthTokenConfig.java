package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.AuthToken;


public interface AuthTokenConfig {
	
	public List<AuthToken> queryall();
	
	public void addAuth(long userId,String authTokens);
	
	public List<AuthToken> queryByUserId(long userId);
	
	boolean updateToken(AuthToken authToken);
	
}
