package com.dfjh.oper.ggplus;

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
public class GgplusSiteQueryWeek {
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

		//根据今天时间获取上周时间
		List<Integer> tempList = new ArrayList<Integer>();
		cal.setTime(myDate1);

		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		sdf.format(cal.getTime());
		cal.add(Calendar.DATE, -6);
		tempList = tu.getWeekOfYearFR(cal.getTime());
		String YearNow = String.valueOf(tempList.get(0));
		String WeekNow = String.valueOf(tempList.get(1));
		System.out.println("查询时间为："+YearNow+"年"+WeekNow+" 周   传入time为："+time);
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);


		try {
			String channelJudge = "SELECT  week from ufeng_ggplus_tops_channel_count_week where week='"+WeekNow+"' limit 0,10";
			List<Map<String,Object>> judgeMapList = null;
			judgeMapList = DBHelper.getQueryRunner().query(channelJudge, new MapListHandler());

			if(judgeMapList!=null && judgeMapList.size()>1){
				System.out.println("已生成"+WeekNow+"周数据！！！");
			}else{
				//生成channel展示表
				String channelSql = "SELECT  a.*,a.id as top_id,d.WEEKNUM AS week,"
					+ "d.VIEWCOUNT AS view_count,CAST((d.VIEWCOUNT-ifnull(f.VIEWCOUNT,0))/ifnull(f.VIEWCOUNT,1)*100 AS decimal(18,2))  as view_count_interval,"
					+ "d.GOODCOUNT as good_count,CAST((d.GOODCOUNT-ifnull(f.GOODCOUNT,0))/ifnull(f.GOODCOUNT,1)*100  AS decimal(18,2)) as good_count_interval,"
					+ "d.BADCOUNT AS bad_count ,CAST((d.BADCOUNT-ifnull(f.BADCOUNT,0))/ifnull(f.BADCOUNT,1)*100  AS decimal(18,2)) as bad_count_interval,"
					+ "d.SHARECOUNT AS share_count,CAST((d.SHARECOUNT-ifnull(f.SHARECOUNT,0))/ifnull(f.SHARECOUNT,1)*100  AS decimal(18,2)) as share_count_interval,"
					+ "d.REVIEWCOUNT as review_count,CAST((d.REVIEWCOUNT-ifnull(f.REVIEWCOUNT,0))/ifnull(f.REVIEWCOUNT,1)*100  AS decimal(18,2)) as review_count_interval,"
					+ "d.CITEMS as citems_count,CAST((d.CITEMS-ifnull(f.CITEMS,0))/ifnull(f.CITEMS,1)*100  AS decimal(18,2)) as citems_count_interval,"
					+ "d.DATETIME as update_time ,"
					+ "b.`LEVEL` AS na "
					+ "from  ufeng_tops as a join " 
					+ "wk_mapcode AS b  ON a.title = b.channel JOIN "
					+ "wk_ggplus_data_channel_date_week as d "
					+ "ON b.channel = d.channel  join  wk_ggplus_data_channel_date_week as f on f.WEEKNUM='"+WeekNow+"'"
					+ " and d.channel= f.channel where  d.WEEKNUM is not null and d.WEEKNUM='"+paramWeek+"'"; 
				List<Map<String,Object>> channelMapList = null;
				channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());
				DBUtil.addListMapForClassCount(channelMapList, "ufeng_ggplus_tops_channel_count_week");
				System.out.println("插入ufeng_ggplus_tops_channel_count_week表大小为："+channelMapList.size());
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
	//		new GgplusChannelQueryWeek().operFromTemp("2015-09-15", setNum);
	//	}
}
