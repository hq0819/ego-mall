package com.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;
@Service
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService{
	@Autowired
	TbContentCategoryMapper tbContentCategoryMapper;

	public List<TbContentCategory> selByPid(long id) {
		
		TbContentCategoryExample ex = new TbContentCategoryExample();
		ex.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(ex);
		
		
		
		return list;
	}
	

	public int insTbContentCategory(TbContentCategory category) {
		return tbContentCategoryMapper.insertSelective(category);
	}
	

	public int updIsParentById(TbContentCategory category) {
		
	
		return tbContentCategoryMapper.updateByPrimaryKeySelective(category);
	}


	public int updByCategoryId(TbContentCategory category) {
			
		return tbContentCategoryMapper.updateByPrimaryKeySelective(category);
	}


	public TbContentCategory selById(long id) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}


	public int deleteById(long id) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.deleteByPrimaryKey(id);
	}

}
