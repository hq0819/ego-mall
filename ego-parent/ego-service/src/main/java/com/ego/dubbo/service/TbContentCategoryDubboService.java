package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	/**
	 * 根据父id查询子类木
	 * @param id
	 * @return
	 */
	
	List<TbContentCategory> selByPid(long id);
	
	/**
	 * 根据id查询分类
	 * @param id
	 * @return
	 */
	TbContentCategory selById(long id);
	
	/**
	 * 新增
	 * @param category
	 * @return
	 */
	int insTbContentCategory(TbContentCategory category);
	
	/**
	 * 修改isparent属性
	 * @param id
	 * @param isParent
	 * @return
	 */
	int updIsParentById(TbContentCategory category);
	
	/**
	 * @param category
	 * @return
	 */
	int updByCategoryId(TbContentCategory category);
	
	int deleteById(long id);
	

}
