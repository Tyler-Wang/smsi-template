package com.smsi.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

	/**
	 * 获取指定对象定义Class时声明的父类的泛型参数的类型数组
	 * 不包括接口
	 * @param t
	 * @return
	 */
	public static <T> Class<?>[] getExtendClassGenericType(T t){
		Class<?> clz = t.getClass();
		Class<?>[] clzs = getExtendClassGenericType(clz);
		return clzs;
	}
	
	/**
	 * 获得定义Class时声明的父类的泛型参数的类型数组
	 * 不包括接口
	 * @param clz
	 * @return
	 */
	public static Class<?>[] getExtendClassGenericType(Class<?> clz){
		List<Class<?>> clzs = new ArrayList<Class<?>>();
		Class<?> curr = clz;
		while(curr != null && !curr.equals(java.lang.Object.class)){
			Type type = curr.getGenericSuperclass();
			if(type instanceof ParameterizedType){
				break;
			}
			curr = curr.getSuperclass();
		}
		if(curr != null){
			Type type = curr.getGenericSuperclass();
			if(type instanceof ParameterizedType){
				Type[] types = ((ParameterizedType) (curr.getGenericSuperclass())).getActualTypeArguments();
				if(types != null && types.length > 0){
					for(Type t : types){
						if(t instanceof Class){
							clzs.add((Class<?>)t);
						}
					}
				}
			}
		}
		return clzs.toArray(new Class<?>[]{});
	}
	
	/**
	 * 不允许实例化
	 */
	private ReflectionUtil(){
		
	}
	
	private class NoGenericClass{
		
	}
	
	private abstract class GenericInter<T, P>{
		public abstract void doSome(T t);
	}
	
	private class StringGenericInter extends GenericInter<String, Integer>{

		@Override
		public void doSome(String t) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(ReflectionUtil.getExtendClassGenericType(StringGenericInter.class).length);
	}
}
