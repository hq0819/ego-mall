package com.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{

	@Autowired
	TbItemParamMapper tbItemParamMapper;
	public EasyUIDataGrid ShowPage(int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		PageInfo<TbItemParam> info = new PageInfo(list);
		
		
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(info.getList());
		datagrid.setTotal(info.getTotal());
		
		
		
		return datagrid;
	}
	public int delByIds(String ids) throws Exception {
		
		String[] idsStr = ids.split(",");
		int index = 0;
		for (String id : idsStr) {
			index+= tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if(index==idsStr.length) {
			return 1;
		}else {
			throw new Exception("删除失败");
		}
		// TODO Auto-generated method stub
	}

}
