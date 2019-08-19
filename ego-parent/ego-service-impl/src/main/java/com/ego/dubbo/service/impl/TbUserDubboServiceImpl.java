package com.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ego.dubbo.service.TbUserDubboService;
import com.ego.mapper.TbUserMapper;
import com.ego.pojo.TbUser;
import com.ego.pojo.TbUserExample;
@Service
public class TbUserDubboServiceImpl implements TbUserDubboService{
	@Autowired
	private TbUserMapper tbUserMapper;

	public TbUser login(TbUser user) {
		TbUserExample ex = new TbUserExample();
		ex.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<TbUser> list = tbUserMapper.selectByExample(ex);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}
}
