package com.dfjh.db.utils.travelTotal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class DBUtil {

	public static void operatSql(String sql){
		try {
			DBHelper.getQueryRunner().update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void add(Map<String,Object> resultMap,String dataTableName){
		StringBuffer keyStr = new StringBuffer();
		StringBuffer valueStr = new StringBuffer();
		for(Entry<String,Object> entry : resultMap.entrySet()){
			keyStr.append(entry.getKey());
			keyStr.append(",");
			valueStr.append(entry.getValue());
			valueStr.append(",");
		}

		if(keyStr.length()>0 || valueStr.length()>0){
			keyStr.deleteCharAt(keyStr.length()-1);
			valueStr.deleteCharAt(valueStr.length()-1);
		}

		String sql = "INSERT INTO "+dataTableName+"("+keyStr.toString()+") VALUES("+valueStr.toString()+")";

		try {
			DBHelper.getQueryRunner().update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static int addListMapForClassCount(List<Map<String,Object>> resultList,String dataTableName){
		int count = resultList.size();
		int[] realCount=null;
		try {
			if(CollectionUtils.isNotEmpty(resultList)){
				Map<String, Object> map = resultList.get(0);
				StringBuffer keyStr = new StringBuffer();
				StringBuffer valueStr = new StringBuffer();
				List<String> list = new ArrayList<String>();
				Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
				Pattern p2 = Pattern.compile("[\u0800-\u4e00]");
				System.out.println("map大小为："+map.size());
				Thread.sleep(2000);
				for(Entry<String,Object> entry : map.entrySet()){
					keyStr.append(entry.getKey());
					keyStr.append(",");
					valueStr.append("?,");
					list.add(entry.getKey());
				}
				if(keyStr.length()>0 || valueStr.length()>0){
					keyStr.deleteCharAt(keyStr.length()-1);
					valueStr.deleteCharAt(valueStr.length()-1);
				}

				String sql = "INSERT ignore INTO "+dataTableName+"("+keyStr.toString()+") VALUES("+valueStr.toString()+")";

				Object[][] param = new Object[count][map.size()];

				for(int i=0;i<resultList.size();i++){
					String str = (String) resultList.get(i).get("TITLE");
					Matcher m = p.matcher(str);
					Matcher m2 = p2.matcher(str);
					if(!m.find()){
						continue;
					}else if(!m2.find()){
						Map<String,Object> bean =  resultList.get(i);
						for(int j = 0; j < list.size(); j++){
							param[i][j] = bean.get(list.get(j));
						}
					}
				}
				System.out.println(sql);
				realCount = DBHelper.getQueryRunner().batch(sql, param);
				System.out.println("*************** realCount的大小为："+realCount.length);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//			System.out.println("addListMap -- "+e.getMessage());
		}

		return count;
	}



	public static int addListMapForTVCount(List<Map<String,Object>> resultList,String dataTableName){
		int count = resultList.size();
		try {
			if(CollectionUtils.isNotEmpty(resultList)){
				Map<String, Object> map = resultList.get(0);
				StringBuffer keyStr = new StringBuffer();
				StringBuffer valueStr = new StringBuffer();
				List<String> list = new ArrayList<String>();
				Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
				Pattern p2 = Pattern.compile("[\u0800-\u4e00]");
				System.out.println("map大小为："+map.size());
				for(Entry<String,Object> entry : map.entrySet()){
					keyStr.append(entry.getKey());
					keyStr.append(",");
					valueStr.append("?,");
					list.add(entry.getKey());
				}
				if(keyStr.length()>0 || valueStr.length()>0){
					keyStr.deleteCharAt(keyStr.length()-1);
					valueStr.deleteCharAt(valueStr.length()-1);
				}

				String sql = "INSERT ignore INTO "+dataTableName+"("+keyStr.toString()+") VALUES("+valueStr.toString()+")";

				Object[][] param = new Object[count][map.size()];

				for(int i=0;i<resultList.size();i++){
					String str = (String) resultList.get(i).get("TITLE");
					Matcher m = p.matcher(str);
					Matcher m2 = p2.matcher(str);
					if(!m.find() && !str.contains("CCTV")){
						continue;
					}else if(!m2.find()){
						Map<String,Object> bean =  resultList.get(i);
						for(int j = 0; j < list.size(); j++){
							param[i][j] = bean.get(list.get(j));
						}
					}
				}
				System.out.println(sql);
				DBHelper.getQueryRunner().batch(sql, param);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	public static int addListMap(List<Map<String,Object>> resultList,String dataTableName){
		int count = resultList.size();
		try {
			if(CollectionUtils.isNotEmpty(resultList)){
				Map<String, Object> map = resultList.get(0);
				StringBuffer keyStr = new StringBuffer();
				StringBuffer valueStr = new StringBuffer();
				List<String> list = new ArrayList<String>();
				Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
				Pattern p2 = Pattern.compile("[\u0800-\u4e00]");
				System.out.println("map大小为："+map.size());
				System.out.println("ok!!");
				for(Entry<String,Object> entry : map.entrySet()){
					keyStr.append(entry.getKey());
					keyStr.append(",");
					valueStr.append("?,");
					list.add(entry.getKey());
				}

				if(keyStr.length()>0 || valueStr.length()>0){
					keyStr.deleteCharAt(keyStr.length()-1);
					valueStr.deleteCharAt(valueStr.length()-1);
				}

				String sql = "INSERT ignore INTO "+dataTableName+"("+keyStr.toString()+") VALUES("+valueStr.toString()+")";

				Object[][] param = new Object[count][map.size()];

				for(int i=0;i<resultList.size();i++){
					String str = (String) resultList.get(i).get("CHANNEL");
					Matcher m = p.matcher(str);
					Matcher m2 = p2.matcher(str);
					if(!m.find()){
						continue;
					}else if(!m2.find()){
						Map<String,Object> bean =  resultList.get(i);
						for(int j = 0; j < list.size(); j++){
							param[i][j] = bean.get(list.get(j));
						}
					}
				}
					System.out.println(sql);
					DBHelper.getQueryRunner().batch(sql, param);
					System.out.println("共操作"+count+"条记录！");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}



	public static void saveMap(Map<String,Object> resultMap,String dataTableName){
		int num = resultMap.size();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < num; i++){
			sb.append("?,");
		}

		if(sb.length()>0 || sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}

		String sql ="insert ignore into "+dataTableName+" values ("+sb.toString()+")";

		Object[][] param = new Object[1][num];

		int i = 0;
		for(Entry<String, Object> entry : resultMap.entrySet()){
			param[0][i] = entry.getValue();
			i++;
		}

		try {
			DBHelper.getQueryRunner().batch(sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveListMap(List<Map<String, Object>> resultList,String dataTableName){
		if(CollectionUtils.isNotEmpty(resultList)){
			int num = resultList.get(0).size();

			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < num; i++){
				sb.append("?,");
			}

			if(sb.length()>0 || sb.length()>0){
				sb.deleteCharAt(sb.length()-1);
			}

			String sql ="insert ignore into "+dataTableName+" values ("+sb.toString()+")";

			Object[][] param = new Object[resultList.size()][num];

			for(int i=0;i<resultList.size();i++){
				Map<String,Object> bean =  resultList.get(i);

				int j = 0;
				for(Entry<String, Object> entry : bean.entrySet()){
					param[i][j] = entry.getValue();
					j++;
				}
			}

			try {
				DBHelper.getQueryRunner().batch(sql, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/***
	 * @author jiyangnan
	 * @date 2015-10-19 13:46:16
	 * @description 扫描字符串中的特殊字符进行转义处理
	 * 
	 * **/
	
	public String transfer(String str){
		System.out.println("传入字符串为："+str);
		String[] strChar = null;
		String[] strUnit = null;
		String finnal_str = null;
		if (str.contains("\"")) {
			strChar = str.split("\"");
			System.out.println("字符串截取成为:"+strChar.length+" 段！");
			strChar = new String[strChar.length];
			strChar = str.split("\"");
			strUnit = new String[strChar.length];
			for(int i=0;i< strChar.length ; i++){
				strUnit[i] = strChar[i];
			}
			for(int j=0;j<strUnit.length-1;j++){
				strUnit[j] = strUnit[j]+"\"";
			}
			finnal_str = strUnit[0];
			for(int k=1;k<strUnit.length;k++){
				finnal_str = finnal_str+strUnit[k];
			}
			System.out.println(finnal_str);
		} 
		if (finnal_str.contains("%")) {
			strChar = finnal_str.split("%");
			System.out.println("字符串截取成为:"+strChar.length+" 段！");
			strChar = new String[strChar.length];
			strChar = str.split("%");
			strUnit = new String[strChar.length];
			for(int i=0;i< strChar.length ; i++){
				strUnit[i] = strChar[i];
			}
			for(int j=0;j<strUnit.length-1;j++){
				strUnit[j] = strUnit[j]+"%";
			}
			finnal_str = strUnit[0];
			for(int k=1;k<strUnit.length;k++){
				finnal_str = finnal_str+strUnit[k];
			}
		} else {
			finnal_str = str;
		}
		System.out.println(finnal_str);
		return finnal_str;
	}
	public List<Map<String , Object>> getChannel() throws SQLException{
		List<Map<String, Object>> getChannelList = null;
		String sql = "select channel from wk_mapcode";
		getChannelList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
		System.out.println(getChannelList.size());
		return getChannelList;
	}
//	public static void main(String[] args) {
//		DBUtil du = new DBUtil();
//		du.transfer("ab!cd@ef#\"ssda\"gh%ijk%lmn*%op");
//	}
}
