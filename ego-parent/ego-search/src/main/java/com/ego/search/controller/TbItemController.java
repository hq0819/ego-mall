package com.ego.search.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.search.service.TbItemService;

@Controller
public class TbItemController {
	
	@Autowired
	private TbItemService tbItemServiceImpl;
	
	
	@ResponseBody
	@RequestMapping(value="solr/init",produces="text/html;charset=utf-8")
	public String init() {
		long start = System.currentTimeMillis();
		tbItemServiceImpl.init();
		long end = System.currentTimeMillis();
		return ""+(end-start)/1000+"秒";
		
	}
	
	@RequestMapping("search.html")
	public String search(Model model,String q,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="12") int rows){
		try {
			q = new String(q.getBytes("iso-8859-1"),"utf-8");
			System.out.println(q);
			Map<String, Object> map = tbItemServiceImpl.search(rows, page, q);
			model.addAttribute("query", q);
			model.addAttribute("itemList", map.get("itemList"));
			model.addAttribute("totalPages", map.get("totalPages"));
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "search";
	}
	@RequestMapping("solr/add")
	public void solrAdd(@RequestBody Map<String,Object> map) {
		
		System.out.println(map.get("item"));
		tbItemServiceImpl.add((LinkedHashMap<String, Object>)map.get("item"));
		
	}
	

}
