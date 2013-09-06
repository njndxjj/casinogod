package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.Item;


public interface ItemConfig {
	
	public List<Item> queryall();
	
	public boolean addItem(int gameType,String itemName, int itemPrice,String comment);
	
	public List<Item> queryById(int id);
	
	public boolean  updateItem(Item item);
	
	public boolean deleteItem(int id);
	
}
