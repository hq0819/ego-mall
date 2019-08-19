package com.ego.item.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.item.service.TbItemCatService;
import com.ego.item.service.TbItemService;

@Controller
public class TbItemCatController {
	@Resource
	private TbItemCatService tbItemCatServiceImpl;
	@Autowired
	private TbItemService tbItemServiceImpl;
	
	/**
	 * 返回jsonp数据格式包含所有菜单信息
	 * @param callback
	 * @return
	 */
	@RequestMapping("rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue showMenu(String callback){
		MappingJacksonValue mjv = new MappingJacksonValue(tbItemCatServiceImpl.showCatMenu());
		mjv.setJsonpFunction(callback);
		return mjv;
	}
	
	/**
	 * 显示商品详情页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("item/{id}.html")
	public String itemDetial(@PathVariable long id,Model model) {
		
		model.addAttribute("item",tbItemServiceImpl.showItemDetailsById(id));
		return "item";
	}
	
	
	
}
