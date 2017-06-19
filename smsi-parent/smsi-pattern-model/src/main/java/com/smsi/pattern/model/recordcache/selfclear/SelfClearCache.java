package com.smsi.pattern.model.recordcache.selfclear;

public interface SelfClearCache<K, V> {
	
	public void put(K key, V value);
	
	public void put(K key);
	
	public boolean isContain(K key);
}
