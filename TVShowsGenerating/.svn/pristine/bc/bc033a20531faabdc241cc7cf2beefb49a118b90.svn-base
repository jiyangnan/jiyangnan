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

public class FacebookChannelQueryMonth {
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
		Date lastLastMonth = new Date();
		String lastMonthStr = null;
		String lastLastMonthStr = null;
		cal.setTime(myDate1);
		cal.add(Calendar.MONTH, -1);
		lastMonth = cal.getTime();
		cal.add(Calendar.MONTH, -1);
		lastLastMonth = cal.getTime(); 
		lastMonthStr  = String.valueOf(lastMonth.getMonth()+1);
		lastLastMonthStr = String.valueOf(lastLastMonth.getMonth()+1);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		//System.out.println("此月最后一天为："+);
		int totalDay = cal.get(Calendar.DATE);
		System.out.println("上个月为："+lastMonthStr+"月,本月共有"+totalDay+"天");
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);




		try {
			String channelJudge = "SELECT  month from ufeng_facebook_tops_class_count_month where month='"+lastMonthStr+"' limit 0,10";
			List<Map<String,Object>> judgeMapList = null;
			judgeMapList = DBHelper.getQueryRunner().query(channelJudge, new MapListHandler());

			if(judgeMapList!=null && judgeMapList.size()>1){
				System.out.println("已生成"+lastMonthStr+"月数据！！！");
			}else{
				//生成channel展示表
				String channelSql = "SELECT  a.*,a.id as top_id,"
					+ " d.MONTHNUM AS month,d.SHARECOUNT as share_count,"
					+ "(d.SHARECOUNT-ifnull(f.SHARECOUNT,0))/ifnull(f.SHARECOUNT,1)*100 as share_count_interval,"
					+ "d.GOODCOUNT as good_count,(d.goodcount-ifnull(f.goodcount,0))/ifnull(f.goodcount,1)*100 as good_count_interval ,"
					+ "d.REVIEWCOUNT as review_count,(d.REVIEWCOUNT-ifnull(f.REVIEWCOUNT,0))/ifnull(f.REVIEWCOUNT,1)*100 as review_count_interval,"
					+ "d.CITEMS as citems_count,(d.CITEMS-ifnull(f.CITEMS,0))/ifnull(f.CITEMS,1)*100 as citems_count_interval,"
					+ "d.DATETIME as update_time  "
					+ "from  ufeng_tops as a  join  ufeng_tops_to_channel as b  on a.id= b.tops_id join "
					+ "ufeng_tops_channel as c on c.id = b.channel_id join wk_facebook_data_channel_date_month as d "
					+ " on c.code=d.channel join  wk_facebook_data_channel_date_month as f on f.MONTHNUM='"+ lastLastMonthStr+ "'"
					+ " and d.channel= f.channel where d.MONTHNUM='"+lastMonthStr + "' order by "
					+ "d.reviewcount desc";
				List<Map<String,Object>> channelMapList = null;
				channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());
				DBUtil.addListMapForClassCount(channelMapList, "ufeng_facebook_tops_class_count_month");
				System.out.println("插入ufeng_facebook_tops_class_count_month表大小为："+channelMapList.size());
			}
			System.out.println("操作完成！！！");
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
	//		new FacebookChannelQueryMonth().operFromTemp("2015-09-15", setNum);
	//	}
}
