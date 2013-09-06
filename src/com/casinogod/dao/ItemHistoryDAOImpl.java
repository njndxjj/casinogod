package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.ItemHistory;

public class ItemHistoryDAOImpl extends SqlMapClientDaoSupport implements ItemHistoryDAO {

	public List<ItemHistory> querAll() {
		// TODO Auto-generated method stub
		
		List <ItemHistory> list=null;
		list=this.getSqlMapClientTemplate().queryForList("itemHistory.findAllItemHistory");
		
		return list;
	}

	public void insertitemHistory(ItemHistory itemHistory) {
		// TODO Auto-generated method stub
        
		this.getSqlMapClientTemplate().insert("itemHistory.insertItemHistory",itemHistory);
	}

	public List<ItemHistory> querAllUser(long userId) {
		// TODO Auto-generated method stub
		List <ItemHistory> list=null;
		list=this.getSqlMapClientTemplate().queryForList("itemHistory.findItemHistoryUser",userId);
		
		return list;
	}

}
