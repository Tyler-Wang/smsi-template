package com.smsi.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CopyUtil {
	
	/**
	 * 深拷贝1：递归方法
	 * @param src
	 * @param dest
	 */
    public void copy(List src,List dest){
        for (int i = 0 ;i < src.size() ; i++) {
            Object obj = src.get(i);            
            if (obj instanceof List){
                dest.add(new ArrayList());
                    copy((List)obj,(List)((List)dest).get(i));
            }else{
                dest.add(obj);
            }
        }
        
    }
    
	/**
	 * 深拷贝2:序列化|反序列化方法
	 * @param src
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    public static Object copyBySerialize(Object src) throws IOException, ClassNotFoundException{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        out.close();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in =new ObjectInputStream(byteIn);
        Object dest = in.readObject();
        byteOut.close();
        byteIn.close();
        in.close();
        return dest;
    }
    
}
