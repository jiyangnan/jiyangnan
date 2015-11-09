package com.dfjh.oper.twitter;
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


public class TwitterQueryIntoTempWeek {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
			Locale.setDefault(Locale.FRANCE) ;
			Date myDate1 = null;
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
					System.out.println("第一天为周日！！！");
					throw new  Exception("第一天为周日，我们要第一天为周一哦亲~~~");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//获取数据库连接配置
			DBHelper.setFileName(propertiesName);

			String sqlBlog = "SELECT CHANNEL from wk_twitter_data_channel_date_week where WEEKNUM = '"+WeekNow+"'";
			List<Map<String,Object>> list = null;
			try {
				list = DBHelper.getQueryRunner().query(sqlBlog, new MapListHandler());

				System.out.println(list.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(CollectionUtils.isEmpty(list)){
				//生成channel中间表
				String sql = "SELECT e.CHANNEL, e.WEEKNUM, CAST(SUM(`e`.`CONCERNSCOUNT`)/7 as decimal(18,0)) AS `CONCERNSCOUNT`,CAST(SUM(`e`.`COLLECTCOUNT`)/7 as decimal(18,0)) AS `COLLECTCOUNT`, CAST(SUM(`e`.`FORWARDCOUNT`)/7 as decimal(18,0)) AS `FORWARDCOUNT`, CAST(SUM(`e`.`REVIEWCOUNT`)/7 as decimal(18,0)) AS `REVIEWCOUNT`,CAST(COUNT(`e`.`CITEMS`)/7 as decimal(18,0)) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_twitter_data_channel_date_day e WHERE DATETIME IN('"+monday+"','"+tuesday+"','"+wensday+"','"+thursday+"','"+friday+"','"+saturday+"','"+sunday+"') AND UPLOADTIME>= DATE_ADD('"+monday+"',INTERVAL -100 DAY) GROUP BY CHANNEL";
				System.out.println(sql);
				List<Map<String,Object>> mapList = null;
				try {
					mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
					System.out.println(mapList.size());
				} catch (SQLException e) {
					e.printStackTrace();
				}

				DBUtil.addListMap(mapList, "wk_twitter_data_channel_date_week");

//				//monday
//				String sqlBlog2 = "SELECT CHANNEL from wk_twitter_data_channel_date_week where WEEKNUM = '"+WeekNow+"'";
//				List<Map<String,Object>> list2 = null;
//				try {
//					list2 = DBHelper.getQueryRunner().query(sqlBlog2, new MapListHandler());
//
//					System.out.println(list2.size());
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				if(CollectionUtils.isEmpty(list2)){
//					//生成channel中间表
//					String sql2 = "SELECT e.CHANNEL, SUM(`e`.`CONCERNSCOUNT`) AS `CONCERNSCOUNT`,SUM(`e`.`COLLECTCOUNT`) AS `COLLECTCOUNT`, SUM(`e`.`FORWARDCOUNT`) AS `FORWARDCOUNT`, SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_twitter_data_"+YearNow+"_"+WeekNow+" e WHERE DATETIME='"+monday+"' AND UPLOADTIME>= DATE_ADD((SELECT DISTINCT DATETIME FROM wk_twitter_data_"+YearNow+"_"+WeekNow+" b WHERE b.`DATETIME`= '"+monday+"'),INTERVAL -100 DAY) GROUP BY CHANNEL";
//					System.out.println(sql);
//					List<Map<String,Object>> mapList2 = null;
//					Map<String,Object> myMap2 = new HashMap<String,Object>();
//					try {
//						mapList2 = DBHelper.getQueryRunner().query(sql2, new MapListHandler());
//						for(int i=0;i<mapList2.size();i++){
//							myMap2 = mapList2.get(i);
//							myMap2.put("WEEKNUM", WeekNow);
//						}
//						System.out.println(mapList2.size());
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//
//					DBUtil.addListMap(mapList2, "wk_twitter_data_channel_date_week");
//
//					//tuesday
//					String sqlBlog3 = "SELECT CHANNEL from wk_twitter_data_channel_date_week where WEEKNUM = '"+WeekNow+"'";
//					List<Map<String,Object>> list3 = null;
//					try {
//						list3 = DBHelper.getQueryRunner().query(sqlBlog3, new MapListHandler());
//
//						System.out.println(list3.size());
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//					if(CollectionUtils.isEmpty(list3)){
//						//生成channel中间表
//						String sql3 = "SELECT e.CHANNEL, SUM(`e`.`CONCERNSCOUNT`) AS `CONCERNSCOUNT`,SUM(`e`.`COLLECTCOUNT`) AS `COLLECTCOUNT`, SUM(`e`.`FORWARDCOUNT`) AS `FORWARDCOUNT`, SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_twitter_data_"+YearNow+"_"+WeekNow+" e WHERE DATETIME='"+tuesday+"' AND UPLOADTIME>= DATE_ADD((SELECT DISTINCT DATETIME FROM wk_twitter_data_"+YearNow+"_"+WeekNow+" b WHERE b.`DATETIME`= '"+tuesday+"'),INTERVAL -100 DAY) GROUP BY CHANNEL";
//						System.out.println(sql3);
//						List<Map<String,Object>> mapList3 = null;
//						Map<String,Object> myMap3 = new HashMap<String,Object>();
//						try {
//							mapList3 = DBHelper.getQueryRunner().query(sql3, new MapListHandler());
//							for(int i=0;i<mapList3.size();i++){
//								myMap3 = mapList3.get(i);
//								myMap3.put("WEEKNUM", WeekNow);
//							}
//							System.out.println(mapList3.size());
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//
//						DBUtil.addListMap(mapList3, "wk_twitter_data_channel_date_week");
//
//						//wensday
//						String sqlBlog4 = "SELECT CHANNEL from wk_twitter_data_channel_date_week where WEEKNUM = '"+WeekNow+"'";
//						List<Map<String,Object>> list4 = null;
//						try {
//							list4 = DBHelper.getQueryRunner().query(sqlBlog4, new MapListHandler());
//
//							System.out.println(list4.size());
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						if(CollectionUtils.isEmpty(list4)){
//							//生成channel中间表
//							String sql4 = "SELECT e.CHANNEL, SUM(`e`.`CONCERNSCOUNT`) AS `CONCERNSCOUNT`,SUM(`e`.`COLLECTCOUNT`) AS `COLLECTCOUNT`, SUM(`e`.`FORWARDCOUNT`) AS `FORWARDCOUNT`, SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_twitter_data_"+YearNow+"_"+WeekNow+" e WHERE DATETIME='"+wensday+"' AND UPLOADTIME>= DATE_ADD((SELECT DISTINCT DATETIME FROM wk_twitter_data_"+YearNow+"_"+WeekNow+" b WHERE b.`DATETIME`= '"+wensday+"'),INTERVAL -100 DAY) GROUP BY CHANNEL";
//							System.out.println(sql4);
//							List<Map<String,Object>> mapList4 = null;
//							Map<String,Object> myMap4 = new HashMap<String,Object>();
//							try {
//								mapList4 = DBHelper.getQueryRunner().query(sql4, new MapListHandler());
//								for(int i=0;i<mapList4.size();i++){
//									myMap4 = mapList4.get(i);
//									myMap4.put("WEEKNUM", WeekNow);
//								}
//								System.out.println(mapList4.size());
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//
//							DBUtil.addListMap(mapList4, "wk_twitter_data_channel_date_week");
//						}else{
//							System.out.println("channel 中间表 已经生成");
//
//						}
//						
//						//thursday
//						String sqlBlog5 = "SELECT CHANNEL from wk_twitter_data_channel_date_week where WEEKNUM = '"+WeekNow+"' and datetime='"+thursday+"'";
//						List<Map<String,Object>> list5 = null;
//						try {
//							list5 = DBHelper.getQueryRunner().query(sqlBlog5, new MapListHandler());
//
//							System.out.println(list5.size());
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						if(CollectionUtils.isEmpty(list5)){
//							//生成channel中间表
//							String sql5 = "SELECT e.CHANNEL, SUM(`e`.`CONCERNSCOUNT`) AS `CONCERNSCOUNT`,SUM(`e`.`COLLECTCOUNT`) AS `COLLECTCOUNT`, SUM(`e`.`FORWARDCOUNT`) AS `FORWARDCOUNT`, SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_twitter_data_"+YearNow+"_"+WeekNow+" e WHERE DATETIME='"+thursday+"' AND UPLOADTIME>= DATE_ADD((SELECT DISTINCT DATETIME FROM wk_twitter_data_"+YearNow+"_"+WeekNow+" b WHERE b.`DATETIME`= '"+thursday+"'),INTERVAL -100 DAY) GROUP BY CHANNEL";
//							System.out.println(sql5);
//							List<Map<String,Object>> mapList5 = null;
//							Map<String,Object> myMap4 = new HashMap<String,Object>();
//							try {
//								mapList5 = DBHelper.getQueryRunner().query(sql5, new MapListHandler());
//								for(int i=0;i<mapList5.size();i++){
//									myMap4 = mapList5.get(i);
//									myMap4.put("WEEKNUM", WeekNow);
//								}
//								System.out.println(mapList5.size());
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//
//							DBUtil.addListMap(mapList5, "wk_twitter_data_channel_date_week");
//						}else{
//							System.out.println("channel 中间表 已经生成");
//
//						}
//						
//						
//						//friday
//						String sqlBlog6 = "SELECT CHANNEL from wk_twitter_data_channel_date_week where WEEKNUM = '"+WeekNow+"' and datetime='"+friday+"'";
//						List<Map<String,Object>> list6 = null;
//						try {
//							list6 = DBHelper.getQueryRunner().query(sqlBlog6, new MapListHandler());
//
//							System.out.println(list6.size());
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						if(CollectionUtils.isEmpty(list6)){
//							//生成channel中间表
//							String sql6 = "SELECT e.CHANNEL, SUM(`e`.`CONCERNSCOUNT`) AS `CONCERNSCOUNT`,SUM(`e`.`COLLECTCOUNT`) AS `COLLECTCOUNT`, SUM(`e`.`FORWARDCOUNT`) AS `FORWARDCOUNT`, SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_twitter_data_"+YearNow+"_"+WeekNow+" e WHERE DATETIME='"+friday+"' AND UPLOADTIME>= DATE_ADD((SELECT DISTINCT DATETIME FROM wk_twitter_data_"+YearNow+"_"+WeekNow+" b WHERE b.`DATETIME`= '"+friday+"'),INTERVAL -100 DAY) GROUP BY CHANNEL";
//							System.out.println(sql6);
//							List<Map<String,Object>> mapList6 = null;
//							Map<String,Object> myMap6 = new HashMap<String,Object>();
//							try {
//								mapList6 = DBHelper.getQueryRunner().query(sql6, new MapListHandler());
//								for(int i=0;i<mapList6.size();i++){
//									myMap6 = mapList6.get(i);
//									myMap6.put("WEEKNUM", WeekNow);
//								}
//								System.out.println(mapList6.size());
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//
//							DBUtil.addListMap(mapList6, "wk_twitter_data_channel_date_week");
//						}else{
//							System.out.println("channel 中间表 已经生成");
//
//						}
//						
//						
//
//						//saturday
//					
//
//						String sqlBlog7 = "SELECT CHANNEL from wk_twitter_data_channel_date_week where WEEKNUM = '"+WeekNow+"' and datetime='"+saturday+"'";
//						List<Map<String,Object>> list7 = null;
//						try {
//							list7 = DBHelper.getQueryRunner().query(sqlBlog7, new MapListHandler());
//
//							System.out.println(list7.size());
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						if(CollectionUtils.isEmpty(list7)){
//							//生成channel中间表
//							String sql7 = "SELECT e.CHANNEL, SUM(`e`.`CONCERNSCOUNT`) AS `CONCERNSCOUNT`,SUM(`e`.`COLLECTCOUNT`) AS `COLLECTCOUNT`, SUM(`e`.`FORWARDCOUNT`) AS `FORWARDCOUNT`, SUM(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,COUNT(`e`.`URL`) AS `CITEMS`,e.DATETIME ,e.UPLOADTIME  FROM  wk_twitter_data_"+YearNow+"_"+WeekNow+" e WHERE DATETIME='"+saturday+"' AND UPLOADTIME>= DATE_ADD((SELECT DISTINCT DATETIME FROM wk_twitter_data_"+YearNow+"_"+WeekNow+" b WHERE b.`DATETIME`= '"+saturday+"'),INTERVAL -100 DAY) GROUP BY CHANNEL";
//							System.out.println(sql7);
//							List<Map<String,Object>> mapList7 = null;
//							Map<String,Object> myMap7 = new HashMap<String,Object>();
//							try {
//								mapList7 = DBHelper.getQueryRunner().query(sql7, new MapListHandler());
//								for(int i=0;i<mapList7.size();i++){
//									myMap7 = mapList7.get(i);
//									myMap7.put("WEEKNUM", WeekNow);
//								}
//								System.out.println(mapList7.size());
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//
//							DBUtil.addListMap(mapList7, "wk_twitter_data_channel_date_week");
						}else{
							System.out.println("wk_twitter_data_channel_date_day 表中对应此周数据已经生成！");

						}
						
				System.out.println("操作完成！");


			}




//			public static void main(String[] args) {
//				String propertiesName = "poiDB.properties";
//				String taday = PropertiesParse.parse("taday", propertiesName);
//				String yesterday = PropertiesParse.parse("yesterday", propertiesName);
//				int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
//				System.out.println(taday +"  "+ yesterday);
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				Date date = new Date();
//				String currentTime = sdf.format(date);
//				Calendar cal = Calendar.getInstance();
//				new TwitterQueryIntoTempWeek().oper("2015-09-14", setNum);
//			}
}
