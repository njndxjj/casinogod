package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.ItemUserDAOImpl;
import com.casinogod.pojo.ItemUser;

public class ItemUserConfigService implements ItemUserConfig {
	
	private ItemUserDAOImpl itemUserDAO;
	
	private ItemUser itemUser;
	
	
	public void setItemUserDAO(ItemUserDAOImpl itemUserDAO) {
		this.itemUserDAO = itemUserDAO;
	}


	public void setItemUser(ItemUser itemUser) {
		this.itemUser = itemUser;
	}


	public List<ItemUser> queryall() {
		// TODO Auto-generated method stub
		return itemUserDAO.querAll();
	}

	
	public List<ItemUser> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		return itemUserDAO.querUserItem(userId);
	}

	public boolean addItemUser(int itemId,long userId, String itemName, int itemNum,
			 int gameType,String comment) {
		// TODO Auto-generated method stub
		itemUser.setItemId(itemId);
		itemUser.setUserId(userId);
		itemUser.setItemName(itemName);
		itemUser.setItemNum(itemNum);
		itemUser.setGameType(gameType);
		itemUser.setComment(comment);
		
		itemUserDAO.insertItemUser(itemUser);
		
		return true;
	}

	public List<ItemUser> getItem(long userId, String itemName, int gameType) {
		// TODO Auto-generated method stub
		return itemUserDAO.getItem(userId, itemName, gameType);
	}

	public void updateItemUser(long userId, String itemName, int gameType,
			int itemNum) {
		// TODO Auto-generated method stub
		itemUserDAO.updateItemUser(userId, itemName, gameType, itemNum);
	}

	public void deleteItemUser(int id) {
		// TODO Auto-generated method stub
		itemUserDAO.deleteItemUser(id);
	}


	public List<ItemUser> getUserItem(int itemId) {
		// TODO Auto-generated method stub
		return itemUserDAO.getUserItem( itemId);
	}


	public void updateItemUserName(String itemName, int itemId) {
		// TODO Auto-generated method stub
		itemUserDAO.updateItemUserName(itemName, itemId);
	}

}
