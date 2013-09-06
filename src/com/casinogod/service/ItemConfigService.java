package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.ItemDAOImpl;
import com.casinogod.pojo.Item;

public class ItemConfigService implements ItemConfig {
	
	private ItemDAOImpl itemDAO;
	private Item item;
	
	
	public void setItemDAO(ItemDAOImpl itemDAO) {
		this.itemDAO = itemDAO;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> queryall() {
		// TODO Auto-generated method stub
		List <Item> list=null;
		list=itemDAO.querAll();
		return list;
	}

	public boolean addItem(int gameType, String itemName, int itemPrice,
			String comment) {
		// TODO Auto-generated method stub
		item.setGameType(gameType);
		item.setItemName(itemName);
		item.setItemPrice(itemPrice);
		item.setComment(comment);
		itemDAO.insertItem(item);
		return true;
	}

	public List<Item> queryById(int id) {
		// TODO Auto-generated method stub
		List <Item> list=null;
		list=itemDAO.queryById(id);
		return list;
	}

	public boolean updateItem(Item item) {
		// TODO Auto-generated method stub
		return itemDAO.updateItem(item);
	}

	public boolean deleteItem(int id) {
		// TODO Auto-generated method stub
		return itemDAO.deleteItem(id);
	}

}
