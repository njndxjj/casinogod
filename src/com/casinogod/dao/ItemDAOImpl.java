package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.Item;

public class ItemDAOImpl extends SqlMapClientDaoSupport implements  ItemDAO {

	public List<Item> querAll() {
		// TODO Auto-generated method stub
		List <Item> list=null;
		list=this.getSqlMapClientTemplate().queryForList("item.findAllItem");
		return list;
	}

	public void insertItem(Item item) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("item.insertItem", item);
	}

	public List<Item> queryById(int id) {
		// TODO Auto-generated method stub
		List <Item> list=null;
		list=this.getSqlMapClientTemplate().queryForList("item.findItemById",id);
		return list;
	}

	public boolean updateItem(Item item) {
		// TODO Auto-generated method stub
		int updateItem;
		updateItem=this.getSqlMapClientTemplate().update("item.updateItem", item);
		return updateItem>0?true:false;
	}

	public boolean deleteItem(int id) {
		// TODO Auto-generated method stub
		int deleteItem;
		deleteItem=this.getSqlMapClientTemplate().delete("item.deleteItem", id);
		return deleteItem>0?true:false;
	}

}
