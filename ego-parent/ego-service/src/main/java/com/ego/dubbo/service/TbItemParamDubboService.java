package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;

public interface TbItemParamDubboService {
	/**
	 * 分页显示商品规格参数
	 */
	EasyUIDataGrid ShowPage(int page,int rows);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	int delByIds(String ids) throws Exception;
	
}
