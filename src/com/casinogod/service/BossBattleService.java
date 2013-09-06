package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.views.xslt.MapAdapter;

import com.casinogod.dao.BossBattleInfoDAOImpl;
import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.pojo.BossConfig;

public class BossBattleService implements BossBattle {
	
	private BossBattleInfo bossBattleInfo;
	private BossBattleInfoDAOImpl bossBattleInfoDAO;
	
	
	public void setBossBattleInfo(BossBattleInfo bossBattleInfo) {
		this.bossBattleInfo = bossBattleInfo;
	}

	public void setBossBattleInfoDAO(BossBattleInfoDAOImpl bossBattleInfoDAO) {
		this.bossBattleInfoDAO = bossBattleInfoDAO;
	}

	public List<BossBattleInfo> queryall() {
		// TODO Auto-generated method stub
		return bossBattleInfoDAO.querAll();
	}

	public boolean addBossBossBattleInfo(BossBattleInfo bossBattleInfo) {
		// TODO Auto-generated method stub
		bossBattleInfoDAO.insertBossBattleInfo(bossBattleInfo);
		return true;
	}

	public List<BossBattleInfo> queryByBattle(int bossType, int bossInstance) {
		// TODO Auto-generated method stub
		Map<Object,Object> ids = new HashMap<Object, Object>();
		ids.put("bossType", bossType);
		ids.put("bossInstance", bossInstance);
		return bossBattleInfoDAO.queryByBattle(ids);
	}

	public List<BossBattleInfo> queryByInstance(int bossInstance) {
		// TODO Auto-generated method stub
		return bossBattleInfoDAO.queryByInstance(bossInstance);
	}

	public boolean updateBossInfo(BossBattleInfo bossBattleInfo) {
		// TODO Auto-generated method stub
		return bossBattleInfoDAO.updateBossBattleInfo(bossBattleInfo);
	}

}
