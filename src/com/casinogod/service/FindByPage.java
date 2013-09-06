package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.PageModel;


public interface FindByPage {
	
	public PageModel findAllByList(List list,int offset, int pagesize);

}
