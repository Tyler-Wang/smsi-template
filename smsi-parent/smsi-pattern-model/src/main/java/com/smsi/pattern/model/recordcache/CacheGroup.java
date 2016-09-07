package com.smsi.pattern.model.recordcache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangj
 * @date 2016年9月7日
 * enum类型做key的CacheGroup
 */
public class CacheGroup<K, T>{
	private ConcurrentHashMap<K, LinkedBlockingQueueRecordCache<T>> cache = new ConcurrentHashMap<K, LinkedBlockingQueueRecordCache<T>>();
	private String cacheGroupName;
	
	public CacheGroup(String cacheGroupName, Class<K> cls){
		this.cacheGroupName = cacheGroupName;
		if(!cls.isEnum()){
			throw new IllegalArgumentException("cls must be enum type");
		}
		K[] karr = cls.getEnumConstants();
		for(K k : karr){
			cache.put(k, new LinkedBlockingQueueRecordCache<T>(this.cacheGroupName + " - " + k.toString()));
		}
	}
	
	public LinkedBlockingQueueRecordCache<T> getRecordCache(K k){
		return this.cache.get(k);
	}
	
}
