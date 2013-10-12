package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.ItemUser;


public interface ItemUserConfig {
	
	public List<ItemUser> queryall();
	
	public boolean addItemUser(int itemId,long userId,String itemName, int itemNum,int gameType,String comment);
	
	public List <ItemUser> queryByUserId(long userId);
		
	public List <ItemUser> getItem(long userId,int itemName,int gameType);
	
	public void updateItemUser(long userId,String itemName,int gameType,int itemNum);
	
	public void deleteItemUser(int id);
	
	public List <ItemUser> getUserItem(int itemId);
	
	public void updateItemUserName(String itemName,int itemId);
	
	
	
}
