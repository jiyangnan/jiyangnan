//测试工作类
//TestJob.java

package quartzPackage.trvel;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dfjh.HotPublishByClassMain;
import com.dfjh.MainTest;
import com.dfjh.main.TravelToPlatform;
import com.dfjh.main.TravelToTempMain;
import com.dfjh.main.TravelToTotalMain;

public class MyJob implements Job {
	SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d = new Date();
	String returnstr = DateFormat.format(d);  

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(returnstr+"★★★★★★★★★★★");
		TravelToTotalMain tm = new TravelToTotalMain();
		tm.justDoToTal();
		System.out.println(returnstr+"#########");
		TravelToTempMain tt = new TravelToTempMain();
		tt.toTemp();
		System.out.println(returnstr+"@@@@@@@@@@@@");
		TravelToPlatform tp = new TravelToPlatform();
		tp.toPlatform();
		System.out.println(returnstr+"%%%%%%%%%%%");
		MainTest mt = new MainTest();
		mt.doPublish();
		System.out.println(returnstr+"#########");
		HotPublishByClassMain hpbcm = new HotPublishByClassMain();
		try {
			hpbcm.doInnerPublish();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*********旅游榜单已生成");
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		
	}

}

