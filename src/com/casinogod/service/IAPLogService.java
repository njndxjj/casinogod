package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casinogod.dao.IAPLogDAOImpl;
import com.casinogod.pojo.IAPlog;

public class IAPLogService implements IAPPurchaseLog {
	
	private IAPlog iaPlog;
	
	private IAPLogDAOImpl iapLogDAO;
	

	public void setIaPlog(IAPlog iaPlog) {
		this.iaPlog = iaPlog;
	}

	public void setIapLogDAO(IAPLogDAOImpl iapLogDAO) {
		this.iapLogDAO = iapLogDAO;
	}

	public List<IAPlog> queryall() {
		// TODO Auto-generated method stub
		return iapLogDAO.querAll();
	}

	public boolean addIAPLog(long userId, String productId, int quantity,
			int money, int statue, String purchaseTime) {
		// TODO Auto-generated method stub
		iaPlog.setMoney(money);
		iaPlog.setProductId(productId);
		iaPlog.setPurchaseTime(purchaseTime);
		iaPlog.setQuantity(quantity);
		iaPlog.setStatue(statue);
		iaPlog.setUserId(userId);
		iapLogDAO.insertIAPlog(iaPlog);
		return true;
	}

	public List<IAPlog> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		return iapLogDAO.queryByUserId(userId);
	}

	public List<IAPlog> queryByDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		Map <String,String> dates=new HashMap<String, String>();
		dates.put("startDate", startDate);
		dates.put("endDate", endDate);
		return iapLogDAO.queryByDate(dates);
	}

}
