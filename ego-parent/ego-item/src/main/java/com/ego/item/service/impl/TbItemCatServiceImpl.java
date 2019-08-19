package com.ego.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.jedis.JedisDao;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.item.pojo.PortalMenu;
import com.ego.item.pojo.PortalMenuNode;
import com.ego.item.service.TbItemCatService;
import com.ego.pojo.TbItemCat;
@Service
public class TbItemCatServiceImpl implements TbItemCatService{
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Autowired
	JedisDao JedisDaoImpl;
	
	public PortalMenu showCatMenu() {	
		if(JedisDaoImpl.exists("item_key")) {
			
			String item = JedisDaoImpl.get("item_key");
			if(item!=null&&!item.equals("")) {
				List<Object> list = JsonUtils.jsonToList(item, Object.class);
				PortalMenu pl = new PortalMenu();
				pl.setData(list);
				return pl;
				
			}
		}
		
		
		
		List<TbItemCat> items = tbItemCatDubboServiceImpl.selById(0);
		PortalMenu pm = new PortalMenu();
		pm.setData(sellAllMenu(items));
		
		System.out.println(pm.getData().toString());
		
		return pm;
	}
	public List<Object> sellAllMenu(List<TbItemCat> items) {

		List<Object> listNode = new ArrayList();
		for (TbItemCat tbItemCat : items) {
			if(tbItemCat.getIsParent()){
				PortalMenuNode pmd  = new PortalMenuNode();
				pmd.setU("/products/"+tbItemCat.getId()+".html");
				pmd.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				pmd.setI(sellAllMenu(tbItemCatDubboServiceImpl.selById(tbItemCat.getId())));
				listNode.add(pmd);
				
			}else{
				listNode.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		
		JedisDaoImpl.set("item_key", JsonUtils.objectToJson(listNode));
		return listNode;
	}

}
