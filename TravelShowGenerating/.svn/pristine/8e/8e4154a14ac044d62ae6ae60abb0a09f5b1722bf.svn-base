package com.dfjh.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class PropertiesParse {
	public static String parse(String key,String fileName){
		String value = null;
		if(StringUtils.isNotEmpty(fileName)){
			Properties p = new Properties();
			try {
				File file = new File(fileName);
				InputStream in = new FileInputStream(file);
				p.load(in);
				//p.load(DBHelper.class.getClassLoader().getResourceAsStream(fileName));
				value = p.getProperty(key);
				//value = new String(value.getBytes("ISO-8859-1"), "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				throw new Exception("未指定配置文件");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return value;
	}
}
