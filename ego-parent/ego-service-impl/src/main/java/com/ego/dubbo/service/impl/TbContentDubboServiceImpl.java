package com.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class TbContentDubboServiceImpl implements TbContentDubboService{
	@Autowired
	private TbContentMapper tbContentMapper;
	public EasyUIDataGrid showContent(int page, int rows, long categoryId) {
		PageHelper.startPage(page, rows);
		
		TbContentExample ex = new TbContentExample();
		ex.createCriteria().andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(ex);
		PageInfo info = new PageInfo(list);
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(info.getList());
		datagrid.setTotal(info.getTotal());
		
		
		
		return datagrid;
	}
	public int updContent(TbContent content) {
		// TODO Auto-generated method stub
		
		return tbContentMapper.updateByPrimaryKeyWithBLOBs(content);
	}
	
	/**
	 * 新增
	 */
	public int insContent(TbContent content) {
		return tbContentMapper.insert(content);
	}
	public int delContetn(String ids) {
		String[] idsStr = ids.split(",");
		int index=0;
		for (String id : idsStr) {
			index += tbContentMapper.deleteByPrimaryKey(Long.parseLong(id));
			
		}
		
		return index;
	}

}
