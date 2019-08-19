package com.ego.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;

public interface TbItemService {
		EasyUIDataGrid show(int page,int rows);
		
		 int updItemStatus(String ids,byte status);
		
		/**
		 * 商品新增
		 */
		 int save(TbItem item,String desc);
		 

}
