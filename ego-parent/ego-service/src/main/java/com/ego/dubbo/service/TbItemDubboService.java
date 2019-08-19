package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

public interface TbItemDubboService {
	//��Ʒ��ҳ��ѯ
	EasyUIDataGrid show(int page,int rows);
	/**
	 * ����id�޸�״̬
	 */
	int updItemStatus(TbItem tbItem);
	//商品新增
	int insTbItem(TbItem tbItem);
	
	
	/**
	 * 新增包含商品表和描述表
	 * @param tbItemSS
	 * @param desc
	 * @return
	 * @throws Exception 
	 */
	int insTbItemDesc(TbItem tbItem,TbItemDesc desc) throws Exception;
	
	/**
	 * 查询全部
	 */
	List<TbItem> selAll(byte status);
	
	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
	TbItem selById(long id);
}
