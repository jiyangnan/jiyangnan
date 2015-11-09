package com.dfjh.oper.twitter;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utilsTVCycle.DBHelper;
import com.dfjh.db.utilsTVCycle.DBUtil;
import com.dfjh.db.utilsTVCycle.TimeUtil;

public class TwitterQueryIntoTempMonth {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
	@SuppressWarnings("deprecation")
	public void oper(String time){
		Date myDate1 = null;
		try {
			myDate1 = sdf.parse(time);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//判断当前日期属于本年内第几周
		TimeUtil tu = new TimeUtil();
		intList = tu.getWeekOfYearFR(myDate1);
		String paramYear = String.valueOf(intList.get(0));
		String paramWeek = String.valueOf(intList.get(1));
		System.out.println("当前时间为："+paramYear+"/"+paramWeek+"    传入time为："+time);
		//根据今天时间获取上月时间
		Date lastMonth = new Date();
		String lastMonthStr = null;
		//lastDayOfWeek = tu.getLastDayOfWeek(myDate1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate1);
		cal.add(Calendar.MONTH, -1);
		lastMonth = cal.getTime();
		lastMonthStr  = String.valueOf(lastMonth.getMonth()+1);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		String finalDay = sdf.format(cal.getTime());
		//System.out.println("此月最后一天为："+);
		int totalDay = cal.get(Calendar.DATE);
		System.out.println("上个月为："+lastMonthStr+"月,本月共有"+totalDay+"天");
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);
		String sqlBlog = "SELECT CHANNEL from wk_twitter_data_channel_date_month where MONTHNUM = '"+lastMonthStr+"'";
		List<Map<String,Object>> list = null;
		try {
			System.out.println(sqlBlog);
			list = DBHelper.getQueryRunner().query(sqlBlog, new MapListHandler());

			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(CollectionUtils.isEmpty(list)){
			//生成channel中间表
			String sql = "SELECT e.CHANNEL, e.MONTHNUM , " +
					"CAST(SUM(`e`.`CONCERNSCOUNT`)/ "+totalDay+" as decimal(18,0)) AS `CONCERNSCOUNT`," +
					"CAST(SUM(`e`.`COLLECTCOUNT`)/ "+totalDay+" as decimal(18,0)) AS `COLLECTCOUNT`, " +
					"CAST(SUM(`e`.`FORWARDCOUNT`)/ "+totalDay+" as decimal(18,0)) AS `FORWARDCOUNT`, " +
					"CAST(SUM(`e`.`REVIEWCOUNT`)/ "+totalDay+" as decimal(18,0)) AS `REVIEWCOUNT`, " +
					"CAST(COUNT(`e`.`CITEMS`)/ "+totalDay+" as decimal(18,0)) AS `CITEMS`,e.DATETIME ," +
					"e.UPLOADTIME  FROM  wk_twitter_data_channel_date_day_new_news e " +
					"WHERE DATETIME >( DATE_ADD('"+finalDay+"',INTERVAL -"+totalDay+" DAY) ) AND " +
					"DATETIME< '"+finalDay+"' GROUP BY CHANNEL";
			System.out.println(sql);
			List<Map<String,Object>> mapList = null;
			try {
				mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
				System.out.println(mapList.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			DBUtil.addListMap(mapList, "wk_twitter_data_channel_date_month");
		}else{
			System.out.println("wk_twitter_data_channel_date_month 表中对应此月份数据已经生成！");
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
//		new TwitterQueryIntoTempMonth().oper("2015-10-09", setNum);
//	}
}
