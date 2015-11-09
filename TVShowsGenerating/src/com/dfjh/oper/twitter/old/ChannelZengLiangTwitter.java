package com.dfjh.oper.twitter.old;

import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utilsTVShowgenerating.DBHelper;
import com.dfjh.db.utilsTVShowgenerating.DBUtil;

public class ChannelZengLiangTwitter {
	private static final String propertiesName = "poiDB.properties";
//	Calendar cal = Calendar.getInstance();
	public void oper(String taday,String yesterday){
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);
		
		String sql = "SELECT a.*,a.forword_count-IFNULL(b.forword_count,0) as forword_count_interval,a.collect_count-IFNULL(b.collect_count,0) as collect_count_interval,a.concerns_count-IFNULL(b.concerns_count,0) as concerns_count_interval,a.review_count-IFNULL(b.review_count,0) as review_count_interval,a.citems_count-IFNULL(b.citems_count,0) as citems_count_interval " +
				"FROM ufeng_twitter_tops_class_count as a "+
				"LEFT JOIN ufeng_twitter_tops_class_count as b on a.id=b.id AND b.update_time='"+yesterday+
				"' "+
				"WHERE a.update_time='"+taday+"'";
	
		List<Map<String,Object>> mapList = null;
		try {
			mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
			
			System.out.println("插入ufeng_twitter_tops_class_count_interval表的大小"+mapList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtil.addListMapForClassCount(mapList, "ufeng_twitter_tops_class_count_interval");
		
		//计算tv增量
		new TvZengLiangTwitter().oper(taday,yesterday);
	}

//	public static void main(String[] args) {
//		new ChannelZengLiang().oper("2015/08/10","2015/08/09");
//	}
}
