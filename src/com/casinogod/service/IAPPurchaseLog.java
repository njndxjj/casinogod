package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.IAPlog;
import com.casinogod.pojo.Item;


public interface IAPPurchaseLog {
	
	public List<IAPlog> queryall();
	
	public boolean addIAPLog(long userId,String productId, int quantity,
			int money, int statue,String purchaseTime);
	
	public List<IAPlog> queryByUserId(long userId);
	
	public List<IAPlog> queryByDate(String startDate,String endDate);
	
}
