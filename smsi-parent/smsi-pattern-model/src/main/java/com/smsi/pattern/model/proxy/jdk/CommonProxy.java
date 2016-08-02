package com.smsi.pattern.model.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangj
 * @param <T>
 * @date 2016年8月2日
 */
public class CommonProxy<T extends ProxyNeed> implements InvocationHandler {
	private T target;
	private ProxyHandler<T> handler;
	public CommonProxy(T target, ProxyHandler<T> handler){
		this.target = target;
		this.handler = handler;
	}
	
	public T getProxyInstance(){
		T newProxyInstance = (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
		return newProxyInstance;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Object result = handler.invoke(target, method, args);
		return result;
	}

}
