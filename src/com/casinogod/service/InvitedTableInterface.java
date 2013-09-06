package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.FriendInvite;
import com.casinogod.pojo.InviteTable;


public interface InvitedTableInterface {
	
	public List<InviteTable> queryall();
	
	public boolean addInviteTable(int userId,int invitedId,String inviteTime);
	
	public List<InviteTable> queryByUserId(int userId);
	
	public List<InviteTable> queryByInvitedId(int invitedId);
	
}
