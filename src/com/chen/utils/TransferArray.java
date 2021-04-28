package com.chen.utils;

public class TransferArray {
	public static int[] StringToInt(String[] arrs) {
		int[] ints = new int[arrs.length];
	    for(int i=0;i<arrs.length;i++){
	        ints[i] = Integer.parseInt(arrs[i]);
	    }
	    return ints;
	}
}
