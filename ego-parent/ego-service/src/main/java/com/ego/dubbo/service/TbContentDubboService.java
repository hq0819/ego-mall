package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentDubboService {
	EasyUIDataGrid showContent(int page,int rows,long categoryId);
	/**
	 * 修改内容
	 * @param content
	 * @return
	 */
	int updContent(TbContent content);
	/**
	 * 新增
	 * @param content
	 * @return
	 */
	int insContent(TbContent content);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	int delContetn(String ids);
	
	

}
