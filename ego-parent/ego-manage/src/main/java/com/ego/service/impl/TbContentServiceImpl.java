package com.ego.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.jedis.JedisDao;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.FtpUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentService;

@Service
public class TbContentServiceImpl implements TbContentService{
	@Autowired
	JedisDao JedisDaoImpl;


	
	@Reference
	TbContentDubboService tbContentDubboServiceImpl;

	public EasyUIDataGrid selContent(int page, int rows, long categoryId) {
		// TODO Auto-generated method stub
		return tbContentDubboServiceImpl.showContent(page, rows, categoryId);
	}
	
	
	

	public int contentEdit(TbContent content) {
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		JedisDaoImpl.del("CONTENT_KEY");
		return tbContentDubboServiceImpl.updContent(content);
	}




	public int contentSave(TbContent content) {
		long id = IDUtils.genItemId();
		Date date = new Date();
		content.setId(id);
		content.setCreated(date);
		content.setUpdated(date);
		JedisDaoImpl.del("CONTENT_KEY");
		// TODO Auto-generated method stub
		return tbContentDubboServiceImpl.insContent(content);
	}




	public int contentdelete(String ids) {
		String[] str = ids.split(",");
		int index = tbContentDubboServiceImpl.delContetn(ids);
		if(index==str.length) {
			return 1;
		}
		JedisDaoImpl.del("CONTENT_KEY");
		return 0;
	}

}
