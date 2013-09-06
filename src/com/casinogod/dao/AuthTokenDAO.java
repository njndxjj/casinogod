package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.AuthToken;
import com.casinogod.pojo.Item;

public interface AuthTokenDAO {
   
	public List <AuthToken> querAll();
	
	public void insertAuthToken(AuthToken authToken);
	
	public List <AuthToken> queryByUserId(long userId);
	
	boolean updateToken (AuthToken authToken);
	
}
