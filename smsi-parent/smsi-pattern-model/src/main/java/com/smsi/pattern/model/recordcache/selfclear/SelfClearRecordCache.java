package com.smsi.pattern.model.recordcache.selfclear;

import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smsi.pattern.model.CustomThreadFactory;

/**
 * @author wangj
 * @date 2016年6月24日
 */
public class SelfClearRecordCache<K, V> {
	private static final int DEFAULT_CACHE_SIZE = 10000;//默认缓存大小
	private static final int DEFAULT_CLEAR_PERIOD = 2;//默认清理间隔，2分钟
	private static final int DEFAULT_RECORD_EXPIRY_TIME = 5; //默认缓存记录失效时间，5分钟
	
	private String cacheName; //缓存名称
	private int clearPeriod; //清理间隔
	private int recordExpiryTime; //缓存记录失效时间
	
	private ConcurrentHashMap<K, CacheData> cache;//缓存
	private ScheduledExecutorService schExecutor;
	private static final Logger logger = LoggerFactory.getLogger(SelfClearRecordCache.class);
	
	public SelfClearRecordCache(String cacheName, int cacheSize, int clearPeriod, int recordExpiryTime){
		if(!(cacheSize > 0 && clearPeriod > 0 && recordExpiryTime > 0))
			throw new IllegalArgumentException("参数必须是正整数");
		this.cacheName = cacheName;
		this.clearPeriod = clearPeriod;
		this.recordExpiryTime = recordExpiryTime;
		this.cache = new ConcurrentHashMap<K, CacheData>(cacheSize);
		schExecutor = Executors.newSingleThreadScheduledExecutor(new CustomThreadFactory(cacheName));
		schExecutor.scheduleAtFixedRate(new ClearTask(), 1, this.clearPeriod, TimeUnit.MINUTES);
	}
	
	public SelfClearRecordCache(String cacheName){
		this(cacheName,DEFAULT_CACHE_SIZE,DEFAULT_CLEAR_PERIOD,DEFAULT_RECORD_EXPIRY_TIME);
	}
	
	public  SelfClearRecordCache(String cacheName, int clearPeriod,  int recordExpiryTime){
		this(cacheName, DEFAULT_CACHE_SIZE, clearPeriod, recordExpiryTime);
	}
	
	public void put(K key, V value){
		this.cache.put(key, new CacheData(value));
	}
	
	public void put(K key){
		this.cache.put(key, new CacheData(null));
	}
	
	public boolean isContain(K key){
		return this.cache.containsKey(key);
	}
	
	/**
	 * 移除过期的
	 * @return
	 */
	private int removeExpiryRecord(){
		//因ConcurrentHashMap.keySet()在jdk7版本返回Set，jdk8版本KeySetView，不兼容，改为keys()实现
		logger.debug("cache size: {}", cache.size());
		int count = 0;
		long currDateTime = new Date().getTime();
		logger.debug("init key enumeration");
		Enumeration<K> keyEnum = cache.keys();
		logger.debug("init key enumeration done");
		while(keyEnum.hasMoreElements()){
			K key = keyEnum.nextElement();
			if((currDateTime - cache.get(key).getCacheTime().getTime()) >= recordExpiryTime * 60 * 1000){
				logger.debug("remove key: {}", key);
				cache.remove(key);
				count++;
			}
		}
		return count;
	}
	
	private class CacheData{
		V v;
		Date cacheTime;
		CacheData(V v){
			this.v = v;
			this.cacheTime = new Date();
		}
		public V getV() {
			return v;
		}
		public void setV(V v) {
			this.v = v;
		}
		public Date getCacheTime() {
			return cacheTime;
		}
		public void setCacheTime(Date cacheTime) {
			this.cacheTime = cacheTime;
		}
	}
	
	private class ClearTask implements Runnable{
		
		public void run() {
			// TODO Auto-generated method stub
			try {
				logger.info("[" + cacheName + "] 缓存开始清理，总数：{}",cache.size());
				int count = removeExpiryRecord();
				logger.info("[" + cacheName + "] 缓存清理记录数量：{}", count);
			} catch (Throwable e) {
				//捕获异常，防止线程因异常而退出
				logger.error("[" + cacheName + "] Throwable: ", e);
			}
		}
		
	}
	
	public static void main(String[] args) {
		SelfClearRecordCache<String, Void> cache = new SelfClearRecordCache<String, Void>("cacheName");
		cache.put("123");
	}
}
