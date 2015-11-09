package com.dfjh.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTxt {
	/**
	 * 读取关键词生成arrayList
	 * @throws IOException 
	 */
	public static List<String> keyWordsList(String filePath) throws IOException{
		List<String> list = new ArrayList<String>();
		
		File f = new File(filePath);
		
		if(f.exists()){
			InputStream is = new FileInputStream(f);
			
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			
			BufferedReader br = new BufferedReader(isr);
			
			String lineTxt = null;
			
			while((lineTxt = br.readLine()) != null){
				list.add(lineTxt);
			}
			br.close();
		}
		
		return list;
	}
}
