package com.dfjh.oper.ytb;

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
import com.dfjh.properties.PropertiesParse;

public class YtbQueryIntoTempDay {
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

		String sqlBlog = "SELECT CHANNEL from wk_ytb_data_channel_date_day where DATETIME = '"+time+"'";
		List<Map<String,Object>> list = null;
		try {
			list = DBHelper.getQueryRunner().query(sqlBlog, new MapListHandler());

			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(CollectionUtils.isEmpty(list)){
			//生成channel中间表
			//声明一个String类型的变量，使用if判断时间处于哪个阶段，用来执行哪个sql
			String sql = null;
//			//使用这个lastDate给条件时间赋值
//			Date lastDate = null;
//			//使用lastDate2给另一个时间阶段赋值   由于ytb比较特殊  数据是分为三个阶段采集的
//			Date lastDate2 = null;
//			try {
//				//设定第一个日期的截止时间为下
//				lastDate = sdf.parse("2015-08-13");
//				//设定第二个日期的截止时间为下
//				lastDate2 = sdf.parse("2015-08-23");
//				//或者传过来的时间为lastDate2之前，则执行以下sql
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//			}
//			//原始的sql语句SELECT e.CHANNEL, SUM(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,SUM(`e`.`GOODCOUNT`) AS `GOODCOUNT`,SUM(`e`.`BADCOUNT`) AS `BADCOUNT`,SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_ytb_data_"+paramYear+"_"+paramWeek+" e WHERE DATETIME='"+time+"' AND UPLOADTIME>= DATE_ADD((SELECT DISTINCT DATETIME FROM wk_ytb_data_"+paramYear+"_"+paramWeek+" b WHERE b.`DATETIME`= '"+time+"'),INTERVAL -100 DAY) GROUP BY CHANNEL
//			if(myDate1.before(lastDate)) {
//				//若传过来的时间为lastDate之前，则执行下列sql
//				sql = "SELECT e.CHANNEL, SUM(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,SUM(`e`.`GOODCOUNT`) AS `GOODCOUNT`,SUM(`e`.`BADCOUNT`) AS `BADCOUNT`,SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_ytb_data_paste_channel e WHERE DATETIME='"+time+"' AND UPLOADTIME>= DATE_ADD( '"+time+"',INTERVAL -100 DAY) GROUP BY CHANNEL";
//			}else if (myDate1.before(lastDate2)) {
//				//若传过来的时间为
//				sql = "SELECT e.CHANNEL, SUM(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,SUM(`e`.`GOODCOUNT`) AS `GOODCOUNT`,SUM(`e`.`BADCOUNT`) AS `BADCOUNT`,SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_ytb_data_"+paramYearUS+"_"+paramWeekUS+"_paste e WHERE DATETIME='"+time+"' AND UPLOADTIME>= DATE_ADD('"+time+"',INTERVAL -100 DAY) GROUP BY CHANNEL";
//			}else {
				//若以上两个条件均不满足，照常执行
				sql = "SELECT e.CHANNEL, SUM(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,SUM(`e`.`GOODCOUNT`) AS `GOODCOUNT`,SUM(`e`.`BADCOUNT`) AS `BADCOUNT`,SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_ytb_data_"+paramYearUS+"_"+paramWeekUS+" e WHERE DATETIME='"+time+"' AND UPLOADTIME>= DATE_ADD('"+time+"',INTERVAL -100 DAY) GROUP BY CHANNEL";
//			}
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

			DBUtil.addListMap(mapList, "wk_ytb_data_channel_date_day");

		}else{
			System.out.println("wk_ytb_data_channel_date_day数据已生成！");
		}
		System.out.println("操作完成！");
	}

	public static void main(String[] args) {
		String propertiesName = "poiDB.properties";
		String taday = PropertiesParse.parse("taday", propertiesName);
//		String taday = "2015-6-29";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentTime = sdf.format(date);
		Calendar cal = Calendar.getInstance();

		while(!taday.equals(currentTime)){
			System.out.println("执行今日日期："+taday);
			System.out.println("&&&&&&&&&&&&&&&    平台日榜中间表           &&&&&&&&&&&&&&&&&");
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				Date myDate = sdf.parse(taday);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				cal.add(Calendar.DATE, +1);
				new YtbQueryIntoTempDay().oper(taday);
				taday = sdf.format(cal.getTime());
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("   *********************************");
		System.out.println("  *                                   *");
		System.out.println("*     旅游YouTube日榜中间表 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
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
	//		new YtbQueryIntoTempDay().oper("2015-09-09", setNum);
	//	}

}
