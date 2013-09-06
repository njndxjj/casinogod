package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.UserAccountDAOImpl;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserAccount;
import com.casinogod.utility.MD5Util;

public class LoginService implements UserLogIn {
	
	private UserAccountDAOImpl userAccountDAO;
	
	private UserAccount useraccount;
	

	public UserAccountDAOImpl getUserAccountDao() {
		return userAccountDAO;
	}

	public void setUserAccountDAO(UserAccountDAOImpl userAccountDao) {
		this.userAccountDAO = userAccountDao;
	}

	public UserAccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(UserAccount useraccount) {
		this.useraccount = useraccount;
	}

	public User login(long userAccount, String password) {
		// TODO Auto-generated method stub
	 
	  useraccount.setAccount(userAccount);
	  useraccount.setPassword(password);
      
      List<UserAccount> list = userAccountDAO.quserUserId(useraccount.getAccount());
      
      for(int i=0;i<list.size();i++)  
      {  
          UserAccount account=list.get(i); 
          if(account.getPassword().equals(password))  
          {
        	 return account.getUser();            
          }
      }  
      return null;  
	}
	
	public boolean isFreeze(long userAccount)
	{
		List<UserAccount> list = userAccountDAO.quserUserId(userAccount);
		
		for(int i=0;i<list.size();i++)  
	    {  
			UserAccount account=list.get(i); 
			if(account.getFreeze()==0)  
	        {
	           return true;            
	        }
	     }  
		return false;
	}

	public boolean updatePassword(long account, String password) {
		// TODO Auto-generated method stub
		useraccount.setAccount(account);
		useraccount.setPassword(MD5Util.string2MD5(password));
		return userAccountDAO.updatePassword(useraccount);
	}

	public List<UserAccount> findAll() {
		// TODO Auto-generated method stub
		return userAccountDAO.querAllUser();
	}

	public User logInSNS(String userType, String snsId) {
		// TODO Auto-generated method stub
		  
		useraccount.setUserType(userType);
		  useraccount.setSnsId(snsId);
//		  useraccount.setSnsToken(snsToken);
	      
	      List<UserAccount> list = userAccountDAO.quserUserSNS(useraccount.getUserType(), useraccount.getSnsId());
	      
	      for(int i=0;i<list.size();i++)  
	      {  
	          UserAccount account=list.get(i); 
	          if(account.getSnsId().equals(snsId))  
	          {
	        	 return account.getUser();            
	          }
	      }  
	      return null;  
	}

	public UserAccount getAccount(long userAccount) {
		// TODO Auto-generated method stub
		
		List<UserAccount> list = userAccountDAO.quserUserId(userAccount);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}

}
