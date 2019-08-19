package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUITree;
import com.ego.pojo.TbItemCat;

public interface TbItemCatDubboService {

	List<EasyUITree> showTbItemcat(long id);
	
	/**
	 * 按照TbItemCat id查询
	 */
	TbItemCat selByIsd(long id);
	
	/**
	 * 递归实现查询类目
	 */
	
	List<TbItemCat> selById(long id);
	
}
