package com.ego.commons.jedis;

import java.util.List;

public interface JedisDao {
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	Boolean exists(String key);
	
	/**
	 * 删除
	 * @param key
	 * @return
	 */
	Long del(String key);
	
	/**
	 * 设置值
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key,String value);
	
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	String get(String key);
	
	Long list(String key,String value);
	
	List getList(String key,long start,long end);
	
	long expire(String key,int seconds);
}