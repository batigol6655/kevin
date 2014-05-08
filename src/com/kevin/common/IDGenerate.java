package com.kevin.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IDGenerate {
	private static IDGenerate instance = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
	static Random r = new Random();
	private static String id;

	/**
	* 获取单例对象
	* @return
	*/
	public synchronized static IDGenerate getInstanse(){
	   if(instance == null){
	    instance = new IDGenerate();
	   }
	   return instance;
	}

	private IDGenerate(){
	}
	
	/**
	* 根据对象获取表主键,加入线程
	* @param clazz
	* @return
	*/
	public synchronized String getID(){
		
	    String time = df.format(new Date());// new Date()为获取当前系统时间
	    //数字算法
	    int[] array = {1,2,3,4,5,6,7,8,9};
		 
		for (int i = 9; i > 1; i--) {
		    int index = r.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		
		int result = 0;
		for(int i = 0; i < 3; i++){
		    result = result * 10 + array[i];
		}
	     id=time+String.valueOf(result);
 
	  return id;
	}

}
