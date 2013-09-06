package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.Configuration;
import com.casinogod.pojo.Item;

public interface ConfigurationDAO {
   
	public List <Configuration> querAll();
	
	public void insertConfiguration(Configuration configuration);
	
	public List <Configuration> queryById(int id);
	
	public boolean updateConfiguration(Configuration configuration);
	
	
	
}
