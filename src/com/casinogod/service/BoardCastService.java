package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.BoardCastDAOImpl;
import com.casinogod.pojo.BoardCastInfo;

public class BoardCastService implements BoardCastConfig {
	
	private BoardCastInfo boardCastInfo;
	
	private BoardCastDAOImpl boardCastDAO;
	
	public void setBoardCastInfo(BoardCastInfo boardCastInfo) {
		this.boardCastInfo = boardCastInfo;
	}

	public void setBoardCastDAO(BoardCastDAOImpl boardCastDAO) {
		this.boardCastDAO = boardCastDAO;
	}

	public List<BoardCastInfo> queryall() {
		// TODO Auto-generated method stub
		return boardCastDAO.querAll();
	}

	public boolean addBoardCastInfo(String title, String content,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		boolean flag=false;
		boardCastInfo.setTitle(title);
		boardCastInfo.setContent(content);
		boardCastInfo.setStartDate(startDate);
		boardCastInfo.setEndDate(endDate);
		boardCastDAO.insertItem(boardCastInfo);
		flag=true;
		return flag;
	}

	public List<BoardCastInfo> queryBybetwend(String startDate, String endDate) {
		// TODO Auto-generated method stub
		boardCastInfo.setStartDate(startDate);
		boardCastInfo.setEndDate(endDate);
		return boardCastDAO.queryByDate(boardCastInfo);
	}

}
