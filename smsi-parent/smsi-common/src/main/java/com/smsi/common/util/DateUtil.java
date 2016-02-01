package com.smsi.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String defaultDateTimePatternStr = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
