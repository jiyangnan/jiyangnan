package com.dfjh.oper.facebook;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utilsTVShowgenerating.DBHelper;
import com.dfjh.db.utilsTVShowgenerating.DBUtil;
import com.dfjh.db.utilsTVShowgenerating.TimeUtil;

public class FacebookTVQueryMonth {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
	@SuppressWarnings("deprecation")
	public void operFromTemp(String time,int setNum){
		Date myDate1 = null;
		Calendar cal = Calendar.getInstance();
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
		cal.setTime(myDate1);
		String month = String .valueOf(cal.get(Calendar.MONTH)+1);
		System.out.println("当前时间为："+paramYear+"年"+month+"月"+paramWeek+" 周...  传入time为："+time);

		//根据今天时间获取上月时间
		Date lastMonth = new Date();
		String lastMonthStr = null;
		cal.setTime(myDate1);
		cal.add(Calendar.MONTH, -1);
		lastMonth = cal.getTime();
		lastMonthStr  = String.valueOf(lastMonth.getMonth()+1);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		//System.out.println("此月最后一天为："+);
		int totalDay = cal.get(Calendar.DATE);
		System.out.println("上个月为："+lastMonthStr+"月,本月共有"+totalDay+"天");
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);


		try {
			String channelJudge = "SELECT  month from ufeng_facebook_tops_tv_count_month where month='"+lastMonthStr+"' limit 0,10";
			List<Map<String,Object>> judgeMapList = null;
			judgeMapList = DBHelper.getQueryRunner().query(channelJudge, new MapListHandler());

			if(judgeMapList!=null && judgeMapList.size()>1){
				System.out.println("已生成"+lastMonthStr+"月数据！！！");
			}else{
				//生成channel展示表
				String channelSql = "SELECT a.*,a.id as top_id,b.month as month,"
					+ "sum(b.view_count) as view_count,sum(b.view_count_interval) as view_count_interval,"
					+ "sum(b.good_count) as good_count ,sum(b.good_count_interval) as good_count_interval,"
					+ "sum(b.share_count) as share_count,sum(b.share_count_interval) as share_count_interval,"
					+ "sum(b.review_count) as review_count,sum(b.review_count_interval) as review_count_interval,"
					+ "sum(b.citems_count) as citems_count,sum(b.citems_count_interval) as citems_count_interval,"
					+ "b.update_time FROM ufeng_tops as a "
					+ "LEFT JOIN ufeng_facebook_tops_class_count_month as b "
					+ "ON a.id = b.pid and a.level='1' and b.month='"+lastMonthStr+"' GROUP BY id";
				List<Map<String,Object>> channelMapList = null;
				channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());
				System.out.println("插入ufeng_facebook_tops_tv_count_month表大小为："+channelMapList.size());
				DBUtil.addListMapForTVCount(channelMapList, "ufeng_facebook_tops_tv_count_month");
				System.out.println("插入ufeng_facebook_tops_tv_count_month表大小为："+channelMapList.size());
			}
			System.out.println("操作完成！");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//	public static void main(String[] args) {
	//		String propertiesName = "poiDB.properties";
	//		String taday = PropertiesParse.parse("taday", propertiesName);
	//		String yesterday = PropertiesParse.parse("yesterday", propertiesName);
	//		int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
	//		System.out.println(taday +"  "+ yesterday);
	//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//		Date date = new Date();
	//		new FacebookTVQueryMonth().operFromTemp("2015-09-15", setNum);
	//	}
}
