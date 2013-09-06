package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.ConfigurationDAOImpl;
import com.casinogod.pojo.Configuration;

public  class ConfigurationService implements ConfigurationSetting {
	
	private Configuration configuration;
	private ConfigurationDAOImpl configurationDAO;
	
	

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void setConfigurationDAO(ConfigurationDAOImpl configurationDAO) {
		this.configurationDAO = configurationDAO;
	}

	public List<Configuration> querAll() {
		// TODO Auto-generated method stub
		return configurationDAO.querAll();
	}

	public void insertConfiguration(int winTimes, int rankSize,int bossRewardNum,int invitedReward,int invitedItem) {
		// TODO Auto-generated method stub
        configuration.setWinTimes(winTimes);
        configuration.setRankSize(rankSize);
        configuration.setBossRewardNum(bossRewardNum);
        configuration.setInvitedReward(invitedReward);
        configuration.setInvitedItem(invitedItem);
        configurationDAO.insertConfiguration(configuration);
        
	}

	public List<Configuration> queryById(int id) {
		// TODO Auto-generated method stub
		return configurationDAO.queryById(id);
	}


	public boolean updateConfiguration(int winTimes, int rankSize, int id,
			int bossRewardNum,int invitedReward,int invitedItem) {
		// TODO Auto-generated method stub
		configuration.setId(id);
		configuration.setRankSize(rankSize);
		configuration.setWinTimes(winTimes);
		configuration.setBossRewardNum(bossRewardNum);
		configuration.setInvitedReward(invitedReward);
		configuration.setInvitedItem(invitedItem);
		return configurationDAO.updateConfiguration(configuration);
	}

}
