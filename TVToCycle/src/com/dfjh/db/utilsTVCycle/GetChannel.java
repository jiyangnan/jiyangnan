package com.dfjh.db.utilsTVCycle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.handlers.MapListHandler;


public class GetChannel {
	static String propertiesName = "poiDB.properties";
	static int s = 0;
	static int w = 0;
	public List<String> getChannel() throws SQLException {
		DBHelper.setFileName(propertiesName); 
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
//		Pattern p2 = Pattern.compile("[\u0800-\u4e00]");
		List<Map<String, Object>> getChannelList = null;
		List<String> pChennel = new ArrayList<String>();
		try {
			String sql = "select channel from wk_mapcode";
			int noCanString = 0;
			getChannelList = DBHelper.getQueryRunner().query(sql,new MapListHandler());
			List<String> list = new ArrayList<String>();
			for (Map<String, Object> entry : getChannelList) {
				list.add((entry.values().toString()).substring(1, (entry.values().toString()).length() - 1));
			}
			for (String string : list) {
				Matcher m = p.matcher(string);
				if ((!m.find()||string.equals("テレビ朝日")||string.equals("笑っていいとも"))&&!string.startsWith("CCTV")) {
					
				}else{
					if (string.length()>2&&!string.equals("24小时")&&!string.equals("12点报道")&&!string.equals("世界游")&&!string.equals("回家吃饭")
							&&!string.equals("原来如此")&&!string.equals("今非昔比")&&!string.equals("真实故事")&&!string.equals("交易时间")
							&&!string.equals("中国人")&&!string.equals("中国梦")&&!string.equals("交易日")) {
//						String [] channel = {"24小时","世界游","回家吃饭","原来如此","今非昔比","真实故事","交易时间","交易日","中国梦","中国人"};
						pChennel.add(string);
					}else{
						System.out.println(++noCanString+"个长度不够或者已经过滤掉的channel				"+string);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pChennel;
	}
	
//	  public static void main(String[] args) throws SQLException {
//	  GetChannel channel = new GetChannel(); 
//	  channel.getChannel();
//	  }
	 
}
