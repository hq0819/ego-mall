package com.ego.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.service.TbItemService;
@Service
public class TbItemServiceImpl implements TbItemService{
	@Reference
	 private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	@Value("${httpclint.url}")
	private String url;
	
	public EasyUIDataGrid show(int page, int rows) {
		// TODO Auto-generated method stub
		return tbItemDubboServiceImpl.show(page, rows);
	}

	public int updItemStatus(String ids,byte status) {
		int index = 0 ;
		TbItem item = new TbItem();
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			index +=tbItemDubboServiceImpl.updItemStatus(item);
		}
		if(index==idsStr.length){
			return 1;
		}
		return 0;
	}

	public int save(TbItem item, String desc) {
		/*
		 * long id = IDUtils.genItemId(); item.setId(id); Date date = new Date();
		 * item.setCreated(date); item.setUpdated(date); item.setStatus((byte)1); int
		 * index = tbItemDubboServiceImpl.insTbItem(item); if (index>0) { TbItemDesc
		 * itemDesc = new TbItemDesc(); itemDesc.setItemDesc(desc);
		 * itemDesc.setItemId(id); itemDesc.setCreated(date); itemDesc.setUpdated(date);
		 * index+= tbItemDescDubboServiceImpl.insDesc(itemDesc);
		 * 
		 * 
		 * } if(index==2) { return 1; }
		 * 
		 * return 0;
		 * 
		 */
		long id = IDUtils.genItemId();
		item.setId(id);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte)1);
		
		TbItemDesc itemDesc = new  TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		final TbItem itemfina = item;
		int index =0;
		
		try {
			index = tbItemDubboServiceImpl.insTbItemDesc(item, itemDesc);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		new Thread() {
			public void run() {
				Map<String,Object> map = new HashMap();
				map.put("item", itemfina);
				
				HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
				
			};
		}.start();
		
		return index;
		
	}

}
