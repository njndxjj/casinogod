package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.ItemUser;

public interface ItemUserDAO {
   
	public List <ItemUser> querAll();
	
	public List <ItemUser> querUserItem(long userId);
	
	public void insertItemUser(ItemUser itemUser);
	
	public List <ItemUser> getItem(long userId,String itemName,int gameType);
	
	public List <ItemUser> getUserItem(int itemId);
	
	public void updateItemUser(long userId,String itemName,int gameType,int itemNum);
	
	public void updateItemUserName(String itemName,int itemId);
	
	public void deleteItemUser(int id);
	
}
