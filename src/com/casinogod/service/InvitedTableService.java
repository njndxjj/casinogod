package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.InviteTableDAOImpl;
import com.casinogod.pojo.FriendInvite;
import com.casinogod.pojo.InviteTable;

public class InvitedTableService implements InvitedTableInterface {
	
	private InviteTable inviteTable;
	
	private InviteTableDAOImpl inviteTableDAO;
	
	

	public void setInviteTable(InviteTable inviteTable) {
		this.inviteTable = inviteTable;
	}

	public void setInviteTableDAO(InviteTableDAOImpl inviteTableDAO) {
		this.inviteTableDAO = inviteTableDAO;
	}

	public List<InviteTable> queryall() {
		// TODO Auto-generated method stub
		return inviteTableDAO.querAll();
	}

	public boolean addInviteTable(int userId, int invitedId, String inviteTime) {
		// TODO Auto-generated method stub
		inviteTable.setInvitedId(invitedId);
		inviteTable.setInviteTime(inviteTime);
		inviteTable.setUserId(userId);
		try{
			inviteTableDAO.insertInviteTable(inviteTable);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	public List<InviteTable> queryByUserId(int userId) {
		// TODO Auto-generated method stub
		return inviteTableDAO.queryById(userId);
	}

	public List<InviteTable> queryByInvitedId(int invitedId) {
		// TODO Auto-generated method stub
		return inviteTableDAO.queryByInvitedId(invitedId);
	}

}
