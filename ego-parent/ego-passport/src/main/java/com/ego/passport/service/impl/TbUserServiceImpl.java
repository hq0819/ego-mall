package com.ego.passport.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.jedis.JedisDao;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;

@Service
public class TbUserServiceImpl implements TbUserService{
	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;
	@Autowired
	private JedisDao jedisDaoImpl;

	public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
		TbUser tbUser = tbUserDubboServiceImpl.login(user);
		EgoResult er  = new EgoResult();
		if(tbUser!=null&&!tbUser.equals("")) {
			er.setStatus(200);
			String key = UUID.randomUUID().toString();
			jedisDaoImpl.set(key, JsonUtils.objectToJson(tbUser));
			jedisDaoImpl.expire(key, 60*60*24*3);
			CookieUtils.setCookie(request, response, "TT_TOKEN", key,60*60*24*3);
			
		}else {
			er.setMsg("用户名或密码错误");
		}
		
		return er;
	}
	
	
	 /**
	  * 通过token获取用户信息
	  */
	public EgoResult getUserInfoByToken(String token) {
		
		EgoResult er = new EgoResult();
		String json = jedisDaoImpl.get(token);
		if(json!=null&&!json.equals("")) {
			TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
			user.setPassword(null);
			
			er.setStatus(200);
			er.setMsg("OK");
			er.setData(user);
		}else {
			er.setMsg("获取失败");
		}
		
		return er;
	}
	


	public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
		EgoResult er = new EgoResult();
		jedisDaoImpl.del(token);
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		er.setMsg("OK");
		er.setStatus(200);
		
		return er;
	}


}
