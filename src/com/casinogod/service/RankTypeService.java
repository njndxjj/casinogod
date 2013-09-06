package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.RankTypeDAOImpl;
import com.casinogod.pojo.RankType;

public class RankTypeService implements RankTypeInterface {
	
	private RankType rankType;
	
	private RankTypeDAOImpl rankTypeDAO;


	public void setRankType(RankType rankType) {
		this.rankType = rankType;
	}

	public void setRankTypeDAO(RankTypeDAOImpl rankTypeDAO) {
		this.rankTypeDAO = rankTypeDAO;
	}

	public boolean addRankType(String rankName, String rankDescription,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		rankType.setRankName(rankName);
		rankType.setRankDescription(rankDescription);
	    rankType.setStartTime(startTime);
	    rankType.setEndTime(endTime);
	    rankTypeDAO.insertRankType(rankType);
		return true;
	}

	public List<RankType> queryById(int typeId) {
		// TODO Auto-generated method stub
		return rankTypeDAO.queryById(typeId);
	}

	public List<RankType> queryAll() {
		// TODO Auto-generated method stub
		return rankTypeDAO.querAll();
	}

	public boolean updateRankType(RankType rankType) {
		// TODO Auto-generated method stub
		return rankTypeDAO.updateRankType(rankType);
	}

	public boolean deleteRankType(int typeId) {
		// TODO Auto-generated method stub
		return rankTypeDAO.deleteRankType(typeId);
	}

}
