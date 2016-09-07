package com.smsi.pattern.model.recordcache;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangj
 * @date 2016年9月7日
 */
public class LinkedBlockingQueueRecordCache<T> {
	private static final Logger logger = LoggerFactory.getLogger(LinkedBlockingQueueRecordCache.class);
	private static final Integer MAX_CACHE_SIZE = 100;
	private Integer maxCacheSize;
	private String cacheName;
	private BlockingQueue<T> queue = new LinkedBlockingQueue<T>();
	
	public LinkedBlockingQueueRecordCache(String cacheName, Integer maxCacheSize){
		this.cacheName = cacheName;
		this.maxCacheSize = maxCacheSize;
	}
	
	public LinkedBlockingQueueRecordCache(String cacheName){
		this(cacheName, MAX_CACHE_SIZE);
	}
	
	public void add(T t){
		logger.info("[" + this.cacheName + "] 缓冲区对象数：" + queue.size());
		if(!isFull()){
			this.queue.add(t);
		}
		
	}
	
	public void add(List<T> list){
		for(T t : list){
			add(t);
		}
	}
	
	public boolean isFull(){
		int size = queue.size();
		if(size >= this.maxCacheSize){
			logger.warn("[" + this.cacheName +"] 缓冲区满，暂停添加");
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(){
		return this.queue.isEmpty();
	}
	
	
	public int remainingCapacity(){
		int remainingCapacity = maxCacheSize  - this.queue.size();
		return remainingCapacity > 0 ? remainingCapacity : 0;
	}
	
	public T take() throws InterruptedException{
		return this.queue.take();
	}
}
