package com.ego.dubbo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;
@Service
public class TbItemDescDubboServiceImpl implements TbItemDescDubboService{
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	public int insDesc(TbItemDesc itemDesc) {
		// TODO Auto-generated method stub
		return tbItemDescMapper.insert(itemDesc);
	}

	public TbItemDesc selByItemId(long id) {
		// TODO Auto-generated method stub
		return tbItemDescMapper.selectByPrimaryKey(id);
	}

}
