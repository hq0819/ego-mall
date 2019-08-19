package com.ego.portal.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.portal.service.AdService;

@Controller
public class TbContentController {
	@Autowired
	private AdService AdServiceImpl;
	
	@RequestMapping("showBigPic")
	public String showBigPic(Model model){
		model.addAttribute("ad1", AdServiceImpl.showBigAd());
		
		return "index";
	}
}
