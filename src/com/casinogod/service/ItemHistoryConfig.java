package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.ItemHistory;


public interface ItemHistoryConfig {
	
	public List<ItemHistory> queryall();
	
	public boolean addItemUser(int gameType,long owenId,int purchaseType,String itemName,int itemNum,
			long giftUserID, String purchaseTime, String otherNotes);
	
	public List<ItemHistory> queryAllUser(long userId);

	
}
