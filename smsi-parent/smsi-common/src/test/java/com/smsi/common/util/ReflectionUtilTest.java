package com.smsi.common.util;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Test;

public class ReflectionUtilTest {

	@Test
	public void testPrimitive(){
		Class<?>[] clzs = ReflectionUtil.getExtendClassGenericType(int.class);
		assertNotNull(clzs);
		assertThat(clzs.length, is(0));
	}
	
	public void testJavaGeneric(){
		Class<?>[] clzs = ReflectionUtil.getExtendClassGenericType(ArrayList.class);
		assertNotNull(clzs);
		assertThat(clzs.length, is(0));
	}
	
	@Test
	public void testNoGeneric(){
		Class<?>[] clzs = ReflectionUtil.getExtendClassGenericType(NoGenericClass.class);
		assertNotNull(clzs);
		assertThat(clzs.length, is(0));
	}
	
	@Test
	public void testOneLayerOneParam(){
		Class<?>[] clzs = ReflectionUtil.getExtendClassGenericType(StringOneLayer.class);
		assertNotNull(clzs);
		assertEquals(clzs[0], String.class);
	}
	
	@Test
	public void testTwoLayerOneParam(){
		Class<?>[] clzs = ReflectionUtil.getExtendClassGenericType(StringTwoLayer.class);
		assertNotNull(clzs);
		assertEquals(clzs[0], String.class);
	}
	
	@Test
	public void testOneLayerMultiParam(){
		Class<?>[] clzs = ReflectionUtil.getExtendClassGenericType(OneLayerMulti.class);
		assertNotNull(clzs);
		assertThat(clzs.length, is(3));
		assertEquals(clzs[0], NoGenericClass.class);
		assertEquals(clzs[1], Integer.class);
		assertEquals(clzs[2], String.class);
	}
	
	@Test
	public void testTwoLayerMultiParam(){
		Class<?>[] clzs = ReflectionUtil.getExtendClassGenericType(TwoLayerMulti.class);
		assertNotNull(clzs);
		assertThat(clzs.length, is(3));
		assertEquals(clzs[0], NoGenericClass.class);
		assertEquals(clzs[1], Integer.class);
		assertEquals(clzs[2], String.class);
	}
	
	/**
	 * 无泛型继承类
	 * @author wangj
	 *
	 */
	private class NoGenericClass{

	}
	
	/**
	 * 泛型基类
	 * @author wangj
	 *
	 * @param <T>
	 */
	private abstract class AbsGeneric<T>{
		
	}
	
	private abstract class AbsBaseGeneric extends AbsGeneric<String>{
		
	}
	
	private class StringOneLayer extends AbsGeneric<String>{
		
	}
	
	private class StringTwoLayer extends AbsBaseGeneric{
		
	}
	
	
	private abstract class AbsMultiGeneric<R, V, W>{
		
	}
	
	private abstract class AbsBaseMultic extends AbsMultiGeneric<NoGenericClass, Integer, String>{
		
	}
	
	private class OneLayerMulti extends AbsMultiGeneric<NoGenericClass, Integer, String>{
		
	}
	
	private class TwoLayerMulti extends AbsBaseMultic{
		
	}
}
