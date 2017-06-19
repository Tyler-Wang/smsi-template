package com.smsi.pattern.model.recordcache.selfclear;

import java.util.concurrent.ConcurrentHashMap;

import com.smsi.pattern.model.recordcache.selfclear.delay.AbsDelayTask;
import com.smsi.pattern.model.recordcache.selfclear.delay.DelayTime;
import com.smsi.pattern.model.recordcache.selfclear.delay.DelayWheelExecutor;

public class TimeWheelSelfClearCache<K, V>{
	private static final int DEFAULT_CACHE_SIZE = 10000;//默认缓存大小
	private ConcurrentHashMap<K, V> cache; //缓存
	private DelayWheelExecutor clearExecutor;
	protected TimeWheelSelfClearCache(String cacheName, Integer recordExpiryTime) {
		this.cache = new ConcurrentHashMap<K, V>(DEFAULT_CACHE_SIZE);
		this.clearExecutor = new DelayWheelExecutor(recordExpiryTime * 60);
		this.clearExecutor.start();
	}

	public void put(K key, V value) {
		// TODO Auto-generated method stub
		this.cache.put(key, value);
	}

	public void put(K key) {
		// TODO Auto-generated method stub
		this.cache.put(key, null);
	}

	public boolean isContain(K key) {
		// TODO Auto-generated method stub
		return this.cache.contains(key);
	}
	
	private class ClearTask<K, V> extends AbsDelayTask{
		private K key;
		private V value;
		protected ClearTask(DelayTime delayTime, K key, V value) {
			super(delayTime);
			this.key = key;
			this.value = value;
		}

		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
