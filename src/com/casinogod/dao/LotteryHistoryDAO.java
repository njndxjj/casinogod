package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.Lottery;
import com.casinogod.pojo.LotteryHistory;

public interface LotteryHistoryDAO {
   
	public List <LotteryHistory> querAll();
	
	public void insertLottery(LotteryHistory lottery);
	
	public List <LotteryHistory> queryById(int id);
	
	public boolean updateLottery(LotteryHistory lotteryHistory);
	
	public boolean deleteLottery(int lotteryId);
	
	
}
