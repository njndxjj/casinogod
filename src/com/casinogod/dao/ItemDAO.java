package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.Item;

public interface ItemDAO {
   
	public List <Item> querAll();
	
	public void insertItem(Item item);
	
	public List <Item> queryById(int id);
	
	public boolean updateItem(Item item);
	
	public boolean deleteItem(int id);
	
}
