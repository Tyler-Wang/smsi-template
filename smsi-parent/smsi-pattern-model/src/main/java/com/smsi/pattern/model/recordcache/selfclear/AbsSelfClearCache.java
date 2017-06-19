package com.smsi.pattern.model.recordcache.selfclear;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsSelfClearCache<K, V> implements SelfClearCache<K, V> {
	private static final Logger logger = LoggerFactory.getLogger(AbsSelfClearCache.class);
	private static final int DEFAULT_RECORD_EXPIRY_TIME = 5; //默认缓存记录失效时间，5分钟
	private String cacheName; //缓存名称
	private Integer recordExpiryTime; //缓存记录失效时间
	private ConcurrentHashMap<K, CacheData> cache;//缓存
	
	protected AbsSelfClearCache(String cacheName, Integer recordExpiryTime){
		this.cacheName = cacheName;
		this.recordExpiryTime = recordExpiryTime;
	}
	
	
	protected abstract Integer removeExpiry();
	
	
	public abstract class CacheData{
		V v;
		CacheData(V v){
			this.v = v;
		}
		public V getV() {
			return v;
		}
		public void setV(V v) {
			this.v = v;
		}
	}
	
	private class ClearTask implements Runnable{
		
		public void run() {
			// TODO Auto-generated method stub
			try {
				logger.info("[" + cacheName + "] 缓存开始清理，总数：{}",cache.size());
				int count = removeExpiry();
				logger.info("[" + cacheName + "] 缓存清理记录数量：{}", count);
			} catch (Throwable e) {
				//捕获异常，防止线程因异常而退出
				logger.error("[" + cacheName + "] Throwable: ", e);
			}
		}
		
	}
}
