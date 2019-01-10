package com.jiale.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class redisDao {
	@Autowired
	RedisTemplate redisTemplate;

	/*
	 * https://www.cnblogs.com/phil_jing/p/7468586.html
	 * https://www.cnblogs.com/yanan7890/p/6617305.html
	 * https://blog.csdn.net/u010648555/article/details/79491988
	 * https://blog.csdn.net/m0_37572458/article/details/77791854
	 */
	/*
	 * set(key, value) 添加字符串缓存 默认永久保存 普通缓存放入并设置时间
	 * 
	 * @param key 键
	 * 
	 * @param value值
	 * 
	 * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * 
	 * @return true成功 false 失败
	 */
	public void setStr(String key, String value) {
		System.out.println(redisTemplate);
		System.out.println(redisTemplate.opsForValue());
		redisTemplate.opsForValue().set(key, value);
	}

	/*
	 * Redis五大类型:字符串（String）、哈希/散列/字典（Hash）、列表（List）、集合（Set）、有序集合（sorted set）五种 总括:
	 * redisTemplate.opsForValue(); //操作字符串 redisTemplate.opsForHash();//操作hash
	 * redisTemplate.opsForList(); //操作list redisTemplate.opsForSet();//操作set
	 * redisTemplate.opsForZSet();//操作有序set
	 * 
	 */
	public String getStr(String key) {
		return redisTemplate.opsForValue().get(key).toString();
	}

	/*
	 * redis对list操作分为左和右两种 lPush将数据添加到key对应的现有数据的左边， 也就是头部， rPush是将现有数据添加到现有数据的右边，
	 * 也就是尾部， 可以根据业务的不同进行对应的添加
	 */
	public void rPushList(String key, String value) {
		redisTemplate.opsForList().rightPush(key, value);

	}

	public String lPopList(String key) {
		return redisTemplate.opsForList().leftPop(key).toString();
	}

	public void delKey(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 批量删除缓存
	 *
	 * @param key
	 *            键
	 */
	public void deleteCache(List<String> key) {
		redisTemplate.delete(key);
	}

	public redisDao() {
		super();
		System.out.println("-----Dao-------");
	}

}
