package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.FriendRequest;
import com.casinogod.pojo.Item;
import com.casinogod.pojo.User;


public interface RequestConfig {
	
	public List<FriendRequest> queryall();
	
	public boolean addFriendRequest(long owenId,long userId, String requestTime,int statue);
	
	public List<FriendRequest> queryOwenId(long owenId);
	
	public List<FriendRequest> queryUserId(long userId);
	
	public List<String> queryStatue(FriendRequest friendRequest);
	
	public boolean updateStatue(FriendRequest friendRequest);
	
	public boolean deleteRequest(long owenId, long userId);
	
}
