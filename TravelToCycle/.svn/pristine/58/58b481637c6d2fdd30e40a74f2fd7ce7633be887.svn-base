package com.dfjh.oper.facebook;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utils.travelCycle.DBHelper;
import com.dfjh.db.utils.travelCycle.DBUtil;
import com.dfjh.db.utils.travelCycle.TimeUtil;
public class FacebookQueryIntoTempWeek {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String propertiesName = "poiDB.properties";
	List<Integer> intList = new ArrayList<Integer>();
	String YearNow = null;
	String WeekNow = null;
	String monday = null;
	String tuesday = null;
	String wensday = null;
	String thursday = null;
	String friday = null;
	String saturday = null;
	String sunday = null;
	public void oper(String time){
		Date myDate1 = null;
		Locale.setDefault(Locale.FRANCE) ;
		try {
			myDate1 = sdf.parse(time);

			//判断当前日期属于本年内第几周
			TimeUtil tu = new TimeUtil();
			intList = tu.getWeekOfYearFR(myDate1);
			String paramYear = String.valueOf(intList.get(0));
			String paramWeek = String.valueOf(intList.get(1));
			System.out.println("当前时间为："+paramYear+"年"+paramWeek+" 周   传入time为："+time);

			//根据今天时间获取上周时间
			List<Integer> tempList = new ArrayList<Integer>();
			//lastDayOfWeek = tu.getLastDayOfWeek(myDate1);
			Calendar cal = Calendar.getInstance();
			cal.setTime(myDate1);


			if(Calendar.MONDAY ==cal.getFirstDayOfWeek()){
				cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
				//得到该周7天日期
				cal.add(Calendar.DATE, -6);
				monday = sdf.format(cal.getTime());

				cal.add(Calendar.DATE, +1);
				tuesday = sdf.format(cal.getTime());

				cal.add(Calendar.DATE, +1);
				wensday= sdf.format(cal.getTime());


				cal.add(Calendar.DATE, +1);
				thursday = sdf.format(cal.getTime());

				cal.add(Calendar.DATE, +1);
				friday = sdf.format(cal.getTime());

				cal.add(Calendar.DATE, +1);
				saturday = sdf.format(cal.getTime());

				cal.add(Calendar.DATE, +1);
				sunday = sdf.format(cal.getTime());
				//获取周数
				cal.add(Calendar.DATE, -6);
				tempList = tu.getWeekOfYearFR(cal.getTime());
				YearNow = String.valueOf(tempList.get(0));
				WeekNow = String.valueOf(tempList.get(1));
				System.out.println("查询时间为："+YearNow+"年"+WeekNow+" 周   传入time为："+time);
			}else{
				throw new  Exception("第一天为周日，我们要第一天为周一哦亲~~~");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);

		String sqlBlog = "SELECT CHANNEL from wk_facebook_data_channel_date_week where WEEKNUM = '"+WeekNow+"'";
		List<Map<String,Object>> list = null;
		try {
			list = DBHelper.getQueryRunner().query(sqlBlog, new MapListHandler());

			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(CollectionUtils.isEmpty(list)){
			//生成channel中间表
			String sql = "SELECT e.CHANNEL,  e.WEEKNUM, CAST(SUM(`e`.`VIEWCOUNT`)/7 as decimal(18,0)) AS `VIEWCOUNT`,CAST(SUM(`e`.`GOODCOUNT`)/7 as decimal(18,0)) AS `GOODCOUNT`,CAST(SUM(`e`.`BADCOUNT`)/7 as decimal(18,0)) AS `BADCOUNT`,CAST(SUM(`e`.`REVIEWCOUNT`)/7 as decimal(18,0)) AS `REVIEWCOUNT`, CAST(SUM(`e`.`SHARECOUNT`)/7 as decimal(18,0)) AS `SHARECOUNT`,CAST(COUNT(`e`.`CITEMS`)/7 as decimal(18,0)) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_facebook_data_channel_date_day e WHERE DATETIME IN('"+monday+"','"+tuesday+"','"+wensday+"','"+thursday+"','"+friday+"','"+saturday+"','"+sunday+"') AND UPLOADTIME>= DATE_ADD('"+monday+"',INTERVAL -100 DAY) GROUP BY CHANNEL";
			System.out.println(sql);
			List<Map<String,Object>> mapList = null;
			try {
				mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
				System.out.println(mapList.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			DBUtil.addListMap(mapList, "wk_facebook_data_channel_date_week");
		}else{
			System.out.println("wk_facebook_data_channel_date_day 表中对应此周数据已经生成！");

		}

		System.out.println("操作完成！");


	}

	//				public static void main(String[] args) {
	//					String propertiesName = "poiDB.properties";
	//					String taday = PropertiesParse.parse("taday", propertiesName);
	//					String yesterday = PropertiesParse.parse("yesterday", propertiesName);
	//					int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
	//					System.out.println(taday +"  "+ yesterday);
	//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//					Date date = new Date();
	//					String currentTime = sdf.format(date);
	//					Calendar cal = Calendar.getInstance();
	//					new FacebookQueryIntoTempWeek().oper("2015-09-14", setNum);
	//				}
}


