package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.LotteryHistoryDAOImpl;
import com.casinogod.pojo.LotteryHistory;

public class LotteryHistoryService implements LotteryHistoryConfig {
	
	private LotteryHistory lotteryHistory;
	
	private LotteryHistoryDAOImpl lotteryHistoryDAO;
	

	public void setLotteryHistory(LotteryHistory lotteryHistory) {
		this.lotteryHistory = lotteryHistory;
	}

	public void setLotteryHistoryDAO(LotteryHistoryDAOImpl lotteryHistoryDAO) {
		this.lotteryHistoryDAO = lotteryHistoryDAO;
	}

	public List<LotteryHistory> queryall() {
		// TODO Auto-generated method stub
		return lotteryHistoryDAO.querAll();
	}

	public boolean addLottery(int lotteryType, 
			String openDateTime,String result) {
		// TODO Auto-generated method stub
		boolean flag=false;
		lotteryHistory.setLotteryType(lotteryType);
		lotteryHistory.setOpenDateTime(openDateTime);
		lotteryHistory.setResult(result);
		lotteryHistoryDAO.insertLottery(lotteryHistory);
		flag=true;
		return flag;
	}

	public List<LotteryHistory> queryById(int id) {
		// TODO Auto-generated method stub
		return lotteryHistoryDAO.queryById(id);
	}

	public boolean updateLottery(LotteryHistory lotteryHistory) {
		// TODO Auto-generated method stub
		return lotteryHistoryDAO.updateLottery(lotteryHistory);
	}

	public boolean deleteLottery(int lotteryId) {
		// TODO Auto-generated method stub
		return lotteryHistoryDAO.deleteLottery(lotteryId);
	}

	

}
