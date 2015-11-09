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
public class TwitterSiteQueryDay {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
	public void operFromTemp(String time,int setNum,String taday,String yesTaday){
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

		String channelJudge = "select * from ufeng_twitter_tops_channel_count_day where update_time= '"+taday+"'";
		List<Map<String,Object>> judgeMapList = null;
		try {
			judgeMapList = DBHelper.getQueryRunner().query(channelJudge, new MapListHandler());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(judgeMapList!=null && judgeMapList.size()>1){
			System.out.println("已生成"+taday+"日数据！！！");
		}else{
			//生成channel展示表
			String channelSql = "select a.*, a.id as top_id,d.WEEKNUM AS week, d.MONTHNUM AS month, " +
			" d.COLLECTCOUNT as collect_count,CAST((d.COLLECTCOUNT-ifnull(f.COLLECTCOUNT,0))/ifnull(f.COLLECTCOUNT,1)*100  AS decimal(18,2))  as collect_count_interval,"
			+ "d.FORWARDCOUNT as forword_count,CAST((d.FORWARDCOUNT-ifnull(f.FORWARDCOUNT,0))/ifnull(f.FORWARDCOUNT,1)*100  AS decimal(18,2))  as forword_count_interval,"
			+ "d.REVIEWCOUNT as review_count , CAST((d.REVIEWCOUNT-ifnull(f.REVIEWCOUNT,0))/ifnull(f.REVIEWCOUNT,1)*100 AS decimal(18,2))  as review_count_interval,"
			+ "d.CITEMS as citems_count,CAST((d.citems-ifnull(f.citems,0))/ifnull(f.citems,1)*100  AS decimal(18,2)) as citems_count_interval,"
			+ "d.DATETIME as update_time ," 
			+ "b.`LEVEL` AS na "
			+ "from  ufeng_tops as a  join "
			+ "wk_mapcode AS b  ON a.title = b.channel JOIN "
			+ "wk_twitter_data_channel_date_day as d "
			+ "ON b.channel = d.channel  join  wk_twitter_data_channel_date_day as f on f.datetime='"+yesTaday+"'and "
			+ "d.channel= f.channel where  d.WEEKNUM is not null and d.datetime='"+taday+"'";

			List<Map<String,Object>> channelMapList = null;
			try {
				channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.addListMapForClassCount(channelMapList, "ufeng_twitter_tops_channel_count_day");
			try{
				System.out.println("插入ufeng_twitter_tops_channel_count_day表大小为："+channelMapList.size());

			}catch(Exception e){
				e.printStackTrace();
				try {
					throw new Exception("请检查数据库连接或数据库服务器磁盘空间！");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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
	//		new TwitterChannelQueryDay().operFromTemp("2015-09-15", setNum);
	//	}
}
