package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.FriendRequest;

public class RequestDAOImpl extends SqlMapClientDaoSupport implements RequestDAO {

	public List<FriendRequest> querAll() {
		// TODO Auto-generated method stub
		List <FriendRequest> list=null;
		list=this.getSqlMapClientTemplate().queryForList("friendRequest.findAllRequest");
		return list;
	}

	public void insertItem(FriendRequest friendRequest) {
		// TODO Auto-generated method stub		
		this.getSqlMapClientTemplate().insert("friendRequest.insertRequest", friendRequest);
	}

	public List<FriendRequest> queryByOwenId(long owenId) {
		// TODO Auto-generated method stub
		List <FriendRequest> list=null;
		list=this.getSqlMapClientTemplate().queryForList("friendRequest.findRequestByOwen",owenId);
		return list;
	}

	public List<FriendRequest> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		List <FriendRequest> list=null;
		list=this.getSqlMapClientTemplate().queryForList("friendRequest.findRequestByUser",userId);
		return list;
	}

	public List<String> queryByStatue(FriendRequest friendRequest) {
		// TODO Auto-generated method stub
		List <String> list=null;
		list=this.getSqlMapClientTemplate().queryForList("friendRequest.findRequestByStatue",friendRequest);
		return list;
	}

	public boolean updateStatue(FriendRequest friendRequest) {
		// TODO Auto-generated method stub
		int updatedCount = 0;    
        updatedCount = getSqlMapClientTemplate().update("friendRequest.updateStatue",friendRequest);  
        System.out.println("updatedCount=>"+updatedCount);   
        return updatedCount>0?true:false; 
	}

	public boolean deleteRequest(Map map) {
		// TODO Auto-generated method stub
		int deleteCount = 0;    
		deleteCount = getSqlMapClientTemplate().update("friendRequest.deleteRequest",map);  
        System.out.println("updatedCount=>"+deleteCount);   
        return deleteCount>0?true:false;
	}

}
