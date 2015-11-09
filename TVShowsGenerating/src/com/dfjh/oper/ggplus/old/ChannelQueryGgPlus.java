package com.dfjh.oper.ggplus.old;

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

import com.dfjh.db.utilsTVShowgenerating.DBHelper;
import com.dfjh.db.utilsTVShowgenerating.DBUtil;
import com.dfjh.db.utilsTVShowgenerating.TimeUtil;
import com.dfjh.properties.PropertiesParse;

public class ChannelQueryGgPlus {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
	//	  String taday = PropertiesParse.parse("taday", propertiesName);
	//	  String yesterday = PropertiesParse.parse("yesterday", propertiesName);
	public void oper(String time,int setNum){


		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);

		String sqlBlog = "SELECT CHANNEL from wk_ggplus_data_channel_date where DATETIME = '"+time+"'";
		List<Map<String,Object>> list = null;
		try {
			list = DBHelper.getQueryRunner().query(sqlBlog, new MapListHandler());

			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(CollectionUtils.isEmpty(list)){
			//生成channel中间表
			String sql = "SELECT e.CHANNEL, sum(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,sum(`e`.`GOODCOUNT`) AS `GOODCOUNT`,sum(`e`.`BADCOUNT`) AS `BADCOUNT`,sum(`e`.`SHARECOUNT`) AS `SHARECOUNT`,sum(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,count(`e`.`URL`) AS `CITEMS`,e.DATETIME from  wk_ggplus_data_paste e WHERE DATETIME='"+time+"' and UPLOADTIME>= DATE_ADD(NOW(),INTERVAL "+setNum+" DAY) GROUP BY CHANNEL";

			List<Map<String,Object>> mapList = null;
			try {
				mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());

				System.out.println(mapList.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			DBUtil.addListMap(mapList, "wk_ggplus_data_channel_date");

			//生成channel表
			String channelSql = "SELECT  a.*,a.id as top_id,d.VIEWCOUNT AS view_count, d.GOODCOUNT as good_count, d.BADCOUNT AS bad_count ,d.SHARECOUNT AS share_count ,d.REVIEWCOUNT as review_count ,d.CITEMS as citems_count,d.DATETIME as update_time FROM ufeng_tops as a JOIN ufeng_tops_to_channel as b on a.id= b.tops_id JOIN ufeng_tops_channel as c on c.id = b.channel_id JOIN wk_ggplus_data_channel_date_day as d on c.code=d.channel and d.DATETIME='"+time+"'";

			List<Map<String,Object>> channelMapList = null;
			try {
				channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());

				System.out.println("插入ufeng_ggplus_tops_class_count_day表的大小:"+channelMapList.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			DBUtil.addListMapForClassCount(channelMapList, "ufeng_ggplus_tops_class_count_day");

			//生成Tv表
			new TvQueryGgPlus().oper(time);
		}else{
			System.out.println("channel 中间表 已经生成");

		}
		//		}
		System.out.println("操作完成！");
	}


	public void operNew(String time,int setNum){


		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);

		String sqlBlog = "SELECT CHANNEL from wk_ggplus_data_channel_date where DATETIME = '"+time+"'";
		List<Map<String,Object>> list = null;
		try {
			list = DBHelper.getQueryRunner().query(sqlBlog, new MapListHandler());

			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(CollectionUtils.isEmpty(list)){
			if(time.equals("2015-08-23")){
				String sql = "SELECT e.CHANNEL, sum(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,sum(`e`.`GOODCOUNT`) AS `GOODCOUNT`,sum(`e`.`BADCOUNT`) AS `BADCOUNT`,sum(`e`.`SHARECOUNT`) AS `SHARECOUNT`,sum(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,count(`e`.`URL`) AS `CITEMS`,e.DATETIME from  wk_ggplus_data_2015_35 e WHERE DATETIME='"+time+"' and UPLOADTIME>= DATE_ADD(NOW(),INTERVAL "+setNum+" DAY) GROUP BY CHANNEL";
				List<Map<String,Object>> mapList = null;
				try {
					mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());

					System.out.println(mapList.size());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBUtil.addListMap(mapList, "wk_ggplus_data_channel_date");
				//生成channel表
				String channelSql = "SELECT a.*,a.id as top_id,d.VIEWCOUNT AS view_count, d.GOODCOUNT as good_count, d.BADCOUNT AS bad_count ,d.REVIEWCOUNT as review_count ,d.SHARECOUNT AS share_count ,d.CITEMS as citems_count,d.DATETIME as update_time FROM ufeng_tops as a JOIN ufeng_tops_to_channel as b on a.id= b.tops_id JOIN ufeng_tops_channel as c on c.id = b.channel_id JOIN wk_ggplus_data_channel_date as d on c.code=d.channel and d.DATETIME='"+time+"'";

				List<Map<String,Object>> channelMapList = null;
				try {
					channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());

					System.out.println("插入ufeng_ggplus_tops_class_count表的大小:"+channelMapList.size());
				} catch (SQLException e) {
					e.printStackTrace();
				}

				DBUtil.addListMapForClassCount(channelMapList, "ufeng_ggplus_tops_class_count");
				//生成Tv表
				new TvQueryGgPlus().oper(time);


			}else{
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
				System.out.println("查询表为："+"wk_ggplus_data_"+paramYear+"_"+paramWeek+"    传入time为："+time);
				String sql = "SELECT e.CHANNEL, sum(`e`.`VIEWCOUNT`) AS `VIEWCOUNT`,sum(`e`.`GOODCOUNT`) AS `GOODCOUNT`,sum(`e`.`BADCOUNT`) AS `BADCOUNT`,sum(`e`.`REVIEWCOUNT`) AS `REVIEWCOUNT`,sum(`e`.`SHARECOUNT`) AS `SHARECOUNT`,count(`e`.`URL`) AS `CITEMS`,e.DATETIME from  wk_ggplus_data_"+paramYear+"_"+paramWeek+" e WHERE DATETIME='"+time+"' and UPLOADTIME>= DATE_ADD(NOW(),INTERVAL "+setNum+" DAY)  GROUP BY CHANNEL";
				List<Map<String,Object>> mapList = null;
				try {
					mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());

					System.out.println(mapList.size());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBUtil.addListMap(mapList, "wk_ggplus_data_channel_date");
				//生成channel表
				String channelSql = "SELECT a.*,a.id as top_id,d.VIEWCOUNT AS view_count, d.GOODCOUNT as good_count, d.BADCOUNT AS bad_count ,d.REVIEWCOUNT as review_count ,d.SHARECOUNT AS share_count ,d.CITEMS as citems_count,d.DATETIME as update_time FROM ufeng_tops as a JOIN ufeng_tops_to_channel as b on a.id= b.tops_id JOIN ufeng_tops_channel as c on c.id = b.channel_id JOIN wk_ggplus_data_channel_date as d on c.code=d.channel and d.DATETIME='"+time+"'";

				List<Map<String,Object>> channelMapList = null;
				try {
					channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());

					System.out.println("插入ufeng_ggplus_tops_class_count表的大小"+channelMapList.size());
				} catch (SQLException e) {
					e.printStackTrace();
				}

				DBUtil.addListMapForClassCount(channelMapList, "ufeng_ggplus_tops_class_count");
				//生成Tv表
				new TvQueryGgPlus().oper(time);

			}
		}else{
			System.out.println("channel 中间表 已经生成");

		}
		//		}
		System.out.println("操作完成！");
	}


	public static void main(String[] args) {
		String propertiesName = "poiDB.properties";
		String taday = PropertiesParse.parse("taday", propertiesName);
		String yesterday = PropertiesParse.parse("yesterday", propertiesName);
		System.out.println(taday +"  "+ yesterday);
		int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
		//int setNum = -300;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentTime = sdf.format(date);
		Calendar cal = Calendar.getInstance();
		//		Calendar cal2 = Calendar.getInstance();
		//		Calendar cal3 = Calendar.getInstance();
		while(!taday.equals("2015-08-23")){
			System.out.println("&&&&&&&&&&&&&&&8月23日以前版本!!!&&&&&&&&&&&&&&&&&");
			System.out.println("&&&&&&&&&&&&&&&Google+&&&&&&&&&&&&&&&&&");
			System.out.println("执行今日日期："+taday);
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				new ChannelQueryGgPlus().oper(taday,setNum);
				Date myDate = sdf.parse(taday);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				yesterday = sdf.format(cal2.getTime());
				cal.add(Calendar.DATE, +1);
				System.out.println("执行昨日日期："+yesterday);
				new ChannelQueryGgPlus().oper(yesterday,setNum);
				new ChannelZengLiangGgPlus().oper(taday,yesterday);
				System.out.println("即将处理数据日期为："+cal3.getTime());
				System.out.println("taday:"+taday+"  ;  yesterday:"+yesterday);
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
		while(!yesterday.equals(currentTime)){
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^8月23日以后版本!!!^^^^^^^^^^^^^^^^^^^^");
			System.out.println("&&&&&&&&&&&&&&&Google+&&&&&&&&&&&&&&&&&");
			System.out.println("执行今日日期："+taday);
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				new ChannelQueryGgPlus().operNew(taday,setNum);
				Date myDate = sdf.parse(taday);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				yesterday = sdf.format(cal2.getTime());
				cal.add(Calendar.DATE, +1);
				System.out.println("执行昨日日期："+yesterday);
				new ChannelQueryGgPlus().operNew(yesterday,setNum);
				new ChannelZengLiangGgPlus().oper(taday,yesterday);
				System.out.println("即将处理数据日期为："+cal3.getTime());
				System.out.println("taday:"+taday+"  ;  yesterday:"+yesterday);
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
	}

}
