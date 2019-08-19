package com.ego.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemDesc;
import com.ego.search.pojo.TbItemChild;
import com.ego.search.service.TbItemService;
@Service
public class TbItemServiceImpl implements TbItemService{
	@Reference
	TbItemCatDubboService tbItemCatDubboService;
	@Reference
	TbItemDescDubboService tbItemDescDubboService;
	@Reference
	TbItemDubboService tbItemDubboService;
	@Autowired
	HttpSolrClient httpSolrClient;

	public void init() {
		List<TbItem> item = tbItemDubboService.selAll((byte)1);
		System.out.println(item.size());
		
		
		for (TbItem tbItem : item) {
			TbItemCat cat = tbItemCatDubboService.selByIsd(tbItem.getCid());
			TbItemDesc desc = tbItemDescDubboService.selByItemId(tbItem.getId());
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", tbItem.getId());
			doc.addField("item_title", tbItem.getTitle());
			doc.addField("item_sell_point", tbItem.getSellPoint());
			doc.addField("item_price",tbItem.getPrice() );
			doc.addField("item_image", tbItem.getImage());
			doc.addField("item_category_name", cat.getName());
			doc.addField("item_desc", desc.getItemDesc());
			
			try {
				httpSolrClient.add(doc);
				httpSolrClient.commit();
			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
			
		}
		
		
		
	}

	public Map<String, Object> search(int rows, int page, String query) throws Exception, IOException {
		SolrQuery params = new SolrQuery();
		//设置分页条件
		params.setStart(rows*(page-1));
		params.setRows(rows);
		//设置条件
		params.setQuery("item_keywords:"+query);
		//设置高亮
		params.setHighlight(true);
		params.addHighlightField("item_title");
		params.setHighlightSimplePre("<span style='color:red'>");
		params.setHighlightSimplePost("</span>");
		
		QueryResponse response = httpSolrClient.query(params);
		
		List<TbItemChild> listChild = new ArrayList();
		//未高亮内容
		SolrDocumentList listSolr = response.getResults();
		//高亮内容
		Map<String, Map<String, List<String>>> hh = response.getHighlighting();
		
		for (SolrDocument doc : listSolr) {
			TbItemChild  child = new TbItemChild();
			
			child.setId(Long.parseLong(doc.getFieldValue("id").toString()));
			List<String> list = hh.get(doc.getFieldValue("id")).get("item_title");
			if(list!=null&&list.size()>0){
				child.setTitle(list.get(0));
			}else{
				child.setTitle(doc.getFieldValue("item_title").toString());
			}
			child.setPrice((Long)doc.getFieldValue("item_price"));
			Object image = doc.getFieldValue("item_image");
			child.setImages(image==null||image.equals("")?new String[1]:image.toString().split(","));
			child.setSellPoint(doc.getFieldValue("item_sell_point").toString());
			
			listChild.add(child);
		}
		
		
		Map<String,Object> resultMap = new HashMap();
		resultMap.put("itemList", listChild);
		resultMap.put("totalPages", listSolr.getNumFound()%rows==0?listSolr.getNumFound()/rows:listSolr.getNumFound()/rows+1);
		
		
		return resultMap;
	}

	public void add(Map<String, Object> map) {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", map.get("id"));
		doc.addField("item_title",map.get("title"));
		doc.addField("item_sell_point", map.get("sellPoint"));
		doc.addField("item_price",map.get("price"));
		doc.addField("item_image",map.get("image"));
		doc.addField("item_category_name",tbItemCatDubboService.selByIsd((Integer)map.get("cid")).getName());
		doc.addField("item_desc", tbItemDescDubboService.selByItemId((Long)map.get("id")).getItemDesc());
		
		try {
			UpdateResponse response = httpSolrClient.add(doc);
			httpSolrClient.commit();
			if(response.getStatus()!=0) {
				throw new Exception("新增失败");
			}
		} catch (Exception e) {
			
		}
		
	}

}
