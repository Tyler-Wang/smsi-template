package com.smsi.pattern.model.proxy.jdk.client;

import java.lang.reflect.Method;

import com.smsi.pattern.model.proxy.jdk.CommonProxy;
import com.smsi.pattern.model.proxy.jdk.ProxyHandler;

/**
 * @author wangj
 * @date 2016年8月2日
 */
public class Client {
	public static void main(String[] args) {
		ServiceImpl serviceImpl = new ServiceImpl();
		CommonProxy<IService> proxyService = new CommonProxy<IService>(serviceImpl, new ProxyHandler<IService>() {

			public Object invoke(IService target, Method method, Object[] args) throws Exception {
				System.out.println("代理执行前");
				Object result = method.invoke(target, args);
				System.out.println("代理执行后");
				return null;
			}
			
		});
		proxyService.getProxyInstance().add();
	}
}
