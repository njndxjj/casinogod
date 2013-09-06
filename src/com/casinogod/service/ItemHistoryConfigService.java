package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.ItemHistoryDAOImpl;
import com.casinogod.pojo.ItemHistory;

public class ItemHistoryConfigService implements ItemHistoryConfig {
	
	private ItemHistoryDAOImpl itemHistoryDAO;
	
	private ItemHistory itemHistory;
	
	

	public void setItemHistoryDAO(ItemHistoryDAOImpl itemHistoryDAO) {
		this.itemHistoryDAO = itemHistoryDAO;
	}

	public void setItemHistory(ItemHistory itemHistory) {
		this.itemHistory = itemHistory;
	}

	public List<ItemHistory> queryall() {
		// TODO Auto-generated method stub
		List <ItemHistory> list=null;
		list=itemHistoryDAO.querAll();
		return list;
	}

	public boolean addItemUser(int gameType,long owenId,int purchaseType,String itemName,int itemNum,
			long giftUserID, String purchaseTime, String otherNotes) {
		// TODO Auto-generated method stub
		itemHistory.setGameType(gameType);
		itemHistory.setOwenId(owenId);
		itemHistory.setPurchaseTime(purchaseTime);
		itemHistory.setPurchaseType(purchaseType);
		itemHistory.setItemName(itemName);
		itemHistory.setItemNum(itemNum);
		itemHistory.setGiftUserId(giftUserID);
		itemHistory.setOtherNotes(otherNotes);
		itemHistoryDAO.insertitemHistory(itemHistory);
		
		return true;
	}

	public List<ItemHistory> queryAllUser(long userId) {
		// TODO Auto-generated method stub
		List <ItemHistory> list=null;
		list=itemHistoryDAO.querAllUser(userId);
		return list;
	}

}
