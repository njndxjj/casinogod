package com.casinogod.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.UserAccount;

public class UserAccountDAOImpl extends SqlMapClientDaoSupport implements UserAccountDAO {

	public List<UserAccount> quserUserId(long account)  {
		// TODO Auto-generated method stub		
		List list=null;
		list=this.getSqlMapClientTemplate().queryForList("userAccout.getAccount", account);
		return list;
	}

	public List<UserAccount> querAllUser() {
		// TODO Auto-generated method stub
		List list=null;
		list=this.getSqlMapClientTemplate().queryForList("userAccout.findAllAccount");
		return list;
	}

	public void insertAccount(UserAccount userAccount) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("userAccout.insertAccount", userAccount);
	}

	public boolean updatePassword(UserAccount userAccount) {
		// TODO Auto-generated method stub
		int updateAccount=0;
		updateAccount=this.getSqlMapClientTemplate().update("userAccout.updatepassword", userAccount);
		System.out.println("updateAccount-->"+updateAccount);
		return updateAccount>0?true:false;
	}

	public List<UserAccount> quserUserSNS(String userType, String snsId) {
		// TODO Auto-generated method stub
	    HashMap<String, String> para=new HashMap<String, String>();
	    
	    para.put("userType", userType);
	    para.put("snsId", snsId);
//	    para.put("snsToken", snsToken);
	    List list=null;
		list=this.getSqlMapClientTemplate().queryForList("userAccout.getSNSAccount", para);
		return list;
	}
	
}
