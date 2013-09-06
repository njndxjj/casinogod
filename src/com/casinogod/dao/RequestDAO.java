package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import com.casinogod.pojo.FriendRequest;

public interface RequestDAO {
   
	public List <FriendRequest> querAll();
	
	public void insertItem(FriendRequest friendRequest);
	
	public List <FriendRequest> queryByOwenId(long owenId);
	
	public List <FriendRequest> queryByUserId(long userId);
	
	public List <String> queryByStatue(FriendRequest friendRequest);
	
	public boolean updateStatue(FriendRequest friendRequest);
	
	public boolean deleteRequest(Map map);
	
}
