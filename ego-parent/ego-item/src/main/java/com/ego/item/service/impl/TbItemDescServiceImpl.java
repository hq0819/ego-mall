package com.ego.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.jedis.JedisDao;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.item.service.TbItemDescService;
@Service
public class TbItemDescServiceImpl implements TbItemDescService{
	@Reference
	private TbItemDescDubboService tbItemDescDubboService;
	@Autowired
	private JedisDao jedisDaoImpl;
	@Value("${redis.desc.key}")
	private String descKey;
	
	public String showDesc(long id) {
		String key = descKey+id;
		
		if(jedisDaoImpl.exists(key)) {
			String json = jedisDaoImpl.get(key);
			if(json!=null&&!json.equals("")) {
				return json;
			}
		}
		
		String itemDesc = tbItemDescDubboService.selByItemId(id).getItemDesc();
		jedisDaoImpl.set(key, itemDesc);
		
		return itemDesc;
	}

}
