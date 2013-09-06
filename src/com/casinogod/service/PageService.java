package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.PageModel;

public class PageService implements FindByPage {

	public PageModel findAllByList(List list, int offset, int pagesize) {
		// TODO Auto-generated method stub
		int total = list.size();
		List datas;
		if(offset+pagesize>total)
			datas=list.subList(offset, total);
		else
			datas = list.subList(offset, offset+pagesize);
		
		PageModel pm = new PageModel();
		pm.setTotal(total);
		pm.setDatas(datas);
		
		return pm;
	}

}
