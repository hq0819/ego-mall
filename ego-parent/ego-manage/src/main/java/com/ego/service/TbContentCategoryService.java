package com.ego.service;

import java.util.List;

import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryService {
	List<EasyUITree> showCategory(long id);
	
	/**
	 * 新增
	 * @param category
	 * @return
	 */
	EgoResult create(TbContentCategory category) ;
	
	/**
	 * 修改
	 * @param category
	 * @return
	 */
	EgoResult updbySelect(TbContentCategory category);
	
	/**
	 *删除类目
	 * @param category
	 * @return
	 */
	EgoResult delete(long id);

}
