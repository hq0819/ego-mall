package com.ego.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.pojo.TbItemParamChildren;
import com.ego.pojo.TbItemParam;
import com.ego.service.TbItemParamService;
@Service
public class TbItemParamServiceImpl implements TbItemParamService{
	@Reference
	private TbItemParamDubboService tbItemParamDubboServiceImpl;
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	public EasyUIDataGrid showPage(int page, int rows) {
		// TODO Auto-generated method stub
		EasyUIDataGrid easyUIDataGrid = tbItemParamDubboServiceImpl.ShowPage(page, rows);
		
		List<TbItemParam> list = (List<TbItemParam>) easyUIDataGrid.getRows();
		List<TbItemParamChildren> child = new ArrayList<TbItemParamChildren>();
		for (TbItemParam tb : list) {
			TbItemParamChildren param = new TbItemParamChildren();
			
			param.setCreated(tb.getCreated());
			param.setId(tb.getId());
			param.setItemCatId(tb.getItemCatId());
			param.setUpdated(tb.getUpdated());
			param.setParamData(tb.getParamData());
			param.setItemCatName(tbItemCatDubboServiceImpl.selByIsd(param.getItemCatId()).getName());
			
			child.add(param);
			
		}
		
		easyUIDataGrid.setRows(child);
		
		return easyUIDataGrid;
		
		
	}
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return tbItemParamDubboServiceImpl.delByIds(ids);
	}

}
