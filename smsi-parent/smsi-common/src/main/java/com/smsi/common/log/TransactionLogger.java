package com.smsi.common.log;

import java.util.ArrayList;

public class TransactionLogger {
	private ArrayList<Object> logCache = new ArrayList<Object>();
	
	public void log(Object o){
		logCache.add(o);
	}
	
	public void flush(){
		
	}
	
	public void flush(Object o){
		this.log(o);
		this.flush();
	}
}
