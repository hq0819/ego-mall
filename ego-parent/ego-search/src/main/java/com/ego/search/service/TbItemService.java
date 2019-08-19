package com.ego.search.service;

import java.io.IOException;
import java.util.Map;

import com.ego.pojo.TbItem;

public interface TbItemService {
	void init();
	
	//搜索
	Map<String,Object> search(int rows,int page,String query) throws Exception, IOException;
	
	//商品新增是索引库进行同步
	
	
	void add(Map<String,Object> map);
}
