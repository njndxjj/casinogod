package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.FriendInvite;

public interface FriendInviteDAO {
   
	public List <FriendInvite> querAll();
	
	public void insertFriendInvite(FriendInvite friendInvite);
	
	public List <FriendInvite> queryById(int userId);
	
	public List <FriendInvite> queryByCode(String inviteCode);
	
	public boolean updateCount(FriendInvite friendInvite);
	

	
}
