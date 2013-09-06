package com.casinogod.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.User;

public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO {

	public List <User> querUserName(String name) {
		// TODO Auto-generated method stub
		List list=null;
		list=this.getSqlMapClientTemplate().queryForList("user.getUserByName",name);
        return list;
	}


	public List<User> querAllUser() {
		// TODO Auto-generated method stub
		List<User> list = null;  
	    list = getSqlMapClientTemplate().queryForList("user.findAllUser");  
	    return list;
	}

	

	public boolean deleteUser(long userId) {
		// TODO Auto-generated method stub
		
		int deletedCount = 0;  
        
		deletedCount = getSqlMapClientTemplate().delete("user.delete",userId);  
          
        return deletedCount>0?true:false; 
	}

	public void insertUser(User user) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("user.insertUser",user); 
		
	}


	public boolean updateUserGold(User user) {
		// TODO Auto-generated method stub
		int updatedCount = 0;    
        updatedCount = getSqlMapClientTemplate().update("user.updateGold",user);  
        System.out.println("updatedCount=>"+updatedCount);   
        return updatedCount>0?true:false; 
	}

	public List <User> quserUserId(long id) {
		// TODO Auto-generated method stub
		
		List <User> user=null;
		
		user=this.getSqlMapClientTemplate().queryForList("user.getUserById",id);
	    
		return user;
		
	}

	public boolean updateUserExp(User user) {
		// TODO Auto-generated method stub
		int updatedCount = 0;    
        updatedCount = getSqlMapClientTemplate().update("user.updateExp",user);  
        System.out.println("updatedCount=>"+updatedCount);   
        return updatedCount>0?true:false;
	}


	public boolean updateUserFriends(User user) {
		// TODO Auto-generated method stub
		int updatedCount = 0;    
        updatedCount = getSqlMapClientTemplate().update("user.updateFriend",user);  
        System.out.println("updatedCount=>"+updatedCount);   
        return updatedCount>0?true:false; 
	}


	public boolean updateUserProfile(long userId,String nickName, String gender,
			String emailAddress, String telephone, String image) {
		// TODO Auto-generated method stub
		int updatedCount=0;
		
		HashMap para=new HashMap();
		para.put("nickName", nickName);
		para.put("gender", gender);
		para.put("emailAddress", emailAddress);
		para.put("telephone", telephone);
		para.put("image", image);
		
		para.put("userId", userId);
		
		updatedCount = getSqlMapClientTemplate().update("user.updateProfile",para);  
        System.out.println("updatedCount=>"+updatedCount);   
        return updatedCount>0?true:false; 
	}




	


}
