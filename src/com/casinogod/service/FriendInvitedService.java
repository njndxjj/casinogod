package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.FriendInviteDAOImpl;
import com.casinogod.pojo.FriendInvite;

public class FriendInvitedService implements FriendInvitedInterface {
	
	private FriendInvite friendInvite;
	
	private FriendInviteDAOImpl friendInviteDAO;
	

	public void setFriendInvite(FriendInvite friendInvite) {
		this.friendInvite = friendInvite;
	}

	public void setFriendInviteDAO(FriendInviteDAOImpl friendInviteDAO) {
		this.friendInviteDAO = friendInviteDAO;
	}

	public List<FriendInvite> queryall() {
		// TODO Auto-generated method stub
		return friendInviteDAO.querAll();
	}

	public boolean addFriendInvite(int userId, String inviteCode,
			String createTime, int friendCount) {
		// TODO Auto-generated method stub
		friendInvite.setCreateTime(createTime);
		friendInvite.setFriendCount(friendCount);
		friendInvite.setInviteCode(inviteCode);
		friendInvite.setUserId(userId);
		friendInviteDAO.insertFriendInvite(friendInvite);
		return true;
	}

	public List<FriendInvite> queryByUserId(int userId) {
		// TODO Auto-generated method stub
		return friendInviteDAO.queryById(userId);
	}

	public boolean updateCount(FriendInvite friendInvite) {
		// TODO Auto-generated method stub
		return friendInviteDAO.updateCount(friendInvite);
	}

	public List<FriendInvite> queryByCode(String inviteCode) {
		// TODO Auto-generated method stub
		return friendInviteDAO.queryByCode(inviteCode);
	}

}
