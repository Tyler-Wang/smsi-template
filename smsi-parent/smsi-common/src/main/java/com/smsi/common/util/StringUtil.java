package com.smsi.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static final String COMMA_SEPERATOR = ",";
	private static final String BRACKET_LEFT = "[";
	private static final String BRACKET_RIGHT = "]";
	private static final String UNDERLINE = "_";
	private static final String ESCAPE = "\\";
	private StringUtil(){
		
	}
	
	public static boolean isNotNull(String s){
		return s == null ? false : true;
	}
	
	/**
	 * String转化为String[]数组
	 * @param s
	 * @return
	 */
	public static String[] toStringArray(String s){
		if(!isNotNull(s)){
			return new String[]{};
		}
		char[] carr = s.toCharArray();
		int len = carr.length;
		String[] sarr = new String[len];
		for(int i=0; i<len; i++){
			sarr[i] = String.valueOf(carr[i]);
		}
		return sarr;
	}
	
	/**
	 * String[]数组转化为中括号包裹，逗号分隔的字符串
	 * @param strArr
	 * @return
	 */
	public static String stringArrToStr(String[] strArr){
		return stringArrToStr(strArr, COMMA_SEPERATOR, BRACKET_LEFT, BRACKET_RIGHT);
	}
	
	/**
	 * String[]数组转化为leftWrapSymbol和rightWrapSymbol包裹，seperator分隔字符串
	 * @param strArr
	 * @param seperator
	 * @param leftWrapSymbol
	 * @param rightWrapSymbol
	 * @return
	 */
	public static String stringArrToStr(String[] strArr, String seperator, String leftWrapSymbol, String rightWrapSymbol){
		StringBuilder sb = new StringBuilder(leftWrapSymbol);
		if(strArr == null){
			sb.append(rightWrapSymbol);
			return sb.toString();
		}
		int sMax = strArr.length - 1;
		for(int i=0; ;i++){
			sb.append(strArr[i]);
			if(i == sMax){
				sb.append(rightWrapSymbol);
				return sb.toString();
			}
			sb.append(seperator);
		}
		//方式有误，如果原始数组本身就有seperator，就有问题
//		if(sb.indexOf(seperator) != -1){
//			sb.deleteCharAt(sb.length() - 1);
//		}
	}
	
	/**
	 * 驼峰转下划线
	 * @param camelStr
	 * @return
	 */
	public static String camelToUnderline(String camelStr){
		if(isNotNull(camelStr) && !camelStr.isEmpty()){
			int len = camelStr.length();
			StringBuilder sb = new StringBuilder(len);
			for(int i=0; i<len; i++){
				char c = camelStr.charAt(i);
				if(Character.isUpperCase(c)){
					sb.append(UNDERLINE).append(Character.toLowerCase(c));
				}else{
					sb.append(c);
				}
			}
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 下划线转驼峰
	 * @param underlineStr
	 * @return
	 */
	public static String underlineToCamel(String underlineStr){
		if(isNotNull(underlineStr) && !underlineStr.isEmpty()){
			String[] arr = underlineStr.split(ESCAPE+UNDERLINE);
			StringBuilder sb = new StringBuilder(arr[0]);
			for(int i=1; i<arr.length; i++){
				if(!arr[i].isEmpty()){
					if(arr[i].length() > 1){
						sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1));
					}else{
						sb.append(Character.toUpperCase(arr[i].charAt(0)));
					}
				}
			}
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 下划线转驼峰，用正则匹配替换
	 * @param underlineStr
	 * @return
	 */
	public static String underlineToCamel2(String underlineStr){
		if(isNotNull(underlineStr) && !underlineStr.isEmpty()){
			StringBuilder sb = new StringBuilder(underlineStr);
			Matcher mc = Pattern.compile(UNDERLINE).matcher(underlineStr);
			int i=0;
			int len = underlineStr.length();
			while(mc.find()){
				 int position=mc.end()-(i++);
				 if(mc.end() < len){
					 sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
				 }else{
					 sb.replace(position-1, position, "");
				 }
			}
			return sb.toString();
		}
		return "";
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtil.underlineToCamel2("_abc_cdf_"));
	}
}
