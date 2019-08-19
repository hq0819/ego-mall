package com.ego.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUITree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.service.TbItemCatService;
@Service
public class TbItemCatServiceImpl implements TbItemCatService{
	@Reference
	TbItemCatDubboService tbItemCatDubboServiceImpl;
	public List<EasyUITree> showTbItemCat(long id) {
		// TODO Auto-generated method stub
		return tbItemCatDubboServiceImpl.showTbItemcat(id);
	}

}
