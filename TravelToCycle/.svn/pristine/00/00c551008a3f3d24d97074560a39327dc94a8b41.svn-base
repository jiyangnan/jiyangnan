package com.dfjh.oper.facebook;

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

import com.dfjh.db.utils.travelCycle.DBHelper;
import com.dfjh.db.utils.travelCycle.DBUtil;
import com.dfjh.db.utils.travelCycle.TimeUtil;

public class FacebookQueryIntoTempDay {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
	List<Integer> intListUS = new ArrayList<Integer>();
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
		String paramYearFR = String.valueOf(intList.get(0));
		String paramWeekFR = String.valueOf(intList.get(1));
		
		intListUS = tu.getWeekOfYearUS(myDate1);
		String paramYearUS= String.valueOf(intListUS.get(0));
		String paramWeekUS = String.valueOf(intListUS.get(1));
		

		String month = String .valueOf(cal.get(Calendar.MONTH)+1);
		System.out.println("当前时间为："+paramYearFR+"年"+month+"月"+paramWeekFR+" 周...  传入time为："+time);
		
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);

		//sunday
		String sqlBlog = "SELECT CHANNEL from wk_facebook_data_channel_date_day where DATETIME = '"+time+"'";
		List<Map<String,Object>> list = null;
		try {
			list = DBHelper.getQueryRunner().query(sqlBlog, new MapListHandler());

			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(CollectionUtils.isEmpty(list)){
			String sql = null;
			//生成channel中间表
			Date lastDate = new Date();
			try {
				lastDate = sdf.parse("2015-08-23");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			if (myDate1.before(lastDate)) {
				sql = "SELECT e.CHANNEL, SUM(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,SUM(`e`.`GOODCOUNT`) AS `GOODCOUNT`,SUM(`e`.`BADCOUNT`) AS `BADCOUNT`,SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`, SUM(`e`.`SHARECOUNT`) AS `SHARECOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_facebook_data_paste e WHERE DATETIME='"+time+"' AND UPLOADTIME>= DATE_ADD('"+time+"',INTERVAL -100 DAY) GROUP BY CHANNEL";
			}else{
				sql = "SELECT e.CHANNEL, SUM(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,SUM(`e`.`GOODCOUNT`) AS `GOODCOUNT`,SUM(`e`.`BADCOUNT`) AS `BADCOUNT`,SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`, SUM(`e`.`SHARECOUNT`) AS `SHARECOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_facebook_data_"+paramYearUS+"_"+paramWeekUS+" e WHERE DATETIME='"+time+"' AND UPLOADTIME>= DATE_ADD('"+time+"',INTERVAL -100 DAY) GROUP BY CHANNEL";
			}
			//生成channel中间表
			System.out.println(sql);
			List<Map<String,Object>> mapList = null;
			Map<String,Object> myMap = new HashMap<String,Object>();
			try {
				mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
				for(int i=0;i<mapList.size();i++){
					myMap = mapList.get(i);
					myMap.put("WEEKNUM", paramWeekFR);
					myMap.put("MONTHNUM", month);
				}
				System.out.println(mapList.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			DBUtil.addListMap(mapList, "wk_facebook_data_channel_date_day");

		}else{
			System.out.println("wk_facebook_data_channel_date_day表数据已生成！！！");
		}
		System.out.println("操作完成！");
	}
	


//	public static void main(String[] args) {
//		String propertiesName = "poiDB.properties";
//		String taday = PropertiesParse.parse("taday", propertiesName);
//		String yesterday = PropertiesParse.parse("yesterday", propertiesName);
//		int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
//		System.out.println(taday +"  "+ yesterday);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date();
//		String currentTime = sdf.format(date);
//		Calendar cal = Calendar.getInstance();
//		new FacebookQueryIntoTempDay().oper("2015-09-09", setNum);
//	}
}
