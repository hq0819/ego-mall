package com.ego.commons.jedis;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ego.commons.jedis.JedisDao;

import redis.clients.jedis.JedisCluster;
public class JedisDaoImpl implements JedisDao{
	@Resource
	private JedisCluster jedisClients;

	public Boolean exists(String key) {
		// TODO Auto-generated method stub
		return jedisClients.exists(key);
	}

	public Long del(String key) {
		// TODO Auto-generated method stub
		return jedisClients.del(key);
	}

	public String set(String key, String value) {
		// TODO Auto-generated method stub
		return jedisClients.set(key,value);
	}

	public String get(String key) {
		// TODO Auto-generated method stub
		return jedisClients.get(key);
	}

	public Long list(String key, String value) {
		// TODO Auto-generated method stub
		return jedisClients.rpush(key, value);
	}

	public List getList(String key,long start,long end) {
		 return jedisClients.lrange(key, start, end);
		
	}

	public long expire(String key, int seconds) {
		// TODO Auto-generated method stub
		return jedisClients.expire(key, seconds);
	}
}
