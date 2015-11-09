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

import com.dfjh.db.utils.travelTotal.DBHelper;
import com.dfjh.db.utils.travelTotal.DBUtil;
import com.dfjh.db.utils.travelTotal.TimeUtil;
/**
 * @author jiyangnan
 * @date 2015-10-09 11:37:23
 */
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
		if(CollectionUtils.isEmpty(list)){
			//生成channel中间表
			String sql = "SELECT e.CHANNEL, SUM(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`," +
					"SUM(`e`.`GOODCOUNT`) AS `GOODCOUNT`,SUM(`e`.`BADCOUNT`) AS `BADCOUNT`," +
					"SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`," +
					"e.DATETIME ,e.UPLOADTIME  FROM  wk_ytb_data_"+year+"_"+month+"_"+day+" e " +
					"GROUP BY CHANNEL";
			System.out.println(sql);
			List<Map<String,Object>> mapList = null;
			Map<String,Object> myMap = new HashMap<String,Object>();
			try {
				mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
				for(int i=0;i<mapList.size();i++){
					myMap = mapList.get(i);
					myMap.put("WEEKNUM", paramWeek);
					myMap.put("MONTHNUM", month);
				}
				System.out.println(mapList.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			DBUtil.addListMap(mapList, "wk_ytb_data_channel_date_total");

		}else{
			System.out.println("wk_ytb_data_channel_date_total表数据已经生成！");
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