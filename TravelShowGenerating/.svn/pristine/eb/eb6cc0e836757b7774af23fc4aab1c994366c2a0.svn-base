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
public class GgplusSitelQueryDay {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();

	public void operFromTemp(String time, int setNum, String taday,String yesTaday) {
		Date myDate1 = null;
		Calendar cal = Calendar.getInstance();
		try {
			myDate1 = sdf.parse(time);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 判断当前日期属于本年内第几周
		TimeUtil tu = new TimeUtil();
		intList = tu.getWeekOfYearFR(myDate1);
		String paramYear = String.valueOf(intList.get(0));
		String paramWeek = String.valueOf(intList.get(1));

		cal.setTime(myDate1);
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		System.out.println("当前时间为：" + paramYear + "年" + month + "月" + paramWeek + " 周...  传入time为：" + time);

		// 获取数据库连接配置
		DBHelper.setFileName(propertiesName);



		String channelJudge = "select * from ufeng_ggplus_tops_channel_count_day where update_time= '"+taday+"'";
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
			// 生成channel展示表
			String channelSql = "SELECT  a.*,a.id as top_id,d.WEEKNUM AS week, d.MONTHNUM AS month,"
				+ "d.VIEWCOUNT AS view_count,CAST((d.VIEWCOUNT-ifnull(f.VIEWCOUNT,0))/ifnull(f.VIEWCOUNT,1)*100 AS decimal(18,2))  as view_count_interval,"
				+ "d.GOODCOUNT as good_count,CAST((d.GOODCOUNT-ifnull(f.GOODCOUNT,0))/ifnull(f.GOODCOUNT,1)*100  AS decimal(18,2)) as good_count_interval,"
				+ "d.BADCOUNT AS bad_count ,CAST((d.BADCOUNT-ifnull(f.BADCOUNT,0))/ifnull(f.BADCOUNT,1)*100  AS decimal(18,2)) as bad_count_interval,"
				+ "d.SHARECOUNT AS share_count,CAST((d.SHARECOUNT-ifnull(f.SHARECOUNT,0))/ifnull(f.SHARECOUNT,1)*100  AS decimal(18,2)) as share_count_interval,"
				+ "d.REVIEWCOUNT as review_count,CAST((d.REVIEWCOUNT-ifnull(f.REVIEWCOUNT,0))/ifnull(f.REVIEWCOUNT,1)*100  AS decimal(18,2)) as review_count_interval,"
				+ "d.CITEMS as citems_count,CAST((d.CITEMS-ifnull(f.CITEMS,0))/ifnull(f.CITEMS,1)*100  AS decimal(18,2)) as citems_count_interval,"
				+ "d.DATETIME as update_time, "
				+ "b.`LEVEL` AS na "
				+ "from  ufeng_tops as a join " 
				+ "wk_mapcode AS b  ON a.title = b.channel JOIN "
				+ "wk_ggplus_data_channel_date_day as d "
				+ "ON b.channel = d.channel  join  wk_ggplus_data_channel_date_day as f on f.datetime='"+yesTaday+ "'"
				+ " and d.channel= f.channel where  d.WEEKNUM is not null and d.datetime='"+ taday + "'";
			List<Map<String, Object>> channelMapList = null;
			try {
				channelMapList = DBHelper.getQueryRunner().query(channelSql,new MapListHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.addListMapForClassCount(channelMapList,"ufeng_ggplus_tops_channel_count_day");
			System.out.println("插入ufeng_ggplus_tops_channel_count_day表大小为："+channelMapList.size());
		}
		System.out.println("操作完成！");

	}
}
