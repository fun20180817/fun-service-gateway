package com.fun.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * ClassName: JedisService <br/>
 * Function: jedis client 用于redis的操作. <br/>
 * date: 2018年8月23日 下午9:48:32 <br/>
 * 
 * @author lishuai8
 * @version
 * @since JDK 1.8
 */
@Service("jedisService")
public class JedisService {

	private static final Logger logger = LoggerFactory.getLogger(JedisService.class);

	@Autowired
	JedisPool jedisPool;

	/**
	 * 
	 * get:(根据key值查value). <br/>
	 * 
	 * @author lishuai8
	 * @param key
	 * @return
	 * @since JDK 1.8
	 */
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String str = "";
		try {
			str = jedis.get(key);
		} catch (Exception e) {
			logger.error("jedis.get() is fail : {}", e.getMessage());
		}
		close(jedis);
		return str;
	}

	/**
	 * 
	 * set:(设置key:value，没有失效时间). <br/>
	 * 
	 * @author lishuai8
	 * @param key
	 * @param value
	 * @return
	 * @since JDK 1.8
	 */
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String str = "";
		try {
			str = jedis.set(key, value);
		} catch (Exception e) {
			logger.error("jedis.set() is fail : {}", e.getMessage());
		}
		close(jedis);
		return str;
	}

	/**
	 * 
	 * setex:(设置key:value;并设置key的失效时间(单位s)). <br/>
	 * 如果key存在，则覆盖key的value；否则创建key:Lvalue
	 * 
	 * @author lishuai8
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 * @since JDK 1.8
	 */
	public String setex(String key, int seconds, String value) {
		Jedis jedis = jedisPool.getResource();
		String str = "";
		try {
			str = jedis.setex(key, seconds, value);
		} catch (Exception e) {
			logger.error("jedis.setex() is fail : {}", e.getMessage());
		}
		close(jedis);
		return str;
	}

	/**
	 * 
	 * isExist:(查看key是否存在). <br/>
	 * 
	 * @author lishuai8
	 * @param key
	 * @return
	 * @since JDK 1.8
	 */
	public boolean isExist(String key) {
		Jedis jedis = jedisPool.getResource();
		boolean result = false;
		try {
			result = jedis.exists(key);
		} catch (Exception e) {
			logger.error("jedis.exists() is fail : {}", e.getMessage());
		}
		close(jedis);
		return result;

	}

	public void close(Jedis jedis) {
		try {
			jedis.close();
		} catch (Exception e) {
			logger.error("jedis close fail : {}", e.getMessage());
		}
	}

}
