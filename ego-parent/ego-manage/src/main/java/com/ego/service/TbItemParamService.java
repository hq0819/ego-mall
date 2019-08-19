package com.ego.service;

import com.ego.commons.pojo.EasyUIDataGrid;

public interface TbItemParamService {
	EasyUIDataGrid showPage(int page,int rows);
	
	/**
	 * 批量删除
	 * @throws Exception 
	 */
	int delete(String ids) throws Exception;
	
}
