package com.dfjh.oper.twitter.old;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utilsTVShowgenerating.DBHelper;
import com.dfjh.db.utilsTVShowgenerating.DBUtil;

public class TvQueryTwitter {
//	private static final String propertiesName = "poiDB.properties";
	public void oper(String time){
		//获取数据库连接配置
//		DBHelper.setFileName(propertiesName);
		
		String sql = "SELECT a.*,a.id as top_id,SUM(b.concerns_count) as concerns_count,SUM(b.collect_count) as collect_count ,SUM(b.forword_count) as forword_count,SUM(b.review_count) as review_count,SUM(b.citems_count) as citems_count,b.update_time FROM ufeng_tops as a LEFT JOIN ufeng_twitter_tops_class_count as b ON a.id = b.pid  WHERE a.level='1' and b.update_time ='"+time+"' GROUP BY id";
	
		List<Map<String,Object>> mapList = null;
		try {
			mapList = DBHelper.getQueryRunner().query(sql, new MapListHandler());
			
			System.out.println("插入ufeng_twitter_tops_tv_count表的大小："+mapList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtil.addListMapForTVCount(mapList, "ufeng_twitter_tops_tv_count");
		
		//查询原始数据表中SUBSCRIBE字段值
//		String upSql = "select a.AUTHOR,a.SUBSCRIBE from wk_twitter_data as a JOIN ufeng_twitter_tops_tv_count as b on a.AUTHOR = b.remarks WHERE a.DATETIME = '"+time+"' GROUP BY AUTHOR ORDER BY CAST(a.SUBSCRIBE as SIGNED) DESC";
//		List<Map<String,Object>> upMapList = null;
//		try {
//			upMapList = DBHelper.getQueryRunner().query(upSql, new MapListHandler());
//			
//			System.out.println(upMapList.size());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		if(CollectionUtils.isNotEmpty(upMapList)){
//			for(Map<String,Object> map : upMapList){
//				String remarks = (String) map.get("AUTHOR");
//				long subscribe = 0;
//				if(null ==map.get("SUBSCRIBE")){
//					 subscribe = 0;
//				}else{
////					subscribe = Long.valueOf((String)(map.get("SUBSCRIBE")));
////					String update_time = (String) map.get("DATETIME");
////					System.out.println("subscribe: "+subscribe);
//					
//				}
//				if(null!=(String)(map.get("SUBSCRIBE"))){
//					String updateSql = "UPDATE ufeng_twitter_tops_tv_count SET subscribe = '"+subscribe+"' where remarks = '"+remarks+"' and update_time = '"+time+"'";
//					try {
//						DBHelper.getQueryRunner().update(updateSql);
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
	}
	
	public static void main(String[] args) {
		new TvQueryTwitter().oper("2015/08/10");
	}

}
