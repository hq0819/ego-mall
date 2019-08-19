package com.ego.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;
import com.ego.service.TbContentCategoryService;

@Controller
public class TbContentCategoryController {
	@Autowired
	TbContentCategoryService tbContentCategoryServiceImpl;

	@RequestMapping("content/category/list")
	@ResponseBody	
	public List<EasyUITree> showCategory(@RequestParam(defaultValue="0")long id) {
		
		
		return tbContentCategoryServiceImpl.showCategory(id);
	
	}
	
	@RequestMapping("content/category/create")
	@ResponseBody
	public EgoResult create(TbContentCategory category) {
		
		return tbContentCategoryServiceImpl.create(category);
		
	}
	
	@RequestMapping("content/category/update")
	@ResponseBody
	public EgoResult update(TbContentCategory category) {
		
		return tbContentCategoryServiceImpl.updbySelect(category);
		
	}
	
	@RequestMapping("content/category/delete/")
	@ResponseBody
	public EgoResult delete(long id) {
		
		return tbContentCategoryServiceImpl.delete(id);
		
	}
	
}
