package com.dfjh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.ToHot.facebook.HotPublishFaceBook;
import com.dfjh.ToHot.ggplus.HotPublishGgPlus;
import com.dfjh.ToHot.twitter.HotPublishTwitter;
import com.dfjh.ToHot.ytb.HotPublishYtb;
import com.dfjh.db.utils.DBHelper;

public class MainTest {
	public  void doPublish() {
		String propertiesName = "poiDB.properties";
		HotPublishTwitter hpt = new HotPublishTwitter();
		HotPublishFaceBook hpf = new HotPublishFaceBook();
		HotPublishGgPlus hpg = new HotPublishGgPlus();
		HotPublishYtb hpy = new HotPublishYtb();
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);
		String setDate = null;
		String SQLTYB = "select update_time from ufeng_ytb_tops_channel_count_day order by update_time desc limit 0,10";
		List<Map<String,Object>> listYTB = new ArrayList<Map<String,Object>>();
		
		String SQLTwitter = "select update_time from ufeng_twitter_tops_channel_count_day order by update_time desc limit 0,10";
		List<Map<String, Object>> listTwitter= new ArrayList<Map<String,Object>>();
		
		String SQLFacebook = "select update_time from ufeng_facebook_tops_channel_count_day order by update_time desc limit 0,10";
		List<Map<String, Object>> listFacebook= new ArrayList<Map<String,Object>>();
		
		String SQLGgplus = "select update_time from ufeng_ggplus_tops_channel_count_day order by update_time desc limit 0,10";
		List<Map<String, Object>> listGgplus= new ArrayList<Map<String,Object>>();

		try {
			listYTB = DBHelper.getQueryRunner().query(SQLTYB, new MapListHandler());
			setDate = String.valueOf(listYTB.get(0).get("update_time")) ;
			hpy.hotPublish(setDate);
			System.out.println("ufeng_ytb_data  已完成！！！,设定日期为："+setDate);
			System.out.println();
			System.out.println();
			System.out.println();
			
			listTwitter = DBHelper.getQueryRunner().query(SQLTwitter, new MapListHandler());
			setDate = String.valueOf(listTwitter.get(0).get("update_time")) ;
			hpt.hotPublish(setDate);
			System.out.println("ufeng_twitter_data  已完成！！！,设定日期为："+setDate);
			System.out.println();
			System.out.println();
			System.out.println();
			
			listGgplus = DBHelper.getQueryRunner().query(SQLGgplus, new MapListHandler());
			if(listGgplus!=null&&listGgplus.size()>0){
				setDate = String.valueOf(listGgplus.get(0).get("update_time")) ;
				hpg.hotPublish(setDate);
				System.out.println("ufeng_ggplus_data  已完成！！！,设定日期为："+setDate);
			}else{
				System.out.println("channel_count_day表中没有此天数据");
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
			
			listFacebook = DBHelper.getQueryRunner().query(SQLFacebook, new MapListHandler());
			setDate = String.valueOf(listFacebook.get(0).get("update_time")) ;
			hpf.hotPublish(setDate);
			System.out.println("ufeng_facebook_data  已完成！！！,设定日期为："+setDate);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
