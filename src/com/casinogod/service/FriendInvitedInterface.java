package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BossUserInfo;
import com.casinogod.pojo.FriendInvite;


public interface FriendInvitedInterface {
	
	public List<FriendInvite> queryall();
	
	public boolean addFriendInvite(int userId,String inviteCode,String createTime,
			int friendCount);
	
	public List<FriendInvite> queryByUserId(int userId);

	public List<FriendInvite> queryByCode(String inviteCode);
	
	public boolean updateCount(FriendInvite friendInvite);
	
}
