package com.smsi.common.util;

import java.util.ArrayList;
import java.util.List;

public class ClassUtil {

	/**
	 * 是否Java语言原生类（非自定义类）
	 * @param clz
	 * @return
	 */
	public static boolean isNativeJavaClass(Class<?> clz) {     
	    return clz != null && clz.getClassLoader() == null;     
	}
	
	public static void collectClassExtendAndImplement(Class<?> clz, List<Class<?>> resultCollector){
		Class<?> superClz = clz.getSuperclass();
		Class<?>[] impls = clz.getInterfaces();
		if(superClz != null && !superClz.equals(java.lang.Object.class)){
			System.out.println(clz.getName() + " extends " + superClz.getName());
			collectClassExtendAndImplement(superClz, resultCollector);
		}
		if(impls != null && impls.length > 0){
			for(Class<?> interClz : impls){
				System.out.println(clz.getName() + " implements " + interClz.getName());
				collectClassExtendAndImplement(interClz, resultCollector);
			}
		}
	}
	
	/**
	 * 不允许实例化
	 */
	private ClassUtil(){
		
	}
	
	public static void main(String[] args) {
		Class<?>[] inters = List.class.getInterfaces();
		collectClassExtendAndImplement(ArrayList.class, new ArrayList<Class<?>>());
	}
}
