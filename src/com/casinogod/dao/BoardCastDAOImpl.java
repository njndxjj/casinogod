package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.BoardCastInfo;

public class BoardCastDAOImpl extends SqlMapClientDaoSupport implements BoardCastDAO {

	public List<BoardCastInfo> querAll() {
		// TODO Auto-generated method stub
		List <BoardCastInfo> list=null;
		list=this.getSqlMapClientTemplate().queryForList("boardCastInfo.findAll");
		return list;
	}

	public void insertItem(BoardCastInfo boardCastInfo) {
		// TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert("boardCastInfo.insertBoardCast", boardCastInfo);
	}

	public List<BoardCastInfo> queryByDate(BoardCastInfo boardCastInfo) {
		// TODO Auto-generated method stub
		List <BoardCastInfo> list=null;
		list=this.getSqlMapClientTemplate().queryForList("boardCastInfo.findbetweenDate",boardCastInfo);
		return list;
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("boardCastInfo.deleteById", id);
	}

}
