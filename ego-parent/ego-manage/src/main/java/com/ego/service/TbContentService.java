package com.ego.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentService {
	EasyUIDataGrid selContent(int page,int rows,long categoryId);
	
	int contentEdit(TbContent content);
	
	
	int contentSave(TbContent content);
	
	int contentdelete(String ids);

}
