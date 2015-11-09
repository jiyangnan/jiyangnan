package com.dfjh.oper.twitter;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utils.travelShow.DBHelper;
import com.dfjh.db.utils.travelShow.DBUtil;
import com.dfjh.db.utils.travelShow.TimeUtil;

/**
 * @author jiyangnan
 * @date 2015-10-12 17:37:57
 */
public class TwitterCityQueryDay {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
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

		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);

		String channelJudge = "select * from ufeng_twitter_tops_class_count_day where update_time= '"+time+"'";
		List<Map<String,Object>> judgeMapList = null;
		try {
			judgeMapList = DBHelper.getQueryRunner().query(channelJudge, new MapListHandler());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(judgeMapList!=null && judgeMapList.size()>1){
			System.out.println("已生成"+time+"日数据！！！");
		}else{
			//生成channel展示表
			String channelSql = "SELECT a.*,a.id as top_id,  "
				+ "b.month as month,b.week as week,"
				+ "sum(b.collect_count) as collect_count, sum(b.collect_count_interval) as collect_count_interval,"
				+ "sum(b.forword_count) as forword_count,sum(b.forword_count_interval) as forword_count_interval, "
				+ "sum(b.review_count) as review_count,sum(b.review_count_interval) as review_count_interval,"
				+ "sum(b.citems_count) as citems_count,sum(b.citems_count_interval) as citems_count_interval,"
				+ "b.update_time   FROM ufeng_tops as a "
				+ "LEFT JOIN ufeng_twitter_tops_channel_count_day as b "
				+ "ON a.id = b.pid  WHERE a.level='2' and b.update_time ='"+time+"' GROUP BY id";

			List<Map<String,Object>> channelMapList = null;
			try {
				channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			DBUtil.addListMapForTVCount(channelMapList, "ufeng_twitter_tops_class_count_day");
			System.out.println("插入ufeng_twitter_tops_class_count_day表大小为："+channelMapList.size());
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
	//		new TwitterTVQueryDay().operFromTemp("2015-09-15", setNum);
	//	}
}
