package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItem;
import com.ego.service.TbItemService;

@Controller
public class TbItemController {
	@Resource
	private TbItemService tbItemServiceImpl;

	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGrid show(int page, int rows) {
		return tbItemServiceImpl.show(page, rows);
	}

	@RequestMapping("rest/page/item-edit")
	public String showItemEdit() {
		return "item-edit";
	}

	@RequestMapping("rest/item/delete")
	@ResponseBody
	public EgoResult delete(String ids) {
		EgoResult er = new EgoResult();
		int index = tbItemServiceImpl.updItemStatus(ids, (byte) 3);
		if (index == 1) {
			er.setStatus(200);
		}
		return er;
	}

	@RequestMapping("rest/item/instock")
	@ResponseBody
	public EgoResult instock(String ids) {
		EgoResult er = new EgoResult();
		int index = tbItemServiceImpl.updItemStatus(ids, (byte) 2);
		if (index == 1) {
			er.setStatus(200);
		}
		return er;
	}

	@RequestMapping("rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelf(String ids) {
		EgoResult er = new EgoResult();
		int index = tbItemServiceImpl.updItemStatus(ids, (byte) 1);
		if (index == 1) {
			er.setStatus(200);
		}
		return er;
	}
	
	
	@RequestMapping("item/save")
	@ResponseBody
	public EgoResult insert(TbItem item,String desc) {
		EgoResult er = new EgoResult();
		
		int index = tbItemServiceImpl.save(item, desc);
		if(index==1) {
			er.setStatus(200);
			return er;
		}
		return er;
	}
	
	@RequestMapping("rest/item/update")
	@ResponseBody
	public EgoResult update(TbItem item,String desc) {
		EgoResult er = new EgoResult();
		
		int index = tbItemServiceImpl.save(item, desc);
		if(index==1) {
			er.setStatus(200);
			return er;
		}
		return er;
	}

}
