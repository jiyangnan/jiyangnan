package com.dfjh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utils.travelTotal.DBHelper;
import com.dfjh.db.utils.travelTotal.DBUtil;
/**
 * @author jiyangnan
 * @date 2015-10-09 11:37:23
 */
public class TwitterCityQueryTotal {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
	String host = "120.25.160.211";
	String driver = "com.mysql.jdbc.Driver";
	String port = ":15000";
	String myDB = "bigdatatravel";
	String url ="jdbc:mysql://"+host+port+"/"+myDB+"?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8";
	String user = "root";
	String passwd = "qazwsx123456";
	public void operFromTemp(String time,int setNum){


		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);


			//生成channel表
		String channelSql = "SELECT a.*,a.id as top_id, sum(b.collect_count) as collect_count," +
				" sum(b.forword_count) as forword_count,sum(b.review_count) as review_count," +
				"sum(b.citems_count) as citems_count," +
				"b.update_time  " +
				"FROM ufeng_tops as a LEFT JOIN ufeng_twitter_tops_channel_count_total as b " +
				"ON a.id = b.pid  where a.level='2' group by id";
		
			List<Map<String,Object>> channelMapList = null;
			try {
				Connection conn=null;
				conn = DriverManager.getConnection(url, user, passwd);
				PreparedStatement ps= null;
				String sql = "delete from ufeng_twitter_tops_class_count_total ";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.execute();
				System.out.println("删除完成！");
				channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());
				System.out.println(channelMapList.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.addListMapForTVCount(channelMapList, "ufeng_twitter_tops_class_count_total");
			System.out.println("插入ufeng_twitter_tops_class_count_total表大小为："+channelMapList.size());
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
//		new TwitterTVQueryTotal().operFromTemp("2015-10-15", setNum);
//	}
}
