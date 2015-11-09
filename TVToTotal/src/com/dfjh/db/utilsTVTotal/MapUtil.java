package com.dfjh.db.utilsTVTotal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {
	public static Map<String,Object> varcharValue(Map<String,Object> map){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		StringBuffer queryString;
		for(Entry<String,Object> entry : map.entrySet()){
			queryString = new StringBuffer();
			queryString.append("'");
			queryString.append(entry.getValue());
			queryString.append("'");
			
			resultMap.put(entry.getKey(), queryString.toString());
		}
		
		return resultMap;
	}
}
