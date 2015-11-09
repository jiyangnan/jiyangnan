package com.dfjh.oper.ytb.old;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utilsTVShowgenerating.DBHelper;
import com.dfjh.db.utilsTVShowgenerating.DBUtil;

public class TvZengLiang {
//	private static final String propertiesName = "poiDB.properties";
	public void oper(String taday,String yesterday){
		//获取数据库连接配置
//		DBHelper.setFileName(propertiesName);
		
		String sql = "SELECT a.*,a.good_count-IFNULL(b.good_count,0) as good_count_interval,a.bad_count-IFNULL(b.bad_count,0) as bad_count_interval, a.review_count-IFNULL(b.review_count,0) as review_count_interval,a.citems_count-IFNULL(b.citems_count,0) as citems_count_interval ,a.view_count-IFNULL(b.view_count,0) as view_count_interval " +
				"FROM ufeng_ytb_tops_tv_count as a LEFT JOIN ufeng_ytb_tops_tv_count as b on a.id=b.id AND b.update_time='"+yesterday+"' WHERE a.update_time='"+taday+"'";
	
		List<Map<String,Object>> mapList = null;
		try {
			mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
			
			System.out.println(mapList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtil.addListMapForTVCount(mapList, "ufeng_ytb_tops_tv_count_interval");
	}
	
	
	public static void main(String[] args) {
		new TvZengLiang().oper("2015/08/10","2015/08/09");
	}

}
