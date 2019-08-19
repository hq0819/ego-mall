package com.ego.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.jedis.JedisDao;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.item.service.TbItemService;
import com.ego.pojo.TbItem;
@Service
public class TbItemServiceImpl implements TbItemService{
	@Reference
	TbItemDubboService tbItemDubboserviceImpl;
	@Autowired
	private JedisDao jedisDaoImpl;
	@Value("${redis.item.key}")
	private String itemKey;
	public TbItem showItemDetailsById(long id) {
		String key = itemKey+id;
		if(jedisDaoImpl.exists(key)){
			String json = jedisDaoImpl.get(key);
			if(json!=null&&!json.equals("")){
				return JsonUtils.jsonToPojo(json, TbItemChild.class);
			}
		}
		
		TbItem item = tbItemDubboserviceImpl.selById(id);
		
		TbItemChild child = new TbItemChild();
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setPrice(item.getPrice());
		child.setSellPoint(item.getSellPoint());
		child.setImages(item.getImage()!=null&&!item.equals("")?item.getImage().split(","):new String[1]);
		jedisDaoImpl.set("key", JsonUtils.objectToJson(child));
		
		return child;
	}

}
