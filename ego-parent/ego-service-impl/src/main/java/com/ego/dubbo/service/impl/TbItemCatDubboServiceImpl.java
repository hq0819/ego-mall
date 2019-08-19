package com.ego.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ego.commons.pojo.EasyUITree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.mapper.TbItemCatMapper;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;
import com.ego.pojo.TbItemCatExample.Criteria;

public class TbItemCatDubboServiceImpl implements TbItemCatDubboService{
	@Autowired
	TbItemCatMapper itemCatMapper;
	public List<EasyUITree> showTbItemcat(long id) {
		// TODO Auto-generated method stub
		TbItemCatExample ex = new TbItemCatExample();
		Criteria criteria = ex.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbItemCat> cats = itemCatMapper.selectByExample(ex);
		List<EasyUITree> list = new ArrayList<EasyUITree>();
		for (TbItemCat cat : cats) {
			EasyUITree node = new EasyUITree();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			list.add(node);
		}
		return list;
	}
	public TbItemCat selByIsd(long id) {
		// TODO Auto-generated method stub
		return itemCatMapper.selectByPrimaryKey(id);
	}
	
	
	
	
	public List<TbItemCat> selById(long id) {
		TbItemCatExample ex = new TbItemCatExample();
		Criteria criteria = ex.createCriteria();
		criteria.andParentIdEqualTo(id);
		 
		List<TbItemCat> items = itemCatMapper.selectByExample(ex);
	
		
		
		return items;
	}

	
}
