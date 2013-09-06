package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.LotteryDAOImpl;
import com.casinogod.pojo.Lottery;

public class LotteryConfigService implements LotteryConfig {
	
	private Lottery lottery;
	
	private LotteryDAOImpl lotteryDAO;
	
	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public void setLotteryDAO(LotteryDAOImpl lotteryDAO) {
		this.lotteryDAO = lotteryDAO;
	}

	public List<Lottery> queryall() {
		// TODO Auto-generated method stub
		return lotteryDAO.querAll();
	}

	public boolean addLottery(long userId, int lotteryId,int lotteryType,
			String lotteryValue, int betAmount, boolean result,int level,int num,String betDateTime) {
		// TODO Auto-generated method stub
		boolean flag=false;
		lottery.setUserId(userId);
		lottery.setLotteryId(lotteryId);
		lottery.setLotteryType(lotteryType);
		lottery.setLotteryValue(lotteryValue);
		lottery.setBetAmount(betAmount);
		lottery.setResult(result);
		lottery.setLevel(level);
		lottery.setNum(num);
		lottery.setBetDateTime(betDateTime);
		lotteryDAO.insertLottery(lottery);
		flag=true;
		return flag;
	}

	public List<Lottery> queryById(int id) {
		// TODO Auto-generated method stub
		return lotteryDAO.queryById(id);
	}

	public List<Lottery> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		return lotteryDAO.queryByUserId(userId);
	}

	public List<Lottery> queryByUserIdAndId(long userId, int id) {
		// TODO Auto-generated method stub
		return lotteryDAO.queryByUserIdAndLottery(userId, id);
	}

	public List<Lottery> queryByUserIdAndLevel(int id, int level) {
		// TODO Auto-generated method stub
		return lotteryDAO.queryByUserIdAndLevel(id, level);
	}

	public boolean updateResult(int level, boolean result, long userId,
			int lotteryId) {
		// TODO Auto-generated method stub
		lottery.setLevel(level);
		lottery.setResult(result);
		lottery.setUserId(userId);
		lottery.setLotteryId(lotteryId);
		return lotteryDAO.updateResult(lottery);
	}

}
