package com.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.jedis.JedisDao;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.portal.service.AdService;
@Service
public class AdServiceImpl implements AdService{
	@Reference
	TbContentDubboService tbContentDubboServiceImpl;
	@Autowired
	JedisDao JedisDaoImpl;
	
	public String showBigAd() {
		
		try {
			if(JedisDaoImpl.exists("CONTENT_KEY")) {
				String value = JedisDaoImpl.get("CONTENT_KEY");
				
				if(value!=null&&!value.equals("")) {
					return value;
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIDataGrid dataGrid = tbContentDubboServiceImpl.showContent(1, 6, 89);
		List<TbContent> list = (List<TbContent>) dataGrid.getRows();
		List<Map<String,Object>> listResult = new ArrayList();
		for (TbContent tc : list) {
			Map<String,Object> map = new HashMap();
						
						map.put("srcB", tc.getPic2());
						map.put("height", 240);
						map.put("alt", "图片");
						map.put("width", 670);
						map.put("src", tc.getPic());
						map.put("widthB", 550);
						map.put("href", tc.getUrl() );
						map.put("heightB", 240);
						listResult.add(map);
					}
		String listJson = JsonUtils.objectToJson(listResult);
		try {
			JedisDaoImpl.set("CONTENT_KEY", listJson);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(listJson);
		return listJson;
	}

}
