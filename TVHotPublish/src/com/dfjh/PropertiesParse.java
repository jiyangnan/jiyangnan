package com.dfjh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesParse {
	public static String parse(String key,String fileName){
		String value = null;
		if(null!=fileName){
			Properties p = new Properties();
			try {
				File file = new File(fileName);
				InputStream in = new FileInputStream(file);
				p.load(in);
				value = p.getProperty(key);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				throw new Exception("未找到配置文件！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
//	public static void main(String[] args) {
//		String filename = "date.properties";
//		System.out.println("打印结果为："+PropertiesParse.parse("year", filename));
//		System.out.println("打印结果为："+PropertiesParse.parse("month", filename));
//		System.out.println("打印结果为："+PropertiesParse.parse("day", filename));
//	}
}
