package com.smsi.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.ValueFilter;

public class FastJsonUtil {
	/**
	 * 属性名，驼峰转下滑线
	 */
	private static final NameFilter nameCamelToUnderline = new NameFilter() {
		
		public String process(Object object, String name, Object value) {
			// TODO Auto-generated method stub
			return StringUtil.camelToUnderline(name);
		}
	};
	
	/**
	 * 属性值，全部转化为String
	 */
	private static final ValueFilter valueToString = new ValueFilter() {
		
		public Object process(Object object, String name, Object value) {
			// TODO Auto-generated method stub
			if(value == null){
				return "";
			}else if(value instanceof Enum){
				Method[] marr = value.getClass().getMethods();
				for(Method m : marr){
					if(m.getName().equals("ordinal")){
						try {
							return m.invoke(value, null).toString();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}else if(value instanceof Integer){
				return value.toString();
			}else if(value instanceof Date){
				return DateUtil.format((Date)value, DateUtil.defaultDateTimePatternStr);
			}
			return value;
		}
	};
	
	/**
	 * bean转化为，属性名驼峰转下划线，属性值全部String类型的JSON字符串
	 * @param o
	 * @return
	 */
	public static String beanToNameUnderlineValueStringFastJson(Object o){
		return JSON.toJSONString(0, new SerializeFilter[]{nameCamelToUnderline, valueToString});
	}
}
