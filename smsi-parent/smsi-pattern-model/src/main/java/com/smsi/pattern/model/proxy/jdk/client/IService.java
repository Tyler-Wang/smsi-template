package com.smsi.pattern.model.proxy.jdk.client;

import com.smsi.pattern.model.proxy.jdk.ProxyNeed;

/**
 * @author wangj
 * @date 2016年8月2日
 */
public interface IService extends ProxyNeed{
	void add();
	
	void update();
}
