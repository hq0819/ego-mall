package com.ego.manage.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentService;

@Controller
public class TbContentController {
	@Autowired
	TbContentService tbContentServiceImpl;
	
	
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGrid showContent(int page,int rows,long categoryId) {
		return tbContentServiceImpl.selContent(page, rows, categoryId);
		
	}
	
	
	@RequestMapping("rest/content/edit")
	@ResponseBody
	public EgoResult edit(TbContent content) {
		
		EgoResult er = new EgoResult();
		
			int index = tbContentServiceImpl.contentEdit(content);
			if(index==1) {
				er.setStatus(200);
			}
		return er;
	}
	
	@RequestMapping("content/save")
	@ResponseBody
	public EgoResult contentSave(TbContent content) {
		
		EgoResult er = new EgoResult();
		
			int index = tbContentServiceImpl.contentSave(content);
			if(index==1) {
				er.setStatus(200);
			}
		return er;
		
		
	}
	/**
	 * 删除
	 * @param content
	 * @return
	 */
	@RequestMapping("content/delete")
	@ResponseBody
	public EgoResult delete(String ids) {
		
		EgoResult er = new EgoResult();
		
			int index = tbContentServiceImpl.contentdelete(ids);
			if(index==1) {
				er.setStatus(200);
			}
		return er;
		
		
	}
	
	
	
	

}
