package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.FriendInvite;
import com.casinogod.pojo.InviteTable;

public interface InviteTableDAO {
   
	public List <InviteTable> querAll();
	
	public void insertInviteTable(InviteTable inviteTable);
	
	public List <InviteTable> queryById(int userId);
	
	public List <InviteTable> queryByInvitedId(int invitedId);
	
	

	
}
