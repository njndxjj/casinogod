package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import com.casinogod.pojo.IAPlog;

public interface IAPLogDAO {
   
	public List <IAPlog> querAll();
	
	public void insertIAPlog(IAPlog iAPlog);
	
	public List <IAPlog> queryByUserId(long userId);
	
	public List <IAPlog> queryByDate(Map <String,String> dates);
	
}
