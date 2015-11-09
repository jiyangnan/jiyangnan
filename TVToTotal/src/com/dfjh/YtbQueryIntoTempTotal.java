package com.dfjh;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utilsTVTotal.DBHelper;
import com.dfjh.db.utilsTVTotal.DBUtil;
import com.dfjh.db.utilsTVTotal.GetChannel;
import com.dfjh.db.utilsTVTotal.TimeUtil;

public class YtbQueryIntoTempTotal {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
	List<Integer> tempList = new ArrayList<Integer>();
	
	public void oper(String time){
		Date myDate1 = null;
		Calendar cal = Calendar.getInstance();
		try {
			myDate1 = sdf.parse(time);
			cal.setTime(myDate1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//判断当前日期属于本年内第几周
		TimeUtil tu = new TimeUtil();
		intList = tu.getWeekOfYearFR(myDate1);
		String paramYear = String.valueOf(intList.get(0));
		String paramWeek = String.valueOf(intList.get(1));
		String str[] = time.split("-");
		//String month = String .valueOf(cal.get(Calendar.MONTH)+1);
		String year = str[0];
		String month = str[1];
		String day = str[2];
		System.out.println("当前时间为："+paramYear+"年"+month+"月"+day+"日...  传入time为："+time);
		
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);

		String sqlBlog = "SELECT CHANNEL from wk_ytb_data_channel_date_total where DATETIME = '"+time+"'";
		List<Map<String,Object>> list = null;
		try {
			list = DBHelper.getQueryRunner().query(sqlBlog, new MapListHandler());

			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		GetChannel getchannel = new GetChannel();
		List<String> channelList = new ArrayList<String>();
		try {
			channelList = getchannel.getChannel();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String channelName = null;
		for(int i=0;i<channelList.size();i++){
			channelName = (String) channelList.get(i);
			if(CollectionUtils.isEmpty(list)){
				//生成channel中间表
				String sql = "SELECT e.CHANNEL, SUM(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`," +
						"SUM(`e`.`GOODCOUNT`) AS `GOODCOUNT`,SUM(`e`.`BADCOUNT`) AS `BADCOUNT`," +
						"SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`," +
						"e.DATETIME ,e.UPLOADTIME  FROM  wk_ytb_data_"+year+"_"+month+"_"+day+" " +
						"e where CHANNEL=\""+channelName+"\" and title like \"%"+channelName+"%\" GROUP BY CHANNEL";
				System.out.println(sql);
				List<Map<String,Object>> mapList = null;
				Map<String,Object> myMap = new HashMap<String,Object>();
				try {
					mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
					for(int j=0;j<mapList.size();j++){
						myMap = mapList.get(j);
						myMap.put("WEEKNUM", paramWeek);
						myMap.put("MONTHNUM", month);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				DBUtil.addListMap(mapList, "wk_ytb_data_channel_date_total");
				System.out.println("插入wk_ytb_data_channel_date_total表大小为："+mapList.size());

			}else{
				System.out.println("wk_ytb_data_channel_date_total中已有今日数据！");
			}
		}
		String deleteSql="DELETE FROM wk_ytb_data_channel_date_total WHERE channel='24小时' " +
				"OR channel='世界游' OR channel='回家吃饭'OR channel='原来如此' OR channel='今非昔比' " +
				"OR channel='真实故事'OR channel='交易时间'OR channel='交易日'OR channel='中国梦' " +
				"OR channel='中国人'";
		try {
			DBHelper.getQueryRunner().update(deleteSql);
			System.out.println(deleteSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("操作完成！");
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	
//	public static void main(String[] args) {
//	String propertiesName = "poiDB.properties";
//	String taday = PropertiesParse.parse("taday", propertiesName);
//	String yesterday = PropertiesParse.parse("yesterday", propertiesName);
//	int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
//	System.out.println(taday +"  "+ yesterday);
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	Date date = new Date();
//	String currentTime = sdf.format(date);
//	Calendar cal = Calendar.getInstance();
//	new YtbQueryIntoTempTotal().oper("2015-09-09");
//}
}
