package com.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemDubboServiceImpl implements TbItemDubboService{
	@Autowired
	TbItemMapper tbItemMapper;
	@Autowired
	TbItemDescMapper tbItemDescMapper;

	public EasyUIDataGrid show(int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		TbItemExample ex = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(ex);
		PageInfo<TbItem> info = new PageInfo(list);
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(info.getList());
		datagrid.setTotal(info.getTotal());
		
		return datagrid;
	}

	public int updItemStatus(TbItem tbItem) {
		// TODO Auto-generated method stub
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	public int insTbItem(TbItem tbItem) {
		// TODO Auto-generated method stub
		return  tbItemMapper.insert(tbItem);
	}

	public int insTbItemDesc(TbItem tbItem, TbItemDesc desc) throws Exception {
		// TODO Auto-generated method stub
		int index = 0;
		try {
			index = tbItemMapper.insertSelective(tbItem);
			index += tbItemDescMapper.insertSelective(desc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(index==2) {
			return 1;
		}else {
			throw new Exception("新增失败！");
		}
		
	}

	public List<TbItem> selAll(byte status) {
		TbItemExample ex = new TbItemExample();
		ex.createCriteria().andStatusEqualTo((byte) status);
		return tbItemMapper.selectByExample(ex);
	
	}

	public TbItem selById(long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}

}
