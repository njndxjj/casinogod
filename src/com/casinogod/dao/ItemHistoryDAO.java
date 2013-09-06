package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.ItemHistory;
import com.casinogod.pojo.ItemUser;

public interface ItemHistoryDAO {
   
	public List <ItemHistory> querAll();
	
    public void insertitemHistory(ItemHistory itemHistory);
    
    public List <ItemHistory> querAllUser(long userId);
	
}
