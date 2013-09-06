package com.casinogod.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.ItemUser;

public class ItemUserDAOImpl extends SqlMapClientDaoSupport implements ItemUserDAO {
	
	

	public List<ItemUser> querAll() {
		// TODO Auto-generated method stub
		List<ItemUser> list=null;
		list=this.getSqlMapClientTemplate().queryForList("itemUser.findAllItem");	
		return list;
	}

	public void insertItemUser(ItemUser itemUser) {
		// TODO Auto-generated method stub
		System.out.println("itemId:-->"+itemUser.getItemId());
       this.getSqlMapClientTemplate().insert("itemUser.insert",itemUser);
	}

	public List<ItemUser> querUserItem(long userId) {
		// TODO Auto-generated method stub
		List<ItemUser> list=null;
		list=this.getSqlMapClientTemplate().queryForList("itemUser.getItems",userId);
		return list;
	}
	
	public List <ItemUser> getItem (long userId, String itemName, int gameType) {
		// TODO Auto-generated method stub
		List <ItemUser> list=null;
		ItemUser itemUser=new ItemUser();
		itemUser.setUserId(userId);
		itemUser.setItemName(itemName);
		itemUser.setGameType(gameType);
		
		list=this.getSqlMapClientTemplate().queryForList("itemUser.getItem", itemUser);
		
		return list;
		
	}

	public void updateItemUser(long userId, String itemName, int gameType,
			int itemNum) {
		// TODO Auto-generated method stub
		ItemUser itemUser=new ItemUser();
		itemUser.setUserId(userId);
		itemUser.setItemName(itemName);
		itemUser.setGameType(gameType);
		itemUser.setItemNum(itemNum);
		
		this.getSqlMapClientTemplate().update("itemUser.updateItemUser", itemUser);
		
	}

	public void deleteItemUser(int id) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("itemUser.deleteItem", id);
	}

	public List<ItemUser> getUserItem(int itemId) {
		// TODO Auto-generated method stub
		List <ItemUser> list=null;
	
		
		list=this.getSqlMapClientTemplate().queryForList("itemUser.getUserItems", itemId);
		
		return list;
	}

	public void updateItemUserName(String itemName, int itemId) {
		// TODO Auto-generated method stub
		
		ItemUser itemUser=new ItemUser();
		itemUser.setItemName(itemName);
		itemUser.setItemId(itemId);
		
		this.getSqlMapClientTemplate().update("itemUser.updateItemUserName", itemUser);
	}

	
}
