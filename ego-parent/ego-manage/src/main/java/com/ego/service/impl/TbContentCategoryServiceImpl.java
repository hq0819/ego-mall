package com.ego.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.pojo.TbContentCategory;
import com.ego.service.TbContentCategoryService;
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{
	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboServiImpl;
	
	public List<EasyUITree> showCategory(long id) {
		List<TbContentCategory> list = tbContentCategoryDubboServiImpl.selByPid(id);

		List<EasyUITree> node = new ArrayList<EasyUITree>();
		for (TbContentCategory category : list) {
			EasyUITree tree = new EasyUITree();
			tree.setId(category.getId());
			tree.setText(category.getName());
			tree.setState(category.getIsParent()?"closed":"open");
			node.add(tree);
			
			
		}

		return node;
	}

	public EgoResult create(TbContentCategory category) {
		EgoResult er = new EgoResult();
		List<TbContentCategory> list = tbContentCategoryDubboServiImpl.selByPid(category.getParentId());
		for (TbContentCategory cate : list) {
			if(category.getName().equals(cate.getName())) {
				return er;
			}
		}
		
		long id = IDUtils.genItemId();
		
		
		Date date = new Date();
		category.setCreated(date);
		category.setUpdated(date);
		category.setSortOrder(1);
		category.setStatus(1);
		category.setIsParent(false);
		category.setId(id);
		int index = tbContentCategoryDubboServiImpl.insTbContentCategory(category);
		if(index>0) {
			
			TbContentCategory parent = new TbContentCategory();
			parent.setId(category.getParentId());
			parent.setIsParent(true);
			tbContentCategoryDubboServiImpl.updIsParentById(parent);
		}
		er.setStatus(200);
		Map<String,Long> data = new HashMap();
		data.put("id",id);
		er.setData(data);
		
		return er;
	}

	public EgoResult updbySelect(TbContentCategory category) {
		EgoResult er = new EgoResult();
		
		TbContentCategory cate = tbContentCategoryDubboServiImpl.selById(category.getId());
		
		List<TbContentCategory> list = tbContentCategoryDubboServiImpl.selByPid(cate.getParentId());
		for (TbContentCategory c : list) {
			if(category.getName().equals(c.getName())) {
				er.setData("该分类已经存在");
				return er;
				
			}
			
		}
		
		int index = tbContentCategoryDubboServiImpl.updByCategoryId(category);
		if(index>0) {
			er.setStatus(200);
		}
		return er;
	}

	//删除
	public EgoResult delete(long id) {
		EgoResult er = new EgoResult();
		TbContentCategory category = tbContentCategoryDubboServiImpl.selById(id);
		int index = tbContentCategoryDubboServiImpl.deleteById(id);
		if(index>0) {
			
			List<TbContentCategory> list = tbContentCategoryDubboServiImpl.selByPid(category.getParentId());
			if(list.isEmpty()) {
				TbContentCategory parent1 = new TbContentCategory();
				parent1.setIsParent(false);
				parent1.setId(category.getParentId());
				tbContentCategoryDubboServiImpl.updIsParentById(parent1);
				
			}
			er.setStatus(200);
		}
		
		
		
		return er;
	}

}
