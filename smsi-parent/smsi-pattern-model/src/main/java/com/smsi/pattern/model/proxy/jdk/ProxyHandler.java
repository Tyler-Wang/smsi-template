package com.smsi.pattern.model.proxy.jdk;

import java.lang.reflect.Method;

/**
 * @author wangj
 * @param <T>
 * @date 2016年8月2日
 */
public interface ProxyHandler<T> {
	Object invoke(T target, Method method, Object[] args) throws Throwable;
}
