package com.smsi.common.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * 删除文件，成功返回true；如果文件不存在或不是文件，返回false
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath){
		File file = new File(filePath);
		if(file.exists() && file.isFile()){
			return file.delete();
		}
		return false;
	}
	
}
