package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.UserRankDAOImpl;
import com.casinogod.pojo.RankUserInfo;

public class RankUserService implements RankUserInterface {
	
	private RankUserInfo rankUserInfo;
	
	private UserRankDAOImpl userRankDAO;
		

	public void setRankUserInfo(RankUserInfo rankUserInfo) {
		this.rankUserInfo = rankUserInfo;
	}

	public void setUserRankDAO(UserRankDAOImpl userRankDAO) {
		this.userRankDAO = userRankDAO;
	}

	public boolean addRankUser(int userId, int typeId, int rankValue,
			String updateTime,int winTime,int loseTime,int drawTime,int totalMoney,int winMoney,int loseMoney) {
		// TODO Auto-generated method stub
		rankUserInfo.setRankValue(rankValue);
		rankUserInfo.setTypeId(typeId);
		rankUserInfo.setUpdateTime(updateTime);
		rankUserInfo.setUserId(userId);
		rankUserInfo.setDrawTime(drawTime);
		rankUserInfo.setLoseTime(loseTime);
		rankUserInfo.setWinTime(winTime);
		rankUserInfo.setTotalMoney(totalMoney);
		rankUserInfo.setWinMoney(winMoney);
		rankUserInfo.setLoseMoney(loseMoney);
		userRankDAO.insertUserRank(rankUserInfo);
		return true;
	}

	public List<RankUserInfo> queryByType(int typeId,String column) {
		// TODO Auto-generated method stub
		return userRankDAO.queryByType(typeId,column);
	}

	public boolean updateRankType(RankUserInfo rankUserInfo) {
		// TODO Auto-generated method stub
		return userRankDAO.updateRank(rankUserInfo);
	}

	public List<RankUserInfo> queryByTypeUser(int typeId, int userId) {
		// TODO Auto-generated method stub
		return userRankDAO.queryByTypeUser(typeId, userId);
	}

}
