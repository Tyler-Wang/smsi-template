package com.smsi.common.util;

import java.util.ArrayList;

public class CollectionUtil {
	private static <T> ArrayList<ArrayList<T>> split(ArrayList<T> srcList, int segmentNum){
		ArrayList<ArrayList<T>> allList = new ArrayList<ArrayList<T>>();
		int size = srcList.size();
		int segment = size / segmentNum;
		int left = size % segmentNum;
		if(segment > 0){
			for(int i=0; i<segment; i++){
				ArrayList<T> segmentList = new ArrayList<T>();
				for(int j=i*segmentNum; j<(i+1)*segmentNum; j++){
					segmentList.add(srcList.get(j));
				}
				allList.add(segmentList);
			}
			if(left > 0){
				ArrayList<T> segmentList = new ArrayList<T>();
				for(int j=segment*segmentNum; j<size; j++){
					segmentList.add(srcList.get(j));
				}
				allList.add(segmentList);
			}
		}else{
			if(left > 0){
				allList.add(srcList);
			}
		}
		return allList;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> srcList = new ArrayList<Integer>();
		int segmentSize = 100;
		for(int i=0; i<200; i++){
			srcList.add(i);
		}
		System.out.println(split(srcList, segmentSize).toString());
	}
}
